package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.eric.tutorversity.OurSingleton;
import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.AddQuestion;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.Tutor;
import com.example.eric.tutorversity.models.api.request.AuthRequest;
import com.example.eric.tutorversity.models.api.response.AuthResponse;

import org.json.JSONObject;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class StudentSidebar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private Student student;

    public void newQuestion(View view)
    {
        Intent intent = new Intent(getBaseContext(), AddQuestion.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String json = getIntent().getExtras().getString(USER);
        student = new Student(json);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        TextView userNameView = (TextView) header.findViewById(R.id.username);
        TextView userEmail = (TextView) header.findViewById(R.id.userEmail);

        userNameView.setText(student.getName());
        userEmail.setText(student.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_request)
        {
            Intent intent = new Intent(getBaseContext(), AddQuestion.class);
            intent.putExtra(USER, student.toJSON().toString());
            startActivity(intent);
        }
        else if (id == R.id.nav_nearby_tutors)
        {
//            Intent intent = new Intent(getBaseContext(), NearbyTutors.class);
//            intent.putExtra(USER, student.toJSON().toString());
//            startActivity(intent);
        }
        else if (id == R.id.nav_messages)
        {
//            Intent intent = new Intent(getBaseContext(), Messages.class);
//            intent.putExtra(USER, student.toJSON().toString());
//            startActivity(intent);
        }
        else if (id == R.id.nav_my_questions)
        {
            Intent intent = new Intent(getBaseContext(), MyQuestions.class);
            intent.putExtra(USER, student.toJSON().toString());
            startActivity(intent);
        }
        else if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(getBaseContext(), Settings.class);
            intent.putExtra(USER, student.toJSON().toString());
            startActivity(intent);
        }
        else if (id == R.id.nav_logout)
        {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            intent.putExtra(USER, student.toJSON().toString());
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
