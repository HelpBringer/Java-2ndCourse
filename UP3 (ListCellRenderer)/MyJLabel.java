import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class MyJLabel extends JLabel implements ListCellRenderer<Country>
{
    private final Border border;

    MyJLabel()
    {
        //  flag icon & country name
        setIconTextGap(10);
        setOpaque(true);
        border = BorderFactory.createLineBorder(Color.BLUE, 2);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Country> list, Country country, int index, boolean isSelected, boolean cellHasFocus) {
       if (isSelected) {
           setText(country.getName() + " " + country.getCapital());
           setBackground(list.getSelectionBackground());
           setForeground(list.getSelectionForeground());
       }
       else {
           setText(country.getName());
           setBackground(list.getBackground());
           setForeground(list.getForeground());
       }
        setIcon(country.getFlagIcon());
        setFont(list.getFont());
        setEnabled(list.isEnabled());

        if (cellHasFocus)
            setBorder(border);
        else
            setBorder(null);

        return this;

    }
}