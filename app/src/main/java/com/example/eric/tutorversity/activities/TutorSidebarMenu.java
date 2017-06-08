package com.example.eric.tutorversity.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Tutor;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class TutorSidebarMenu implements Drawer.OnDrawerItemClickListener
{
    private Drawer drawer;
    private Tutor tutor;
    private Activity activity;

    private PrimaryDrawerItem dashboardItem = new PrimaryDrawerItem().withIdentifier(1).withName("Dashboard")
            .withIcon(R.drawable.ic_apps_black_24dp);
    private PrimaryDrawerItem nearbyQuestions = new PrimaryDrawerItem().withIdentifier(4).withName("Nearby questions")
            .withIcon(R.drawable.ic_place_black_24dp);
    private PrimaryDrawerItem messagesItem = new PrimaryDrawerItem().withIdentifier(5).withName("Messages")
            .withIcon(R.drawable.ic_forum_black_24dp);
    private PrimaryDrawerItem myProfileItem = new PrimaryDrawerItem().withIdentifier(6).withName("My Profile")
            .withIcon(R.drawable.ic_person_black_24dp);
    private PrimaryDrawerItem settingsItem = new PrimaryDrawerItem().withIdentifier(6).withName("Settings")
            .withIcon(R.drawable.ic_more_vert_black_24dp);
    private PrimaryDrawerItem logoutItem = new PrimaryDrawerItem().withIdentifier(7).withName("Logout")
            .withIcon(R.drawable.ic_clear_black_24dp);

    public TutorSidebarMenu(final Activity activity, Toolbar toolbar, Tutor tutor)
    {
        this.tutor = tutor;
        this.activity = activity;
        //create the drawer and remember the `Drawer` drawer object
        drawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .addDrawerItems(
                        dashboardItem,
                        nearbyQuestions,
                        messagesItem,
                        myProfileItem,
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
            Intent intent = new Intent(activity.getBaseContext(), TutorDashboard.class);
            intent.putExtra(USER, tutor.toJSON().toString());
            activity.startActivity(intent);

        }
        else if (drawerItem.equals(myProfileItem))
        {

        }
        else if (drawerItem.equals(nearbyQuestions))
        {
            Intent intent = new Intent(activity.getBaseContext(), TutorMyQuestions.class);
            intent.putExtra(USER, tutor.toJSON().toString());
            activity.startActivity(intent);
        }
        else if (drawerItem.equals(messagesItem))
        {

        }
        else if (drawerItem.equals(settingsItem))
        {

        }
        else if (drawerItem.equals(logoutItem))
        {
            Intent intent = new Intent(activity.getBaseContext(), LoginActivity.class);
            intent.putExtra(USER, tutor.toJSON().toString());
            activity.startActivity(intent);
        }
        return true;
    }
}
