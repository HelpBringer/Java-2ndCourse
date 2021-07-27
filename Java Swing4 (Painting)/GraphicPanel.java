import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicPanel extends JPanel {

    private BufferedImage bufferedImage;
    //
    private Color currentColor = Color.WHITE;

    public GraphicPanel(Dimension screenSize) {
        super(null);
        bufferedImage = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
        this.setPreferredSize(screenSize);

        PanelMouseListener mouseListener = new PanelMouseListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        redrawPanel();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public void paintComponent( Graphics g) {
        g.drawImage(bufferedImage, 0, 0, null);
    }

    public void redrawPanel() {
        Dimension newSize = this.getSize();
        Dimension imageCurrentSize = new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());

        Image img = bufferedImage.getScaledInstance(imageCurrentSize.width, imageCurrentSize.height,
                Image.SCALE_DEFAULT);
        bufferedImage = new BufferedImage(newSize.width, newSize.height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        repaint();
    }
}
