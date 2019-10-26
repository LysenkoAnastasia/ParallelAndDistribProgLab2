package ru.bmstu.hadoop.spark.lab3;

public class FlightParser {
    final String[] strings;
    private static int ORIGION_AIR_ID = 11;
    private static int DEST_AIR_ID = 14;
    private static int DELAY_TIME = 18;
    private static int CANCELLED = 19;
    static private String COMMA = ",";
    private CSVParser csvParser = new CSVParser();


    private FlightParser(String line, String delimiter) {
        this.strings = line.split(delimiter);
    }

    static FlightParser FlightParser(String line) {
        return new FlightParser(line, COMMA);
    }

    public long getOrigionAirportID() {
        return Long.parseLong(csvParser.removeQuotation(strings[ORIGION_AIR_ID]));
    }

    public long getDestAirportID() {
        return Long.parseLong(csvParser.removeQuotation(strings[DEST_AIR_ID]));
    }

    public long getDelayTime() {
        if (!strings[DELAY_TIME].equals("")) {
            return (long)Double.parseDouble(csvParser.removeQuotation(strings[DELAY_TIME]));
        }
        return 0;
    }

    public long getCancelled() {
        if (!strings[CANCELLED].isEmpty()) {
            return  (long)Double.parseDouble(csvParser.removeQuotation(strings[CANCELLED]));
        }
        return 0;
    }
}
