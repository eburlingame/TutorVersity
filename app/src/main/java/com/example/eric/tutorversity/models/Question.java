package com.example.eric.tutorversity.models;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Question implements Serializable {

    public String getAsker() {
        return asker;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question_text;
    }

    public String getSubject() {
        return subject;
    }

    private String asker;
    private String title;
    private String question_text;
    private String subject;

    public Question(String asker, String title, String question, String subject) {
        this.asker = asker;
        this.title = title;
        this.question_text = question;
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

    /* Setters */
    public void setAsker(String asker) {this.asker = asker;}
    public void setQuestion(String question) {this.question_text = question;}
    public void setSubject(String subject) {this.subject = subject;}
    public void setTitle(String title) {this.title = title;}
}
