package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
    private JPanel loginView;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton button1;
    private JTextField textField2;
    private JPasswordField passwordField2;
    private JButton button2;
    private JTextArea PHYSIOTHERAPYPORTALTextArea;
    private JTextArea PATIENTLOGINTextArea;
    private JTextArea DOCTORLOGINTextArea;
   public static JFrame frame;

    public static void setFrame(JFrame frame){

        frame.setLocation(400,100);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setContentPane(new Login().loginView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

}


    public static void main(String[] args) {
        frame = new JFrame("LOGIN");
        setFrame(frame);


    }



    private Login() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pMain.createFrame();


            }
        });
    }


}