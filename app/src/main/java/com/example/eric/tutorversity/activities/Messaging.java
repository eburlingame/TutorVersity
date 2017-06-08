package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Messaging extends AppCompatActivity implements View.OnClickListener {

    private MessagesAdapter adapter;
    private ArrayList<Message> messages;
    private static final String KARTIK = "Kartik";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Intent intent = getIntent();
        String title = intent.getStringExtra("contact name");
        setTitle(title);

        Message temp = new Message();
        Message temp1 = new Message();
        Message temp2 = new Message();
        temp.setSender("Adam");
        temp.setText("Hey! I figured out\nthe answer to your\nquestion.");

        ListView listView = (ListView)findViewById(R.id.messages_list);
        messages = new ArrayList<>();
        messages.add(temp);
        temp1.setSender(KARTIK);
        temp1.setText("That's awesome! I\nthink we should meet up");
        messages.add(temp1);
        temp2.setSender("Adam");
        temp2.setText("I'll be free tomorrow\nafter 5 pm.");
        messages.add(temp2);
        adapter = new MessagesAdapter(messages);
        listView.setAdapter(adapter);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button sendMessage = (Button)findViewById(R.id.send_message);
        sendMessage.setOnClickListener(this);

        String[] ids = {"Adam", "-", KARTIK};
        Arrays.sort(ids);
    }

    @Override
    public void onClick(View v) {
        EditText newMessageView = (EditText)findViewById(R.id.new_message);

        String newMessage = newMessageView.getText().toString();
        newMessageView.setText("");
        Message message = new Message();
        message.setText(newMessage);
        message.setSender(KARTIK);
        if (message.getText().length() > 0)
        {
            messages.add(message);
            adapter.notifyDataSetChanged();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private class MessagesAdapter extends ArrayAdapter<Message> {
        MessagesAdapter(ArrayList<Message> messages) {
            super(Messaging.this, R.layout.message_item, R.id.message, messages);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = super.getView(position, convertView, parent);
            Message message = getItem(position);

            TextView nameView = (TextView) view.findViewById(R.id.message);
            nameView.setText(message.getText());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nameView.getLayoutParams();

            int sdk = Build.VERSION.SDK_INT;

            if (message.getSender().equals(KARTIK)) {
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackground(getDrawable(R.drawable.bubble_right_green));
                } else {
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_right_green));
                }
                layoutParams.gravity = Gravity.RIGHT;
            } else {
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackground(getDrawable(R.drawable.bubble_left_gray));
                } else {
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_left_gray));
                }
                layoutParams.gravity = Gravity.LEFT;
            }

            nameView.setLayoutParams(layoutParams);


            return view;
        }
    }
}
