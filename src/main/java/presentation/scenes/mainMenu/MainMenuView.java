package presentation.scenes.mainMenu;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainMenuView extends Pane {

    protected Button play;
    protected Button leaderboard;
    protected Button settings;

    protected HBox settingsBox;

    public MainMenuView() {
        play = new Button("PLAY");
        leaderboard = new Button("LEADERBOARD");
        settings = new Button("SETTINGS");

        settingsBox = new HBox();

        settingsBox.getChildren().addAll(play, leaderboard, settings);
        this.getChildren().add(settingsBox);
    }
}
