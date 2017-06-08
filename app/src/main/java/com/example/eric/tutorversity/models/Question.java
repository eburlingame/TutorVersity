package com.example.eric.tutorversity.models;

import java.io.Serializable;

public class Question implements Serializable {

    public String getAsker() {
        return asker;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public String getSubject() {
        return subject;
    }

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
