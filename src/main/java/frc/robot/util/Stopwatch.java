package frc.robot.util;

public class Stopwatch{


    private long lastTime;

    // Measures elapsed time
    public Stopwatch(){
        reset();
    }


    // reset the stopwatch to measur from the current time
    public void reset(){
        lastTime = System.currentTimeMillis();
    }
    // returns the elapsed time in milliseconds
    public long getMilliseconds(){
        return System.currentTimeMillis() - lastTime;

    }
    // Return the elapsed time in seconds
    public double getSeconds(){
        return getMilliseconds() / 1000.0;
    }
}