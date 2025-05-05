package org.orgst;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Menu {
    public static JFrame frame;
    public static JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
    public interface App {
        void launch(JFrame frame);
    }

    public static void main(String[] args) {
        // Schedule on Event Dispatch Thread
        SwingUtilities.invokeLater(Menu::start);
    }

    public static void start() {
        frame = new JFrame("HomeRoom Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 400);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        HashMap<String, App> apps = new HashMap<>();
        apps.put("Video/Audio", new org.orgst.MediaPlay());
        apps.put("MultiPlayer (LAN Only)", new org.orgst.MPUI());
        for (String name : apps.keySet()) {
            JButton btn = new JButton(name);
            App app = apps.get(name); // Capture reference for lambda
            btn.addActionListener(e -> app.launch(frame));
            panel.add(btn);
        }

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}