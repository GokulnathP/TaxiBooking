package com.gokulnathp;

import com.gokulnathp.model.Booking;
import com.gokulnathp.model.Taxi;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookingManagerTest {
    BookingManager bookingManager;

    @Before
    public void setUp() {
        bookingManager = new BookingManager();
        bookingManager.addTaxi(Taxi.create(new String[]{"Taxi 1", "Taxi 2", "Taxi 3", "Taxi 4", "Taxi 5"}));
    }

    @Test
    public void shouldBookTaxi1AndReturnBookingObject() {
        Booking booking = bookingManager.bookTaxi("A", "D", "9.00 AM");
        Booking expected = new Booking(
                "1",
                "1",
                "A",
                "D",
                "9.00 AM",
                "9.45 AM",
                450,
                "Taxi 1"
        );

        assertTrue(booking.checkTaxiName("Taxi 1"));
        assertEquals(booking, expected);
    }

    @Test
    public void shouldReturnHistoryOfTaxi1() {
        List<Booking> expected = new ArrayList<>();
        expected.add(bookingManager.bookTaxi("A", "D", "9.00 AM"));
        expected.add(bookingManager.bookTaxi("D", "E", "10.00 AM"));
        bookingManager.bookTaxi("B", "A", "10.00 AM");

        List<Booking> historyOfTaxi = bookingManager.historyOf("Taxi 1");

        assertEquals(historyOfTaxi, expected);
    }
}
