package com.usthb.views;

import com.usthb.controllers.LoginController;
import com.usthb.models.Joueur;
import com.usthb.models.JoueurAdulte;
import com.usthb.models.JoueurEnfant;
import com.usthb.models.ThemeJeu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Comparator.comparing;

@SuppressWarnings("serial")
public class LevelPage extends JPanel {

    private Joueur joueur;
    private ThemeJeu themeJeu;

    public LevelPage(Joueur joueur, ThemeJeu themeJeu) {
        this.themeJeu = themeJeu;
        this.joueur = joueur;
        this.initialize();
    }

    private void initialize() {
        setLayout(null);
        printLevels();
        printLeaderBoardEnfant();
        printLeaderBoardAdulte();
        printOtherComponents();

    }

    private void printOtherComponents() {
        JLabel lblNewLabel_2 = new JLabel("EUREKA ");
        lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(240, 12, 529, 27);
        add(lblNewLabel_2);

        JLabel lblPlayer = new JLabel("Joueur: " + joueur.getNom());
        lblPlayer.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblPlayer.setBounds(30, 12, 389, 28);
        add(lblPlayer);
        
        JLabel lblTheme = new JLabel("Thème choisis : " + this.themeJeu.getType().getLabel());
        lblTheme.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTheme.setBounds(28, 66, 391, 28);
        add(lblTheme);

        JLabel lblTopScore = new JLabel("Meilleur score: " + joueur.getTotalScore());
        lblTopScore.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopScore.setBounds(780, 12, 210, 28);
        add(lblTopScore);

        JLabel lblTopLevel = new JLabel("Meilleur niveau: " + joueur.getDernierNiveau());
        lblTopLevel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopLevel.setBounds(780, 50, 210, 28);
        add(lblTopLevel);

        JLabel lblTopLevel1 = new JLabel("Meilleur niveau du thème: " + joueur.getNiveau().get(themeJeu.getType()));
        lblTopLevel1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopLevel1.setBounds(780, 88, 250, 28);
        add(lblTopLevel1);

        JButton btnLogout = new JButton("Retour");
        btnLogout.setBounds(807, 434, 149, 25);
        btnLogout.addActionListener(e -> MainFrame.switchView(new ThemesPage(joueur)));
        add(btnLogout);

    }
    
    private void printLevels() {
    	/*
    	 * L'affichage des boutons pour le choix du niveau
    	 */
        JLabel lblSelectATheme = new JLabel("Choisissez un niveau:");
        lblSelectATheme.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectATheme.setBounds(279, 150, 420, 50);
        add(lblSelectATheme);
        int x = 320;
        int y = 200;
        for(int i=0; i<5; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.setBounds(x, y, 100, 100);
            int level = i;
            button.addActionListener(e -> MainFrame.switchView(new GamePage(joueur, themeJeu, level)));

            button.setEnabled(joueur.getNiveau().get(themeJeu.getType()) >= i);
            add(button);
            x = x + 120;
            if(i == 2){y = 310; x = 385;}
        }
    }

    private void printLeaderBoardAdulte() {
    	/*
    	 * Afficher le classement des joueur
    	 */
        JPanel panel = new JPanel();
        panel.setBounds(52, 148, 186, 275);
        panel.setBorder(BorderFactory.createTitledBorder("Classement Adultes"));
        panel.setLayout(null);
        add(panel);

        ArrayList<Joueur> sorted = new ArrayList<>();
        LoginController.getInstance().getListeJoueurs().values().forEach(joueur -> {
        	if (joueur instanceof JoueurAdulte)
        		sorted.add(joueur);
        });
        sorted.sort(comparing(Joueur::getTotalScore));
        Collections.reverse(sorted);

        AtomicInteger gridy = new AtomicInteger(25);
        AtomicInteger count = new AtomicInteger();
        sorted.forEach(joueur -> {
            count.set(count.get() + 1);
            JLabel label = new JLabel(count.get() + "/ " + joueur.getNom() + " : " + joueur.getTotalScore());
            label.setBounds(30, gridy.get(), 100, 25);
            gridy.set(gridy.get()+25);
            panel.add(label);
        });
    }
    
    private void printLeaderBoardEnfant() {
    	/*
    	 * Afficher le classement des joueur
    	 */
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(770, 148, 186, 275);
        panel_1.setBorder(BorderFactory.createTitledBorder("Classement Enfants"));
        panel_1.setLayout(null);
        add(panel_1);

        ArrayList<Joueur> sorted = new ArrayList<>();
        LoginController.getInstance().getListeJoueurs().values().forEach(joueur -> {
        	if (joueur instanceof JoueurEnfant)
        		sorted.add(joueur);
        });
        sorted.sort(comparing(Joueur::getTotalScore));
        Collections.reverse(sorted);

        AtomicInteger gridy = new AtomicInteger(25);
        AtomicInteger count = new AtomicInteger();
        sorted.forEach(joueur -> {
            count.set(count.get() + 1);
            JLabel label = new JLabel(count.get() + "/ " + joueur.getNom() + ": " + joueur.getTotalScore());
            label.setBounds(30, gridy.get(), 100, 25);
            gridy.set(gridy.get()+25);
            panel_1.add(label);
        });
    }

}