package com.example.eric.tutorversity.models.api.response;

import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.Tutor;
import com.example.eric.tutorversity.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eric.tutorversity.models.api.JSONConstants.STATUS;
import static com.example.eric.tutorversity.models.api.JSONConstants.SUCCESS;
import static com.example.eric.tutorversity.models.api.JSONConstants.USER;
import static com.example.eric.tutorversity.models.api.JSONConstants.USER_TYPE;
import static com.example.eric.tutorversity.models.api.JSONConstants.USER_TYPE_STUDENT;
import static com.example.eric.tutorversity.models.api.JSONConstants.USER_TYPE_TUTOR;

public class LogoutResponse {

    private boolean success;

    private LogoutResponse()
    {
        success = false;
    }

    public LogoutResponse(JSONObject body) throws JSONException {
        success = true;
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
