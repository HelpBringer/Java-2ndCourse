import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyFrame extends JFrame {
    Series example;
    JTextField countField, outField, coefficientField, firstField, fField;

    public static void main(String[] args){
        JFrame f=new MyFrame("Series");
    }
    public MyFrame(String s){
        super(s);
        this.setSize(550,400);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        //this.add(new JLabel("exp"));
        JButton b= new JButton("Start");

        this.add(b);
        // Создание текстовых полей
        countField = new JTextField("число элементов",25);
        countField.setToolTipText("Введите число элементов");
        firstField = new JTextField("первый элемент",25);
        firstField.setToolTipText("Введите первый элемент");
        coefficientField = new JTextField(" коэффициент",25);
        coefficientField.setToolTipText("Введите коэффициент");
        outField = new JTextField("Поле вывода", 25);
        outField.setToolTipText("Длиное поле");
        // Настройка шрифта
        outField.setFont(new Font("Dialog", Font.PLAIN, 14));
        outField.setHorizontalAlignment(JTextField.RIGHT);
        fField = new JTextField("Документ",25);

        JRadioButton lin = new JRadioButton("Liner");
        JRadioButton exp = new JRadioButton("Exponential");
        ButtonGroup group = new ButtonGroup();
        group.add(lin);
        group.add(exp);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (lin.isSelected()) {
                        example = new Liner(Double.parseDouble(firstField.getText()), Integer.parseInt(countField.getText()), Double.parseDouble(coefficientField.getText()));
                    }
                    if (exp.isSelected()) {
                        example = new Exponential(Double.parseDouble(firstField.getText()), Integer.parseInt(countField.getText()), Double.parseDouble(coefficientField.getText()));
                    }
                }
                catch( NumberFormatException e){
                    outField.setText("Нельзя использовать символы");
                }
                catch (IllegalArgumentException e){
                    outField.setText(e.getMessage());
                }
            }


        });
        JButton sum= new JButton("Sum");
        sum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (example!=null) {
                    outField.setText(Double.toString(example.findSum()));
                }
            }
        });
        JButton inFile= new JButton("Write result in file");
        inFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (example != null) {
                    try {
                        example.fOut(fField.getText());
                    } catch (IOException e) {
                        outField.setText("Wrong file name");
                    }
                }
            }
        });
        JButton  inProgram= new JButton("Write result");
        inProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (example!=null) {
                    outField.setText(example.toString());
                }
            }
        });
        this.add(inFile);
        this.add(inProgram);
        this.add(sum);
        this.add(exp);
        this.add(lin);
        this.add(countField);
        this.add(firstField );
        this.add(coefficientField) ;
        this.add(fField);
        this.add(outField );

        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
