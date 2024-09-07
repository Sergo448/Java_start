package someGUILessons;

import java.awt.*;
import javax.swing.*;

public class Panel1 {
    
    int frameHeight = 300;
    int frameWidth = 300;

    public static void main(String[] args) {
        Panel1 panel1 = new Panel1();
        panel1.goFolowLayout3();
    }

    public void goFolowLayout1() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBackground(Color.darkGray);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void goFolowLayout2() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBackground(Color.darkGray);

        JButton button = new JButton("shock me");

        panel.add(button);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(frameHeight, frameWidth);
        frame.setVisible(true);
    }

    public void goFolowLayout3() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);

        JButton button = new JButton("SHOCK ME!");
        JButton button1 = new JButton("blis");

        panel.add(button);
        panel.add(button1);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(frameHeight, frameWidth);
        frame.setVisible(true);
    }
}
