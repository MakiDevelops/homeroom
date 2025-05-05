package org.orgst;
import javax.swing.*;
import java.awt.*;
public class ServerUII implements Menu.App{
    public void launch(JFrame frame){
        frame.setTitle("HomeRoom - Server");
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("File Path:");
        JTextField inputField = new JTextField(50);
        JButton startButton = new JButton("Start Server");
        startButton.addActionListener(e->{
            String path = inputField.getText().trim();
            if (!path.isEmpty()) {
                if (!path.matches(".*\\.mp3$") && !path.matches(".*\\.mp4$")) {Multiplayer.server(path);}
                else {JOptionPane.showMessageDialog(frame, "Invalid file type!", "Error", JOptionPane.ERROR_MESSAGE);}
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
                    frame.setContentPane(Menu.panel);
            }
        });
        frame.setVisible(true);
    }
}
