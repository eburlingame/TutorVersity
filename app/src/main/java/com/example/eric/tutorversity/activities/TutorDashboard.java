package com.example.eric.tutorversity.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Tutor;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class TutorDashboard extends AppCompatActivity {
    private Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_dashboard2);

        String json = getIntent().getExtras().getString(USER);
        tutor = new Tutor(json);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TutorSidebarMenu menu = new TutorSidebarMenu(this, toolbar, tutor);
    }
}
