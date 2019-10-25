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
        return Long.getLong(strings[11].replace("\"", ""));
    }

    public long getDestAirportID() {
        return Long.getLong(strings[14].replace("\"", ""));
    }

    public long getDelayTime() {
        if (!strings[18].isEmpty()) {
            return Long.parseLong(strings[18].replace("\"", ""));
        }
        return 0;
    }

    public long getCancelled() {
        if (!strings[19].isEmpty()) {
            return Long.parseLong(strings[19].replace("\"", ""));
        }
        return 0;
    }
}
