package com.example.eric.tutorversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eric.tutorversity.models.api.JSONConstants.*;

public abstract class User {

    String email;
    String bio;
    Location currentLocation;
    boolean showMyLocation;

    public User(JSONObject jsonObject) throws JSONException {
        email = jsonObject.getString(EMAIL);
        bio = jsonObject.getString(BIO);
        currentLocation = null;
    }
}
