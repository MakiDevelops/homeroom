package org.orgst;

import java.io.IOException;

public class Audio {
    private static Process audioProcess = null;
    public static void playAudio(String path) throws IOException {

        ProcessBuilder processBuilder;
        processBuilder = new ProcessBuilder("mpv", path);

        // Start the process and play audio
        Thread th = new Thread(() -> {
            try {
            audioProcess = processBuilder.start();
            System.out.println("▶ Playing: " + path);
            // Wait for process to finish (audio playback)
            try {
                audioProcess.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("⏹ Playback finished!");
        } catch (IOException e){
            e.printStackTrace();
        }});
        th.start();
    }
    public static void stop() {
        if (audioProcess != null) {
            audioProcess.destroy(); // Stop the audio playback process
            System.out.println("⏹ Audio stopped.");
        } else {
            System.out.println("No audio playing to stop.");
        }
    }
}
