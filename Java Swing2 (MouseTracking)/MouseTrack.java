/*
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class MouseTrack extends JFrame implements MouseMotionListener, MouseListener {

    JLabel label1, label2;
    JLabel statusLabel;
    JFrame frame;
    String str;
    JPanel statusPanel;
    Point p;
        public static void main(String[] args){
            JFrame f=new MouseTrack("MouseTrack");
        }
        public MouseTrack(String s){
            super(s);
            frame = new JFrame();
            JPanel mainPanel=new JPanel(new BorderLayout());
            frame.setLayout(new BorderLayout());
            frame.setSize(400, 600);



           // frame.setVisible(true);


            //frame = new JFrame("Window");
            label1= new JLabel("Tracking mouse cursor in the Frame window", JLabel.CENTER);
            label2= new JLabel();
            JPanel upperPanel = new JPanel();
            upperPanel.add(label1);
            upperPanel.add(label2);


            frame.setLayout(new FlowLayout());
            mainPanel.add(upperPanel, BorderLayout.NORTH);
            //mainPanel.add(label2);
            JPanel statusPanel = new JPanel();
            statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            mainPanel.add(statusPanel, BorderLayout.SOUTH);
            statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
            statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
            JLabel statusLabel = new JLabel("status");
            statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
            statusPanel.add(statusLabel);
//Registering class MouseEx1 to catch and respond to mouse motion motion events
            frame.addMouseMotionListener(this);
            //frame.setSize(280,700);
            frame.add(mainPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    public void mouseMoved(MouseEvent e)
    {
        String s = e.getX() + "," + e.getY();
        label2.setText("Mouse moved "+ s);
        frame.setVisible(true);
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
        String s = e.getX() + "," + e.getY();
        label2.setText("Mouse dragged "+ s);
        frame.setVisible(true);
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    }

*/
