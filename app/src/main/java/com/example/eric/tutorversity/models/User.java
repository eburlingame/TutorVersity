package com.example.eric.tutorversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eric.tutorversity.models.Util.fromJSON;
import static com.example.eric.tutorversity.models.api.JSONConstants.*;

public class User {

    private String email;
    private String bio;
    private String name;
    private String token;
    private Location currentLocation;
    private boolean showMyLocation;

    public User(JSONObject jsonObject) {
        try
        {
            email = jsonObject.getString(EMAIL);
            bio = jsonObject.getString(BIO);
            name = jsonObject.getString(NAME);
            token = jsonObject.getString(TOKEN);
            currentLocation = null;
        }
        catch (JSONException e)
        {
            throw new RuntimeException("Could not parse JSON." + e.getMessage());
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
            throw new RuntimeException("Could not create User." + e.getMessage());
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
            throw new RuntimeException("JSON could not be parsed for some reason");
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
