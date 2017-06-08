package com.example.eric.tutorversity.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Manasi on 6/7/17.
 */
public class LocationTest2 {
    @Test
    public void getLocName() throws Exception {
        String expected = "slo";
        String actual = new Location(5, "slo").getLocName();
        assertEquals(expected, actual);
    }

    @Test
    public void getLocAreaCode() throws Exception {
        int expected = 5;
        int actual = new Location(5, "slo").getLocAreaCode();
        assertEquals(expected, actual);
    }

}