package com.example.eric.tutorversity.models;

import org.json.JSONObject;

public class Location {

    private double latitude;
    private double longitude;

    public JSONObject toJSON() {
        return new JSONObject();
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
