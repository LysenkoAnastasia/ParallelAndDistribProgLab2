package ru.bmstu.hadoop.spark.lab3;

public class CsvParser {
    final String[] strings;

    private CsvParser(String line, String delimiter) {
        this.strings = line.split(delimiter);
    }

    static CsvParser getFlightParser(String line) {
        return new CsvParser(line, ",");
    }

    static CsvParser getAirportParser(String line) {
        return new CsvParser(line, ",[\"]");
    }

    public long getAirportId() {
        return Long.getLong(strings[0]);
    }

    public String getAiroportName() {
        return strings[1];
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
