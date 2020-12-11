package com.gokulnathp;

public class Time {
    private final int hour;
    private final int minutes;
    private final Meridiem amOrPm;

    public Time(String time) {
        String[] dotSplit = time.split("\\.");
        String[] spaceSplit = dotSplit[1].split(" ");

        this.hour = Integer.parseInt(dotSplit[0]);
        this.minutes = Integer.parseInt(spaceSplit[0]);
        this.amOrPm = spaceSplit[1].equals("AM") ? Meridiem.AM : Meridiem.PM;
    }

    public Time(int hour, int minutes, Meridiem amOrPm) {
        this.hour = hour;
        this.minutes = minutes;
        this.amOrPm = amOrPm;
    }

    public String getTime() {
        return hour + "." + (minutes < 10 ? "0" + minutes : minutes) + " " + amOrPm.name();
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public Meridiem getAmOrPm() {
        return amOrPm;
    }
}
