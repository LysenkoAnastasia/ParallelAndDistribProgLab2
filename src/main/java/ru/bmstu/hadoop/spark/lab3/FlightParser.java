package ru.bmstu.hadoop.spark.lab3;

public class FlightParser {
    final String[] strings;

    FlightParser(String line, String delimiter) {
        this.strings = line.split(delimiter);
    }

    FlightParser getFlightParser(String line) {
        
    }

    public long getAirportId(String str) {
        String[] strings = str.split(",[\"]");
        return Long.getLong(strings[0]);
    }

    public String getAiroportName(String str) {
        String[] strings = str.split(",[\"]");
        return strings[1];
    }

    public long getOrigionAirportID(String str) {
        String[] strings = str.split(",");
        return Long.getLong(strings[11]);
    }

    public long getDestAirportID(String str) {
        String[] strings = str.split(",");
        return Long.getLong(strings[14]);
    }

    public long getDelayTime(String str) {
        String[] strings = str.split(",");
        return Long.parseLong(strings[18]);
    }

    public long getCancelled(String str) {
        String[] strings = str.split(",");
        return Long.parseLong(strings[19]);
    }

}
