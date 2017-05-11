package com.example.eric.tutorversity.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Student;
import com.example.eric.tutorversity.models.User;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class StudentSidebarMenu implements Drawer.OnDrawerItemClickListener
{
    private Drawer drawer;
    private Student student;
    private Activity activity;

    //if you want to update the items at a later time it is recommended to keep it in a variable
    private PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Dashboard");
    private PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("New Request");
    private PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("My Questions");
    private PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Nearby tutors");
    private PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Messages");
    private PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Settings");
    private PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("Logout");

    public StudentSidebarMenu(final Activity activity, Toolbar toolbar, Student student)
    {
        this.student = student;
        this.activity = activity;
        //create the drawer and remember the `Drawer` drawer object
        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item5,
                        item6,
                        item7
                )
                .withOnDrawerItemClickListener(this)
                .build();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        if (drawerItem.equals(item1))
        {

        }
        else if (drawerItem.equals(item2))
        {
            Intent intent = new Intent(activity.getBaseContext(), AddQuestion.class);
            intent.putExtra(USER, student.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(item3))
        {

        }
        else if (drawerItem.equals(item4))
        {

        }
        else if (drawerItem.equals(item5))
        {

        }
        else if (drawerItem.equals(item6))
        {

        }
        else if (drawerItem.equals(item7))
        {

        }
        return true;
    }
}
