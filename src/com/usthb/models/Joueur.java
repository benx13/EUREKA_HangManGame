package com.usthb.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("serial")
public abstract class Joueur implements Serializable {
	
    private static int joueurSequence = 0;

	private int numeroJoueur;
    private HashMap<Type, Integer> niveau;
    private String nom;
    private String prenom;
    private String motPass;
    private LocalDate dateNaissance;
    private List<PartieJeu> games;

    public Joueur(String nom, String prenom, String motPass, LocalDate dateNaissance) {
    	this.niveau = new HashMap<>();
    	this.initNiveau();
        this.numeroJoueur = joueurSequence++;
        this.nom = nom;
        this.prenom = prenom;
        this.motPass = motPass;
        this.dateNaissance = dateNaissance;
        this.games = new ArrayList<>();
    }

    
    public abstract void getQuestions(ThemeJeu themeJeu);
	public abstract Question getQuestionByLevel(int level, ThemeJeu theme);
	
	public static void setJoueurSequence(int joueurSequence) {
		Joueur.joueurSequence = joueurSequence;
	}

	public static int getJoueurSequence() {
		return joueurSequence;
	}
	
    public List<PartieJeu> getGames() {
		return games;
	}

	public void addGame(PartieJeu game) {
		this.games.add(game);
	}

    public int getNumeroJoueur() {

        return numeroJoueur;
    }

    public void setNumeroJoueur(int numeroJoueur) {

        this.numeroJoueur = numeroJoueur;
    }

    public int getDernierNiveau() {

        return Collections.max(this.niveau.values());
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getPrenom() {

        return prenom;
    }

    public void setPrenom(String prenom) {

        this.prenom = prenom;
    }

    public String getMotPass() {

        return motPass;
    }

    public void setMotPass(String motPass) {

        this.motPass = motPass;
    }

    public LocalDate getDateNaissance() {

        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {

        this.dateNaissance = dateNaissance;
    }
    
    private void initNiveau() {
    	
    	this.niveau.put(Type.CULTUREGENERALE, 0);
    	this.niveau.put(Type.GEOGRAPHIE, 0);
    	this.niveau.put(Type.HISTOIRE, 0);
    	this.niveau.put(Type.ISLAM, 0);
    	this.niveau.put(Type.SANTE, 0);
    	
    }

    public int getTotalScore () {
    	/*
    	 * Parcours de la liste des parties et récupération du score
    	 */
		int s=0;
		for (PartieJeu partie : games) {
			
			partie.getScore();
			s += partie.getScore() * partie.getTheme().getCoef();
			
		}
		return s;
	}
    
    public void incrementThemeNiveau(Type themeType) {
    	this.niveau.put(themeType, this.niveau.get(themeType)+1);
    }

	public HashMap<Type, Integer> getNiveau() {
		return niveau;
	}
    
}
