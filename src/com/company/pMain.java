package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Login.frame;

public class pMain {
    private JTextArea LOLLOLOLOLOLOOLTextArea;
    private JPanel pMainView;

    public static void createFrame(){

        frame .setTitle("pMain");
        frame.setLocation(400,100);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setContentPane(new pMain().pMainView);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }




}
