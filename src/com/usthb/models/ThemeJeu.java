package com.usthb.models;

import java.io.Serializable;
import java.util.List;

public class ThemeJeu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Type type;
    private String label;
    private int coef;
    private List<Question> questions;

    public ThemeJeu(Type type, String label, int coef, List<Question> questions) {
        this.type = type;
        this.label = label;
        this.coef = coef;
        this.questions = questions;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

}
