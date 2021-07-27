package main.window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMouseListener extends MouseAdapter {

    private MainWindow window;

    MainMouseListener(MainWindow window) {
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        window.buttonMove(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        window.updateText(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        window.updateText(e.getX(), e.getY());
    }
}
