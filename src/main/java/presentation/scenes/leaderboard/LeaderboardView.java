package presentation.scenes.leaderboard;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

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

        /*
         * view for leaderboard
         */

        Region fillerRegion1 = new Region();
        Region fillerRegion2 = new Region();
        HBox.setHgrow(fillerRegion1, Priority.ALWAYS);
        HBox.setHgrow(fillerRegion2, Priority.ALWAYS);

        scrollPane = new ScrollPane();
        controls = new HBox();
        leaderboardWindow = new VBox();

        backButton = new Button("BACK TO MAIN MENU");
        backButton.getStyleClass().add("return-button");
        settingsButton = new Button("Settings");
        settingsButton.getStyleClass().add("return-button");
        leaderBoardLabel = new Label("Our POG Champs");
        leaderBoardLabel.getStyleClass().add("title");
        viewName = new Label("Leaderboard");
        viewName.getStyleClass().add("title");

        leaders = new ListView<>();
        leaders.getStyleClass().add("list");
        leaders.setPrefWidth(1200);
        leaders.setMaxWidth(1200);

        scrollPane.setContent(leaders);
        scrollPane.setFitToWidth(true);

        leaderboardWindow.getChildren().addAll(leaderBoardLabel, leaders);
        leaders.setPrefWidth(1200);
        leaderboardWindow.setPadding(new Insets(20));
        controls.setPadding(new Insets(20));
        controls.getChildren().addAll(backButton, fillerRegion1, viewName, fillerRegion2, settingsButton);

        this.setTop(controls);
        this.setCenter(leaderboardWindow);
        this.getStyleClass().add("container");
    }
}
