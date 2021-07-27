package main.window;

import main.students.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainWindow extends JFrame {
    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);
    private static final Object[] COLUMNS_HEADER = new String[]{"Name", "ID", "Course", "Group"};
    private static final Font FONT_1 = new Font(Font.DIALOG, Font.BOLD, 20);

    private ArrayList<Student> studentList;

    private JTable displayTable;
    private JTable processingTable;
    private JTextField groupToFind;

    public MainWindow(String title) {
        super(title);
        this.setSize(WINDOW_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WindowMenu windowMenu = new WindowMenu(this);
        this.setJMenuBar(windowMenu);

        studentList = null;

        this.setLayout(new GridLayout());
        setUpDisplayPanel();
        setUpProcessingPanel();

        this.setVisible(true);
    }

    public void displayOpenFile(String filePath) {

        try {
            studentList = (Student.readFromFile(filePath));
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "File could not be opened",
                    "IOException", JOptionPane.WARNING_MESSAGE);
        }

        DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
        while (model.getRowCount() > 0) {
            for (int i = 0; i < model.getRowCount(); ++i) {
                model.removeRow(i);
            }
        }

        for (Student stud : Objects.requireNonNull(studentList)) {
            ((DefaultTableModel) displayTable.getModel()).addRow(stud.toStringArray());
        }
    }

    public void displayOpenXml(String filePath) {
        try {
            studentList = (Student.readFromXml(filePath));
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "File could not be opened",
                    "IOException", JOptionPane.WARNING_MESSAGE);
        }
        catch (SAXException e ){
            JOptionPane.showMessageDialog(this, "File could not be opened",
                    "SAXException", JOptionPane.WARNING_MESSAGE);
        }
        catch (ParserConfigurationException e){
            JOptionPane.showMessageDialog(this, "File could not be opened",
                    "ParserConfigurationException", JOptionPane.WARNING_MESSAGE);
        }

        DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
        while (model.getRowCount() > 0) {
            for (int i = 0; i < model.getRowCount(); ++i) {
                model.removeRow(i);
            }
        }

        for (Student stud : Objects.requireNonNull(studentList)) {
            ((DefaultTableModel) displayTable.getModel()).addRow(stud.toStringArray());
        }

    }



    public void addElementStudent(Student student) {
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
        studentList.add(student);
        ((DefaultTableModel) displayTable.getModel()).addRow(student.toStringArray());
    }

    public void createSortList(ArrayList<Student> students, int inputNumber) {
        students.sort(Comparator.comparingInt(Student::getId));
        //if needed add .filter((student)->student.getCourse() == inputNumberCourse)
        List<Student> sortedStudents=students.stream().distinct().filter((student)->student.getGroup() == inputNumber).collect(Collectors.toList());
        DefaultTableModel model = createTableModel();
        for (Student student : sortedStudents) {
            model.addRow(student.toStringArray());
        }
        processingTable.setModel(model);

    }

    private void setUpDisplayPanel() {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayTable = new JTable();
        setUpTable(displayPanel, displayTable);
        this.add(displayPanel);
    }

    private void setUpProcessingPanel() {
        JPanel processingPanel = new JPanel(new BorderLayout());

        JPanel filterPanel = new JPanel(new GridLayout(3, 0));
        JPanel tmpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel text = new JLabel("Group:");
        text.setFont(FONT_1);
        tmpPanel.add(text);
        //if needed add another JTextField to enter course data
        Dimension textFieldSize = new Dimension(70, 20);
        groupToFind = new JTextField();
        groupToFind.setPreferredSize(textFieldSize);
        tmpPanel.add(groupToFind);
        filterPanel.add(tmpPanel);

        JButton sortButton = new JButton("sort");
        sortButton.setFont(FONT_1);
        sortButton.addActionListener(e -> updateSorted());

        filterPanel.add(sortButton);

        JButton saveButton = new JButton("save");
        saveButton.setFont(FONT_1);
        //Change here if needed
        saveButton.addActionListener(e -> writeSorted("output.xml"));

        filterPanel.add(saveButton);

        processingPanel.add(filterPanel, BorderLayout.NORTH);
        processingTable = new JTable();
        setUpTable(processingPanel, processingTable);
        this.add(processingPanel);
    }

    private void updateSorted() {
        if (studentList != null) {
            try {
                //if needed change here to parse to get course
                int inputNumber= Integer.parseInt(groupToFind.getText());
                createSortList(studentList, inputNumber);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Entered data in the wrong format or not enough data",
                        "NumberFormatException", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private void writeByHand(String path){
        StringBuffer str= new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        str.append("<students>\n");
        for(Student student : studentList){
            str.append(student.toXmlString());
        }
        str.append("</students>");
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(str.toString());
            //writer.flush();
            writer.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(this, "Entered data in the wrong format or not enough data",
                    "NumberFormatException", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void writeSorted(String path) {
        if (studentList != null) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                // создаем корневой элемент
                Element rootElement =
                        doc.createElement("students");
                // добавление корневого элемента в объект Document
                doc.appendChild(rootElement);

                for (Student student : studentList) {
                    rootElement.appendChild(getStudent(doc, student));
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);

                //doc.setXmlStandalone(true); Если хотим убрать кодировку из Xml
                //печатаем в файл
                StreamResult file = new StreamResult(new File(path));
                transformer.transform(source, file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Something gone wrong during writing",
                        "Exception", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

        // метод для создания нового узла XML-файла
        private static Node getStudent(Document doc, Student student){
            Element stud = doc.createElement("student");

            // атрибут id
            stud.setAttribute("id", Integer.toString(student.getId()));

            // элементы
            stud.appendChild(getStudentElements(doc, stud, "name", student.getName()));
            stud.appendChild(getStudentElements(doc, stud, "course", Integer.toString(student.getCourse())));
            stud.appendChild(getStudentElements(doc, stud, "group", Integer.toString(student.getGroup())));
            return stud;
        }

        // утилитный метод для создание нового узла XML-файла
        private static Node getStudentElements(Document doc, Element element, String name, String value){
            Element node = doc.createElement(name);
            node.appendChild(doc.createTextNode(value));
            return node;
        }


    private void setUpTable(JPanel parentPanel, JTable table) {
        JPanel tablePanel = new JPanel(new BorderLayout());
        table.setFont(FONT_1);
        table.setModel(createTableModel());
        tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        tablePanel.add(table, BorderLayout.CENTER);
        parentPanel.add(tablePanel, BorderLayout.CENTER);
    }

    private DefaultTableModel createTableModel() {
        return new DefaultTableModel(COLUMNS_HEADER, 0);
    }

}
