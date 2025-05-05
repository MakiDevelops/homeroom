package org.orgst;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MediaPlay implements Menu.App {

    public void launch(JFrame frame) {
        Map<String, String> BuiltIns = DLVid.dl();
        frame.setTitle("HomeRoom - Media Player");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel videoPanel = new JPanel(new GridLayout(5, 3, 10, 10));
        if (new File("tmp/videos").exists() && new File("tmp/videos").isDirectory()){
        for (File file : new File("tmp/videos").listFiles()) {
            JButton btn = new JButton(file.getName());
            btn.addActionListener(e -> {
                try {
                    if (file.getName().matches(".*\\.mp3$")) {Audio.playAudio(file.getAbsolutePath());}
                    if (file.getName().matches(".*\\.mp4$")) {Video.playVideo(file.getAbsolutePath());}
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            videoPanel.add(btn);
        }}
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
                frame.dispose();
                Menu.start();
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