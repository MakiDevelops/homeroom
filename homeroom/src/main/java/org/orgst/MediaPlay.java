package org.orgst;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MediaPlay implements Menu.App {

    public void launch(JFrame frame) {
        // find the file if you wanna know what this shit does
        Map<String, String> BuiltIns = DLVid.dl();
        frame.setTitle("HomeRoom - Media Player");

        JPanel mainPanel = new JPanel(new BorderLayout());
        // i made this variable in another project and no i am not renaming it bitch
        JPanel videoPanel = new JPanel(new GridLayout(5, 3, 10, 10));

        // if the built in vids dir exists do this
        if (new File("tmp/videos").exists() && new File("tmp/videos").isDirectory()){
            // loop through files
        for (File file : new File("tmp/videos").listFiles()) {
            // make a new button that when clicked opens the mp4 or mp3
            JButton btn = new JButton(file.getName());
            btn.addActionListener(e -> {
                try {
                    if (file.getName().matches(".*\\.mp3$")) {Audio.playAudio(file.getAbsolutePath());}
                    if (file.getName().matches(".*\\.mp4$")) {Video.playVideo(file.getAbsolutePath());}
                } catch (IOException ex) {
                    // if your stupid java says fuck you and prints random shit
                    ex.printStackTrace();
                }
            });
            // adds it to video panel
            videoPanel.add(btn);
        }}
        // makes mainpanel the main character because it is
        mainPanel.add(videoPanel, BorderLayout.CENTER);

        // Controls
        JTextField inputField = new JTextField(40);
        JButton loadButton = new JButton("▶ Play");
        JButton stopButton = new JButton("⏹ Stop");
        // button add to panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("File Path:"));
        controlPanel.add(inputField);
        controlPanel.add(loadButton);
        controlPanel.add(stopButton);
        // goes as far south as antarctica, where you belong
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.pack();
        // make it relative to nothing (null) like youll always e nothing
        frame.setLocationRelativeTo(null);
        // do nothing like your lazy ass then kills itself and respawns
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.dispose();
                Menu.start();
            }
        });
        // makes it visible unlike you ever will be
        frame.setVisible(true);


        // Button logic no im not gonna elaborate look up near the start for somethin kinda like it
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose media file");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Media Files", "mp3", "mp4"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                    try {
                        // if you dont understand wtf .*\\.mp3$ is check the regex docs orrr
                        // use your head
                        // wait i forgot it doesnt work
                        if (file.getName().matches(".*\\.mp3$")) Audio.playAudio(file.getAbsolutePath());
                        if (file.getName().matches(".*\\.mp4$")) Video.playVideo(file.getAbsolutePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
            }
        });
        // stops audio, like i wish you could do you anoyyin bitch
        stopButton.addActionListener(e -> Audio.stop());
    }
}