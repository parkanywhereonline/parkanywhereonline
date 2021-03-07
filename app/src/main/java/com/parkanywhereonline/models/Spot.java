package com.parkanywhereonline.models;

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
    private Location location;

    public Spot(String name, String address, boolean enabled, Location location, String price) {
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

    public String getPrice() {
        return price;
    }

    public Location getLocation() {
        return location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // Return a map so we can use it in firebase
    public Map<String, Object> getSpotAsHashMap() {
        Map<String, Object> spotHashMap = new HashMap<>();
        spotHashMap.put("name", this.name);
        spotHashMap.put("address", this.address);
        spotHashMap.put("enabled", this.enabled);
        spotHashMap.put("price", this.price);

        return spotHashMap;
    }
}
