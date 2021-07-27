import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonsDemo extends JPanel {

    static final int SPACE = 4;

    public ButtonsDemo(){
        super();
        this.setLayout(new GridLayout(SPACE, SPACE));

        MouseAdapter mouseAdapter = new MouseAdapter() {

            String lastContent;

            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                var clickedComponent = (JButton) e.getComponent();
                lastContent= clickedComponent.getText();
                clickedComponent.setText("CLICKED");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                var clickedComponent = (JButton) e.getComponent();
                clickedComponent.setText(lastContent);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                var clickedComponent = (JButton) e.getComponent();
                clickedComponent.setBackground(null);
            }
        };
        for( int i=1; i<=SPACE*SPACE;++i){
            JButton button = new JButton(Integer.toString(i));
            button.addMouseListener(mouseAdapter);
            this.add(button);
        }
    }
}
