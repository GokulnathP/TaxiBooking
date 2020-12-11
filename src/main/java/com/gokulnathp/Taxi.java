package com.gokulnathp;

public class Taxi {
    public final String name;
    public int earned;
    public String location;
    public String dropTime;

    public Taxi(String name) {
        this.name = name;
        this.earned = 0;
        this.location = "A";
        this.dropTime = null;
    }

    public void startJourney(String destination, String dropTime, int amount) {
        this.location = destination;
        this.dropTime = dropTime;
        this.earned += amount;
    }

    private static class Time {
        private final int hour;
        private final int minutes;
        private final String amOrPm;

        private Time(String time) {
            String[] dotSplit = time.split("\\.");
            String[] spaceSplit = dotSplit[1].split(" ");

            this.hour = Integer.parseInt(dotSplit[0]);
            this.minutes = Integer.parseInt(spaceSplit[0]);
            this.amOrPm = spaceSplit[1];
        }
    }

    public boolean isTraveling(String pickupTime) {
        if (dropTime == null) {
            return false;
        }

        Time pickup = new Time(pickupTime);
        Time drop = new Time(dropTime);

        if (pickup.amOrPm.equals(drop.amOrPm)) {
            if (pickup.hour > drop.hour) {
                return false;
            } else if (pickup.hour < drop.hour) {
                return true;
            } else {
                return pickup.minutes < drop.minutes;
            }
        } else {
            return pickup.amOrPm.equals("AM");
        }
    }
}
