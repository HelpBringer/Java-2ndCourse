import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class WindowMenu extends JMenuBar {
    private static final String PNG = "PNG", JPG = "JPG";
    private static final String prefixPNG = "png", prefixJPG = "jpg";

    private final MainWindow window;

    WindowMenu(MainWindow window) {
        super();
        this.window = window;
        this.add(createFileMenu());
    }

    private JMenu createFileMenu() {
        Font font = new Font(Font.DIALOG, Font.BOLD, 15);

        JMenu fileJMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open...");
        JMenuItem saveAsMenuItem = new JMenuItem("Save as...");

        fileJMenu.setFont(font);
        openMenuItem.setFont(font);
        saveAsMenuItem.setFont(font);

        fileJMenu.add(openMenuItem);
        fileJMenu.add(saveAsMenuItem);

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = createFileChooser();
                int result = fileChooser.showOpenDialog(window);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                        window.setOpenImage(ImageIO.read(selectedFile));
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(window, "File could not be opened",
                                "IOException", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = createFileChooser();
                int userSelection = fileChooser.showSaveDialog(window);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();

                    String fileFormat = fileChooser.getFileFilter().getDescription();
                    String fileName = fileToSave.toString();

                    switch (fileFormat) {
                        case PNG:
                            if (!fileName.endsWith("." + prefixPNG)) {
                                fileName = fileName + "." + prefixPNG;
                            }
                            break;
                        case JPG:
                            if (!fileName.endsWith("." + prefixJPG)) {
                                fileName = fileName + "." + prefixJPG;
                            }
                    }

                    try {
                        ImageIO.write(window.getSaveImage(), fileFormat, new File(fileName));
                        System.out.println(fileName);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(window, "File could not be saved",
                                "IOException", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        return fileJMenu;
    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filterPNG = new FileNameExtensionFilter(PNG, prefixPNG);
        FileNameExtensionFilter filterJPG = new FileNameExtensionFilter(JPG, prefixJPG);

        // remove type "All Files"
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());

        // add types PNG and JPG
        fileChooser.addChoosableFileFilter(filterPNG);
        fileChooser.addChoosableFileFilter(filterJPG);

        fileChooser.setFileFilter(filterPNG);

        return fileChooser;
    }
}
