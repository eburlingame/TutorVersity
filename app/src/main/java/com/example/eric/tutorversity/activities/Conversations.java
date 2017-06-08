package com.example.eric.tutorversity.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Conversation;
import com.example.eric.tutorversity.models.Student;

import java.util.ArrayList;

import static com.example.eric.tutorversity.models.api.JSONConstants.USER;

public class Conversations extends AppCompatActivity {

    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        String json = getIntent().getExtras().getString(USER);
        Student student = new Student(json);
        ListView listView = (ListView)findViewById(R.id.conversations);
        context = this;
        ArrayList<Conversation> conversationList = new ArrayList<>();
        Conversation temp = new Conversation();
        temp.setConversationID("Adam");
        conversationList.add(temp);
        temp = new Conversation();
        temp.setConversationID("Julie");
        conversationList.add(temp);
        ArrayAdapter<Conversation> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conversationList);
        listView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        new StudentSidebarMenu(this, toolbar, student);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                String name = parent.getItemAtPosition(pos).toString();
                Intent intent = new Intent(context, Messaging.class);
                intent.putExtra("contact name",name);
                startActivity(intent);
            }
        });


    }
}
