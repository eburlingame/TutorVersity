package com.example.eric.tutorversity;

import android.app.Application;

import com.example.eric.tutorversity.models.User;

public class OurApplication extends Application {

    public static User currentUser;

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
    }

}