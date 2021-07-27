package main.student;

import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class Student implements Serializable {
    private final Boolean deducted;
    private final Integer group;
    private final String surname;
    private final String name;
    private final Double mark;
    private final Integer height;
    private static String path="output.xml";


    public Student(Boolean deducted, Integer group, String surname, String name, Double mark, Integer height) {
        this.deducted = deducted;
        this.group = group;
        this.surname = surname;
        this.name = name;
        this.mark = mark;
        this.height = height;
    }

    public static ArrayList<Student> readFromXMLFIle(String filePath) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Student> resultList = new ArrayList<>();

        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);

        NodeList nodeList = document.getElementsByTagName("student");
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String tmp = element.getAttribute("deducted");
                Boolean deducted = !tmp.equals("not");
                Integer group = Integer.parseInt(element.getAttribute("group"));
                String surname = element.getElementsByTagName("surname").item(0).getTextContent();
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                Double mark = Double.parseDouble(element.getElementsByTagName("mark").item(0).getTextContent());
                Integer height = Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent());

                resultList.add(new Student(deducted, group, surname, name, mark, height));
            }
        }
        return resultList;
    }

    @Override
    public String toString() {
        String tmp = deducted ? "+" : "-";
        return surname + " " + name + " " + tmp + " " + Integer.toString(group) + " " + Double.toString(mark) + " " + height;
    }

    public static String collectionToString(ArrayList<Student> studentArrayList) {
        StringBuilder result = new StringBuilder();

        for (Student student : studentArrayList) {
            result.append(student.toString()).append("\n");
        }

        return result.toString();
    }

    public String[] toStringArray() {
        return new String[]{surname, name, deducted ? "+" : "-", Integer.toString(group), Double.toString(mark), Integer.toString(height) + " cm"};
    }

    public String toXML() {
        String tmp = (deducted) ? "yes" : "not";
        return " <student deducted=\"" + tmp + "\" group=\"" + group.toString() + "\">\n" +
                "        <surname>" + surname + "</surname>\n" +
                "        <name>" + name + "</name>\n" +
                "        <mark>" + mark.toString() + "</mark>\n" +
                "        <height>" + height.toString() + "</height>\n" +
                "    </student>\n";
    }

    static public String collectionToXML(ArrayList<Student> studentArrayList) {
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        result.append("\t<students_list>\n");
        for (Student student : studentArrayList) {
            result.append(student.toXML());
        }
        result.append("\t</students_list>\n");
        return result.toString();
    }

    static public void writeSorted(ArrayList<Student> studentArrayList, String fileName) throws TransformerException, ParserConfigurationException {
        if (studentArrayList != null) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;

                builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                // создаем корневой элемент
                Element rootElement =
                        doc.createElement("students_list");
                // добавление корневого элемента в объект Document
                doc.appendChild(rootElement);

                for (Student student : studentArrayList) {
                    rootElement.appendChild(getCoffee(doc, student));
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);

                //doc.setXmlStandalone(true); Если хотим убрать кодировку из Xml
                //печатаем в файл
                StreamResult file = new StreamResult(new File(fileName));
                transformer.transform(source, file);

        }
    }

    // метод для создания нового узла XML-файла
    private static Node getCoffee(Document doc, Student student){
        Element stud = doc.createElement("student");
        boolean flag=false;
        // атрибут id
        if(student.deducted){
            stud.setAttribute("deducted", "yes");
        }
        else {
            stud.setAttribute("deducted", "not");
        }
        stud.setAttribute("group",Integer.toString(student.group));

        // элементы
        stud.appendChild(getCoffeeElements(doc, stud, "surname", student.surname));
        stud.appendChild(getCoffeeElements(doc, stud, "name", student.name));
        stud.appendChild(getCoffeeElements(doc, stud, "mark",  Double.toString(student.mark)));
        stud.appendChild(getCoffeeElements(doc, stud, "height", Integer.toString(student.height)));
        return stud;
    }

    // утилитный метод для создание нового узла XML-файла
    private static Node getCoffeeElements(Document doc, Element element, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    static public XMLHandler parseWithSAX(String filePath) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File(filePath), handler);

        return handler;
    }

    public static class XMLHandler extends DefaultHandler {
        private double averageMark, maxMark, counter;
        private boolean isMark;


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            System.out.println("Start parse XML...");
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println("Stop parse XML...");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (qName.equals("students_list")) {
                maxMark = 0;
                averageMark = 0;
            }
            if (qName.equals("student")) {
                ++counter;
            }
            if (qName.equals("mark")) {
                isMark = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("mark")) {
                isMark = false;
            }
            if (qName.equals("students_list")) {
                averageMark /= counter;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            if (isMark) {
                double price = Double.parseDouble(new String(ch, start, length));
                maxMark = Math.max(price, maxMark);
                averageMark += price;
            }
        }

        public double getAverageMark() {
            return averageMark;
        }

        public double getMaxMark() {
            return maxMark;
        }
    }

    public static void convertXMLToHTML(Source xml, Source xslt, String file) {
        StringWriter sw = new StringWriter();
        //xslt = new StreamSource(new File("../../file.xsl"));
        try {

            FileWriter fw = new FileWriter(file);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transform = tFactory.newTransformer(xslt);
            transform.transform(xml, new StreamResult(sw));
            fw.write(sw.toString());
            fw.close();

            System.out
                    .println("html generated successfully ");

        } catch (IOException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
