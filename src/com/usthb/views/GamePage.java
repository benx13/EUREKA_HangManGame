package com.usthb.views;

import com.usthb.controllers.GameController;
import com.usthb.models.Joueur;
import com.usthb.models.PartieJeu;
import com.usthb.models.Question;
import com.usthb.models.ThemeJeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("serial")
public class GamePage extends JPanel {

    private int level;
    private Joueur joueur;
    private ThemeJeu themeJeu;
    
    public Potence getPotence() {
		return potence;
	}

	private PartieJeu partieJeu;
    private Potence potence;

    private JLabel lblNewLabel_2;
    

    private ArrayList<String> listeCar = new ArrayList<>(Arrays.asList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "A", "Z", "E", "R", "T", "Y", "U", "I", "O", "P",
            "Q", "S", "D", "F", "G", "H", "J", "K", "L","M",
            "W", "X", "C", "V", "B", "N", "É", "È"));


    /**
     * @wbp.parser.constructor
     */
    public GamePage(Joueur joueur, ThemeJeu themeJeu, int level) {
        this.level = level;
        this.joueur = joueur;
        this.themeJeu = themeJeu;
        this.partieJeu = new PartieJeu(themeJeu, joueur, level);
        this.potence = new Potence(new Dimension(300, 300));
        this.initialize();
    }
    
    public GamePage(Joueur joueur, ThemeJeu themeJeu, int level, Question question) {
        this.level = level;
        this.joueur = joueur;
        this.themeJeu = themeJeu;
        this.partieJeu = new PartieJeu(themeJeu, joueur, level, question);
        this.potence = new Potence(new Dimension(300, 300));
        this.initialize();
    }

    private void initialize() {
        setLayout(null);
        printCurrentResponse();
        printPotence();
        printKeyboard();
        printQuestion();
        printOtherComponents();
    }

    public void updatePotenceResponse(){
    	
    	this.potence.repaint();
        this.lblNewLabel_2.setText("Réponse actuelle: " + partieJeu.getCurrentResponse().toString());
    	
    }
    
    private void printQuestion() {
        JLabel lblNewLabel_3 = new JLabel("<html>Question:<br/>" +  reformat(partieJeu.getCurrentQuestion().getLabel()) + "</html>");
        lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel_3.setBounds(30, 82, 229, 382);
        add(lblNewLabel_3);
    }

    private void printPotence() {
        
        this.potence.setBounds(267,28,470,400);
        add(this.potence);
    }

    private void printCurrentResponse() {
    	
    	this.lblNewLabel_2 = new JLabel("Réponse actuelle: " + partieJeu.getCurrentResponse().toString());
    	this.lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
    	this.lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    	this.lblNewLabel_2.setBounds(267, 31, 470, 27);
        add(this.lblNewLabel_2);
    	
    }
    
    private void printOtherComponents() {


        JLabel lblPlayer = new JLabel("Joueur: " + joueur.getNom());
        lblPlayer.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
        lblPlayer.setBounds(30, 12, 240, 28);
        add(lblPlayer);

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


        JLabel lblSelectATheme = new JLabel("Selectionner une lettre:");
        lblSelectATheme.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectATheme.setBounds(300, 250, 200, 50);

        JButton btnLogout = new JButton("Rejouer");
        btnLogout.setBounds(817, 439, 139, 25);
        btnLogout.addActionListener(e -> MainFrame.switchView(new GamePage(joueur, themeJeu, level, partieJeu.getCurrentQuestion())));
        add(btnLogout);
        
        JButton retourButton = new JButton("Retour");
        retourButton.setBounds(817, 405, 139, 23);
        retourButton.addActionListener(e -> MainFrame.switchView(new LevelPage(joueur, themeJeu)));
        add(retourButton);
    }


    private String reformat(String string) {
    	/*
    	 * Cette méthode permet d'ajouter des sauts de lignes à des endroits précis
    	 * Pour reformater une chaine de caractère afin qu'a l'affichage elle soit adaptée 
    	 * à l'espace qui lui est octroyé
    	 * 
    	 */
    	
    	int nbCarLine = 0; 
    	
        var stringSplit =  string.split(" ");
        
        StringBuilder str = new StringBuilder();
        
        for (int i=0; i<stringSplit.length; i++) {
        	
            str.append(stringSplit[i]).append(" ");
            nbCarLine += stringSplit[i].length()+1;
            
            if (i < stringSplit.length - 1)
            	
            	if(nbCarLine + stringSplit[i+1].length() > 30) {
	            	str.append("<br/>");
	            	nbCarLine = 0;
            	}
            
        }
        
        return str.toString();
    }



    private void printKeyboard() {
    	/*
    	 * Affichage du clavier
    	 */
        int count = -1;
        int basex = 230;
        AtomicInteger gridx = new AtomicInteger(basex);
        AtomicInteger gridy = new AtomicInteger(350);
        for(int i=0; i<4; i++) {
            do {
                count++;
                JButton button = new JButton(listeCar.get(count));
                button.setBounds(gridx.get(), gridy.get(), 50, 40);
                button.addActionListener(e -> buttonClicked(button));
                /*
                 * La partie ci-dessous permet l'entrée des lettres et chiffres directement par le clavier
                 * En utilisant un bouton virtuelle
                 */
                InputMap im = button.getInputMap(WHEN_IN_FOCUSED_WINDOW);
                ActionMap am = button.getActionMap();
                im.put(KeyStroke.getKeyStroke(button.getText()), "keyboardButton");
                am.put("keyboardButton", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.doClick();
                    }
                });
                
                gridx.set(gridx.get() + 50);
                add(button);

            } while (!listeCar.get(count).equals("0") &&
                    !listeCar.get(count).equals("P") &&
                    !listeCar.get(count).equals("M") &&
                    !listeCar.get(count).equals("È"));
            gridx.set(basex + 25);
            basex += 25;
            gridy.set(gridy.get() + 40);

        }
    }

    private void buttonClicked(JButton button) {
        
        button.setEnabled(false);
        
        GameController.getInstance().setBuffer(button.getText());
        GameController.getInstance().verifications(this, partieJeu);
        

    }
}