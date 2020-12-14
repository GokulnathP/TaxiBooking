package com.gokulnathp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AdminTest {
    Admin admin;

    @Before
    public void loadTaxi() {
        admin = new Admin();
        admin.addTaxi(Taxi.create(new String[]{"Taxi 1", "Taxi 2", "Taxi 3", "Taxi 4", "Taxi 5"}));
    }

    @Test
    public void bookTaxi() {
        Booking booking = admin.bookTaxi("A", "D", "9.00 AM");
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
        assertThat(booking, is(expected));
    }

    @Test
    public void historyOf() {
        List<Booking> expected = new ArrayList<>();
        expected.add(admin.bookTaxi("A", "D", "9.00 AM"));
        expected.add(admin.bookTaxi("D", "E", "10.00 AM"));
        admin.bookTaxi("B", "A", "10.00 AM");
        List<Booking> historyOfTaxi = admin.historyOf("Taxi 1");
        assertThat(historyOfTaxi, is(expected));
    }
}
