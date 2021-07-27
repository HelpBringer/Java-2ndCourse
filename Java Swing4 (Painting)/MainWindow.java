import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {

    private static final Dimension WINDOW_SIZE = new Dimension(800, 800);
    private static final Dimension PANEL_SIZE = new Dimension(WINDOW_SIZE.width, WINDOW_SIZE.height / 18);
    private final GraphicPanel graphicPanel;

    public void setOpenImage(BufferedImage openImage) {
        graphicPanel.setBufferedImage(openImage);
    }

    public BufferedImage getSaveImage() {
        return graphicPanel.getBufferedImage();
    }

    public MainWindow(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // get screen size and use in constructor
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // create central panel with scrolling
        graphicPanel = new GraphicPanel(screenSize);
        JScrollPane scrollPane = new JScrollPane(graphicPanel);
        this.add(scrollPane);

        Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 20);

        // create panel with buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(PANEL_SIZE);

        Dimension buttonSize = new Dimension(PANEL_SIZE.width / 5, PANEL_SIZE.height - 10);

        JToggleButton whiteButton = new JToggleButton("white");
        whiteButton.setPreferredSize(buttonSize);
        whiteButton.setBackground(Color.WHITE);
        whiteButton.setFont(font);
        whiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphicPanel.setCurrentColor(Color.WHITE);
            }
        });

        JToggleButton blueButton = new JToggleButton("blue");
        blueButton.setPreferredSize(buttonSize);
        blueButton.setBackground(Color.BLUE);
        blueButton.setFont(font);
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphicPanel.setCurrentColor(Color.BLUE);
            }
        });

        JToggleButton greenButton = new JToggleButton("green");
        greenButton.setPreferredSize(buttonSize);
        greenButton.setBackground(Color.GREEN);
        greenButton.setFont(font);
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphicPanel.setCurrentColor(Color.GREEN);
            }
        });

        ButtonGroup colorButtons = new ButtonGroup();
        colorButtons.add(whiteButton);
        colorButtons.add(blueButton);
        colorButtons.add(greenButton);

        buttonPanel.add(whiteButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(greenButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                graphicPanel.redrawPanel();
            }
        });

        WindowMenu windowMenu = new WindowMenu(this);
        this.setJMenuBar(windowMenu);

        this.setSize(WINDOW_SIZE);
        this.setVisible(true);
    }

}
