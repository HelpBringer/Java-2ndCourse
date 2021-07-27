package main.window;

import main.student.Student;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class AddDialog extends JDialog {
    private static final Dimension FIELD_SIZE = new Dimension(150, 40);
    private final JComboBox comboBox;

    public AddDialog(MainWindow window, String title, boolean modal, Font font) {
        super(window, title, modal);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.setLayout(new GridLayout(7, 0, 10, 10));

        String[] keys = new String[]{"Фамилия", "Имя", "Отчислен", "Группа", "Оценка", "Рост"};
        HashMap<String, JTextField> inputFields = new HashMap<>();


        String[] items = {"yes", "not"};
        comboBox = new JComboBox(items);

        for (int i = 0; i < keys.length; ++i) {
            if (i == 2) {
                JLabel label = new JLabel(keys[i]);
                label.setFont(font);

                this.add(label);
                this.add(comboBox);

            } else {
                inputFields.put(keys[i], new JTextField());
                JTextField textField = inputFields.get(keys[i]);
                textField.setPreferredSize(FIELD_SIZE);

                JLabel label = new JLabel(keys[i]);
                label.setFont(font);

                this.add(label);
                this.add(textField);
            }
        }

        JLabel text = new JLabel("");
        this.add(text);
        setUpButton(inputFields, keys, this);

        this.pack();
    }



    private void setUpButton(HashMap<String, JTextField> inputFields, String[] keys, JDialog dialog) {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            MainWindow window = (MainWindow) dialog.getOwner();
            Boolean deducted;
            Integer group;
            String surname;
            String name;
            Double mark;
            Integer height;

            try {
                deducted = comboBox.getSelectedIndex() == 0;
                group = Integer.parseInt(inputFields.get(keys[3]).getText());
                surname = inputFields.get(keys[0]).getText();
                name = inputFields.get(keys[1]).getText();
                mark = Double.parseDouble(inputFields.get(keys[4]).getText());
                height = Integer.parseInt(inputFields.get(keys[5]).getText());

                window.addElementInList(new Student(deducted, group, surname, name, mark, height));
                dialog.dispose();

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(window, "Entered data in the wrong format or not enough data",
                        "NumberFormatException", JOptionPane.WARNING_MESSAGE);
            }

        });
        this.add(addButton);
    }
}
