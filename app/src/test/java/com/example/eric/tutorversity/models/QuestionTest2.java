package com.example.eric.tutorversity.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Manasi on 6/7/17.
 */
public class QuestionTest2 {
    @Test
    public void getAsker() throws Exception {
        String expected = "Bob";
        String actual = new Question("Bob", "Help", "NA", "NA").getAsker();
        assertEquals(expected, actual);
    }

    @Test
    public void getTitle() throws Exception {
        String expected = "Help";
        String actual = new Question("Bob", "Help", "NA", "NA").getTitle();
        assertEquals(expected, actual);
    }

}