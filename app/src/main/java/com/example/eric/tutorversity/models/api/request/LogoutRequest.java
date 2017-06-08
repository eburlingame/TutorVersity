package com.example.eric.tutorversity.models.api.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.eric.tutorversity.models.User;
import com.example.eric.tutorversity.models.api.response.LogoutResponse;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eric.tutorversity.models.api.JSONConstants.EMAIL;
import static com.example.eric.tutorversity.models.api.URLConstants.LOGOUT_URL;

public class LogoutRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private User user;
    private Response.Listener<LogoutResponse> responseListener;

    public LogoutRequest(User user) {
        this.user = user;
    }

    public JSONObject getJSONObject() {
        try {
            return new JSONObject()
                    .put(EMAIL, user.getEmail());
        } catch (JSONException e) {
            Log.e("E", e.getMessage(), e);
            return null;
        }
    }

    public Request<JSONObject> makeRequest(Response.Listener<LogoutResponse> responseListener) {

        this.responseListener = responseListener;
        JSONObject request = getJSONObject();

        Log.d("I", request.toString());
        return new JsonObjectRequest(Request.Method.POST,
                LOGOUT_URL,
                request,
                this,
                this);
    }

    @Override
    public void onResponse(JSONObject response) {

        Log.d("I", response.toString());
        LogoutResponse authResponse = null;
        try {
            authResponse = new LogoutResponse(response);
        } catch (JSONException e) {
            Log.e("E", e.getMessage(), e);
            authResponse = LogoutResponse.failed();
        }
        responseListener.onResponse(authResponse);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("E", error.getMessage());
    }
}
