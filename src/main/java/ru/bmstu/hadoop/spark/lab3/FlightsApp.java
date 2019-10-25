package ru.bmstu.hadoop.spark.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import ru.bmstu.hadoop.spark.lab3.FlightParser;
import scala.Tuple2;

import java.util.Map;


public class FlightsApp {
    public static void main(String[] args) {
        final FlightParser flP = new FlightParser();
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        final Broadcast<Map<Long, String>> airportsBroadcasted = sc.broadcast(
                sc.textFile("L_AIRPORT_ID")
                        .map(l -> FlightParser.getAirportParser(l))
                        .mapToPair(p -> new Tuple2<>(p.getAirportId(), p.getAiroportName()))
                        .collectAsMap()
        );


        JavaPairRDD<Tuple2<Long, Long>, FlightSerializable> flightPair = sc.textFile("664600583_T_ONTIME_sample.csv")
                .mapToPair(s ->
                        new Tuple2<>(
                                new Tuple2<>(flP.getOrigionAirportID(s), flP.getDestAirportID(s)),
                                new FlightSerializable(flP.getDelayTime(s), flP.getCancelled(s))
                        )
                );

        JavaPairRDD<Tuple2<Long, Long>, Iterable<FlightSerializable>> flightGroupPair = flightPair.groupByKey();

        flightGroupPair.map(
                elem -> {
//                    FlightSerializable fs = elem._2().iterator().next()
                    return new Tuple2<>(
                            new Tuple2<>(
                                    airportsBroadcasted.getValue().get(elem._1()._1()),
                                    airportsBroadcasted.getValue().get(elem._1()._2())
                            ),
                            "result"
                    );
                }
        ).saveAsTextFile("output");
    }
}
