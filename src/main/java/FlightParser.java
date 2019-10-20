public class FlightParser {

    public int getAirportId(String str) {
        String[] strings = str.split(",[\"]");
        return Integer.getInteger(strings[0]);
    }

    public String getAiroportName(String str) {
        String[] strings = str.split(",[\"]");
        return strings[1];
    }

    public int getOrigionAirportID(String str) {
        String[] strings = str.split(",");
        return Integer.getInteger(strings[11]);
    }

    public int getDestAirportID(String str) {
        String[] strings = str.split(",");
        return Integer.getInteger(strings[14]);
    }

    public float getDelayTime(String str) {
        String[] strings = str.split(",");
        return Integer.getInteger(strings[18]);
    }

    public in


}
