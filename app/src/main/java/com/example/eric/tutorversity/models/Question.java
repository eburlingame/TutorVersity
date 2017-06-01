package com.example.eric.tutorversity.models;

import java.io.Serializable;

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
}
