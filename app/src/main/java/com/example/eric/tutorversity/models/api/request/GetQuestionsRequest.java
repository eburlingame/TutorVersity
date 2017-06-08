package com.example.eric.tutorversity.models.api.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.eric.tutorversity.models.User;
import com.example.eric.tutorversity.models.api.response.AuthResponse;
import com.example.eric.tutorversity.models.api.response.GetQuestionsResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.example.eric.tutorversity.models.api.JSONConstants.EMAIL;
import static com.example.eric.tutorversity.models.api.JSONConstants.PASSWORD;
import static com.example.eric.tutorversity.models.api.JSONConstants.TOKEN;
import static com.example.eric.tutorversity.models.api.URLConstants.AUTH_URL;
import static com.example.eric.tutorversity.models.api.URLConstants.GET_QUESTIONS_URL;

public class GetQuestionsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private User user;
    private Response.Listener<GetQuestionsResponse> responseListener;

    public GetQuestionsRequest(User user) {
        this.user = user;
    }

    public JSONObject getJSONObject() {
        try {
            return new JSONObject()
                    .put(EMAIL, user.getEmail())
                    .put(TOKEN, user.getToken());
        } catch (JSONException e) {
            return null;
        }
    }

    public Request<JSONObject> makeRequest(Response.Listener<GetQuestionsResponse> responseListener) {
        this.responseListener = responseListener;
        JSONObject request = getJSONObject();
        Log.d("I", request.toString());
        return new JsonObjectRequest(Request.Method.POST,
                GET_QUESTIONS_URL,
                request,
                this,
                this);
    }

    @Override
    public void onResponse(JSONObject response) {

        Log.d("I", response.toString());
        GetQuestionsResponse qResponse = null;
        try {
            qResponse = new GetQuestionsResponse(response);
        } catch (JSONException e) {
            Log.d("E", e.getMessage() + Arrays.toString(e.getStackTrace()));
            qResponse = GetQuestionsResponse.failed();
        }
        responseListener.onResponse(qResponse);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
