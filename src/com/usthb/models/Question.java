package com.usthb.models;

import java.io.Serializable;

public abstract class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int[] questionSequence;

    private String label;
    private String number;
    private String response;
    private int niveau;

    public int getNBPoints() {
        switch (this.niveau) {
            case 1:
                return 5;
            case 2:
                return 10;
            case 3:
                return 18;
            case 4:
                return 28;
            case 5:
                return 40;
            default:
                return 0;
        }
    }

    public Question(String label, Type themeType, String response, int niveau) {
    	questionSequence = new int[]{0, 0, 0, 0, 0};
    	
        this.label = label;
        this.number = getQuestionNumber(themeType);
        this.response = response;   
        this.niveau = niveau;
    }

    private String getQuestionNumber(Type themeType) {
         switch (themeType) {
            case ISLAM: return "ISL" + questionSequence[0]++;
            case SANTE: return "SAN" + questionSequence[1]++;
            case HISTOIRE: return "HIS" + questionSequence[2]++;
            case GEOGRAPHIE: return "GEO" + questionSequence[3]++;
            case CULTUREGENERALE: return "CUL" + questionSequence[4]++;
        }
        throw new IllegalArgumentException ("Theme not recognized");
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {

        this.label = label;
    }

    public String getNumber() {

        return number;
    }

    public void setNumber(String number) {

        this.number = number;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {

        this.response = response;
    }

    public int getNiveau() {

        return niveau;
    }

    public void setNiveau(int niveau) {

        this.niveau = niveau;
    }

}
