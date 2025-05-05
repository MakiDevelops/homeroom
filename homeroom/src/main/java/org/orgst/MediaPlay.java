package org.orgst;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MediaPlay implements Menu.App {

    public void launch(JFrame frame) {
        frame.setTitle("HomeRoom - Media Player");

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Video output panel
        Canvas videoCanvas = new Canvas();
        videoCanvas.setBackground(Color.black);
        videoCanvas.setPreferredSize(new Dimension(800, 300));

        JPanel videoPanel = new JPanel(new BorderLayout());
        videoPanel.add(videoCanvas, BorderLayout.CENTER);
        mainPanel.add(videoPanel, BorderLayout.CENTER);

        // Controls
        JTextField inputField = new JTextField(40);
        JButton loadButton = new JButton("▶ Play");
        JButton stopButton = new JButton("⏹ Stop");

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("File Path:"));
        controlPanel.add(inputField);
        controlPanel.add(loadButton);
        controlPanel.add(stopButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
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


        // Button logic
        loadButton.addActionListener(e -> {
            String path = inputField.getText().trim();
            if (!path.isEmpty()) {
                File file = new File(path);
                if (file.exists()) {
                    try {
                        if (path.matches(".*\\.mp3$")) {Audio.playAudio(file.getAbsolutePath());}
                        if (path.matches(".*\\.mp4$")) {Video.playVideo(file.getAbsolutePath());}
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        stopButton.addActionListener(e -> Audio.stop());
    }
}