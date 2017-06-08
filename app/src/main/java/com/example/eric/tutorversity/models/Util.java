package com.example.eric.tutorversity.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Util {

    private Util() {}

    public static JSONObject fromJSON(String json)
    {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            Log.e("E", e.getMessage(), e);
            return null;
        }
    }
}
