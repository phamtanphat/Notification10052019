package khoapham.ptp.phamtanphat.notification10052019;

public class Schedule {
    private long time;
    private boolean picked;

    public Schedule(long time, boolean picked) {
        this.time = time;
        this.picked = picked;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }
}
