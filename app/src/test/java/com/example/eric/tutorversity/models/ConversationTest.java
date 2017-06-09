package com.example.eric.tutorversity.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jsheriff on 6/8/2017.
 */

public class ConversationTest {

    @Test
    public void createConversation() throws Exception {
        Conversation conversation = new Conversation();

        assertEquals(conversation.conversationID, "");
    }

    @Test
    public void setConversationID() throws Exception {
        Conversation conversation = new Conversation();
        conversation.setConversationID("conversation_id");

        assertEquals(conversation.conversationID, "conversation_id");
    }


}
