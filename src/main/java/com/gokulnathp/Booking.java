package com.gokulnathp;

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
}
