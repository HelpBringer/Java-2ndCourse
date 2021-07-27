import javax.swing.*;
import java.awt.*;

public class RadioButtonSomething extends JPanel {
    ImageIcon defaultIcon =getResizedIcon(new ImageIcon("home.jpg"));
    ImageIcon selectedIcon=getResizedIcon(new ImageIcon("setting.png"));
    ImageIcon rolloverIcon=getResizedIcon(new ImageIcon("like.png"));
    ImageIcon pressedIcon=getResizedIcon(new ImageIcon("settings.png"));

    private void prepareButton(JRadioButton button){
        button.setIcon(defaultIcon);
        button.setSelectedIcon(selectedIcon);
        button.setRolloverIcon(rolloverIcon);
        button.setPressedIcon(pressedIcon);
    }
//Обрезает картинку
    private ImageIcon getResizedIcon(ImageIcon icon){
        return new ImageIcon(icon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
    }

    public RadioButtonSomething(){

        JRadioButton yesButton= new JRadioButton("Yes");
        JRadioButton noButton= new JRadioButton("No");

        prepareButton(yesButton);
        prepareButton(noButton);

        ButtonGroup buttonGroup= new ButtonGroup();
        buttonGroup.add(yesButton);
        buttonGroup.add(noButton);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(2,1));
        radioPanel.add(yesButton);
        radioPanel.add(noButton);

        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Some"));
        this.add(radioPanel);


    }

}
