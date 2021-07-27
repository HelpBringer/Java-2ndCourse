package main.window;

import main.students.Student;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AddDialog extends JDialog {

    private static final Dimension FIELD_SIZE = new Dimension(100, 20);

    public AddDialog(MainWindow window, String title, boolean modal, Font font) {
        super(window, title, modal);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(5, 0, 10, 10));

        String[] keys = new String[]{"Name", "ID", "Course", "Group"};
        HashMap<String, JTextField> inputFields = new HashMap<>();

        for (String s : keys) {
            inputFields.put(s, new JTextField());
            JTextField textField = inputFields.get(s);
            textField.setPreferredSize(FIELD_SIZE);

            JLabel label = new JLabel(s);
            label.setFont(font);

            this.add(label);
            this.add(textField);
        }

        setUpButton(inputFields, keys, this);

        this.pack();
    }

    private void setUpButton(HashMap<String, JTextField> inputFields, String[] keys, JDialog dialog) {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String name;
            int id;
            int course, group;

            MainWindow window = (MainWindow) dialog.getOwner();

            try {
                name = inputFields.get(keys[0]).getText();
                id = Integer.parseInt(inputFields.get(keys[1]).getText());
                course = Integer.parseInt(inputFields.get(keys[2]).getText());
                group = Integer.parseInt(inputFields.get(keys[3]).getText());

                window.addElementStudent(new Student(name, id, course, group));

                dialog.dispose();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(window, "Check entered data",
                        "NumberFormatException", JOptionPane.WARNING_MESSAGE);
            }
        });
        this.add(addButton);
    }
}
