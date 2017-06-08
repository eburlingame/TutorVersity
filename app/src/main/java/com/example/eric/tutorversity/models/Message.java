package com.example.eric.tutorversity.models;

import java.util.Date;

/**
 * Created by amcinnis on 5/5/17.
 */

public class Message {

    private String text;
    private String sender;

    public Message()
    {

    }
    public Message(String s, String t)
    {
        text = t;
        sender = s;
    }

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
