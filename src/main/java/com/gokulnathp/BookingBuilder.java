package com.gokulnathp;

public class BookingBuilder {
    private String bookingId;
    private String customerId;
    private String from;
    private String to;
    private String pickupTime;
    private String dropTime;
    private int amount;
    private String taxiName;

    public BookingBuilder setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public BookingBuilder setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public BookingBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public BookingBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public BookingBuilder setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
        return this;
    }

    public BookingBuilder setDropTime(String dropTime) {
        this.dropTime = dropTime;
        return this;
    }

    public BookingBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public BookingBuilder setTaxiName(String taxiName) {
        this.taxiName = taxiName;
        return this;
    }

    public Booking getBooking() {
        return new Booking(
                bookingId,
                customerId,
                from,
                to,
                pickupTime,
                dropTime,
                amount,
                taxiName
        );
    }
}
