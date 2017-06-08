package com.example.eric.tutorversity.activities;

import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.eric.tutorversity.R;

public class ViewTutor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tutor);

        ImageView tutorPhoto = (ImageView) findViewById(R.id.tutorPhoto);
        tutorPhoto.setImageResource(R.mipmap.ic_tutor_image);

        EditText tutorName = (EditText) findViewById(R.id.tutorName);
        tutorName.setText("Tutor Name");

        RatingBar tutorRating = (RatingBar) findViewById(R.id.tutorRating);
        tutorRating.setRating((float) 4.0);

        ListView subjectsList = (ListView) findViewById(R.id.subjectsList);
        String[] subjects = {"Calculus", "Architecture", "Computer Science"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjects);
        subjectsList.setAdapter(adapter);
    }
}
