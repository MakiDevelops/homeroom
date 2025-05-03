package org.orgst;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.application.*;
import javafx.geometry.Insets;

import java.util.HashMap;
public class Menu extends Application {
    public static Scene launcherScene;
    public interface App {
        void launch(Stage primStage);
    }
    @Override
    public void start(Stage primStage) {
        GridPane root = new GridPane();
        root.setHgap(10); // Horizontal spacing
        root.setVgap(10); // Vertical spacing
        root.setPadding(new Insets(10));

        HashMap<String, App> apps = new HashMap<>();
        apps.put("Video/Audio", new org.orgst.MediaPlay());
        int btnC = 0;

        for (String name : apps.keySet()) {
            Button btn = new Button(name);
            btn.setOnAction(e -> apps.get(name).launch(primStage));
            root.add(btn, btnC, 0);
            btnC++;
        }

        Scene menuScene = new Scene(root, 200, 400);
        Menu.launcherScene = menuScene;
        primStage.setTitle("HomeRoom Launcher");
        primStage.setScene(menuScene);
        primStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}