package com.example.eric.tutorversity.models.api.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.eric.tutorversity.models.api.response.AuthResponse;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eric.tutorversity.models.api.JSONConstants.EMAIL;
import static com.example.eric.tutorversity.models.api.URLConstants.AUTH_URL;

public class AuthRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private String username;
    private String password;
    private Response.Listener<AuthResponse> responseListener;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private JSONObject getJSONObject() {
        try {
            return new JSONObject()
                    .put(EMAIL, username)
                    .put("password", password);
        } catch (JSONException e) {
            Log.e("E", e.getMessage(), e);
            return null;
        }
    }

    public Request<JSONObject> makeRequest(Response.Listener<AuthResponse> responseListener) {
        this.responseListener = responseListener;
        JSONObject request = getJSONObject();
        Log.d("I", request.toString());
        return new JsonObjectRequest(Request.Method.POST,
                AUTH_URL,
                request,
                this,
                this);
    }

    @Override
    public void onResponse(JSONObject response) {

        Log.d("I", response.toString());
        AuthResponse authResponse = null;
        try {
            authResponse = new AuthResponse(response);
        } catch (JSONException e) {
            Log.e("E", e.getMessage(), e);
            authResponse = AuthResponse.failed();
        }
        responseListener.onResponse(authResponse);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("E", error.getMessage());
    }
}
