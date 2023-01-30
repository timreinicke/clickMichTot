package presentation.scenes.mainMenu;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuView extends BorderPane {

    protected Label viewName;
    protected Button play;
    protected Button leaderboard;
    protected Button settings;
    protected Button quit;
    protected VBox settingsBox;

    public MainMenuView() {
        viewName = new Label("Main Menu");
        viewName.getStyleClass().addAll("title", "center");
        BorderPane.setAlignment(viewName, Pos.CENTER);

        play = new Button("PLAY");
        play.getStyleClass().add("menu-button");
        leaderboard = new Button("LEADERBOARD");
        leaderboard.getStyleClass().add("menu-button");
        settings = new Button("SETTINGS");
        settings.getStyleClass().add("menu-button");

        quit = new Button("QUIT");
        quit.getStyleClass().add("menu-button");

        settingsBox = new VBox();

        settingsBox.getChildren().addAll(viewName, play, leaderboard, settings, quit);
        this.setCenter(settingsBox);
        settingsBox.getStyleClass().addAll("container", "center");
        settingsBox.setSpacing(20);
        this.getStyleClass().add("container");
    }
}
