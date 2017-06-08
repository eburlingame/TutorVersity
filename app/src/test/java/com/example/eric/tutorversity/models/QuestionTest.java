package com.example.eric.tutorversity.models;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by GarrettLeone on 6/7/17.
 */
public class QuestionTest {

    @Test
    public void testTruncate() {
        String expected = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx";
        Question q = new Question("hello", "title", "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz", "");
        String actual = q.truncateQuestion(q.getQuestion());
        assertEquals(expected, actual);
    }

    @Test
    public void testDateDiff() {
        long expected = 10000;
        Date date1 = new Date(10000);
        Date date2 = new Date(20000);
        long actual = Question.getDateDiff(date1,date2, TimeUnit.MILLISECONDS);
        assertEquals(expected, actual);
    }

}