package com.usthb.controllers;

import com.usthb.models.QuestionAdulte;
import com.usthb.models.QuestionEnfant;
import com.usthb.models.Question;
import com.usthb.models.ThemeJeu;
import com.usthb.models.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ThemesController {
	
	
	private static class ThemesControllerHolder {
        private static final ThemesController instance = new ThemesController();
    }

    public static ThemesController getInstance() {
        return ThemesControllerHolder.instance;
    }
    

	private HashSet<ThemeJeu> listeThemes = new HashSet<>();
    
	private ThemesController()  {
	
		initThemes();
			
	}
	
	private void initThemes() {
		
		try {
			/*
			 * Remplir le HashSet contenant les thèmes
			 */
			String path = System.getProperty("user.dir")+"\\src\\com\\usthb\\ressources\\Themes";
			LinkedList<String> listeTh = new LinkedList<String> (Arrays.asList("HIS","GEO","CUL","ISL","SAN"));
			
			for (String current : listeTh) {
				
				Type curentType = Type.of(current);
				
				File theme = new File(path+"/"+current+".txt");
				BufferedReader reader = new BufferedReader(new FileReader(theme));
			    
			    ThemeJeu currentTheme = new ThemeJeu(curentType, curentType.getLabel(), curentType.getCoef(), new LinkedList<Question>());
			    
			    String line = reader.readLine();
			    String age, quest, rep, niv;
			    List<String> list;
			    
			    while (line != null)
			    {
			    	
			    	list = Arrays.asList(line.split(","));
			    	
			    	age = list.get(0);
			    	quest = list.get(1);
			    	rep = list.get(2);
			    	niv = list.get(3);
			    	
			    	Question question;
			    	
			    	if (age.equals("Enfant"))
			    		question = new QuestionEnfant(quest, curentType, rep, Integer.valueOf(niv));
			    	else 
			    		question = new QuestionAdulte(quest, curentType, rep, Integer.valueOf(niv));
			    	
			    	currentTheme.addQuestion(question);
			        
			    	line = reader.readLine();
			    	
			    	
			    }
			    
			    listeThemes.add(currentTheme);
			    reader.close();
			}
			
		}
		catch (FileNotFoundException e) { System.out.println("Themes file not found"); }
		catch (Exception e) {
			System.out.println(e);
			}
		
	}
	
	public HashSet<ThemeJeu> getListeThemes() {
        return listeThemes;
    }

}
