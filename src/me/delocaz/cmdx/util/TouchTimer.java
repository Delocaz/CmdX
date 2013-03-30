package me.delocaz.cmdx.util;

public class TouchTimer {
    long lasttouched = System.currentTimeMillis();
    public long touch() {
        if (lasttouched == 0L) {
            return 0L;
        }
        long diff = System.currentTimeMillis()-lasttouched;
        lasttouched = System.currentTimeMillis();
        return diff;
    }
    public long get() {
        return System.currentTimeMillis()-lasttouched;
    }
}
