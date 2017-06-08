package com.example.eric.tutorversity.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Student;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class StudentSidebarMenu implements Drawer.OnDrawerItemClickListener
{
    private Student student;
    private Activity activity;

    private PrimaryDrawerItem dashboardItem = new PrimaryDrawerItem().withIdentifier(1).withName("Dashboard")
            .withIcon(R.drawable.ic_apps_black_24dp);
    private PrimaryDrawerItem newRequestItem = new PrimaryDrawerItem().withIdentifier(2).withName("New Request")
            .withIcon(R.drawable.ic_add_black_24dp);
    private PrimaryDrawerItem myQuestionsItem = new PrimaryDrawerItem().withIdentifier(3).withName("My Questions")
            .withIcon(R.drawable.ic_dehaze_black_24dp);
    private PrimaryDrawerItem nearbyTutorsItem = new PrimaryDrawerItem().withIdentifier(4).withName("Nearby tutors")
            .withIcon(R.drawable.ic_place_black_24dp);
    private PrimaryDrawerItem messagesItem = new PrimaryDrawerItem().withIdentifier(5).withName("Messages")
            .withIcon(R.drawable.ic_forum_black_24dp);
    private PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(6).withName("Settings")
            .withIcon(R.drawable.ic_more_vert_black_24dp);
    private PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withIdentifier(7).withName("Logout")
            .withIcon(R.drawable.ic_clear_black_24dp);
    private PrimaryDrawerItem viewQuestionItem = new PrimaryDrawerItem().withIdentifier(8).withName("View Question")
            .withIcon(R.drawable.ic_menu_send);
    private PrimaryDrawerItem viewTutorItem = new PrimaryDrawerItem().withIdentifier(9).withName("View Tutor")
            .withIcon(R.drawable.ic_person_black_24dp);

    public StudentSidebarMenu(final Activity activity, Toolbar toolbar, Student student)
    {
        this.student = student;
        this.activity = activity;
        //create the drawer and remember the `Drawer` drawer object
        new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .addDrawerItems(
                        dashboardItem,
                        newRequestItem,
                        myQuestionsItem,
                        nearbyTutorsItem,
                        messagesItem,
                        settingsItem,
                        viewQuestionItem,
                        viewTutorItem,
                        logoutItem
                )
                .withOnDrawerItemClickListener(this)
                .build();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        if (drawerItem.equals(dashboardItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), StudentDashboard.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(newRequestItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), AddQuestion.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(myQuestionsItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), MyQuestions.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(nearbyTutorsItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), NearbyTutorsMap.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(messagesItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), Conversations.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(settingsItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), Settings.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(logoutItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), LoginActivity.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(viewQuestionItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), ViewQuestion.class);
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(viewTutorItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), ViewTutor.class);
            activity.startActivity(intent);
        }
        return true;
    }
}
