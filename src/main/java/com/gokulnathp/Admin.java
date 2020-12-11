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
        Time pickup = new Time(pickupTime);

        int hour = pickup.getHour();
        int minutes = pickup.getMinutes();
        Meridiem amOrPm = pickup.getAmOrPm();

        int duration = distance * 1; // Time taken for the travel in minutes
        minutes += duration;

        if (minutes > 59) {
            hour += minutes / 60;
            minutes %= 60;
        }

        if (hour >= 12) {
            amOrPm = amOrPm == Meridiem.AM ? Meridiem.PM : Meridiem.AM;
            hour = hour > 12 ? hour % 12 : hour;
        }

        dropTime = new Time(hour, minutes, amOrPm).getTime();
        return dropTime;
    }

    private Taxi findTaxi(String from, String pickupTime) {
        Taxi availableTaxi;
        List<Taxi> minTaxi = new ArrayList<>();
        int minimumDistance = Integer.MAX_VALUE;

        for(Taxi taxi : taxis) {
            if (!taxi.isTraveling(pickupTime)) {
                int distance = calculateDistance(from, taxi.getLocation());
                if (minimumDistance > distance) {
                    minTaxi = new ArrayList<>();
                    minimumDistance = distance;
                } else if (minimumDistance != distance) {
                    continue;
                }
                minTaxi.add(taxi);
            }
        }

        availableTaxi = minTaxi.get(0);
        if (minTaxi.size() > 1) {
            int earned = availableTaxi.getEarned();
            for (int i = 1; i < minTaxi.size(); i++) {
                Taxi taxi = minTaxi.get(i);
                if (earned > taxi.getEarned()) {
                    availableTaxi = taxi;
                    earned = taxi.getEarned();
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
        String taxiName = taxi.getName();

        Booking booking = new BookingBuilder()
                .setBookingId(bookingId)
                .setCustomerId(customerId)
                .setFrom(from)
                .setDropTime(dropTime)
                .setAmount(amount)
                .setPickupTime(pickupTime)
                .setTaxiName(taxiName)
                .getBooking();
        history.add(booking);

        return booking;
    }

    public List<Booking> historyOf(String taxiName) {
        List<Booking> historyOfTaxi = new ArrayList<>();
        history.forEach(booking -> {
            if (booking.getTaxiName().equals(taxiName)) {
                historyOfTaxi.add(booking);
            }
        });
        return historyOfTaxi;
    }
}
