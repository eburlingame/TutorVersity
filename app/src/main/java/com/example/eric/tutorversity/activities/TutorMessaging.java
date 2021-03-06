package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eric.tutorversity.R;
import com.example.eric.tutorversity.models.Message;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by GarrettLeone on 6/7/17.
 */

public class TutorMessaging extends AppCompatActivity implements View.OnClickListener {

    private MessagesAdapter adapter;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Intent intent = getIntent();
        String title = intent.getStringExtra("contact name");
        setTitle(title);

        ListView listView = (ListView) findViewById(R.id.messages_list);
        messages = new ArrayList<>();
        adapter = new MessagesAdapter(messages);
        listView.setAdapter(adapter);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button sendMessage = (Button) findViewById(R.id.send_message);
        sendMessage.setOnClickListener(this);

        String[] ids = {"Adam", "-", "Kartik"};
        Arrays.sort(ids);
    }

    @Override
    public void onClick(View v) {
        EditText newMessageView = (EditText) findViewById(R.id.new_message);

        String newMessage = newMessageView.getText().toString();
        newMessageView.setText("");
        Message msg = new Message();
        msg.setText(newMessage);
        msg.setSender("Bill");
        if (msg.getText().length() > 0) {
            messages.add(msg);
            adapter.notifyDataSetChanged();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private class MessagesAdapter extends ArrayAdapter<Message> {
        MessagesAdapter(ArrayList<Message> messages) {
            super(TutorMessaging.this, R.layout.message_item, R.id.message, messages);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            Message message = getItem(position);

            TextView nameView = (TextView) view.findViewById(R.id.message);
            nameView.setText(message.getText());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nameView.getLayoutParams();

            if (message.getSender().equals("Bill")) {
                nameView.setBackground(getDrawable(R.drawable.bubble_right_green));
                layoutParams.gravity = Gravity.RIGHT;
            } else {
                nameView.setBackground(getDrawable(R.drawable.bubble_left_gray));
                layoutParams.gravity = Gravity.LEFT;
            }

            nameView.setLayoutParams(layoutParams);

            return view;
        }
    }
}

