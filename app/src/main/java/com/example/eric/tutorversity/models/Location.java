package com.example.eric.tutorversity.models;

import org.json.JSONObject;

public class Location {

    private double latitude;
    private double longitude;

    public String getLocName() {
        return locName;
    }

    public int getLocAreaCode() {
        return locAreaCode;
    }

    private String locName;
    private int locAreaCode;

    public JSONObject toJSON() {
        return new JSONObject();
    }

    public Location(int lc, String ln)
    {
        locName = ln;
        locAreaCode = lc;
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
