package com.usthb.models;

public  class QuestionEnfant extends Question{

	private static final long serialVersionUID = 1L;

	public QuestionEnfant(String label, Type themeType, String response, int niveau) {
        super(label, themeType, response, niveau);
    }
}