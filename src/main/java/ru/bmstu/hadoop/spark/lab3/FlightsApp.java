package ru.bmstu.hadoop.spark.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import ru.bmstu.hadoop.spark.lab3.FlightParser;
import scala.Float;
import scala.Tuple2;

import java.util.Map;


public class FlightsApp {
    public static void main(String[] args) {
        FlightParser flP = new FlightParser();
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airFile = sc.textFile("L_AIRPORT_ID");
        JavaPairRDD<Long,String> airPair = sc.;

        final Broadcast<Map<Long, String>> airportsBroadcasted = sc.broadcast(airFile
                .mapToPair(s -> new Tuple2<>(flP.getAirportId(s), flP.getAiroportName(s)))
                .collectAsMap());

        JavaRDD<String> flightsFile = sc.textFile("664600583_T_ONTIME_sample.csv");


        JavaPairRDD<Tuple2<Integer, Integer>, Tuple2<Float, Float>> flightPair = flightsFile.mapToPair(s -> new Tuple2<>(new Tuple2<>(flP.getOrigionAirportID(s), flP.getDestAirportID(s)),
                new Tuple2<>(flP.getDelayTime(s), flP.getCancelled(s)))
        );

    }

}
