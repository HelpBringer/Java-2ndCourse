import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class TableView extends JFrame {
   public final static char A = 'A';
    final static int NUM = 26;
    private JTable table;
    private JScrollPane mainPane;
    private DefaultTableModel tableModel;


    TableView() {
        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                else
                    return super.isCellEditable(row, column);
            }

            public Class getColumnClass(int column) {
                return MyCell.class;
            }
        };
        table = new JTable(tableModel);
        mainPane = new JScrollPane(table);
        mainPane.setVisible(true);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainPane.setPreferredSize(new Dimension(700, 500));
        setContentPane(mainPane);
        Vector v = new Vector();
        for (int i = 1; i < NUM; i++) {
            v.add(i);
        }


        table.getTableHeader().setReorderingAllowed(false);
        tableModel.addColumn("", v);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        for (int i = 0; i < NUM; i++) {
            tableModel.addColumn((char) (A + i));
            table.getColumnModel().getColumn(i).setPreferredWidth(300);
        }
        tableModel.setValueAt(new MyCell(1111, 10, 11), 1, 1);
        tableModel.setValueAt(new MyCell(2021, 4, 20), 2, 2);
        tableModel.setValueAt(new MyCell(2222, 2, 22), 3, 3);
        CellController editor = new CellController();
        table.setDefaultEditor(MyCell.class, editor);

        setVisible(true);

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


}
