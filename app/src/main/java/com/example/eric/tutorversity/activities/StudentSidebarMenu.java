package com.example.eric.tutorversity.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.eric.tutorversity.models.Student;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class StudentSidebarMenu implements Drawer.OnDrawerItemClickListener
{
    private Drawer drawer;
    private Student student;
    private Activity activity;

    //if you want to update the items at a later time it is recommended to keep it in a variable
    private PrimaryDrawerItem dashboardItem = new PrimaryDrawerItem().withIdentifier(1).withName("Dashboard");
    private PrimaryDrawerItem newRequestItem = new PrimaryDrawerItem().withIdentifier(2).withName("New Request");
    private PrimaryDrawerItem myQuestionsItem = new PrimaryDrawerItem().withIdentifier(3).withName("My Questions");
    private PrimaryDrawerItem nearbyTutorsItem = new PrimaryDrawerItem().withIdentifier(4).withName("Nearby tutors");
    private PrimaryDrawerItem messagesItem = new PrimaryDrawerItem().withIdentifier(5).withName("Messages");
    private PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(6).withName("Settings");
    private PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withIdentifier(7).withName("Logout");

    public StudentSidebarMenu(final Activity activity, Toolbar toolbar, Student student)
    {
        this.student = student;
        this.activity = activity;
        //create the drawer and remember the `Drawer` drawer object
        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .addDrawerItems(
                        dashboardItem,
                        newRequestItem,
                        myQuestionsItem,
                        nearbyTutorsItem,
                        messagesItem,
                        settingsItem,
                        logoutItem
                )
                .withOnDrawerItemClickListener(this)
                .build();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        if (drawerItem.equals(dashboardItem))
        {

        }
        else if (drawerItem.equals(newRequestItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), AddQuestion.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(myQuestionsItem))
        {

        }
        else if (drawerItem.equals(nearbyTutorsItem))
        {

        }
        else if (drawerItem.equals(messagesItem))
        {

        }
        else if (drawerItem.equals(settingsItem))
        {

        }
        else if (drawerItem.equals(logoutItem))
        {

        }
        return true;
    }
}
