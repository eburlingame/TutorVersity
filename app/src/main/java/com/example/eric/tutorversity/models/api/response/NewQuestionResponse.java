package com.example.eric.tutorversity.models.api.response;

import com.example.eric.tutorversity.models.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import static com.example.eric.tutorversity.models.api.JSONConstants.EMAIL;
import static com.example.eric.tutorversity.models.api.JSONConstants.MESSAGE;
import static com.example.eric.tutorversity.models.api.JSONConstants.QUESTION;
import static com.example.eric.tutorversity.models.api.JSONConstants.SUBJECT;
import static com.example.eric.tutorversity.models.api.JSONConstants.SUCCESS;

public class NewQuestionResponse {

    private boolean success;
    private String message;

    private NewQuestionResponse()
    {
        success = false;
        message = "The network call failed";
    }

    public NewQuestionResponse(JSONObject body) throws JSONException {
        success = body.getBoolean(SUCCESS);
        message = body.getString(MESSAGE);
    }

    public static NewQuestionResponse failed()
    {
        return new NewQuestionResponse();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
