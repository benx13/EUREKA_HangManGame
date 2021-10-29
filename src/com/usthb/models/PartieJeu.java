package com.usthb.models;

import java.io.Serializable;
import java.util.LinkedList;

public class PartieJeu implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int number = 0;
    private int gameNumber;
    private ThemeJeu theme;
    private int questionNumber;
    private StringBuffer currentResponse;
    private Question currentQuestion;
    private Joueur joueur;
    private int score;
    private int level;


    public PartieJeu(ThemeJeu theme, Joueur joueur, int level) {
    	this.level = level;
        number ++;
        this.gameNumber = number;
        this.theme = theme;
        this.joueur = joueur;
        this.score = 0;
        this.currentQuestion = joueur.getQuestionByLevel(level+1, theme);
        this.currentResponse = new StringBuffer();
        initializeCurrentResponse();
    }
    
    public PartieJeu(ThemeJeu theme, Joueur joueur, int level, Question question) {
    	this.level = level;
        this.gameNumber = number;
        this.theme = theme;
        this.joueur = joueur;
        this.score = 0;
        this.currentQuestion = question;
        this.currentResponse = new StringBuffer();
        initializeCurrentResponse();
    }

    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean checkCharacter (String car) {
		
		LinkedList <Integer> occ = new LinkedList<Integer>();
		occ=occDeCaractere(currentQuestion.getResponse().toLowerCase(), car); // occ de type linkedliste contient l'index de toutes les occurences de car (elle est vide si il n'apparait pas dans reponse)
		
		boolean cond = occ.size()>0;
		if (cond) {
			for(int i=0;i<occ.size();i++) {
				
				this.currentResponse.replace(occ.get(i), occ.get(i)+1, String.valueOf(currentQuestion.getResponse().charAt(occ.get(i))));
				
				
			}
			
			occ.clear();// Vider la liste pour éviter la perte d'espace mémoire
			
			
		}
		return cond;
		
	}
    
    public int getGameNumber() {
		return gameNumber;
	}

    public void initializeCurrentResponse() {
    	
    	this.currentResponse=new StringBuffer();// Initialiser la reponse en cour
    	this.currentResponse.append("*".repeat(currentQuestion.getResponse().length()));
    	
    	//String response = this.currentQuestion.getResponse();
    	LinkedList <Integer> occ=new LinkedList<Integer>();
    	
		occ=this.occDeCaractere(currentQuestion.getResponse(), " ");
    	
    	for(int i : occ) {// Recuperer les blancs
			
			currentResponse.replace(i, i+1, " ");
			
		}
    	if (!occ.isEmpty()) 
    		occ.remove();
    	
    }
    
    public LinkedList<Integer> occDeCaractere (String mot, String car) { //Cette méthode retourne une linkedlist de toutes les occurences de car dans mot
		
        LinkedList<Integer> indexes = new LinkedList<Integer>();
 
        int index = 0;
        while(index != -1){// Si le car existe
        	
            index = mot.toUpperCase().indexOf(car.toUpperCase(), index);
            
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }


    public ThemeJeu getTheme() {
        return theme;
    }

    public void setTheme(ThemeJeu theme) {
        this.theme = theme;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
        this.currentQuestion = this.theme.getQuestions().get(questionNumber);
        this.initializeCurrentResponse();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public StringBuffer getCurrentResponse() {
        return currentResponse;
    }

    public void setCurrentResponse(StringBuffer currentResponse) {
        this.currentResponse = currentResponse;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

}


