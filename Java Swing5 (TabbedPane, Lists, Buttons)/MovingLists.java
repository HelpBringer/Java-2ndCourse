import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class MovingLists extends JPanel {
    private final DefaultListModel<String> leftListModel;
    private final DefaultListModel<String> rightListModel;
    private final JList<String> leftList;
    private final JList<String> rightList;
    private final JButton onRightButton;
    private final JButton onLeftButton;

    MovingLists(Dimension windowSize) {
        this.setLayout(new BorderLayout());

        leftListModel = new DefaultListModel<>();
        for (int i = 0; i < 5; ++i) {
            leftListModel.addElement(Integer.toString(i + 1));
        }

        rightListModel = new DefaultListModel<>();
        for (int i = 6; i < 10; ++i) {
            rightListModel.addElement(Integer.toString(i + 1));
        }

        Font font = new Font("TimesRoman", Font.BOLD, 24);

        Dimension listSize = new Dimension(windowSize.width / 3, windowSize.height);
        leftList = createList(listSize, leftListModel, font);
        rightList = createList(listSize, rightListModel, font);

        this.add(leftList, BorderLayout.WEST);
        this.add(rightList, BorderLayout.EAST);

        Dimension buttonSize = new Dimension(windowSize.width / 3, windowSize.height / 4);
        onRightButton = createButton(buttonSize, ">", font);
        onRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] indices = leftList.getSelectedIndices();

                    for (int index : indices) {
                        String element = leftListModel.getElementAt(index);
                        rightListModel.addElement(element);
                    }

                    List<Integer> reverseSortedIndecedList= Arrays.stream(indices).boxed().collect(Collectors.toList());
                    reverseSortedIndecedList.sort(Comparator.reverseOrder());
                    for(int index : reverseSortedIndecedList){
                        leftListModel.removeElementAt(index);
                    }
            }
        });

        onLeftButton = createButton(buttonSize, "<", font);
        onLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] indices = rightList.getSelectedIndices();

                    for (int index : indices) {
                        String element = rightListModel.getElementAt(index);
                        leftListModel.addElement(element);
                    }

                    List<Integer>reverseSortedIndicesList= Arrays.stream(indices).boxed().collect(Collectors.toList());
                    reverseSortedIndicesList.sort(Comparator.reverseOrder());
                    for(int index : reverseSortedIndicesList){
                        rightListModel.removeElementAt(index);
                    }

            }
        });

        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(onRightButton, BorderLayout.NORTH);
        centralPanel.add(onLeftButton, BorderLayout.SOUTH);
        centralPanel.setBackground(Color.GRAY);

        this.add(centralPanel, BorderLayout.CENTER);
    }

    public void resizeComponents(Dimension windowSize) {
        Dimension listSize = new Dimension(windowSize.width / 3, windowSize.height);
        leftList.setPreferredSize(listSize);
        rightList.setPreferredSize(listSize);

        Dimension buttonSize = new Dimension(windowSize.width / 3, windowSize.height / 4);
        onRightButton.setPreferredSize(buttonSize);
        onLeftButton.setPreferredSize(buttonSize);
    }

    private JList<String> createList(Dimension listSize, DefaultListModel<String> model, Font font) {
        JList<String> list = new JList<>(model);
        list.setPreferredSize(listSize);
        list.setFont(font);
        return list;
    }

    private JButton createButton(Dimension buttonSize, String text, Font font) {
        JButton button = new JButton(text);
        button.setPreferredSize(buttonSize);
        button.setBackground(Color.WHITE);
        button.setFont(font);
        return button;
    }
}
