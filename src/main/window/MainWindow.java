package main.window;

import main.student.Student;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private static final Dimension WINDOW_SIZE = new Dimension(1000, 800);
    private static final Object[] COLUMNS_HEADER = new String[]{"Фамилия", "Имя", "Отчислен", "Группа", "Оценка", "Рост"};
    private static final Font FONT = new Font("TimesRoman", Font.PLAIN, 20);
    private static final String PREFIX_BIN = "bin";
    private static final String HTML = "HTML";
    private static final String PREFIX_HTML = "html";
    Source XSLT = new StreamSource(new File("../../file.xsl"));


    private JTable displayTable;
    private ArrayList<Student> studentList;

    private static DefaultTableModel model;

    public MainWindow(String title) {
        super(title);
        this.setSize(WINDOW_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WindowMenu windowMenu = new WindowMenu(this);
        this.setJMenuBar(windowMenu);

        studentList = null;

        this.setLayout(new BorderLayout());
        setUpDisplayPanel();

        setUpButtons();

        this.setVisible(true);
    }

    private void setUpDisplayPanel() {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayTable = new JTable();
        setUpTable(displayPanel, displayTable);
        this.add(displayPanel);
    }

    private void setUpTable(JPanel parentPanel, JTable table) {
        JPanel tablePanel = new JPanel(new BorderLayout());
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setFont(FONT);
        table.getTableHeader().setFont(new Font("TimesRoman", Font.PLAIN, 22));
        table.setRowHeight(30);
        table.setModel(createTableModel());
        tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        tablePanel.add(table, BorderLayout.CENTER);
        parentPanel.add(tablePanel, BorderLayout.CENTER);
    }

    public void displayOpenFile(String filePath) {
        try {
            if (filePath.endsWith(PREFIX_BIN)) {
                try (FileInputStream fin = new FileInputStream(filePath)) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                        studentList = ((ArrayList<Student>) ois.readObject());
                    } catch (ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(this, "File could not be opened",
                                "ClassNotFoundException", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                studentList = Student.readFromXMLFIle(filePath);
            }
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "File could not be opened",
                    "IOException", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException | ParserConfigurationException exception) {
            JOptionPane.showMessageDialog(this, "Check the correctness of the entered numeric data",
                    "NumberFormatException", JOptionPane.WARNING_MESSAGE);
        } catch (SAXException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while reading the xml file",
                    "SAXException", JOptionPane.WARNING_MESSAGE);
        }

        model = createTableModel();
        for (Student student : studentList) {
            model.addRow(student.toStringArray());
        }
        displayTable.setModel(model);
    }

    private DefaultTableModel createTableModel() {
        return new DefaultTableModel(COLUMNS_HEADER, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public ArrayList<Student> getCoffeeList() {
        return studentList;
    }

    void setUpButtons() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            if (displayTable.getSelectedRow() != -1) {
                int index = displayTable.getSelectedRow();
                model.removeRow(index);
                studentList.remove(index);
            }
        });
        removeButton.setPreferredSize(new Dimension(200, 40));
        removeButton.setFont(FONT);
        buttonsPanel.add(removeButton);

        MainWindow window = this;
        JButton changeButton = new JButton("Change");
        changeButton.addActionListener(e -> {
            AddDialog dialog = new AddDialog(window, "Add row in table", true, FONT);
            dialog.setVisible(true);
        });
        changeButton.setPreferredSize(new Dimension(200, 40));
        changeButton.setFont(FONT);
        buttonsPanel.add(changeButton);

        JButton htmlButton = new JButton("Html");
        htmlButton.addActionListener(e-> {

            AddAnotherDialog anotherDialog= new AddAnotherDialog(window, "HTML", true, FONT);
            anotherDialog.setVisible(true);
        });
        htmlButton.setPreferredSize(new Dimension(200, 40));
        htmlButton.setFont(FONT);
        buttonsPanel.add(htmlButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void addElementInList(Student student) {
        if (studentList == null) {
            studentList = new ArrayList<>();
            model = createTableModel();
            displayTable.setModel(model);
        }
        studentList.add(student);
        ((DefaultTableModel) displayTable.getModel()).addRow(student.toStringArray());
    }

    public void showSAXResult(String filePath) {
        try {
            Student.XMLHandler handler = Student.parseWithSAX(filePath);
            double averagePrice = handler.getAverageMark();
            double maxPrice = handler.getMaxMark();

            JOptionPane.showMessageDialog(this, "Average mark of a student is " + String.format("%.2f", averagePrice) + ".\n" +
                            "The best student has mark " + maxPrice + ".",
                    "Calculation result", JOptionPane.WARNING_MESSAGE);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

}
