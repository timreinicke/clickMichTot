package presentation.scenes.mainMenu;

import application.GameApplication;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;

public class MainMenuController {
    GameApplication application;

    MainMenuView view;
    private final Button play;
    private final Button leaderboard;
    private final Button settings;
    private Button quit;

    /*
     * main menu to navigate through the game
     */
    public MainMenuController(GameApplication application){

        this.application = application;
        view = new MainMenuView();
        play = view.play;
        leaderboard = view.leaderboard;
        settings = view.settings;
        quit = view.quit;

        initialize();
    }
    public void initialize(){
        quit.setOnAction(e -> {
            System.exit(0);
        });

        play.setOnAction(e -> {
            try {
                application.switchScene("PlaylistScreen");
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

    public MainMenuView getView(){
        return view;
    }
}
