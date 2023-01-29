package presentation.scenes.mainMenu;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuView extends BorderPane {

    protected Label viewName;
    protected Button play;
    protected Button leaderboard;
    protected Button settings;

    protected VBox settingsBox;

    public MainMenuView() {
        viewName = new Label("Main Menu");
        play = new Button("PLAY");
        leaderboard = new Button("LEADERBOARD");
        settings = new Button("SETTINGS");

        settingsBox = new VBox();

        settingsBox.getChildren().addAll(play, leaderboard, settings);
        this.setCenter(settingsBox);
        this.setTop(viewName);
    }
}
