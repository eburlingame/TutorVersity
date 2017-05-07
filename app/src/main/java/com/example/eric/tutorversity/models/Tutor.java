package com.example.eric.tutorversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class Tutor extends User {

    Set<String> subjects;

    public Tutor(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
