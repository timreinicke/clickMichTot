package application;

import business.Einstellungen;
import business.MP3;
import business.PlaylistManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.scenes.gameScreen.GameScreenController;
import presentation.scenes.playlistMenu.PlaylistMenuController;
import presentation.scenes.songMenu.SongMenuController;

import java.io.FileNotFoundException;
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
            scenes.put("GameScreen", playlistMenu.getView());
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

    public void switchScene(String viewName, String... optPane) throws FileNotFoundException {

        if(mainScene != null) {
            Pane nextView = scenes.get(viewName);
            if(nextView != null) {
                if(optPane.length == 0) {
                    mainScene.setRoot(nextView);
                } else if(optPane[0].equals("SongList")) {
                    SongMenuController songlistMenu = new SongMenuController(this, manager, player);
                    mainScene.setRoot(songlistMenu.getView());
                } else if(optPane[0].equals("GameScreen") && !Objects.equals(optPane[1], "")) {
                    GameScreenController game = new GameScreenController(optPane[1]);
                    mainScene.setRoot(game.getView());
                }
            }
        }
    }

    public PlaylistManager getManager() {
        return manager;
    }
}
