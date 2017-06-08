package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Question;
import com.example.eric.tutorversity.models.User;

import java.util.Date;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class ViewQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);

        Intent i = getIntent();
        Question question = (Question)i.getSerializableExtra("QUESTION_ID");

        //Labels
        EditText subjectLabel = (EditText) findViewById(R.id.subjectLabel);
        subjectLabel.setText(question.getSubject());

        EditText timeLabel = (EditText) findViewById(R.id.timeLabel);
        timeLabel.setText(new Date().toString());

        EditText titleLabel = (EditText) findViewById(R.id.questionTitleLabel);
        titleLabel.setText(question.getTitle());

        EditText questionLabel = (EditText) findViewById(R.id.questionBody);
        questionLabel.setText(question.getQuestion());
    }
}
