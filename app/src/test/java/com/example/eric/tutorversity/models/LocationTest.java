/**
 * Created by Kartik on 6/7/17.
 */

package com.example.eric.tutorversity.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Test
    public void getLatitude() throws Exception {
        double expected = 90.0;
        double actual = new Location(90, 90).getLatitude();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void getLongitude() throws Exception {
        double expected = 90.0;
        double actual = new Location(90, 90).getLongitude();
        assertEquals(expected, actual, 0);
    }

}