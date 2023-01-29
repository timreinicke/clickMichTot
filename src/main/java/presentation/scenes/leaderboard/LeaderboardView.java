package presentation.scenes.leaderboard;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LeaderboardView extends Pane {

    ScrollPane scrollPane;
    VBox controls;
    HBox leaderboardWindow;
    Button backButton;
    Label leaderBoardLabel;
    protected ListView<String> leaders;

    public LeaderboardView(){

        scrollPane = new ScrollPane();
        controls = new VBox();
        leaderboardWindow = new HBox();

        backButton = new Button();
        backButton.getStyleClass().add("backButton");
        leaderBoardLabel = new Label("Leaderboard");
        leaders = new ListView<>();

        scrollPane.setContent(leaders);
        scrollPane.setFitToWidth(true);

        leaderboardWindow.getChildren().addAll(leaderBoardLabel, leaders);
        controls.getChildren().add(backButton);
        this.getChildren().addAll(controls, leaderboardWindow);
    }
}
