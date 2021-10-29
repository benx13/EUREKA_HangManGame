package com.usthb.views;

import javax.swing.*;

import com.usthb.controllers.LoginController;
import com.usthb.models.Joueur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;


@SuppressWarnings("serial")
public class LoginPage extends JPanel {

	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JPasswordField passwordField_2;
	private Choice date;
	private Choice month;
	private Choice year;
	

	public LoginPage() {
		initialize();
	}

	private void initialize() {
		setLayout(null);
		printLoginComponents();
		printOtherComponents();
		printSeperators();
		printSignupComponents();
	}
	
	

	private void printOtherComponents() {
		JButton btnExit = new JButton("Quitter");
		btnExit.setBounds(820, 441, 135, 25);
		btnExit.addActionListener(e -> MainFrame.destroyFrame());
		add(btnExit);

		JLabel lblNewLabel_2 = new JLabel("Bienvenu au jeu du pendu : EUREKA ! ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(172, 12, 660, 27);
		add(lblNewLabel_2);
	}

	private void printSeperators() {

		JSeparator separator = new JSeparator();
		separator.setBounds(47, 318, 415, 17);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(47, 51, 923, 2);
		add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(500, 82, 2, 345);
		add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(47, 145, 415, 17);
		add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(544, 145, 426, 17);
		add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(544, 358, 426, 14);
		add(separator_5);
	}

	private void printSignupComponents() {
		JLabel lblNewMemberSign = new JLabel("Nouveau membre ? Inscrivez vous ");
		lblNewMemberSign.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewMemberSign.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		lblNewMemberSign.setBounds(544, 78, 411, 71);
		add(lblNewMemberSign);



		JLabel label = new JLabel("Utilisateur ");
		label.setBounds(558, 168, 81, 33);
		add(label);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(679, 174, 276, 26);
		add(textField_1);

		JLabel lblFirstName = new JLabel("Prénom ");
		lblFirstName.setBounds(558, 206, 81, 33);
		add(lblFirstName);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(679, 212, 276, 26);
		add(textField_2);

		JLabel lblPassword_1 = new JLabel("Mot de passe ");
		lblPassword_1.setBounds(558, 250, 117, 33);
		add(lblPassword_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(679, 250, 276, 26);
		add(passwordField_1);

		JLabel label_3 = new JLabel("Mot de passe ");
		label_3.setBounds(558, 286, 117, 33);
		add(label_3);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(679, 288, 276, 26);
		add(passwordField_2);


		JLabel dob = new JLabel("Date naissance ");
		dob.setFont(new Font("Dialog", Font.BOLD, 12));
		dob.setSize(138, 20);
		dob.setLocation(558, 326);
		add(dob);

		
		String[] dates = {"1", "2", "3", "4", "5",
				"6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30",
				"31"};
		date = new Choice();
		date.setFont(new Font("Arial", Font.PLAIN, 15));
		date.setSize(75, 24);
		date.setLocation(702, 328);
		for (String s : dates) {
			
			date.add(s);
			
		}
		date.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent ie)
	        {
	        }
	    });
		add(date);

		String[] months = {"Jan", "Fev", "Mar", "Avr",
				"Mai", "Jun", "Jul", "Aou",
				"Sep", "Oct", "Nov", "Dec"};
		month = new Choice();
		month.setFont(new Font("Arial", Font.PLAIN, 15));
		month.setSize(75, 24);
		month.setLocation(783, 328);
		for (String s : months) {
			
			month.add(s);
			
		}
		date.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent ie)
	        {
	        	//TODO
	        }
	    });
		add(month);

		String[] years = {"1960", "1961", "1962", 
				"1963", "1964", "1965", "1966", 
				"1967", "1968", "1969", "1970", 
				"1971", "1972", "1973", "1974", 
				"1975", "1976", "1977", "1978", 
				"1979", "1980", "1981", "1982",
				"1983", "1984", "1985", "1986",
				"1987", "1988", "1989", "1990",
				"1991", "1992", "1993", "1994", 
				"1995", "1996", "1997", "1998",
				"1999", "2000", "2001", "2002",
				"2003", "2004", "2005", "2006",
				"2007", "2008", "2009", "2010",
				"2011", "2012", "2013", "2014",
				"2015", "2016", "2017", "2018",
				"2019"};
		year = new Choice();
		year.setFont(new Font("Arial", Font.PLAIN, 15));
		year.setSize(91, 24);
		year.setLocation(864, 328);
		for (String s : years) {
			
			year.add(s);
			
		}
		date.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent ie)
	        {
	        }
	    });
		add(year);


		JButton btnSignUp = new JButton("Inscription");
		btnSignUp.addActionListener(this::signUp);
		btnSignUp.setBounds(589, 378, 135, 25);
		add(btnSignUp);

		JButton button_1 = new JButton("Réinitialiser");
		button_1.addActionListener(this::resetSignUp);
		button_1.setBounds(757, 378, 145, 25);
		add(button_1);
	}

	private void printLoginComponents() {
		JLabel lblNewLabel = new JLabel("Déja membre ? Connectez vous");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 78, 392, 71);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Utilisateur");
		lblNewLabel_1.setBounds(47, 185, 117, 33);
		add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(159, 189, 276, 26);
		add(textField);
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Mot de passe ");
		lblPassword.setBounds(47, 250, 117, 33);
		add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(159, 254, 276, 26);
		add(passwordField);

		JButton btnNewButton = new JButton("Connection");
		btnNewButton.addActionListener(this::validateUser);
		btnNewButton.setBounds(80, 347, 135, 25);
		add(btnNewButton);

		JButton btnReset = new JButton("Réinitialiser");
		btnReset.addActionListener(this::resetLogin);
		btnReset.setBounds(277, 347, 158, 25);
		add(btnReset);
	}


	private void resetLogin(ActionEvent actionEvent) {
		passwordField.setText(null);
		textField.setText(null);
	}




	private void resetSignUp(ActionEvent actionEvent) {
		textField_1.setText(null);
		textField_2.setText(null);
		passwordField_1.setText(null);
		passwordField_2.setText(null);
	}





	public void validateUser(ActionEvent event) {
		/*
		 * Verification de la validité du joueur
		 */
		
		String username = this.textField.getText();
		String password = String.valueOf(this.passwordField.getPassword());
			Joueur joueur = LoginController.getInstance().login(username, password);
			if (joueur == null)
				MessageDialog.incorrectPassWord(this);
			else {
				MessageDialog.succesfullLogin(this, username);
				/*
				 * Création et affichage de la page d'accueil 
				 */
				MainFrame.switchView(new HomePage(joueur));
			}

	}





	
	private void signUp(ActionEvent event) {
		/*
		 * L'inscription du joueur
		 */
		String name = this.textField_1.getText();
		String firstName = this.textField_2.getText();
		String password = String.valueOf(this.passwordField_1.getPassword());
		
		LocalDate dateN = LocalDate.of(Integer.valueOf(year.getSelectedItem()),  month.getSelectedIndex()+1, Integer.valueOf(date.getSelectedItem()));

		try {
			if (LoginController.getInstance().validateFields(name, firstName, password)) {
				MessageDialog.emptyFields(this);
			}
			else if (LoginController.getInstance().searchJoueur(name)) {
				
				MessageDialog.userAlreadyExists(this);
				
			}
			else if (LoginController.getInstance().checkPasswords(password, String.copyValueOf(this.passwordField_2.getPassword()))) {
				MessageDialog.succesfullSignup(this);
				LoginController.getInstance().subscribe(name, firstName, password, dateN);
				
			} else {
				MessageDialog.passWordsDoesntMatch(this);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
