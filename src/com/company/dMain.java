package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Login.frame;

/**
 * Created by atabe on 15.04.2017.
 */
public class dMain {
    private JTextArea doctorMainTextArea;
    private JPanel dMainView;





    public static void setFrame(){

        frame .setTitle("dMain");
        frame.setLocation(400,100);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setContentPane(new dMain().dMainView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
