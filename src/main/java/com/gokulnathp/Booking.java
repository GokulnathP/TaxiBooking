package com.gokulnathp;

import java.util.Objects;

public class Booking {
    public final String bookingId;
    public final String customerId;
    public final String from;
    public final String to;
    public final String pickupTime;
    public final String dropTime;
    public final int amount;
    public final String taxiName;

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
