package presentation.scenes.victoryScreen;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class VictoryScreenView extends Pane {
    Text headline;
    Text highScore;
    Button restart;
    Button leaderboard;
    Button mainMenu;


    public VictoryScreenView() {
        headline = new Text("Success!");
        highScore = new Text();
        restart = new Button("Restart");
        mainMenu = new Button("Main Menu");
        leaderboard = new Button("Enter Leaderboard");

        this.getChildren().addAll(headline, highScore, restart, mainMenu, leaderboard);
    }
}
