package org.orgst;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AVloader {
        public static MediaView load(Stage primStage, Media media, BorderPane root, Button playBtn, Button pauseBtn, Button stopBtn, Button back, Button inpSub) {
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
    
        // Create slider
        Slider seekSlider = new Slider();
        seekSlider.setMin(0);
        seekSlider.setMax(100); // Will adjust later
    
        // When media is ready, set slider max to media duration
        mediaPlayer.setOnReady(() -> {
            Duration total = mediaPlayer.getMedia().getDuration();
            seekSlider.setMax(total.toSeconds());
        });
    
        // Update slider as the media plays
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!seekSlider.isValueChanging()) {
                seekSlider.setValue(newTime.toSeconds());
            }
        });
    
        // Seek when slider is dragged
        seekSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
            }
        });
    
        // Also seek when slider is clicked
        seekSlider.setOnMouseReleased(e -> {
            mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
        });
    
        // Buttons
        playBtn.setOnAction(event -> mediaPlayer.play());
        pauseBtn.setOnAction(event -> mediaPlayer.pause());
        stopBtn.setOnAction(event -> mediaPlayer.stop());
        back.setOnAction(e -> {
            mediaPlayer.stop();
            primStage.setScene(Menu.launcherScene);
        });
    
        // Add everything to bottom layout
        javafx.scene.layout.VBox controlBox = new javafx.scene.layout.VBox(
            new javafx.scene.layout.HBox(5, inpSub, back, playBtn, pauseBtn, stopBtn),
            seekSlider
        );
        root.setBottom(controlBox);
        root.setCenter(mediaView);
        return mediaView;
    }
}
