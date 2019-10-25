package ru.bmstu.hadoop.spark.lab3;

public class FlightParser {
    final String[] strings;

    private FlightParser(String line, String delimiter) {
        this.strings = line.split(delimiter);
    }

    static FlightParser FlightParser(String line) {
        return new FlightParser(line, ",");
    }

    public long getOrigionAirportID() {
        return Long.getLong(strings[11]);
    }

    public long getDestAirportID() {
        return Long.getLong(strings[14]);
    }

    public long getDelayTime() {
        return Long.parseLong(strings[18]);
    }

    public long getCancelled() {
        return Long.parseLong(strings[19]);
    }

}
