package models;

public class TimeStamp {

    private Long stamp;

    public TimeStamp() {
        stamp = System.currentTimeMillis();
    }

    public double delta() {
        Long lastStamp = stamp;
        Long nowStamp = System.currentTimeMillis();
        stamp = nowStamp;
        return (double)(nowStamp - lastStamp) / (double)1000;
    }
}
