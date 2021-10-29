package com.usthb.views;

import com.usthb.controllers.LoginController;
import com.usthb.controllers.ThemesController;
import com.usthb.models.Joueur;
import com.usthb.models.JoueurAdulte;
import com.usthb.models.JoueurEnfant;
import com.usthb.models.ThemeJeu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Comparator.comparing;

@SuppressWarnings("serial")
public class ThemesPage extends JPanel {

    private Joueur joueur;

    public ThemesPage(Joueur joueur) {
        this.joueur = joueur;
        this.initialize();
    }

    private void initialize() {
        setLayout(null);
        printThemes();
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
        lblPlayer.setBounds(30, 12, 315, 40);
        add(lblPlayer);

        JLabel lblTopScore = new JLabel("Meilleur score: " + joueur.getTotalScore());
        lblTopScore.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopScore.setBounds(780, 12, 210, 28);
        add(lblTopScore);

        JLabel lblTopLevel = new JLabel("Meilleur niveau: " + joueur.getDernierNiveau());
        lblTopLevel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopLevel.setBounds(780, 50, 210, 28);
        add(lblTopLevel);

        JButton btnLogout = new JButton("Retour");
        btnLogout.setBounds(816, 434, 140, 25);
        btnLogout.addActionListener(e -> MainFrame.switchView(new HomePage(joueur)));
        add(btnLogout);
        

    }


    private void printThemes() {
        JLabel lblSelectATheme = new JLabel("Choisissez un theme");
        lblSelectATheme.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectATheme.setBounds(284, 100, 239, 50);
        add(lblSelectATheme);
        
        JLabel lblLevel = new JLabel("Dernier niveau atteint");
        lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
        lblLevel.setBounds(525, 100, 227, 50);
        add(lblLevel);

        Set<ThemeJeu> themes = ThemesController.getInstance().getListeThemes();
        AtomicInteger gridy = new AtomicInteger(150);
        themes.forEach(themeJeu -> {
        	
            JButton button = new JButton(themeJeu.getType().toString());
            button.setBounds(350, gridy.get(), 200, 25);
            button.addActionListener(e -> MainFrame.switchView(new LevelPage(joueur, themeJeu)));
            add(button);
            
            JLabel label = new JLabel(String.valueOf(joueur.getNiveau().get(themeJeu.getType())));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBounds(575, gridy.get(), 150, 25);
            add(label);

            gridy.set(gridy.get()+50);
        });
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
            JLabel label = new JLabel(count.get() + "/ " + joueur.getNom() + ": " + joueur.getTotalScore());
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
