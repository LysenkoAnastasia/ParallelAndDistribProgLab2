package ru.bmstu.hadoop.spark.lab3;

public class AirportParser {
    final String[] strings;
    static private int AIRPORT_ID = 0;
    static private int AIRPORT_NAME = 1;
    private CSVParser csvParser = new CSVParser();
    static private String COMMA_QUOTATION = ",[\"]";


    private AirportParser(String line, String delimiter) {
        this.strings = line.split(delimiter);
    }

    static AirportParser AirportParser(String line) {
        return new AirportParser(line, COMMA_QUOTATION);
    }

    public long getAirportId() {
        String str = (csvParser.removeQuotation(strings[AIRPORT_ID]));
        return Long.parseLong(str);
    }

    public String getAiroportName() {
        return (csvParser.removeQuotation(strings[AIRPORT_NAME]));
    }

}
