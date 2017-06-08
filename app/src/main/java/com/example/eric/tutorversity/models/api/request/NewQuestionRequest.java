package com.example.eric.tutorversity.models.api.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.eric.tutorversity.models.Location;
import com.example.eric.tutorversity.models.Question;
import com.example.eric.tutorversity.models.User;
import com.example.eric.tutorversity.models.api.response.NewQuestionResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.example.eric.tutorversity.models.api.JSONConstants.EMAIL;
import static com.example.eric.tutorversity.models.api.JSONConstants.LATITUDE;
import static com.example.eric.tutorversity.models.api.JSONConstants.LONGITUDE;
import static com.example.eric.tutorversity.models.api.JSONConstants.QUESTION;
import static com.example.eric.tutorversity.models.api.JSONConstants.SUBJECT;
import static com.example.eric.tutorversity.models.api.JSONConstants.TOKEN;
import static com.example.eric.tutorversity.models.api.URLConstants.NEW_QUESITON_URL;

public class NewQuestionRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private User user;
    private Location location;
    private Question question;

    private Response.Listener<NewQuestionResponse> responseListener;

    public NewQuestionRequest(User user, Question question) {
        this.question = question;
        this.location = new Location(-120, 20);
        this.user = user;
    }

    public JSONObject getJSONObject() {
        try {
            return new JSONObject()
                    .put(EMAIL, user.getEmail())
                    .put(TOKEN, user.getToken())
                    .put(QUESTION, question.getQuestion())
                    .put(SUBJECT, question.getSubject())
                    .put(LATITUDE, 45)
                    .put(LONGITUDE, -120);
        } catch (JSONException e) {
            return null;
        }
    }

    public Request<JSONObject> makeRequest(Response.Listener<NewQuestionResponse> responseListener) {
        this.responseListener = responseListener;
        JSONObject request = getJSONObject();
        Log.d("I", request.toString());
        return new JsonObjectRequest(Request.Method.POST,
                NEW_QUESITON_URL,
                request,
                this,
                this);
    }

    @Override
    public void onResponse(JSONObject response) {

        Log.d("I", response.toString());
        NewQuestionResponse questionResponse = null;
        try {
            questionResponse = new NewQuestionResponse(response);
        } catch (JSONException e) {
            Log.d("E", e.getMessage() + Arrays.toString(e.getStackTrace()));
            questionResponse = NewQuestionResponse.failed();
        }
        responseListener.onResponse(questionResponse);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("E", error.getMessage());
    }
}
