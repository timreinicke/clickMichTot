package presentation.scenes.leaderboardEntry;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LeaderboardEntryView extends BorderPane {
    Label viewName;
    TextField getPlayerName;
    Button submit;
    Button continueOn;
    VBox contentContainer;
    VBox playerEntry;
    Label playerNameLabel;
    HBox topContainer;
    HBox bottomContainer;

    public LeaderboardEntryView(){
        contentContainer = new VBox();
        topContainer = new HBox();
        bottomContainer = new HBox();

        viewName = new Label("LEADERBOARD ENTRY");
        viewName.getStyleClass().add("title");
        viewName.setAlignment(Pos.CENTER);

        playerNameLabel = new Label("Enter Playername");
        playerNameLabel.getStyleClass().add("input-label");
        getPlayerName = new TextField();
        getPlayerName.getStyleClass().add("input-field");
        getPlayerName.prefWidth(200);
        getPlayerName.setMaxWidth(500);

        submit = new Button("SUBMIT");
        submit.getStyleClass().add("return-button");

        continueOn = new Button("BACK TO MAIN MENU");
        continueOn.getStyleClass().add("return-button");

        playerEntry = new VBox();
        playerEntry.getChildren().addAll(playerNameLabel, getPlayerName, submit);
        playerEntry.getStyleClass().add("center");
        playerEntry.setSpacing(20);
        playerEntry.setPrefWidth(500);

        topContainer.getChildren().add(viewName);
        topContainer.getStyleClass().add("center");
        topContainer.setPadding(new Insets(20));

        bottomContainer.getChildren().add(continueOn);
        bottomContainer.getStyleClass().add("right-center");
        bottomContainer.setPadding(new Insets(20));

        this.setTop(topContainer);
        this.setCenter(playerEntry);
        this.setBottom(bottomContainer);
        this.getStyleClass().addAll("container", "center");
    }
}
