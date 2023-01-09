package application;

import business.Einstellungen;
import business.MP3;
import business.PlaylistManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameApplication extends Application {
    private Stage primaryStage;
    private Map<String, Pane> scenes;
    private Scene mainScene;
    MP3 player;
    PlaylistManager manager;
    Einstellungen settings;

    public void start(Stage primaryStage) {

        try {
            this.primaryStage = primaryStage;
            settings = new Einstellungen();
            scenes = new HashMap<String, Pane>();
            manager = new PlaylistManager(settings);
            player = new MP3(this);
            Pane root = scenes.get("PlaylistOverView");

            Scene scene = new Scene(root,800,800);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            mainScene = scene;
            primaryStage.setScene(mainScene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void switchScene(String viewName, String... optPane) {
        if(mainScene != null) {
            Pane nextView = scenes.get(viewName);
            if(nextView != null) {
                mainScene.setRoot(nextView);
            }
        }
    }
}
