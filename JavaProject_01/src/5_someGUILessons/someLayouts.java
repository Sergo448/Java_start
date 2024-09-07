package someGUILessons;

import java.awt.*;
import javax.swing.*;

public class someLayouts {

    int hightSize = 300;
    int widthSize = 300;
    
    public void go1() {
        /* FlowLayout */
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBackground(Color.DARK_GRAY);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(hightSize, widthSize);
        frame.setVisible(true);
    }
    
    public void go2() {
        /* FlowLayout */
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBackground(Color.DARK_GRAY);

        JButton button = new JButton("jbutton");

        panel.add(button);
        frame.getContentPane().add(BorderLayout.EAST, panel);

        frame.setSize(hightSize, widthSize);
        frame.setVisible(true);

    }

    public void go3() {
        /* FlowLayout */
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBackground(Color.DARK_GRAY);

        JButton button1 = new JButton("jbutton1");
        JButton button2 = new JButton("jbutton2");

        panel.add(button1);
        panel.add(button2);
        frame.getContentPane().add(BorderLayout.EAST, panel);

        frame.setSize(hightSize, widthSize);
        frame.setVisible(true);

    }

    public void go4() {
        /* BoxLayout */
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button1 = new JButton("JBUTTON1");
        JButton button2 = new JButton("jbutto2");

        panel.add(button1);
        panel.add(button2);

        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(hightSize, widthSize);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        someLayouts gui = new someLayouts();
        gui.go4();
    }    

    
}
