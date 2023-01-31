package application;

import business.Einstellungen;
import business.MP3;
import business.PlaylistManager;
import business.Song;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.scenes.defeatScreen.DefeatScreenController;
import presentation.scenes.gameScreen.GameScreenController;
import presentation.scenes.leaderboard.LeaderboardController;
import presentation.scenes.leaderboardEntry.LeaderboardEntryController;
import presentation.scenes.mainMenu.MainMenuController;
import presentation.scenes.playlistMenu.PlaylistMenuController;
import presentation.scenes.settings.SettingsController;
import presentation.scenes.songMenu.SongMenuController;
import presentation.scenes.victoryScreen.VictoryScreenController;

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
    Pane emptyPane;

    public void start(Stage primaryStage) {

        try {
            emptyPane = new Pane();
            this.primaryStage = primaryStage;
            settings = new Einstellungen();
            scenes = new HashMap<String, Pane>();
            manager = new PlaylistManager(settings);
            player = new MP3(this);

            PlaylistMenuController playlistMenu = new PlaylistMenuController(this, manager);
            LeaderboardController leaderboard = new LeaderboardController(this);
            MainMenuController mainMenu = new MainMenuController(this);
            SettingsController settings = new SettingsController(this, player, manager, this.settings);

            scenes.put("SonglistScreen", playlistMenu.getView());
            scenes.put("GameScreen", emptyPane);
            scenes.put("PlaylistScreen", playlistMenu.getView());
            scenes.put("Leaderboard", leaderboard.getView());
            scenes.put("leaderBoardEntry", emptyPane);
            scenes.put("MainMenu", mainMenu.getView());
            scenes.put("Settings", settings.getView());
            scenes.put("DefeatScreen", emptyPane);
            scenes.put("VictoryScreen", emptyPane);

            Pane root = scenes.get("MainMenu");
            primaryStage.setMaximized(true);
            Scene scene = new Scene(root);
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
                } else {
                    switch (optPane[0]) {
                        case "SongList" -> {
                            SongMenuController songlistMenu = new SongMenuController(this, manager, player, settings);
                            mainScene.setRoot(songlistMenu.getView());
                        }
                        case "GameScreen" -> {
                            GameScreenController game = new GameScreenController(optPane[1], player, this, settings);
                            mainScene.setRoot(game.getView());
                        }
                        case "DefeatScreen" -> {
                            DefeatScreenController defeat = new DefeatScreenController(Integer.parseInt(optPane[1]), this);
                            mainScene.setRoot(defeat.getView());
                        }
                        case "VictoryScreen" -> {
                            VictoryScreenController victory = new VictoryScreenController(Integer.parseInt(optPane[1]), this);
                            mainScene.setRoot(victory.getView());
                        }

                        case "leaderBoardEntry" -> {
                            LeaderboardEntryController leaderboardADD = new LeaderboardEntryController(this, Integer.parseInt(optPane[1]));
                            mainScene.setRoot(leaderboardADD.getView());
                        }
                    }
                }
            }
        }
    }

    public PlaylistManager getManager() {
        return manager;
    }

    public void reloadPlaylists(){
        PlaylistMenuController playlistMenu = new PlaylistMenuController(this, manager);
        scenes.put("Playlistscreen", playlistMenu.getView());
    }

    public void reloadLeaderboard(){
        LeaderboardController leaderboard = new LeaderboardController(this);
        scenes.put("Leaderboard", leaderboard.getView());
    }

    public Song getAktSong(){
        return player.getAktSong();
    }
}
