package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




/**
 * Created by atabe on 14.04.2017.
 */
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


    public static void createFrame(){


        JFrame frame = new JFrame("LOGIN");
        frame.setLocation(400,100);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setContentPane(new Login().loginView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



}




    public static void main(String[] args) {

        createFrame();


    }



    public Login() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pMain pmain=new pMain();
                pMain.createFrame();

            }
        });
    }


}