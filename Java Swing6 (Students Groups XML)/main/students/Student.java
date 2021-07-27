package main.students;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Student {

    private String name;
    private int id;
    private int course;
    private int group;

    public Student(String name, int id, int course, int group) {
        this.name = name;
        this.id = id;
        this.course = course;
        this.group = group;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj!=null) {
            Student stud = (Student) obj;
            if (this.getId() == stud.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + getId();
            return result;
        }
    public static ArrayList<Student> readFromXml(String filePath) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Student> resultList = new ArrayList<>();
        // Создается построитель документа сразу из фабрики
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        Document document = documentBuilder.parse(filePath);
        NodeList nList = document.getDocumentElement().getElementsByTagName("student");
        for(int i=0; i<nList.getLength();i++){
            Node node=nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) node;

                resultList.add(new Student(eElement.getElementsByTagName("name").item(0).getTextContent(), Integer.parseInt(eElement.getAttribute("id")),
                        Integer.parseInt(eElement.getElementsByTagName("course").item(0).getTextContent()),
                        Integer.parseInt(eElement.getElementsByTagName("group").item(0).getTextContent())));
            }
        }

        return resultList;
    }
    public static ArrayList<Student> readFromFile(String filePath) throws IOException {

        ArrayList<String> lines = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Student> resultList = new ArrayList<>();

        for (String line : lines) {
            ArrayList<String> words = new ArrayList<>(Arrays.asList(line.split(", ")));
            if (words.size() != 4) {
                JOptionPane.showMessageDialog(null, "Problem with input data","IOException", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            String name = words.get(0);
            int id = Integer.parseInt(words.get(1));
            int course = Integer.parseInt(words.get(2));
            int group = Integer.parseInt(words.get(3));
            resultList.add(new Student(name, id, course, group));
        }
        return resultList;
    }
    public String[] toStringArray() {
        return new String[]{name, Integer.toString(id), Integer.toString(course) , Integer.toString(group)};
    }
    @Override
    public String toString() {
        return name+", "+ id + ", "+ course + ", "+ group;
    }
    public String toXmlString(){
        return "\t<student id=\""+this.getId()+"\">\n\t\t<name>"+this.getName()+"</name>\n" +
                "\t\t<course>"+this.getCourse()+"</course>\n"+"\t\t<group>"+this.getGroup()+"</group>\n"+"\t</student>\n";
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }

}
