package com.example.eric.tutorversity.models.api.request;

import com.android.volley.Response;
import com.example.eric.tutorversity.models.api.response.AuthResponse;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthRequestTest {

    @Test
    public void makeRequest() throws Exception {
        final AuthRequest request = new AuthRequest("username", "password");

        request.makeRequest(new Response.Listener<AuthResponse>() {
            @Override
            public void onResponse(AuthResponse response) {
                assertFalse(response.getSuccess());
            }
        });

        request.onResponse(new JSONObject("{ 'success': false, 'message': 'error' } "));
    }

}