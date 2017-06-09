package com.example.eric.tutorversity.models;

import java.util.List;

/**
 * Created by amcinnis on 5/5/17.
 */

public class Conversation {

    String conversationID;
    User partner;
    List<Message> messages;
    public String toString()
    {
        return conversationID;
    }
    public String getConversationID() {
        return conversationID;
    }

    public Conversation() {
        this.conversationID = "";
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }
}
