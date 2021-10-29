package com.usthb.models;

public class QuestionAdulte extends Question{

	private static final long serialVersionUID = 1L;

	public QuestionAdulte(String label, Type themeType, String response, int niveau) {
        super(label, themeType, response, niveau);
    }
}