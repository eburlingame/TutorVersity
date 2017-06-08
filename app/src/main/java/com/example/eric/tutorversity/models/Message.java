package com.example.eric.tutorversity.models;

import java.util.Date;

/**
 * Created by amcinnis on 5/5/17.
 */

public class Message {
    Conversation conversation;
    User from;
    User to;
    Date timestamp;

    private String text;
    private String sender;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
