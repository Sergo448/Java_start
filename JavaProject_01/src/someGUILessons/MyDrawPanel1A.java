package someGUILessons;

import java.awt.*;
import javax.swing.*;

public class MyDrawPanel1A extends JPanel {

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int red0 = (int) (Math.random() * 255);
        int blue0 = (int) (Math.random() * 255);
        int green0 = (int) (Math.random() * 255);

        Color startColor = new Color(red0, green0, blue0);


        int red1 = (int) (Math.random() * 255);
        int green1 = (int) (Math.random() * 255);
        int blue1 = (int) (Math.random() * 255);

        Color endColor = new Color(red1, green1, blue1);

        GradientPaint gradiend = new GradientPaint(70, 70, startColor, 150, 150, endColor);
        g2d.setPaint(gradiend);
        g2d.fillOval(70, 70, 100, 100);
    }
}
