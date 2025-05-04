package org.orgst;

import java.io.IOException;

public class Video {
    public static void playVideo(String path) throws IOException {
        ProcessBuilder vidProc = new ProcessBuilder("mpv", path);

        // Start the process and play audio
        Process proc = vidProc.start();
        System.out.println("▶ Playing: " + path);
        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("⏹ Playback finished!");
    }
}
