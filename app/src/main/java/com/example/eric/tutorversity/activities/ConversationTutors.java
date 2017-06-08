package com.example.eric.tutorversity.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Conversation;
import com.example.eric.tutorversity.models.Tutor;

import java.util.ArrayList;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class ConversationTutors extends AppCompatActivity {

    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        String json = getIntent().getExtras().getString(USER);
        Tutor tutor = new Tutor(json);
        ListView listView = (ListView)findViewById(R.id.conversations);
        context = this;
        ArrayList<Conversation> conversations = new ArrayList<>();
        Conversation temp = new Conversation();
        temp.setConversationID("Joe");
        conversations.add(temp);
        temp = new Conversation();
        temp.setConversationID("Bob");
        conversations.add(temp);
        ArrayAdapter<Conversation> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conversations);
        listView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        new TutorSidebarMenu(this, toolbar, tutor);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                String name = parent.getItemAtPosition(pos).toString();
                Intent intent = new Intent(context, TutorMessaging.class);
                intent.putExtra("contact name",name);
                startActivity(intent);
            }
        });}
}


