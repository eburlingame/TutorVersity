package com.example.eric.tutorversity.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Student;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class StudentDashboard extends AppCompatActivity {

    private Student student;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StudentSidebarMenu menu = new StudentSidebarMenu(this, toolbar, student);

        Button questions = (Button) findViewById(R.id.btnViewQuestions);
        questions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboard.this, MyQuestions.class);
                intent.putExtra(USER, student.toJSON().toString());
                if(intent != null)
                    startActivity(intent);
            }
        });

        Button ask = (Button) findViewById(R.id.btnAskQuestion);
        ask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboard.this, AddQuestion.class);
                intent.putExtra(USER, student.toJSON().toString());
                if(intent != null)
                    startActivity(intent);
            }
        });
    }

}
