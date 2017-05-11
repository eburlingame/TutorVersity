package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Student;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class AddQuestion extends AppCompatActivity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);
    }

    //need to figure out how to go back to previous activity properly
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
