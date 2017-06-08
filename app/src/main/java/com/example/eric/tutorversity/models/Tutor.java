package com.example.eric.tutorversity.models;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Set;

import static com.example.eric.tutorversity.models.Util.fromJSON;

public class Tutor extends User implements Serializable {

    public Tutor(String jsonString) {
        this(fromJSON(jsonString));
    }

    public Tutor(JSONObject jsonObject) { super(jsonObject); }
}
