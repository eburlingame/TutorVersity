package com.example.eric.tutorversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import static com.example.eric.tutorversity.models.Util.fromJSON;

public class Student extends User implements Serializable {

    List<Question> questions;

    public Student(String jsonString) {
        this(fromJSON(jsonString));
    }

    public Student(JSONObject jsonObject) {
        super(jsonObject);
    }

    public JSONObject toJSON() {
        return super.toJSON();
    }

}
