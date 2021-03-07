package com.parkanywhereonline.models;

public class Spot {
    // Name of the spot
    private String name;
    // Indicates whether the spot is currently available (or just in the database)
    private boolean enabled;
    // The price of the spot
    private double price;
    // The location of the spot
    private Location location;

    public Spot(String name, boolean enabled, Location location) {
        this.name = name;
        this.enabled = enabled;
        this.location = location;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Location getLocation() {
        return location;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
