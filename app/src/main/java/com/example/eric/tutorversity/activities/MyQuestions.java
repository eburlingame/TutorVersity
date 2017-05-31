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
        Question question = new Question("Joe Smith", "Stuck on factorials", "How do I compute 12! by hand?", "Math", time);
        Question question1 = new Question("Kim Davis", "Try-Catch Blocks", "What is the proper usage of try-catch block for input?", "Computer Engineering", time);
        Question question2 = new Question("Bob Miller", "Time complexity", "How do I compute the time complexity of a for loop?", "Computer Engineering", time);
        Question question5 = new Question("Taylor Williams", "Atomic numbers", "On the periodic table, which number is the atomic number?", "Chemistry", time);
        Question question3 = new Question("Taylor Williams", "Atomic numbers", "On the periodic table, which number is the atomic number?", "Chemistry", time);
        Question question4 = new Question("Taylor Williams", "Atomic numbers", "On the periodic table, which number is the atomic number?ajsdkfjlkasdjfaklsdjflkasjdlfjalkdfjlkasljdflkasdjflaklsjdflk", "Biology", time);
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

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.question_item, parent, false);
            }

            Question currentItem = list.get(position);

            ImageView newsImage = (ImageView) convertView.findViewById(R.id.leftIco);
            TextView question = (TextView) convertView.findViewById(R.id.desc);
            TextView heading = (TextView) convertView.findViewById(R.id.heading);

            heading.setText(currentItem.title);
            question.setText(currentItem.question);
            newsImage.setImageResource(getImage(currentItem.subject));


            return convertView;
        }

        private int getImage(String subject) {
            if(subject.equals("Chemistry")) {
                return R.mipmap.ic_chem;
            }
            else if(subject.equalsIgnoreCase("Physics")) {
                return R.mipmap.ic_phys;
            }
            else if(subject.equalsIgnoreCase("English")) {
                return R.mipmap.ic_engl;
            }
            else if(subject.equalsIgnoreCase("Biology")) {
                return R.mipmap.ic_bio;
            }
            else if(subject.equalsIgnoreCase("Math")) {
                return R.mipmap.ic_math;
            }
            else if(subject.equalsIgnoreCase("Mechanical Engineering")) {
                return R.mipmap.ic_me;
            }
            else if(subject.equalsIgnoreCase("Computer Engineering")) {
                return R.mipmap.ic_cpe;
            }
            else {
                return R.mipmap.ic_launcher_round;
            }
        }

    }
}




