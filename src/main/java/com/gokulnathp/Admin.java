package com.gokulnathp;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private final List<Taxi> taxis;
    private final List<Booking> history;

    public Admin() {
        taxis = new ArrayList<>();
        history = new ArrayList<>();
    }

    public void addTaxi(String taxiName) {
        Taxi taxi = new Taxi(taxiName);
        taxis.add(taxi);
    }

    private int calculateDistance(String from, String to) {
        int fromInt = from.toUpperCase().charAt(0);
        int toInt = to.toUpperCase().charAt(0);
        return Math.abs(fromInt - toInt) * 15;
    }

    private int calculateAmount(int distance) {
        return distance * 10;
    }

    private String calculateDropTime(String pickupTime, int distance) {
        String dropTime;
        String[] dotSplit = pickupTime.split("\\.");
        String[] spaceSplit = dotSplit[1].split(" ");

        int hour = Integer.parseInt(dotSplit[0]);
        int minutes = Integer.parseInt(spaceSplit[0]);
        String amOrPm = spaceSplit[1];

        int duration = distance * 1; // Time taken for the travel in minutes
        minutes += duration;

        if (minutes > 59) {
            hour += minutes / 60;
            minutes %= 60;
        }

        if (hour >= 12) {
            amOrPm = amOrPm.equals("AM") ? "PM" : "AM";
            hour = hour > 12 ? hour % 12 : hour;
        }

        dropTime = hour + "." + (minutes < 10 ? "0" + minutes : minutes) + " " + amOrPm;
        return dropTime;
    }

    private Taxi findTaxi(String from, String pickupTime) {
        Taxi availableTaxi;
        List<Taxi> minTaxi = new ArrayList<>();
        int minimumDistance = -1;

        for(Taxi taxi : taxis) {
            if (!taxi.isTraveling(pickupTime)) {
                int distance = calculateDistance(from, taxi.location);
                if (minimumDistance == -1) {
                    minTaxi.add(taxi);
                    minimumDistance = distance;
                } else if (minimumDistance > distance) {
                    minTaxi = new ArrayList<>();
                    minimumDistance = distance;
                    minTaxi.add(taxi);
                } else if (minimumDistance == distance) {
                    minTaxi.add(taxi);
                }
            }
        }

        availableTaxi = minTaxi.get(0);
        if (minTaxi.size() > 1) {
            int earned = availableTaxi.earned;
            for (int i = 1; i < minTaxi.size(); i++) {
                Taxi taxi = minTaxi.get(i);
                if (earned > taxi.earned) {
                    availableTaxi = taxi;
                    earned = taxi.earned;
                }
            }
        }
        return availableTaxi;
    }

    public Booking bookTaxi(String from, String to, String pickupTime) {
        String bookingId = (history.size() + 1) + "";
        String customerId = (history.size() + 1) + "";

        int distance = calculateDistance(from, to);
        String dropTime = calculateDropTime(pickupTime, distance);
        int amount = calculateAmount(distance);

        Taxi taxi = findTaxi(from, pickupTime);
        taxi.startJourney(to, dropTime, amount);
        String taxiName = taxi.name;

        Booking booking = new Booking(
                bookingId,
                customerId,
                from,
                to,
                pickupTime,
                dropTime,
                amount,
                taxiName
        );
        history.add(booking);

        return booking;
    }

    public List<Booking> historyOf(String taxiName) {
        List<Booking> historyOfTaxi = new ArrayList<>();
        history.forEach(booking -> {
            if (booking.taxiName.equals(taxiName)) {
                historyOfTaxi.add(booking);
            }
        });
        return historyOfTaxi;
    }
}
