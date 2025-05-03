package org.orgst;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class MediaPlay implements org.orgst.Menu.App {
    public void launch(Stage primStage){
        Button back = new Button("<- Back");
        back.setOnAction(e -> {primStage.setScene(Menu.launcherScene);});
        BorderPane root = new BorderPane();
        TextField inp = new TextField();
        inp.setPromptText("FULL MP4 File Path");
        Button inpSub = new Button("Load");
        Button playBtn = new Button("▶");
        Button pauseBtn = new Button("⏸");

        Button stopBtn = new Button("⏹");
        inpSub.setOnAction(e -> {
            // Load the media file when the "Load" button is pressed
            String filePath = inp.getText();
            if (filePath.startsWith("https://") || filePath.startsWith("http://")){
                Media media = new Media(filePath);
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);

                // Set the media view to the center of the layout
                root.setCenter(mediaView);

                // Set actions for the play, pause, and stop buttons
                playBtn.setOnAction(event -> mediaPlayer.play());
                pauseBtn.setOnAction(event -> mediaPlayer.pause());
                stopBtn.setOnAction(event -> mediaPlayer.stop());
            } else {
                File file = new File(filePath);
                Media media = new Media(file.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);

                // Set the media view to the center of the layout
                root.setCenter(mediaView);

                // Set actions for the play, pause, and stop buttons
                playBtn.setOnAction(event -> mediaPlayer.play());
                pauseBtn.setOnAction(event -> mediaPlayer.pause());
                stopBtn.setOnAction(event -> mediaPlayer.stop());
            }
            primStage.getIcons().add(new Image(getClass().getResourceAsStream("/image.png")));
        });
        // /Users/william/Downloads/20080819_Jeopardy Theme.mp4 is one on my pc (pls keep this i cant remember were an mp4 is)
        root.setBottom(new javafx.scene.layout.HBox(5, back, inpSub, playBtn, pauseBtn, stopBtn));
        root.setTop(inp);
        Scene scene = new Scene(root, 800, 600);
        primStage.setTitle("HomeRoom");
        primStage.setScene(scene);
        primStage.show();
    }
}
