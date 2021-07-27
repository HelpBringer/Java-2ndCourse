package Task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {
    private Timer timer;
    private int width, height;
    private int delay=1000;
    private int angle;

    MyPanel() {
        angle = 0;
        width = 350;
        height = 350;
        setPreferredSize(new Dimension(width + 10, height + 10));
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                angle +=6;
                if (angle >= 360) {
                    angle = 0;
                }
                repaint();
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, width+10, height+10);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));


        double radAngle = Math.toRadians(angle);
        int x2 = 5+width / 2 - (int) ((width / 2) * Math.sin(-radAngle));
        int y2 = 5+width / 2 - (int) ((width / 2) * Math.cos(radAngle));

        g2.drawLine(5 + width / 2, 5 + height / 2, x2, y2);
        g2.drawOval(5, 5, width, height);
    }


}