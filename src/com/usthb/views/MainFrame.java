package com.usthb.views;

import javax.swing.*;

import com.usthb.controllers.LoginController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {

    public static JFrame frame = new JFrame();


    public static void switchView(JPanel view) {
        MainFrame.frame.getContentPane().removeAll();
        MainFrame.frame.getContentPane().add(view);
        MainFrame.frame.revalidate();
        MainFrame.frame.repaint();
    }

    /**
     * @wbp.parser.entryPoint
     */
    
    public static void createFrame() {
        frame.setTitle("EUREKA");
        frame.setBounds(200, 100, 1000, 570);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                JFrame frame = (JFrame)e.getSource();

                Object[] options = {"Oui",
                "Non"};
                
                int result = JOptionPane.showOptionDialog(
                        frame,
                        "Etes-vous sur de vouloir quitter ?",
                        "Quitter",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
        		        null,     //do not use a custom Icon
        		        options,  //the titles of buttons
        		        options[0]); //default button title);

                if (result == JOptionPane.YES_OPTION)
                	MainFrame.destroyFrame();

            }
        });
        frame.setResizable(false);
        frame.setVisible(true);
        MainFrame.switchView(new LoginPage());
    }

    public static void destroyFrame(){
        LoginController.getInstance().Save();
        frame.setVisible(false);
        System.exit(0);
    }
}



