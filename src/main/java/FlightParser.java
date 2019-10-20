public class FlightParser {

    public int getAirportId(String str) {
        String[] strings = str.split(",[\"]");
        return strings[0];
    }

    public String getAiroportName(String str) {
        String[] strings = str.split(",[\"]");
        return strings[1];
    }

    public int 


}
