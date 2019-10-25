package ru.bmstu.hadoop.spark.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Iterator;
import java.util.Map;

import static org.spark_project.guava.primitives.Longs.max;


public class FlightsApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        final Broadcast<Map<Long, String>> airportsBroadcasted = sc.broadcast(
                sc.textFile("L_AIRPORT_ID")
                        .zipWithIndex()
                        .filter(elem -> elem._2() != 0)
                        .map(l -> CsvParser.getAirportParser(l._1()))
                        .mapToPair(p -> new Tuple2<>(p.getAirportId(), p.getAiroportName()))
                        .collectAsMap()
        );


        JavaPairRDD<Tuple2<Long, Long>, FlightSerializable> flightPair = sc.textFile("664600583_T_ONTIME_sample.csv")
                .zipWithIndex()
                .filter(elem -> elem._2() != 0)
                .map(l -> CsvParser.getFlightParser(l._1()))
                .mapToPair(p ->
                        new Tuple2<>(
                                new Tuple2<>(p.getOrigionAirportID(), p.getDestAirportID()),
                                new FlightSerializable(p.getDelayTime(), p.getCancelled())
                        )
                );

        JavaPairRDD<Tuple2<Long, Long>, Iterable<FlightSerializable>> flightGroupPair = flightPair.groupByKey();

        flightGroupPair.map(
                elem -> {
                    Iterator<FlightSerializable> fs = elem._2().iterator();
                    Long cancelled = 0L, delTime, count = 0L, countDel = 0L;
                    Long maxDelTime = Long.MIN_VALUE;
                    while (fs.hasNext()) {
                        FlightSerializable flNext = fs.next();
                        delTime  = flNext.getDelayTime();
                        if (delTime > 0) {
                            countDel++;
                        }
                        maxDelTime = max(maxDelTime, delTime);
                        cancelled += flNext.getCancelled();
                        count ++;

                    }
                    return new Tuple2<>(
                            new Tuple2<>(
                                    airportsBroadcasted.getValue().get(elem._1()._1()),
                                    airportsBroadcasted.getValue().get(elem._1()._2())
                            ),
                            maxDelTime + " " + countDel*100/count + " " + cancelled*100/count
                    );
                }
        ).saveAsTextFile("output");
    }
}
