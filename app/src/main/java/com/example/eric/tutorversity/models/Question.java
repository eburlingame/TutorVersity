package com.example.eric.tutorversity.models;

import java.io.Serializable;

/**
 * Created by amcinnis on 5/5/17.
 */

public class Question implements Serializable {

    public String asker;
    public String title;
    public String question;
    public String subject;
    public String time;

    public Question(String asker, String title, String question, String subject, String time) {
        this.asker = asker;
        this.title = title;
        this.question = question;
        this.subject = subject;
        this.time = time;
    }
}
