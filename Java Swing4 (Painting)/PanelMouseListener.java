import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMouseListener extends MouseAdapter {

    private final GraphicPanel panel;
    private Point startDrawPoint;

    PanelMouseListener(GraphicPanel panel) {
        this.panel = panel;
        startDrawPoint = new Point(0, 0);
    }

    @Override
    public void mousePressed( MouseEvent e){
        super.mousePressed(e);
        startDrawPoint = e.getPoint();
    }

    @Override
    public void mouseDragged( MouseEvent e) {
        super.mouseDragged(e);

        Point endDrawPoint = e.getPoint();

        Graphics g = panel.getBufferedImage().getGraphics();
        g.setColor(panel.getCurrentColor());
        g.drawLine(startDrawPoint.x, startDrawPoint.y, endDrawPoint.x, endDrawPoint.y);

        Graphics g1 = panel.getGraphics();
        g1.setColor(panel.getCurrentColor());
        g1.drawLine(startDrawPoint.x, startDrawPoint.y, endDrawPoint.x, endDrawPoint.y);

        startDrawPoint = endDrawPoint;
    }
}
