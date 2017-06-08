package com.example.eric.tutorversity.test;

import com.example.eric.tutorversity.models.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by amcinnis on 5/17/17.
 */
public class UserTest {
    @Test
    public void getEmail() throws Exception {
        User user = new User("Austin", "amcinnis@calpoly.edu");
        String expected = "amcinnis@calpoly.edu";
        assertEquals(expected, user.getEmail());
    }

    @Test
    public void getName() throws Exception {
        User user = new User("Austin", "amcinnis@calpoly.edu");
        String expected = "Austin";
        assertEquals(expected, user.getName());
    }
}