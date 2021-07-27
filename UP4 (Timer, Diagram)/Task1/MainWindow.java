package Task1;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    //Task 1
    MainWindow() {

        MyPanel panel1 = new MyPanel();
        panel1.setPreferredSize(new Dimension(700, 600));
        this.add(panel1);

        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();

    }


}

