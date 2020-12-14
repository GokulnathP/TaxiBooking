package com.gokulnathp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Taxi {
    private final String name;
    private int earned;
    private String location;
    private String dropTime;

    public Taxi(String name, int earned, String location, String dropTime) {
        this.name = name;
        this.earned = earned;
        this.location = location;
        this.dropTime = dropTime;
    }

    public static Taxi create(String name) {
        return new Taxi(name, 0, "A", null);
    }

    public static List<Taxi> create(String[] names) {
        return Arrays.stream(names).map(Taxi::create).collect(Collectors.toList());
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
