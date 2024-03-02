package Presentation;

import javax.swing.*;
import java.awt.*;
import Repositories.*;

public class RecepiePrincipale{
    private static void createAndShowGUI() throws Exception {
        RecepieRepo recepieRepo = new RecepieRepo("src\\recipes.xml");
        String title = recepieRepo.getRecepies().get(0).getTitle();


        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new GridLayout(4, 2));

        Label label = new Label("Name");
        JTextArea textArea = new JTextArea(title);

        frame.add(label);
        frame.add(textArea);

        


        // Add a submit button
        // JButton submitButton = new JButton("Submit");
        // frame.add(submitButton);
        // submitButton.addActionListener(e -> nextRecipie());

        // frame.pack();
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private static void nextRecipie() {
        System.out.println("Next Recipie");
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable()  {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
