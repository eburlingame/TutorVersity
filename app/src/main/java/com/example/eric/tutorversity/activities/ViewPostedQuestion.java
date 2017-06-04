package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Question;
import com.example.eric.tutorversity.models.User;

import java.util.Date;

/**
 * Created by GarrettLeone on 6/3/17.
 */

public class ViewPostedQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posted_question);


        Intent i = getIntent();
        Question question = (Question) i.getSerializableExtra("QUESTION_ID");

        //Labels
        EditText subjectLabel = (EditText) findViewById(R.id.subjectLabel);
        subjectLabel.setText(question.subject);

        EditText timeLabel = (EditText) findViewById(R.id.timeLabel);
        timeLabel.setText(new Date().toString());

        EditText titleLabel = (EditText) findViewById(R.id.questionTitleLabel);
        titleLabel.setText(question.title);

        EditText questionLabel = (EditText) findViewById(R.id.questionBody);
        questionLabel.setText(question.question);

    }
}
