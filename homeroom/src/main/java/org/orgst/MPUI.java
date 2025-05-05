package org.orgst;
import javax.swing.*;
import java.awt.*;

public class MPUI implements Menu.App{
    public void launch(JFrame frame){
        frame.setTitle("HomeRoom - Multiplayer");
        JPanel panel = new JPanel(new BorderLayout());
        JButton srvButton = new JButton("Server");
        JButton clButton = new JButton("Client");
        srvButton.addActionListener(e->{
            new org.orgst.ServerUII().launch(frame);
        });
        clButton.addActionListener(e->{
            new org.orgst.ClientUI().launch(frame);
        });
        panel.add(srvButton, BorderLayout.NORTH);
        panel.add(clButton, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.setContentPane(Menu.panel);
            }
        });
        frame.setVisible(true);
    }
}
