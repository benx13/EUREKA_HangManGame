package com.usthb.controllers;

import com.usthb.models.Joueur;
import com.usthb.models.JoueurAdulte;
import com.usthb.models.JoueurEnfant;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class LoginController {
	
	
	private static class LoginControllerHolder {
        private static final LoginController instance = new LoginController();
    }

    public static LoginController getInstance() {
        return LoginControllerHolder.instance;
    }

    
    private HashMap<Integer, Joueur> listeJoueurs = new HashMap<>();

    private LoginController() {
    	read();
    }
    
    public HashMap<Integer, Joueur> getListeJoueurs() {
        return listeJoueurs;
    }
    
    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
    	if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } 
    	return 0;
    }
    
    public Joueur login(String name, String password){
    	/*
    	 * Vérification de la présence du joueur et du mot de passe
    	 */
	    for (Joueur joueur : listeJoueurs.values()) {
	    		
	    	if (joueur.getNom().equals(name) && joueur.getMotPass().equals(password)) // Joueur trouvé
	    		return joueur;
	    		
	    }
	    return null;
    
    }

    public void subscribe(String user, String firstName, String password, LocalDate date) {
    	/*
    	 * Inscription avec création du joueur
    	 */
    	Joueur player;
    	
    	if (calculateAge(date, LocalDate.now()) > 18) 
    		player = new JoueurAdulte(user, firstName, password, date);
    	else
    		player = new JoueurEnfant(user, firstName, password, date);
	    	
	    listeJoueurs.put(player.getNumeroJoueur(), player);
    }
    
    public boolean searchJoueur(String nom) {
    	
    	for (Joueur j : listeJoueurs.values()) {
    		
    		if (j.getNom().equals(nom)) //Le joueur existe
    			return true;
    		
    	}
    	return false;
    	
    }
    
    public boolean validateFields (String name, String firstName, String password) {
    	/*
    	 * Vérifier si les champs sont remplis
    	 */
    	
    	return name.equals("") || firstName.contentEquals("") || password.equals("");
    	
    }
    
    public boolean checkPasswords(String pW1, String pW2) {
    	/*
    	 * Si les 2 mots de passe sont identiques
    	 */
    	
    	return pW1.equals(String.valueOf(pW2));
    	
    }

	@SuppressWarnings("unchecked")
	public void read() {
		/*
		 * Pour lire le fichier JoueursHashMap.txt et remplir le HashMap
		 */
        try {
        	String path = System.getProperty("user.dir")+"\\src\\com\\usthb\\ressources\\JoueursHashMap.txt";
        	
        	File toRead = new File(path);
            FileInputStream fis=new FileInputStream(toRead);
            ObjectInputStream ois=new ObjectInputStream(fis);

            listeJoueurs = (HashMap<Integer, Joueur>) ois.readObject();
            Joueur.setJoueurSequence(listeJoueurs.size());

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) { System.out.println("Login file not found"); }
          catch (IOException e) { System.out.println("Error initializing stream"); }
          catch (ClassNotFoundException e) { e.printStackTrace(); }
        
    }

    public void Save() {
    	/*
    	 * Pour sauvegarder le fichier JoueursHashMap.txt
    	 */
        try {
        	String path = System.getProperty("user.dir")+"\\src\\com\\usthb\\ressources\\JoueursHashMap.txt";
        	
        	File toSave = new File(path);
            FileOutputStream fos = new FileOutputStream(toSave);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(listeJoueurs);
            oos.flush();
            oos.close();
            fos.close();
            
        } catch (FileNotFoundException e) { System.out.println("Login file not found"); }
          catch (IOException e) { System.out.println("Error initializing stream"); }

    }

}
