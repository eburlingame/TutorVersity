package com.example.eric.tutorversity.models.api.response;

import android.util.Log;

import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.Tutor;
import com.example.eric.tutorversity.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.eric.tutorversity.models.api.JSONConstants.*;

public class AuthResponse {

    private boolean success;
    private User user;

    private AuthResponse()
    {
        success = false;
    }

    public AuthResponse(JSONObject body) throws JSONException {
        if (body.getString(STATUS).equals(SUCCESS))
        {
            success = true;
            JSONObject userData = body.getJSONObject(USER);
            if (userData.getString(USER_TYPE).equals(USER_TYPE_STUDENT))
            {
                user = new Student(userData);
            }
            else if (userData.getString(USER_TYPE).equals(USER_TYPE_TUTOR))
            {
                user = new Tutor(userData);
            }
        }
        user = null;
    }

    public static AuthResponse failed()
    {
        return new AuthResponse();
    }

    public boolean getSuccess()
    {
        return success;
    }

    public User getUser()
    {
        return user;
    }

}
