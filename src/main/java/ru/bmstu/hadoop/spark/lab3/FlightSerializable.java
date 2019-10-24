package ru.bmstu.hadoop.spark.lab3;

import scala.Serializable;

public class FlightSerializable implements Serializable {
    private long origionAirportID;
    private long destAirportID;
    private long delayTime;
    private long cancelled;

    public FlightSerializable() {
    }

    public FlightSerializable(long origionAirportID, long destAirportID, long delayTime, long cancelled) {
        this.origionAirportID = origionAirportID;
        this.destAirportID = destAirportID;
        this.delayTime = delayTime;
        this.cancelled = cancelled;
    }



    public long getDestAirportID () {
        return destAirportID;
    }

    public long getOrigionAirportID() {
        return origionAirportID;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public long getCancelled() {
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
