package com.example.eric.tutorversity.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eric.tutorversity.models.Util.fromJSON;
import static com.example.eric.tutorversity.models.api.JSONConstants.*;

public class User {

    private String email;
    private String bio;
    private String name;
    private String token;
    private boolean showMyLocation;

    public User(JSONObject jsonObject) {
        try
        {
            email = jsonObject.getString(EMAIL);
            bio = jsonObject.getString(BIO);
            name = jsonObject.getString(NAME);
            token = jsonObject.getString(TOKEN);
        }
        catch (JSONException e) {
            Log.e("E", e.getMessage(), e);
        }
    }

    public User(String json)
    {
        this(fromJSON(json));
    }

    public User(String name, String email) {
        try {
            this.email = email;
            this.name = name;
        }
        catch (Exception e) {
            Log.e("E", e.getMessage(), e);
        }
    }

    public JSONObject toJSON() {
        try {
            return new JSONObject()
                    .put(EMAIL, email)
                    .put(BIO, bio)
                    .put(NAME, name)
                    .put(TOKEN, token)
//                    .put(LOCATION, currentLocation’.toJSON())
                    .put(SHOW_MY_LOCATION, showMyLocation);
        } catch (JSONException e) {
            Log.e("E", e.getMessage(), e);
            return null;
        }
    }

    public String getToken() { return token; }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getBio()
    {
        return bio;
    }
}
