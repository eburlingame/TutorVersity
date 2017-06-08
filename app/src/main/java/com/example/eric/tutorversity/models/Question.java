package com.example.eric.tutorversity.models;

import java.io.Serializable;

public class Question implements Serializable {

    private String asker;
    private String title;
    private String question;
    private String subject;

    public Question(String asker, String title, String question, String subject) {
        this.setAsker(asker);
        this.setTitle(title);
        this.setQuestion(question);
        this.setSubject(subject);
    }

    /* Getters */
    public String getAsker() {return asker;}
    public String getTitle() {return title;}
    public String getQuestion() {return question;}
    public String getSubject() {return subject;}

    /* Setters */
    public void setAsker(String asker) {this.asker = asker;}
    public void setQuestion(String question) {this.question = question;}
    public void setSubject(String subject) {this.subject = subject;}
    public void setTitle(String title) {this.title = title;}
}
