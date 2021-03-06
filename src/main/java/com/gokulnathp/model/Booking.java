package com.gokulnathp.model;

import java.util.Objects;

public class Booking {
    private final String bookingId;
    private final String customerId;
    private final String from;
    private final String to;
    private final String pickupTime;
    private final String dropTime;
    private final int amount;
    private final String taxiName;

    public Booking(
            String bookingId,
            String customerId,
            String from,
            String to,
            String pickupTime,
            String dropTime,
            int amount,
            String taxiName
    ) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.from = from;
        this.to = to;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
        this.taxiName = taxiName;
    }

    public boolean checkTaxiName(String otherTaxiName) {
        return otherTaxiName.equals(taxiName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return customerId.equals(booking.customerId) &&
                bookingId.equals(booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, taxiName, customerId);
    }
}
