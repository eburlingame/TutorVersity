package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Question;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.User;

import java.util.ArrayList;
import java.util.List;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class MyQuestions extends AppCompatActivity {
    private Student student;
    private List<Question> list = new ArrayList<>();
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions_list);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);

        String title = "Integration Help";
        String q = "How to I integrate 1/x?";
        String time = "7:35 PM";
        String subject = "Calculus";
        Question question = new Question("Garrett", title, q, time, subject);
        Question question1 = new Question("Garrett", title, q, time, subject);
        Question question2 = new Question("Garrett", title, q, time, subject);
        Question question3 = new Question("Garrett", "hello", "jell", time, subject);
        Question question4 = new Question("Garrett", "testing", "no", time, subject);
        Question question5 = new Question("Garrett", "working", "yes", time, subject);
        Question question6 = new Question("Garrett", "nwe one", "eh", time, subject);
        list.add(question);
        list.add(question1);
        list.add(question2);
        list.add(question3);
        list.add(question4);
        list.add(question5);
        list.add(question6);
        list.add(question1);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StudentSidebarMenu menu = new StudentSidebarMenu(this, toolbar, student);


        ArrayAdapter<Question> adapter = new customAdapter();

        ListView newsItems = (ListView) (findViewById(R.id.newsItems));
        newsItems.setAdapter(adapter);

        newsItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question currentItem = list.get(position);
                Intent intent = new Intent(MyQuestions.this, ViewQuestion.class);
                intent.putExtra("QUESTION_ID", currentItem);
                startActivity(intent);
            }
        });

    }


    private class customAdapter extends ArrayAdapter<Question> {
        public customAdapter() {
            super(MyQuestions.this, R.layout.activity_my_questions_list, list);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.question_item, parent, false);
            }

            Question currentItem = list.get(position);

            ImageView newsImage = (ImageView) convertView.findViewById(R.id.leftIco);
            TextView question = (TextView) convertView.findViewById(R.id.desc);
            TextView heading = (TextView) convertView.findViewById(R.id.heading);

            heading.setText(currentItem.asker);
            question.setText(currentItem.question);
            //newsImage.setImageResource(currentItem.getImageID());


            return convertView;

        }
    }

}
