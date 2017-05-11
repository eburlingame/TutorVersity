package com.example.eric.tutorversity.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Student;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class StudentDashboard extends AppCompatActivity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StudentSidebarMenu menu = new StudentSidebarMenu(this, toolbar, student);
    }

}
