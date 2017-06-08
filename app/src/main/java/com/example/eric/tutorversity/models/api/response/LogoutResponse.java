package com.example.eric.tutorversity.models.api.response;

import org.json.JSONException;
import org.json.JSONObject;

public class LogoutResponse {

    private boolean success;

    private LogoutResponse()
    {
        success = false;
    }

    public LogoutResponse(JSONObject body) throws JSONException {
        if (body != null) {
            success = true;
        }
    }

    public static LogoutResponse failed()
    {
        return new LogoutResponse();
    }

    public boolean getSuccess()
    {
        return success;
    }

}
