package com.example.eric.tutorversity.activities;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.eric.tutorversity.OurSingleton;
import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Question;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.User;
import com.example.eric.tutorversity.models.api.request.GetQuestionsRequest;
import com.example.eric.tutorversity.models.api.response.GetQuestionsResponse;

import java.util.ArrayList;
import java.util.List;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

/**
 * Created by GarrettLeone on 6/3/17.
 */

public class TutorMyQuestions extends AppCompatActivity {

    private Student student;
    private List<Question> list = new ArrayList<>();
    private Activity activity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_view_question);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);

        Question question = new Question("Joe Smith", "Stuck on factorials", "How do I compute 12! by hand?", "Math");
        Question question1 = new Question("Kim Davis", "Try-Catch Blocks", "What is the proper usage of try-catch block for input?", "Computer Engineering");
        Question question2 = new Question("Bob Miller", "Time complexity", "How do I compute the time complexity of a for loop?", "Computer Engineering");
        Question question5 = new Question("Taylor Williams", "Atomic numbers", "On the periodic table, which number is the atomic number?", "Chemistry");
        Question question3 = new Question("Taylor Williams", "Atomic numbers", "On the periodic table, which number is the atomic number?", "Chemistry");
        Question question4 = new Question("Taylor Williams", "Atomic numbers", "On the periodic table, which number is the atomic number?ajsdkfjlkasdjfaklsdjflkasjdlfjalkdfjlkasljdflkasdjflaklsjdflk", "Biology");
        list.add(question);
        list.add(question1);
        list.add(question2);
        list.add(question3);
        list.add(question4);
        list.add(question5);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StudentSidebarMenu menu = new StudentSidebarMenu(this, toolbar, student);

        ArrayAdapter<Question> adapter = new customAdapter();

        ListView newsItems = (ListView) (findViewById(R.id.newsItems));
        newsItems.setAdapter(adapter);

        newsItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question currentItem = list.get(position);
                Intent intent = new Intent(TutorMyQuestions.this, ViewQuestion.class);
                intent.putExtra("QUESTION_ID", currentItem);
                startActivity(intent);
            }
        });


    }


    private class customAdapter extends ArrayAdapter<Question> {


        public customAdapter() {
            super(TutorMyQuestions.this, R.layout.activity_tutor_view_question, list);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tutor_question_item, parent, false);
            }

            Question currentItem = list.get(position);

            ImageView newsImage = (ImageView) convertView.findViewById(R.id.leftIco);
            TextView question = (TextView) convertView.findViewById(R.id.desc);
            TextView heading = (TextView) convertView.findViewById(R.id.heading);

            ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
            delete.setFocusable(false);
            delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("CLICKED", "DELETE");
                }
            });

            ImageView reply = (ImageView) convertView.findViewById(R.id.reply);
            reply.setFocusable(false);
            reply.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("CLICKED", "REPLY");
                }
            });


            heading.setText(currentItem.title);
            question.setText(currentItem.question);
            newsImage.setImageResource(getImage(currentItem.subject));


            return convertView;
        }

        private int getImage(String subject) {
            if (subject.equals("Chemistry")) {
                return R.mipmap.ic_chem;
            } else if (subject.equalsIgnoreCase("Physics")) {
                return R.mipmap.ic_phys;
            } else if (subject.equalsIgnoreCase("English")) {
                return R.mipmap.ic_engl;
            } else if (subject.equalsIgnoreCase("Biology")) {
                return R.mipmap.ic_bio;
            } else if (subject.equalsIgnoreCase("Math")) {
                return R.mipmap.ic_math;
            } else if (subject.equalsIgnoreCase("Mechanical Engineering")) {
                return R.mipmap.ic_me;
            } else if (subject.equalsIgnoreCase("Computer Engineering")) {
                return R.mipmap.ic_cpe;
            } else {
                return R.mipmap.ic_launcher_round;
            }
        }

    }
}
