package com.usthb.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JoueurAdulte extends Joueur{
	
	private static final long serialVersionUID = 1L;
	
	private List<Question> listQuestions;
	
    public JoueurAdulte(String nom, String prenom, String motPass, LocalDate dateNaissance) {
        super(nom, prenom, motPass, dateNaissance);
        listQuestions = new ArrayList <>();
    }

    @Override
    public void getQuestions(ThemeJeu themeJeu) {
    	ArrayList <Question> tmp = new ArrayList<>();
    	themeJeu.getQuestions().forEach(question -> {
    		if (question instanceof QuestionAdulte) {
    			tmp.add(question);
    		}
    	});
    	this.listQuestions = tmp;
    }
    
    @Override
    public Question getQuestionByLevel(int level, ThemeJeu theme) {

    	getQuestions(theme);
    	List<Question> tmp = new ArrayList<> ();
    	
        for (Question quest : listQuestions) 
        	if(quest.getNiveau() == level)
        		tmp.add(quest);

        Random rand = new Random();
        return tmp.get(rand.nextInt(tmp.size()));
        
    }
}
