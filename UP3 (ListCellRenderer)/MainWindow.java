import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class MainWindow extends JFrame {
    private JTable table;
    private JTabbedPane mainPane;
    private JTextField country, price, description;
    private JButton addTrip;


    private boolean action;
    //Task 1
    final static String[] COUNTRIES = {"Belarus", "USA", "Russia", "China", "Sweden", "Germany", "France", "Montserrat", "Belarus", "England"};
    final static String[] CAPITALS = {"Minsk", "Washington", "Moscow", "Beijing", "Stockholm", "Berlin", "Paris", "Plymouth", "Minsk", "London",};
    final static Object[][] DATA = {//Task 2
            {new Country("Belarus").getFlagIcon(), "We have the BSU", 500, false},
            {new Country("USA").getFlagIcon(), "We have the Statue of Liberty", 1000, false},
            {new Country("France").getFlagIcon(), "We gave them the Statue of Liberty", 999, false},
            {new Country("China").getFlagIcon(), "需要帮忙", 5000, false}
    };
    final static Object[] NAMES_OF_COLUMNS = {"Country", "Description", "Price", "Choose"};
    final static Object[] RESULT = {"", "Final price"};

    MainWindow(String s) {
        super(s);
        Map<String, Country> countries = new TreeMap<>();
        mainPane = new JTabbedPane();
        for (int i = 0; i < COUNTRIES.length; i++) {
            countries.put(COUNTRIES[i], new Country(COUNTRIES[i], CAPITALS[i]));
        }

        DefaultListModel<Country> model = new DefaultListModel<>();
        model.addAll(countries.values());
        JList<Country> list = new JList<>();
        list.setModel(model);

        JPanel panel1 = new JPanel();
        JScrollPane pane = new JScrollPane(list);
        pane.setVisible(true);
        pane.setPreferredSize(new Dimension(700, 500));
        setContentPane(mainPane);
        panel1.setPreferredSize(new Dimension(700, 600));

        panel1.add(pane);
        list.setCellRenderer(new MyJLabel());
        mainPane.add(panel1, "List");



        action = false;
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(1000, 600));
        table = new JTable();
        country = new JTextField();
        price = new JTextField();
        description = new JTextField();
        addTrip = new JButton("Add tour");

        JPanel panelForTextFields = new JPanel(new GridLayout(3, 4));

        panelForTextFields.add(new JLabel("Country:"));
        panelForTextFields.add(new JLabel("Description:"));
        panelForTextFields.add(new JLabel("Price:"));

        panelForTextFields.add(country);
        panelForTextFields.add(description);
        panelForTextFields.add(price);
        panelForTextFields.add(addTrip);


        JScrollPane js = new JScrollPane(table);
        js.setVisible(true);
        js.setPreferredSize(new Dimension(700, 600));
        panel2.add(js);
        panel2.add(panelForTextFields);

        DefaultTableModel tableModel = new DefaultTableModel(DATA, NAMES_OF_COLUMNS) {
            public Class<?> getColumnClass(int column) {
                    return getValueAt(0, column).getClass();
                }

                public boolean isCellEditable(int row, int column) {
                    if (row == this.getRowCount() - 1)
                        return false;
                    else
                        return super.isCellEditable(row, column);
                }
            };
        tableModel.addRow(RESULT);
        table.setRowHeight(80);
        table.setModel(tableModel);

        mainPane.add(panel2, "Tour");

        addTrip.addActionListener(e -> {
            try {
                int priceField = Integer.parseInt(price.getText());
                Country country = new Country(this.country.getText());
                Object[] obj = new Object[]{country.getFlagIcon(), description.getText(), priceField, false};
                tableModel.insertRow(tableModel.getRowCount() - 1, obj);
            }
            catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(null, "Invalid data");
            }
        });

        tableModel.addTableModelListener(e -> {
            if (!action) {
                int sum = 0;
                for (int i = 0; i < tableModel.getRowCount() - 1; i++) {
                    if ((Boolean) tableModel.getValueAt(i, 3)) {
                        sum += (int) tableModel.getValueAt(i, 2);
                    }
                }
                action = true;
                tableModel.setValueAt(sum, tableModel.getRowCount() - 1, 2);
                action = false;
            }
        });
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow("List&Table");
    }

}

