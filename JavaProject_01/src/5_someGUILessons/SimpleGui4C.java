package someGUILessons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui4C {
    
    JFrame frame;
    JLabel label;

    int pWidth = 600;
    int pHight = 600;

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelbutton = new JButton("change label");
        labelbutton.addActionListener(new innerLabelListener());

        JButton colorButton = new JButton("Change Circle");
        colorButton.addActionListener(new innerButtonListener());

        label = new JLabel("I`am Label!");     
        MyDrawPanel1A drawpanel = new MyDrawPanel1A();

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawpanel);
        frame.getContentPane().add(BorderLayout.EAST, labelbutton);
        frame.getContentPane().add(BorderLayout.WEST, label);
        
        frame.setSize(pWidth, pHight);
        frame.setVisible(true);
    }

    class innerLabelListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            label.setText("Ouch!");
        }
    }

    class innerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            frame.repaint();
        }
    }

    public static void main(String[] args) {
        SimpleGui4C gui = new SimpleGui4C();
        gui.go();
    }
}
