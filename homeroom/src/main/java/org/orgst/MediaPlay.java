package org.orgst;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurface;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MediaPlay implements Menu.App {

    private EmbeddedMediaPlayer mediaPlayer;

    public void launch(JFrame frame) {
        frame.setTitle("HomeRoom - Media Player");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout());

        // Create video panel first
        JPanel videoPanel = new JPanel(new BorderLayout());
        videoPanel.setPreferredSize(new Dimension(800, 600));
        
        // Create canvas
        Canvas videoCanvas = new Canvas();
        videoCanvas.setBackground(Color.black);
        videoCanvas.setSize(800, 600);
        
        videoPanel.add(videoCanvas, BorderLayout.CENTER);
        mainPanel.add(videoPanel, BorderLayout.CENTER);

        // Control panel setup
        JTextField inputField = new JTextField(30);
        JButton loadButton = new JButton("Load");
        JButton playButton = new JButton("▶");
        JButton pauseButton = new JButton("⏸");
        JButton stopButton = new JButton("⏹");
        JButton backButton = new JButton("<- Back");

        controlPanel.add(backButton);
        controlPanel.add(inputField);
        controlPanel.add(loadButton);
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Make the frame visible first
        frame.setVisible(true);

        // Create media player after frame is visible
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize VLC with proper options for macOS
                MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(
                    "--vout=macosx",
                    "--no-video-deco",
                    "--no-plugins-cache"
                );
                
                mediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
                
                // Ensure the canvas has a native peer
                videoCanvas.addNotify();
                
                // Create and set video surface
                VideoSurface videoSurface = mediaPlayerFactory.videoSurfaces().newVideoSurface(videoCanvas);
                mediaPlayer.videoSurface().set(videoSurface);
                
                // Add button listeners after media player is initialized
                loadButton.addActionListener(e -> {
                    String path = inputField.getText().trim();
                    if (!path.isEmpty()) {
                        File file = new File(path);
                        path = file.getAbsolutePath();
                        mediaPlayer.media().prepare(path);
                        mediaPlayer.controls().play();
                    }
                });

                playButton.addActionListener(e -> mediaPlayer.controls().play());
                pauseButton.addActionListener(e -> mediaPlayer.controls().pause());
                stopButton.addActionListener(e -> mediaPlayer.controls().stop());

                backButton.addActionListener(e -> {
                    mediaPlayer.controls().stop();
                    mediaPlayer.release();
                    org.orgst.Menu.start();
                });
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, 
                    "Error initializing media player: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}