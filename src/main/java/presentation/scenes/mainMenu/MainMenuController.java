package presentation.scenes.mainMenu;

import application.GameApplication;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

public class MainMenuController {
    GameApplication application;
    private Button play;
    private Button leaderboard;
    private Button settings;

    private HBox settingsBox;

    MainMenuView view;
    public MainMenuController(GameApplication application){

        this.application = application;
        view = new MainMenuView();
        play = view.play;
        leaderboard = view.leaderboard;
        settings = view.settings;

        initialize();
    }
    public void initialize(){
        play.setOnAction(e -> {
            try {
                application.switchScene("GameScreen");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        leaderboard.setOnAction(e -> {
            try {
                application.switchScene("Leaderboard");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        settings.setOnAction(e -> {
            try {
                application.switchScene("Settings");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
