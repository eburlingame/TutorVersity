package com.example.eric.tutorversity.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Util {

    public static JSONObject fromJSON(String json)
    {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            throw new RuntimeException("JSON could not be parsed: " + e.getMessage());
        }
    }
}
