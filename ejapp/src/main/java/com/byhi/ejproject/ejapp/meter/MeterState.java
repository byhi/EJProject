package com.byhi.ejproject.ejapp.meter;

/**
 *Contain the actual status of metered method
 */
public class MeterState {
    Long counter;
    Long calls;
    Double avg;


    public MeterState() {
        this.counter = new Long(0);
        this.calls = new Long(0);
        this.avg = new Double(0);
    }

    public Long getCounter() {
        return this.counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Double getAvg() {
        return this.avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Long getCalls() {
        return this.calls;
    }

    public void setCalls(Long calls) {
        this.calls = calls;
    }

    @Override
    public String toString() {
        return "MeterState{" +
                "counter=" + this.counter +
                ", calls=" + this.calls +
                ", avg=" + this.avg +
                '}';
    }


    /**
     * Recalculate the average
     */
    public void reCalculateAvg() {
        this.avg = this.counter.doubleValue() / this.calls.doubleValue();
    }

    /**
     * Adds the value of the input parameter to the counter
     * @param lastTaskTimeMillis - result of last measurement
     */
    public void addCounter(long lastTaskTimeMillis) {
        this.counter = this.counter +  lastTaskTimeMillis;
    }
}
