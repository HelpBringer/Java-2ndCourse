import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements MouseMotionListener {
    JLabel statusLabel= new JLabel("x=0 y=0");
   JButton jButton;
   int lastX, lastY;
   boolean flag=false,flag1=false;
    public static void main(String[] args){
        JFrame f=new MyFrame();
    }
    public MyFrame(){
        super("title");
        this.setSize(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel statusPanel= new JPanel();
        add(statusPanel, BorderLayout.SOUTH);
       /* JLabel statusLabel= new JLabel("x=0 y=0");*/
        statusPanel.add(statusLabel);
        JPanel jPanel= new JPanel();
        add(jPanel);
       /* JButton*/ jButton= new JButton("button");
        jButton.addMouseMotionListener(this);
        this.addMouseMotionListener(this);
       // jButton.addMouseListener(new MyMouseAdapter());
        jPanel.add(jButton);

        jButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String buttonText= jButton.getText();
                if(e.getKeyChar()==KeyEvent.VK_BACK_SPACE){
                    if(!buttonText.equals("")){
                        jButton.setText(buttonText.substring(0,buttonText.length()-1));
                    }
                }
                else{
                    jButton.setText(buttonText+e.getKeyChar());
                }
                if(e.isControlDown()){
                    flag=true;
                }
            }
        });

        setVisible(true);
    }
    @Override
    public void mouseDragged(MouseEvent arg0){
        int xCom=0, yCom=0;
        if (arg0.getSource().equals(jButton)) {
            JButton x = (JButton) arg0.getSource();
            xCom = x.getX();
            JButton y = (JButton) arg0.getSource();
            yCom = y.getY();
           if(arg0.isControlDown()){
                jButton.setLocation(arg0.getX()+xCom-lastX,arg0.getY()+yCom-lastY);
            }
        }
        statusLabel.setText("x="+(arg0.getX()+xCom)+"y="+(arg0.getY()+yCom));
    }
    @Override
    public void mouseMoved(MouseEvent arg0){
        int xCom=0, yCom=0;
        if (arg0.getSource().equals(jButton)) {
            JButton x = (JButton) arg0.getSource();
            xCom = x.getX();
            JButton y = (JButton) arg0.getSource();
            yCom = y.getY();
        }
        lastX=arg0.getX();
        lastY=arg0.getY();
        statusLabel.setText("x="+(arg0.getX()+xCom)+"y="+(arg0.getY()+yCom));
    }
    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent arg0){
            super.mouseEntered(arg0);
            JOptionPane.showMessageDialog(MyFrame.this, "vvv");
        }
    }
}
