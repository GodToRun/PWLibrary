package pw.ui;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;


public class PWTextField {
	String text;
    JTextField textField;
    boolean activate = false;

    public void createUI(JFrame frame,String text,Color back, Color fore){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        MainPanel mainPanel = new MainPanel(text,back,fore);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        frame.add(mainPanel,BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /*public static void main(String[] args) {
        PWTextField PWTextField = new PWTextField();
        PWTextField.createUI(new JFrame("Test"),"",Color.white,Color.gray);
    }*/

    public void setActivate(boolean activate){
        this.activate = activate;
    }

    @SuppressWarnings("serial")
    class MainPanel extends JPanel{
        public MainPanel(String text,Color Background,Color Foreground){

            textField = new JTextField(text);
            Font fieldFont = new Font("Arial", Font.PLAIN, 20);
            textField.setFont(fieldFont);
            textField.setBackground(Background);
            textField.setForeground(Foreground);
            textField.setColumns(30);
            textField.setBorder(BorderFactory.createCompoundBorder(
                    new CustomeBorder(), 
                    new EmptyBorder(new Insets(15, 25, 15, 25))));
            textField.addActionListener(new FieldListener());
            textField.addMouseListener(new FieldMouseListener());


            add(textField,BorderLayout.CENTER);
            setBackground(Color.blue);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }

    @SuppressWarnings("serial")
    class CustomeBorder extends AbstractBorder{
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(Color.blue);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }   
    }

    class FieldListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            text = textField.getText();
        }

    }

    class FieldMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            if(activate == false){
                textField.setText("");
            }
            activate = true;
            textField.setForeground(Color.black);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    }
}