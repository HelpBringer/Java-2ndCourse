package main.window;

import main.student.Student;
import main.validator.ValidatorXML;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class WindowMenu extends JMenuBar {
    private static final String BIN = "BIN";
    private static final String XML = "XML";
    private static final String HTML = "HTML";
    //private static  String XSD = "src/resources/student_schema.xsd";
    private static  Source XSD=new StreamSource(AddAnotherDialog.class.getClassLoader().getResourceAsStream("resources/student_schema.xsd"));;
    private static final String PREFIX_BIN = "bin";
    private static final String PREFIX_XML = "xml";
    private static final String PREFIX_HTML = "html";
    private static final Font FONT = new Font(Font.DIALOG, Font.PLAIN, 20);

    private final MainWindow window;

    WindowMenu(MainWindow window) {
        super();
        this.window = window;
        this.add(createFileMenu());
    }

    private JMenu createFileMenu() {
        JMenu fileJMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open...");
        JMenuItem saveMenuItem = new JMenuItem("Save as");
        JMenuItem SAXMenuItem = new JMenuItem("Open with SAX");
        JMenuItem validate = new JMenuItem("Check XML");

        fileJMenu.setFont(FONT);
        openMenuItem.setFont(FONT);
        fileJMenu.add(openMenuItem);
        saveMenuItem.setFont(FONT);
        fileJMenu.add(saveMenuItem);
        fileJMenu.addSeparator();
        SAXMenuItem.setFont(FONT);
        fileJMenu.add(SAXMenuItem);
        fileJMenu.addSeparator();
        validate.setFont(FONT);
        fileJMenu.add(validate);


        openMenuItem.addActionListener(e -> openFileDialog());
        saveMenuItem.addActionListener(e -> saveFileDialog());
        SAXMenuItem.addActionListener(e -> openFileWithSAX());
        validate.addActionListener(e -> checkXML());
        return fileJMenu;
    }

    private void checkXML(){
        JFileChooser fileChooser = createFileChooser();
        int result = fileChooser.showOpenDialog(window);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

           //Source xsd= new StreamSource(AddAnotherDialog.class.getClassLoader().getResourceAsStream("resources/convert.xsl"));
            XSD=new StreamSource(AddAnotherDialog.class.getClassLoader().getResourceAsStream("resources/student_schema.xsd"));;

            if(!ValidatorXML.validateXMLSchema(XSD, selectedFile.getPath())){
                JOptionPane.showMessageDialog(window, "Something is wrong with that file ",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void openFileWithSAX() {
        JFileChooser fileChooser = createFileChooserSAX();
        int result = fileChooser.showOpenDialog(window);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            window.showSAXResult(selectedFile.toString());
        }
    }

    private void openFileDialog() {
        JFileChooser fileChooser = createFileChooser();
        int result = fileChooser.showOpenDialog(window);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            window.displayOpenFile(selectedFile.toString());
        }
    }

    private void saveFileDialog() {
        JFileChooser fileChooser = createFileChooser();
        int userSelection = fileChooser.showSaveDialog(window);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            String fileFormat = fileChooser.getFileFilter().getDescription();
            String fileName = fileToSave.toString();

            try {
                switch (fileFormat) {
                    case XML:
                        if (!fileName.endsWith("." + PREFIX_XML)) {
                            fileName = fileName + "." + PREFIX_XML;
                        }
                        Student.writeSorted(window.getCoffeeList(), fileName);
                        break;

                    case BIN:
                        if (!fileName.endsWith("." + PREFIX_BIN)) {
                            fileName = fileName + "." + PREFIX_BIN;
                        }

                        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                            out.writeObject(window.getCoffeeList());
                        }

                        break;
                }
            }  catch (TransformerException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            catch (IOException exception) {
                JOptionPane.showMessageDialog(window, "File could not be saved",
                        "IOException", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filterBIN = new FileNameExtensionFilter(BIN, PREFIX_BIN);
        FileNameExtensionFilter filterXML = new FileNameExtensionFilter(XML, PREFIX_XML);

        // remove type "All Files"
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());

        fileChooser.addChoosableFileFilter(filterBIN);
        fileChooser.addChoosableFileFilter(filterXML);
        fileChooser.setFileFilter(filterXML);

        return fileChooser;
    }

    private JFileChooser createFileChooserSAX() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filterXML = new FileNameExtensionFilter(XML, PREFIX_XML);

        // remove type "All Files"
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());

        fileChooser.addChoosableFileFilter(filterXML);
        fileChooser.setFileFilter(filterXML);

        return fileChooser;
    }



}