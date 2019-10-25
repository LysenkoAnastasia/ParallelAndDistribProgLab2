package ru.bmstu.hadoop.spark.lab3;

public class AirportParser {
    final String[] strings;

    private AirportParser(String line, String delimiter) {
        this.strings = line.split(delimiter);
    }

    static AirportParser AirportParser(String line) {
        return new AirportParser(line, ",[\"]");
    }

    public long getAirportId() {
        return Long.getLong(strings[0]);
    }

    public String getAiroportName() {
        return strings[1];
    }

}
