package presentation.scenes.leaderboard;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaderboardView extends BorderPane {

    ScrollPane scrollPane;
    HBox controls;
    VBox leaderboardWindow;
    Button backButton;
    Button settingsButton;
    Label leaderBoardLabel;
    Label viewName;
    protected ListView<String> leaders;

    public LeaderboardView(){

        scrollPane = new ScrollPane();
        controls = new HBox();
        leaderboardWindow = new VBox();

        backButton = new Button("back");
        settingsButton = new Button("Settings");
        backButton.getStyleClass().add("backButton");
        leaderBoardLabel = new Label("Our POG Champs");
        viewName = new Label("Leaderboard");
        leaders = new ListView<>();

        scrollPane.setContent(leaders);
        scrollPane.setFitToWidth(true);

        leaderboardWindow.getChildren().addAll(leaderBoardLabel, leaders);
        controls.getChildren().addAll(backButton, settingsButton, viewName);

        this.setTop(controls);
        this.setCenter(leaderboardWindow);
    }
}
