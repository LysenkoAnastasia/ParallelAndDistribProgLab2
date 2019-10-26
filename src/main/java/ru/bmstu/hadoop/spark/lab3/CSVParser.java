package ru.bmstu.hadoop.spark.lab3;

public class CSVParser {
    private static String QUOTATION = "\"";
    private static String EMPTY_STR = "";

    CSVParser() {

    }

    public String removeQuotation(String str) {
        return str.replace(QUOTATION, EMPTY_STR);
    }
}
