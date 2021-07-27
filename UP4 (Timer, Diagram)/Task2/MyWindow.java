package Task2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyWindow extends JFrame {
    static final int MIN_SPEED = 1;
    static final int MAX_SPEED = 10;
    static final int INITIAL_SPEED = 1;

    String[] items = {
            "По часовой",
            "Против часовой"
    };
    private JSlider slider;
    private JComboBox comboBox;
    private int direction;

    MyWindow() {
        super();

        direction = 1;
        setVisible(true);
        JButton openImage = new JButton("open...");
        comboBox = new JComboBox(items);
        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(450, 450));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        slider = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, INITIAL_SPEED);
        JPanel pane = new JPanel();
        pane.setPreferredSize(new Dimension(450, 50));
        MyPanel2 panel = new MyPanel2(this);
        this.add(panel, BorderLayout.CENTER);
        pane.add(openImage);
        pane.add(slider);
        pane.add(comboBox);
        this.add(pane, BorderLayout.SOUTH);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = (String) comboBox.getSelectedItem();
                switch (item) {
                    case "По часовой":
                        direction = 1;
                        break;
                    default:
                        direction = -1;
                        break;
                }
            }
        });

        openImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    try {
                        BufferedImage img = ImageIO.read(file);
                        panel.setImage(img.getSubimage(0, 0, 50, 50));

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        pack();
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return slider.getValue();
    }

    public static void main(String args[]) {
        new MyWindow();
    }

}
