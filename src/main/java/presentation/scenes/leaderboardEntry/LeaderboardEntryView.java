package presentation.scenes.leaderboardEntry;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class LeaderboardEntryView extends BorderPane {
    Label viewName;
    TextField getPlayerName;
    Button submit;
    Button continueOn;

    HBox playerEntry;
    public LeaderboardEntryView(){
        viewName = new Label("LEADERBOARD ENTRY");
        getPlayerName = new TextField();
        getPlayerName.setText("Enter Playername");
        submit = new Button("SUBMIT");
        continueOn = new Button("CONTINUE");

        playerEntry = new HBox();

        playerEntry.getChildren().addAll(getPlayerName, submit);

        this.setTop(viewName);
        this.setCenter(playerEntry);
        this.setBottom(continueOn);

        viewName.setAlignment(Pos.BASELINE_CENTER);
        continueOn.setAlignment(Pos.BASELINE_RIGHT);
    }
}
