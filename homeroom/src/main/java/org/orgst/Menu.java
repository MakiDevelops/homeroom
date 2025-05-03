package org.orgst;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.application.*;
import java.util.HashMap;
public class Menu extends Application {
    public static Scene launcherScene;
    public interface App {
        void launch(Stage primStage);
    }
    @Override
    public void start(Stage primStage) {
        HashMap<String, App> apps = new HashMap<>();
        apps.put("Video/Audio", new org.orgst.MediaPlay());
        Group root = new Group();
        int btnY = 20;

        for (String name : apps.keySet()) {
            Button btn = new Button(name);
            btn.setTranslateX(20);
            btn.setTranslateY(btnY);

            btn.setOnAction(e -> apps.get(name).launch(primStage));

            root.getChildren().add(btn);
            btnY += 40;
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