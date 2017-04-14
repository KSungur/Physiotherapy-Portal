package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by atabe on 14.04.2017.
 */
public class pMain {
    private JTextArea LOLLOLOLOLOLOOLTextArea;
    private JPanel pMainView;

    public static void createFrame(){

        JFrame frame =new JFrame("pMain");
        frame.setLocation(400,100);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setContentPane(new pMain().pMainView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main() {



    }


}
