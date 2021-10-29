package com.usthb.views;


import com.usthb.controllers.LoginController;
import com.usthb.models.Joueur;
import com.usthb.models.JoueurAdulte;
import com.usthb.models.JoueurEnfant;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import static java.util.Comparator.comparing;

@SuppressWarnings("serial")
public class HomePage extends JPanel {

    private Joueur joueur;


    public HomePage(Joueur joueur) {
        this.joueur = joueur;
        initialize();
    }


    private void initialize() {
        setLayout(null);
        printLeaderBoardAdulte();
        printLeaderBoardEnfant();
        printOtherComponents();

    }

    private void printOtherComponents() {
        JButton btnNewGame = new JButton("Nouvelle partie");
        btnNewGame.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));
        btnNewGame.setBounds(346, 210, 313, 80);
        btnNewGame.addActionListener(e -> MainFrame.switchView(new ThemesPage(joueur)));
        add(btnNewGame);

        JButton btnTutorial = new JButton("Tutoriel");
        btnTutorial.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        btnTutorial.addActionListener(e -> MessageDialog.showTutorial(this));
        btnTutorial.setBounds(425, 323, 150, 60);
        add(btnTutorial);

        JLabel lblNewLabel = new JLabel("EUREKA");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel.setBounds(361, 48, 282, 60);
        add(lblNewLabel);

        JLabel lblGame = new JLabel("Jeu");
        lblGame.setHorizontalAlignment(SwingConstants.CENTER);
        lblGame.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        lblGame.setBounds(361, 103, 282, 60);
        add(lblGame);

        JLabel lblPlayer = new JLabel("Joueur: " + joueur.getNom());
        lblPlayer.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblPlayer.setBounds(30, 12, 282, 28);
        add(lblPlayer);

        JLabel lblTopScore = new JLabel("Meilleur score: " + joueur.getTotalScore());
        lblTopScore.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopScore.setBounds(780, 12, 210, 28);
        add(lblTopScore);

        JLabel lblTopLevel = new JLabel("Meilleur niveau: " + joueur.getDernierNiveau());
        lblTopLevel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopLevel.setBounds(780, 50, 210, 28);
        add(lblTopLevel);

        JLabel lblTopLevel1 = new JLabel("");
        lblTopLevel1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblTopLevel1.setBounds(746, 700, 210, 28);
        add(lblTopLevel1);

        JButton btnLogout = new JButton("Déconnection");
        btnLogout.setBounds(805, 454, 150, 25);
        btnLogout.addActionListener(e -> MainFrame.switchView(new LoginPage()));
        add(btnLogout);
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