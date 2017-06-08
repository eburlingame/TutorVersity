package com.example.eric.tutorversity.models;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Question implements Serializable {

    public String asker;
    public String title;
    public String question;
    public String subject;

    public Question(String asker, String title, String question, String subject) {
        this.asker = asker;
        this.title = title;
        this.question = question;
        this.subject = subject;
    }


    public String truncateQuestion(String question) {
       if(question.length() > 50) {
           return question.substring(0, 50);
       }
       return question;
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
