package com.gokulnathp;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.newTaxi("Taxi1");
        admin.newTaxi("Taxi2");
        admin.newTaxi("Taxi3");
        admin.newTaxi("Taxi4");
        admin.newTaxi("Taxi5");
	    admin.bookTaxi("A", "D", "9.00 AM");
	    admin.bookTaxi("D", "E", "10.00 AM");
	    admin.bookTaxi("B", "A", "10.00 AM");
	    admin.historyOf("Taxi1");
    }
}
