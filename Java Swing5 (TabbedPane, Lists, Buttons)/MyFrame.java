import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class MyFrame extends JFrame {

    private static Dimension WINDOW_SIZE = new Dimension(600, 800);

    public MyFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_SIZE);

        MovingLists panel1 = new MovingLists(WINDOW_SIZE);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.NORTH);
        JPanel panel2 = new ButtonsDemo();
        JPanel panel3 = new RadioButtonSomething();

        tabbedPane.addTab("panel 1", panel1);
        tabbedPane.addTab("panel 2", panel2);
        tabbedPane.addTab("panel 3", panel3);

        this.setContentPane(tabbedPane);

        JFrame window = this;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                panel1.resizeComponents(window.getSize());
            }
        });

        // maximize the window
        this.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                panel1.resizeComponents(window.getSize());
            }
        });
        this.pack();
        this.setVisible(true);

    }

}
