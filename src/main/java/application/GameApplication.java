package application;

import business.Einstellungen;
import business.MP3;
import business.PlaylistManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.scenes.playlistMenu.PlaylistMenuController;
import presentation.scenes.songMenu.SongMenuController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

            //GameScreenController game = new GameScreenController();
            PlaylistMenuController playlistMenu = new PlaylistMenuController(this, manager);
            scenes.put("SonglistScreen", playlistMenu.getView());
            //scenes.put("GameScreen", game.getView());
            scenes.put("PlaylistScreen", playlistMenu.getView());
            Pane root = scenes.get("PlaylistScreen");

            Scene scene = new Scene(root,1800,800);
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
            System.out.println("hier");
            Pane nextView = scenes.get(viewName);
            if(nextView != null) {
                System.out.println("bin");
                if(optPane.length == 0) {
                    System.out.println("ich");
                    mainScene.setRoot(nextView);
                } else if(optPane[0].equals("SongList")) {
                    System.out.println("nicht");
                    SongMenuController songlistMenu = new SongMenuController(this, manager, player);
                    mainScene.setRoot(songlistMenu.getView());
                }

            }
        }
    }

    public PlaylistManager getManager() {
        return manager;
    }
}
