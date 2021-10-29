package com.usthb.models;

public enum Type {
	
    HISTOIRE("Histoire", "HIS", 3),
    GEOGRAPHIE("Géographie", "GEO", 2),
    SANTE("Santé", "SAN", 2),
    CULTUREGENERALE("Culture Générale", "CUL", 3),
    ISLAM("Islam", "ISL", 3);
    
    Type(String label, String abv, int coef) {
		this.label = label;
		this.abv = abv;
		this.coef = coef;
	}

	private String label;
	private String abv;
	private int coef;
	
	public String getLabel() {
		return label;
	}
	public String getAbv() {
		return abv;
	}
	public int getCoef() {
		return coef;
	}
    
	public static Type of(String theme) {
			
			Type type =  null;
			
			switch (theme) {
		         case "HIS":  type=Type.HISTOIRE;
		         break;
		         case "GEO":  type=Type.GEOGRAPHIE;
		         break;
		         case "CUL":  type=Type.CULTUREGENERALE;
		         break;
		         case "ISL":  type=Type.ISLAM;
		         break;
		         case "SAN":  type=Type.SANTE;
		         break;
			}
			
		    return type;
			
	}
	
}