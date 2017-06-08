/**
 * Created by Kartik on 6/7/17.
 */

package com.example.eric.tutorversity.models;

import org.junit.Test;

import static org.junit.Assert.*;


public class MessageTest {
    @Test
    public void getText() throws Exception {
        String expected = "hello";
        String actual = new Message("Bob", "hello").getText();
        assertEquals(expected, actual);
    }

    @Test
    public void getSender() throws Exception {
        String expected = "Bob";
        String actual = new Message("Bob", "hello").getSender();
        assertEquals(expected, actual);
    }

}