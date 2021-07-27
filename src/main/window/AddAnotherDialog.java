package main.window;

import main.student.Student;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;

public class AddAnotherDialog extends JDialog{
    private static final Dimension FIELD_SIZE = new Dimension(150, 40);
    private static final String XML = "XML";
    private static final String PREFIX_XML = "xml";
    private static final String HTML = "HTML";
    private static final String PREFIX_HTML = "html";
    Source XSLT = new StreamSource(new File("src/resources/convert.xsl"));
    //Source xml = new StreamSource(new File("menu_input.xml"));

    public AddAnotherDialog(MainWindow window, String title, boolean modal, Font font) {
        super(window, title, modal);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(3, 0, 10, 10));
        JLabel label = new JLabel("Input");
        label.setFont(font);


        JTextField textField =  new JTextField(25);
        textField.setPreferredSize(FIELD_SIZE);
        textField.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filterXML = new FileNameExtensionFilter(XML, PREFIX_XML);
            fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
            fileChooser.addChoosableFileFilter(filterXML);
            fileChooser.setFileFilter(filterXML);

            int result = fileChooser.showOpenDialog(window);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                textField.setText(selectedFile.toString());
            }

        });

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filterHTML = new FileNameExtensionFilter(HTML, PREFIX_HTML);
            fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
            fileChooser.addChoosableFileFilter(filterHTML);
            fileChooser.setFileFilter(filterHTML);


            int userSelection = fileChooser.showSaveDialog(window);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                String fileFormat = fileChooser.getFileFilter().getDescription();
                String fileName = fileToSave.toString();

                    if (HTML.equals(fileFormat)) {
                        if (!fileName.endsWith("." + PREFIX_HTML)) {
                            fileName = fileName + "." + PREFIX_HTML;
                        }
                        //FileWriter fileWriter1 = new FileWriter(fileName);
                        XSLT=  new StreamSource(AddAnotherDialog.class.getClassLoader().getResourceAsStream("resources/convert.xsl"));
                        Student.convertXMLToHTML(/*xml*/ new StreamSource(new File(textField.getText())),XSLT, fileName);
                        //fileWriter1.close();
                    }

            }
        });
        this.add(okButton);
        this.add(label);
        this.add(textField);
        this.pack();
    }
}
