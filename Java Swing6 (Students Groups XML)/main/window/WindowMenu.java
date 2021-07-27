package main.window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;


public class WindowMenu extends JMenuBar {
    private static final String TXT = "TXT";
    private static final String PREFIX_TXT = "txt";
    private static final String XML = "XML";
    private static final String PREFIX_XML="xml";
    private static final Font FONT = new Font(Font.DIALOG, Font.BOLD, 15);

    private final MainWindow window;

    WindowMenu(MainWindow window) {
        super();
        this.window = window;
        this.add(createFileMenu());
        this.add(createChangeMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileJMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open...");
        JMenuItem openMenuXml = new JMenuItem("Open XML...");

        fileJMenu.setFont(FONT);
        openMenuItem.setFont(FONT);
        openMenuXml.setFont(FONT);
        fileJMenu.add(openMenuItem);
        fileJMenu.add(openMenuXml);
        openMenuItem.addActionListener(e -> openFileDialog());
        openMenuXml.addActionListener(e-> openXmlDialog());
        return fileJMenu;
    }

    private void openFileDialog() {
        JFileChooser fileChooser = createFileChooserT();
        int result = fileChooser.showOpenDialog(window);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            window.displayOpenFile(selectedFile.toString());
        }
    }

    private void openXmlDialog() {
        JFileChooser fileChooser = createFileChooserX();
        int result = fileChooser.showOpenDialog(window);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            window.displayOpenXml(selectedFile.toString());
        }
    }

    private JMenu createChangeMenu(){
        JMenu changeMenu = new JMenu("Change");
        JMenuItem addMenuItem = new JMenuItem("Add");

        changeMenu.setFont(FONT);
        addMenuItem.setFont(FONT);
        changeMenu.add(addMenuItem);

        addMenuItem.addActionListener(e -> {
            AddDialog dialog = new AddDialog(window, "Add element", true, FONT);
            dialog.setVisible(true);
        });

        return changeMenu;
    }

    private JFileChooser createFileChooserT() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filterPNG = new FileNameExtensionFilter(TXT, PREFIX_TXT);

        // remove type "All Files"
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());

        // add types PNG and JPG
        fileChooser.addChoosableFileFilter(filterPNG);
        fileChooser.setFileFilter(filterPNG);

        return fileChooser;
    }
    private JFileChooser createFileChooserX() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filterPNG = new FileNameExtensionFilter(XML, PREFIX_XML);

        // remove type "All Files"
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());

        // add types PNG and JPG
        fileChooser.addChoosableFileFilter(filterPNG);
        fileChooser.setFileFilter(filterPNG);

        return fileChooser;
    }
}
