package org.orgst;

import java.io.IOException;

public class Audio {
    private static Process audioProcess = null;
    public static void playAudio(String path) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();

        ProcessBuilder processBuilder;

        // MacOS
        if (os.contains("mac")) {
            processBuilder = new ProcessBuilder("afplay", path);
        }
        // Linux (using mpg123 as an example, can change to whatever is available)
        else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            processBuilder = new ProcessBuilder("mpg123", path);
        }
        // Windows (using start command for Windows default player)
        else if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd", "/c", "start", path);
        }
        // Unrecognized OS
        else {
            throw new IOException("Unsupported OS: " + os);
        }

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
