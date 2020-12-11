package com.gokulnathp;

public class Taxi {
    private final String name;
    private int earned;
    private String location;
    private String dropTime;

    public Taxi(String name) {
        this.name = name;
        this.earned = 0;
        this.location = "A";
        this.dropTime = null;
    }

    public String getName() {
        return name;
    }

    public int getEarned() {
        return earned;
    }

    public String getLocation() {
        return location;
    }

    public void startJourney(String destination, String dropTime, int amount) {
        this.location = destination;
        this.dropTime = dropTime;
        this.earned += amount;
    }

    public boolean isTraveling(String pickupTime) {
        if (dropTime == null) {
            return false;
        }

        Time pickup = new Time(pickupTime);
        Time drop = new Time(dropTime);

        if (pickup.getAmOrPm() != drop.getAmOrPm()) {
            return pickup.getAmOrPm() == Meridiem.AM;
        } else if (pickup.getHour() == drop.getHour()) {
            return pickup.getMinutes() < drop.getMinutes();
        } else {
            return pickup.getHour() < drop.getHour();
        }
    }
}
