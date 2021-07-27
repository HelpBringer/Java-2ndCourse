package Task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class    MyPanel2 extends JPanel {
    private BufferedImage image;
    private int width, height;
    private int angle;
    private int direction;
    private int speed;
    private int delay=100;


    MyPanel2(MyWindow parent) {
        super();
        angle = 0;
        width = 400;
        height = 400;
        setPreferredSize(new Dimension(width, height));
        speed = 1;
        direction = 1;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                width = getWidth();
                height = getHeight();
                speed = parent.getSpeed();
                direction = parent.getDirection();
                int size = Math.min(width, height)/2;

                angle += direction*speed*delay/size;
                if (angle >= 360) {
                    angle = angle - 360;
                }
                repaint();

            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, width * 2, height * 2);


        double radAngle = Math.toRadians(angle);
        if (image != null) {
            int size = Math.min(width, height);
            System.out.println(angle);
            int xImage = size / 2 - image.getWidth() / 2 - (int) ((size / 2 - image.getWidth() / 2) * Math.sin(-radAngle));
            int yImage = size / 2 - image.getHeight() / 2 - (int) ((size / 2 - image.getHeight() / 2) * Math.cos(-radAngle));
            g.drawImage(image, xImage, yImage, null);
        }
    }
}