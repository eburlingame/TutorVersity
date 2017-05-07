package com.example.eric.tutorversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Student extends User {

    List<Question> questions;

    public Student(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
