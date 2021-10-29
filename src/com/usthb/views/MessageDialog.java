package com.usthb.views;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MessageDialog {
	/*
	 * Cette classe renferme tous les messages d'erreurs qui peuvent �tre g�n�r�s
	 */
	
	public static String reformat(String string) {
        var stringSplit =  string.split(" ");
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < stringSplit.length; i++) {
            str.append(stringSplit[i]).append(" ");
            if(i % 12 == 11) {
                str.append("<br/>");
            }
        }
        return str.toString();
    }

	public static void userAlreadyExists(JPanel panel) {
		JOptionPane.showMessageDialog(panel,
				"L'utilisateur existe deja",
				"Erreur",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void printCongratsDialog(JPanel panel) {
        JOptionPane.showMessageDialog(panel,
                "Bravo vous avez gagn�",
                "F�licitation",
                JOptionPane.PLAIN_MESSAGE);
    }
	
	public static void printCongratsDialogTheme(JPanel panel, String label) {
        JOptionPane.showMessageDialog(panel,
                "Bravo vous avez fini le th�me "+label,
                "F�licitation",
                JOptionPane.PLAIN_MESSAGE);
    }

	public static int didWeLoseDialog(JPanel panel) {
		Object[] options = {"Oui",
        "Non"};

        return JOptionPane.showOptionDialog(
                panel,
                "Voulez-vous r�essayer ?",
                "Perdu",
                JOptionPane.YES_NO_OPTION,
		        JOptionPane.QUESTION_MESSAGE,
		        null,   
		        options, 
		        options[0]); 

    }

    public static int didWeWinDialog(JPanel panel) {
    	Object[] options = {"Oui",
        "Non"};
    	
        return JOptionPane.showOptionDialog(
                panel,
                "Voulez-vous jouer une autre partie ?",
                "Nouvelle partie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
		        null,     //do not use a custom Icon
		        options,  //the titles of buttons
		        options[0]); //default button title);
    }
    
    public static void showTutorial(JPanel panel){
        JOptionPane.showMessageDialog(panel,
        		"<html>" + reformat("Le but de ce jeu est de r�pondre aux questions pos�es lettre par lettre, attention vous n'avez que 8 chances donc ne vous tropez pas !") + "</html>",
                //TODO
                "Tutoriel",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void incorrectPassWord(JPanel panel) {
    	JOptionPane.showMessageDialog(panel,
				"Le mot de passe ne correspond pas ou l'utilisateur n'existe pas",
				"Erreur de connection",
				JOptionPane.ERROR_MESSAGE);
    }
    
    public static void succesfullLogin(JPanel panel, String username) {
    	JOptionPane.showMessageDialog(panel,
				"Bienvenu au jeu "+username+" !",
				"Connection r�ussie",
				JOptionPane.PLAIN_MESSAGE);
    }
    
    public static void passWordsDoesntMatch(JPanel panel) {
    	
    JOptionPane.showMessageDialog(panel,
			"Les deux mots de passe ne correspondent pas",
			"Mot de passe non valide",
			JOptionPane.ERROR_MESSAGE);
    }
    
    public static void succesfullSignup(JPanel panel) {
    JOptionPane.showMessageDialog(panel,
			"Veuillez vous connecter",
			"Inscription r�ussie",
			JOptionPane.PLAIN_MESSAGE);
    }
    
    public static void emptyFields(JPanel panel) {
    JOptionPane.showMessageDialog(panel,
			"Veuillez remplir les champs",
			"Erreur",
			JOptionPane.ERROR_MESSAGE);
    }
}
