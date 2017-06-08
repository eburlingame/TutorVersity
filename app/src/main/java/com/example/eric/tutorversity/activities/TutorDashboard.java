package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Tutor;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class TutorDashboard extends AppCompatActivity {

    Tutor tutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_dashboard2);

        String json = getIntent().getExtras().getString(USER);
        tutor = new Tutor(json);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        new TutorSidebarMenu(this, toolbar, tutor);

        Button questions = (Button) findViewById(R.id.btnViewQuestions);
        questions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TutorDashboard.this, TutorMyQuestions.class);
                intent.putExtra(USER, tutor.toJSON().toString());
                startActivity(intent);
            }
        });

        Button ask = (Button) findViewById(R.id.btnAskQuestion);
        ask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TutorDashboard.this, AddQuestion.class);
                intent.putExtra(USER, tutor.toJSON().toString());
                startActivity(intent);
            }
        });
        new TutorSidebarMenu(this, toolbar, tutor);
    }
}
