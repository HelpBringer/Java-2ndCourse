package main.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    private static  Dimension WINDOW_SIZE = new Dimension(640, 480);
    private static  Dimension BUTTON_SIZE = new Dimension(100, 100);
    private JButton jButton;
    private JLabel statusLabel;


    public MainWindow(String title) {
        super(title);
        this.setSize(WINDOW_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainMouseListener mouseListener = new MainMouseListener(this);

        createButton();

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(null);
        centralPanel.setSize(WINDOW_SIZE);
        centralPanel.add(jButton);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());

        statusLabel = new JLabel("0, 0");
        statusPanel.add(statusLabel);

      add(statusPanel, BorderLayout.SOUTH);
      add(centralPanel, BorderLayout.CENTER);

        centralPanel.addMouseListener(mouseListener);
        centralPanel.addMouseMotionListener(mouseListener);

        this.setVisible(true);
    }

    public void buttonMove(int x, int y) {
        jButton.setLocation(x, y);
    }

    public void updateText(int x, int y) {
        statusLabel.setText(String.format("%d, %d", x, y));
    }

    private void createButton() {
        jButton = new JButton("start");
        jButton.setSize(BUTTON_SIZE);

        MainWindow buttonSource = this;
        jButton.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged( MouseEvent e) {
                if (e.isControlDown()) {
                    buttonSource.buttonMove(jButton.getX() + e.getX(), jButton.getY() + e.getY());
                }
                buttonSource.updateText(e.getX() + jButton.getX(), e.getY() + jButton.getY());
            }

            @Override
            public void mouseMoved( MouseEvent e) {
                updateText(e.getX() + jButton.getX(), e.getY() + jButton.getY());
            }
        });

        jButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                String buttonText= jButton.getText();
                if(e.getKeyChar()==KeyEvent.VK_BACK_SPACE){
                    if(!buttonText.equals("")){
                        jButton.setText(buttonText.substring(0,buttonText.length()-1));
                    }
                }
                else{
                    jButton.setText(buttonText+e.getKeyChar());
                }

            }

        });
    }

}
