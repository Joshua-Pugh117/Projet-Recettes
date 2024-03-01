package Presentation;

import javax.swing.*;
import java.awt.*;
import Repositories.*;

public class RecepiePrincipale{
    private static void createAndShowGUI() throws Exception {
        RecepieRepo recepieRepo = new RecepieRepo("src\\recipes.xml");

        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a text box in the middle
        JTextField textField = new JTextField();
        frame.add(textField, BorderLayout.CENTER);

        // Create two buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable()  {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
