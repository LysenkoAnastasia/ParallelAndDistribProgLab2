import scala.Serializable;

public class FlightSerializable implements Serializable {
    private int origionAirportID;
    private int destAirportID;
    private float delayTime;
    private float cancelled;

    public FlightSerializable(int origionAirportID, int destAirportID, float delayTime, float cancelled) {
        this.origionAirportID = origionAirportID;
        this.destAirportID = destAirportID;
        this.delayTime = delayTime;
        this.cancelled = cancelled;
    }

    public int getDestAirportID () {
        return destAirportID;
    }

    public int getOrigionAirportID() {
        return origionAirportID;
    }

    public float getDelayTime() {
        return delayTime;
    }

    public float getCancelled() {
        return cancelled;
    }

    @Override
    public String toString() {
        return "FlightSerializable{" +
                "origionAirportID = '" + origionAirportID + '\'' +
                ", destAirportID = " + destAirportID +
                ", delayTime = '" + delayTime + '\'' +
                ", cancelled = " + cancelled +
                '}';
    }
}
