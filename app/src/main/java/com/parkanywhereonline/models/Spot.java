package com.parkanywhereonline.models;

import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Spot {
    // Name of the spot
    private String name;
    // Indicates whether the spot is currently available (or just in the database)
    private boolean enabled;
    // address lmao
    private String address;
    // The price of the spot
    private String price;
    // The location of the spot
    private GeoPoint location;

    public Spot(String name, String address, boolean enabled, GeoPoint location, String price) {
        this.name = name;
        this.address = address;
        this.enabled = enabled;
        this.location = location;
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }

    public GeoPoint getLocation() {
        return location;
    }
}
