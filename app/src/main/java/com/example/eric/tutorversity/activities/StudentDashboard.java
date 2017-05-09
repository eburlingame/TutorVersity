package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.eric.tutorversity.OurSingleton;
import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.AddQuestion;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.Tutor;
import com.example.eric.tutorversity.models.api.request.AuthRequest;
import com.example.eric.tutorversity.models.api.response.AuthResponse;

import org.json.JSONObject;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class StudentDashboard extends StudentSidebar {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

}
