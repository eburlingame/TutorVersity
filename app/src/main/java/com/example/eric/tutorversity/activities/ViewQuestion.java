package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Question;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.User;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class ViewQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);

        //Generate fake question
        User asker = new User("Austin McInnis", "amcinnis@calpoly.edu");
        String title = "Integration Help";
        String q = "How to I integrate 1/x?";
        String time = "7:35 PM";
        String subject = "Calculus";
        //Question question = new Question("Garrett", title, q, time, subject);

        Intent i = getIntent();
        Question question = (Question)i.getSerializableExtra("QUESTION_ID");

        //Labels
        EditText subjectLabel = (EditText) findViewById(R.id.subjectLabel);
        subjectLabel.setText(question.subject);

        EditText timeLabel = (EditText) findViewById(R.id.timeLabel);
        timeLabel.setText(question.time);

        EditText titleLabel = (EditText) findViewById(R.id.questionTitleLabel);
        titleLabel.setText(question.title);

        EditText questionLabel = (EditText) findViewById(R.id.questionBody);
        questionLabel.setText(question.question);
    }
}
