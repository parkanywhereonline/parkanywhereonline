package com.parkanywhereonline.models;

public class Spot {
    // Indicates whether the spot is currently available (or just in the database)
    private boolean enabled;
    // The price of the spot
    private double price;
    // The location of the spot
    private Location location;

    public Spot(double price, double lat, double lng) {

    }
}
