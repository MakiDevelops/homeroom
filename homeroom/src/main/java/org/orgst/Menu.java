package org.orgst;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Menu {
    public static JFrame frame;
    public static JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
    public interface App {
        // make apps work universally
        // like your face is universally ugly
        void launch(JFrame frame);
    }

    public static void main(String[] args) {
        // do it later like ill do your mom later
        SwingUtilities.invokeLater(Menu::apps);
    }
    public static void apps(){
    HashMap<String, App> apps = new HashMap<>();
        // put stuff in apps, like i put something in your mother
        apps.put("Video/Audio", new org.orgst.MediaPlay());
        apps.put("MultiPlayer (LAN Only)", new org.orgst.MPUI());
        // loop through something, i forgot where i defined this
        for (String name : apps.keySet()) {
        JButton btn = new JButton(name);
        App app = apps.get(name);
        // looks for action
            //if btn was you this would do nothing cause your a lazy fuck
        btn.addActionListener(e -> {frame.dispose(); app.launch(frame);});
        panel.add(btn);
        start();
    }}
    public static void start() {
        frame = new JFrame("HomeRoom Launcher");
        // exit on close, like when i closed your laptop you exited thinking
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set size, this is most likely bigger than something on your lower body
        frame.setSize(200, 400);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(panel);
        // makes it relative to null, i cant think of something for this
        frame.setLocationRelativeTo(null);
        // makes frame visible unlike your pain reading this
        frame.setVisible(true);
    }
}