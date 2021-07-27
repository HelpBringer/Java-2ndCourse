package main.window2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private static Dimension WINDOW_SIZE = new Dimension(640, 480);
    private static Dimension BUTTON_SIZE = new Dimension(90, 40);
    private  int SPACE = 10;
    private int DISTANCE = 20;
    private JPanel centralPanel, answerPanel;
    private JButton jButton, movingJButton;
    private boolean isMoving;

    public MainWindow(String title) {
        super(title);
        this.setSize(WINDOW_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        Font  font1  = new Font(Font.SERIF, Font.BOLD,  30);

        JLabel questionLabel = new JLabel("Вы довольны своей работой по программированию?");
        questionLabel.setFont(font1);
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        questionPanel.add(questionLabel);

        JLabel answerLabel = new JLabel("А чего ж ты хотел?");
        answerLabel.setFont(font1);
        answerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        answerPanel.add(answerLabel);
        answerPanel.setVisible(false);

        centralPanel = new JPanel();
        centralPanel.setLayout(null);
        centralPanel.setPreferredSize(WINDOW_SIZE);

        jButton = new JButton("Нет");
        jButton.setFont(font1);
        jButton.setSize(BUTTON_SIZE);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerPanel.setVisible(true);
            }
        });

        movingJButton = new JButton("Да");
        movingJButton.setFont(font1);
        movingJButton.setSize(BUTTON_SIZE);
        movingJButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                enterAction(e);
            }
        });
        centralPanel.add(jButton);
        isMoving = false;
        centralPanel.add(movingJButton);

        container.add(questionPanel, BorderLayout.NORTH);
        container.add(centralPanel, BorderLayout.CENTER);
        container.add(answerPanel, BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                buttonLocation();
            }
        });

        this.pack();
        this.setVisible(true);
    }

    void buttonLocation() {
        Dimension newSize = centralPanel.getSize();
        int jButtonX = newSize.width / 2 - BUTTON_SIZE.width - SPACE;
        int movingJButtonX = newSize.width / 2 + SPACE;

        int x1 = jButton.getLocation().x;
        int y1 = jButton.getLocation().y;
        int x2 = movingJButton.getLocation().x;
        int y2 = movingJButton.getLocation().y;

        jButton.setLocation(jButtonX, 0);

        if (!isMoving) {
            movingJButton.setLocation(movingJButtonX, 0);
        } else {
            int dx = x1 - x2;
            int dy = y1 - y2;

            movingJButton.setLocation(jButton.getLocation().x - dx,
                    jButton.getLocation().y - dy);
        }
    }

    void enterAction(MouseEvent e) {
        int xDirection = (int) (Math.random() * 2);
        int yDirection = (int) (Math.random() * 2);
        Point current = movingJButton.getLocation();
        int mouseX = e.getX() + current.x;
        int mouseY = e.getY() + current.y;

        Point newPoint = new Point();

        Dimension panelSize = centralPanel.getSize();


        int dx = BUTTON_SIZE.width + DISTANCE;
        int dy = BUTTON_SIZE.height + DISTANCE;

        if (xDirection == 0 && mouseX - dx >= 0 || mouseX + dx > panelSize.width) {
            newPoint.x = mouseX - dx;
        } else {
            newPoint.x = mouseX + DISTANCE;
        }

        if (yDirection == 0 && mouseY - dy >= 0 || mouseY + dy > panelSize.height) {
            newPoint.y = mouseY - dy;
        } else {
            newPoint.y = mouseY + DISTANCE;
        }

        movingJButton.setLocation(newPoint);
        isMoving = true;
    }
}
