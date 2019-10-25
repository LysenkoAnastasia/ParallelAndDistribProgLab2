package ru.bmstu.hadoop.spark.lab3;

import scala.Serializable;

public class FlightSerializable implements Serializable {
    private long delayTime;
    private long cancelled;

    public FlightSerializable() {
    }

    public FlightSerializable(long delayTime, long cancelled) {
        this.delayTime = delayTime;
        this.cancelled = cancelled;
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
                ", delayTime = '" + delayTime + '\'' +
                ", cancelled = " + cancelled +
                '}';
    }
}
