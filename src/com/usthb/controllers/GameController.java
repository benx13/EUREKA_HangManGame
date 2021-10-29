package com.usthb.controllers;

import javax.swing.JOptionPane;

import com.usthb.models.Joueur;
import com.usthb.models.PartieJeu;
import com.usthb.views.GamePage;
import com.usthb.views.HomePage;
import com.usthb.views.MainFrame;
import com.usthb.views.MessageDialog;
import com.usthb.views.Potence;
import com.usthb.views.ThemesPage;

public class GameController {

	private static class GameControllerHolder {
		private static final GameController instance = new GameController();
	}

	private static String Buffer;

	public static GameController getInstance() {
		return GameControllerHolder.instance;
	}

	public boolean didWeFinishAll(Joueur joueur) {
		/*
		 * Pour savoir si le joueur a atteint le dernier niveau 5
		 */
		return didWeFinish(joueur);
	}

	public boolean didWeFinishTheme(PartieJeu partie) {
		/*
		 * Pour savoir si le joueur a atteint le dernier niveau 5
		 */
		return partie.getJoueur().getNiveau().get(partie.getTheme().getType()) == 5;
	}
	
    public boolean didWeFinish(Joueur joueur) {
    	
    	for (int niv : joueur.getNiveau().values())
    		if (niv != 5)
    			return false;
    	return true;
    	
    }


	public boolean didWeLose(Potence potence) {
		/*
		 * Si la potence a atteint l'état final
		 */
		return potence.getState() >= 8;
	}

	public boolean didWeWin(PartieJeu partieJeu) {
		/*
		 * Vérifier si la réponse correspond
		 */
		return partieJeu.getCurrentResponse().toString().toUpperCase()
				.equals(partieJeu.getCurrentQuestion().getResponse().toUpperCase());
	}

	public void setBuffer(String buffer) {
		Buffer = buffer;
	}

	public void updateScores(int level, PartieJeu partieJeu) {
		/*
		 * Mise a jour du score si le niveau joué peut débloquer le suivant
		 */

		if (partieJeu.getJoueur().getNiveau().get(partieJeu.getTheme().getType()) == level)
			partieJeu.getJoueur().incrementThemeNiveau(partieJeu.getTheme().getType());
		/*
		 * Mise a jour du score de la partie puis l'ajout de la partie a la liste des
		 * jeu du joueurs
		 */
		partieJeu.setScore(partieJeu.getCurrentQuestion().getNBPoints());
		partieJeu.getJoueur().addGame(partieJeu);
	}

	public void verifications(GamePage gamePage, PartieJeu partie) {
		/*
		 * Cette méthode permet de savoir si la partie est gagnée, si les joueur a fini
		 * un thème etc...
		 */
		if (!partie.checkCharacter(Buffer))
			gamePage.getPotence().incrementState();

		gamePage.updatePotenceResponse();
		if (didWeWin(partie)) {

			gamePage.getPotence().setFound(true);
			updateScores(partie.getLevel(), partie);

			if (didWeFinishAll(partie.getJoueur())) {
				MessageDialog.printCongratsDialog(gamePage);
				MainFrame.switchView(new HomePage(partie.getJoueur()));
			} else if (didWeFinishTheme(partie)) {
				MessageDialog.printCongratsDialogTheme(gamePage, partie.getTheme().getLabel());
				MainFrame.switchView(new HomePage(partie.getJoueur()));
			} else if (MessageDialog.didWeWinDialog(gamePage) == JOptionPane.YES_OPTION)
				MainFrame.switchView(new ThemesPage(partie.getJoueur()));
			else
				MainFrame.switchView(new HomePage(partie.getJoueur()));
		} else if (didWeLose(gamePage.getPotence()))
			if (MessageDialog.didWeLoseDialog(gamePage) == JOptionPane.YES_OPTION)
				MainFrame.switchView(new GamePage(partie.getJoueur(), partie.getTheme(), partie.getLevel()));
			else
				MainFrame.switchView(new HomePage(partie.getJoueur()));

	}

}
