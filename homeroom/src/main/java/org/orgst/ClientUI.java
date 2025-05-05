package org.orgst;
import javax.swing.*;
import java.awt.*;
public class ClientUI implements Menu.App{
    public void launch(JFrame frame){
        frame.setTitle("HomeRoom - Client");
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Server IP:");
        JTextField inputField = new JTextField(50);
        JButton startButton = new JButton("Connect");
        startButton.addActionListener(e->{
            String ip = inputField.getText().trim();
            if (!ip.isEmpty()) {
                String fileName = Multiplayer.client(ip);
                try {
                    if (fileName.matches(".*\\.mp3$")) {Audio.playAudio("tmp/"+fileName);}
                    if (fileName.matches(".*\\.mp4$")) {Video.playVideo("tmp/"+fileName);}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
                Menu.start();
            }
        });
        frame.setVisible(true);
    }
}
