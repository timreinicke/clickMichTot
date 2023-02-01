package presentation.scenes.victoryScreen;

import application.GameApplication;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

/*
 * Victory screen after you win the game, switches to
 * leaderboard entry later
 */
public class VictoryScreenController {
    VictoryScreenView view;
    Button leaderBoard;
    Button mainMenu;
    int highScore;
    GameApplication application;
    public VictoryScreenController(int highScore, GameApplication application) {
        this.view = new VictoryScreenView();
        view.highScore.setText("Score: " + highScore);
        leaderBoard = view.leaderboard;
        mainMenu = view.mainMenu;
        this.highScore = highScore;
        this.application = application;
        initialize();
    }

    public void initialize() {
        mainMenu.setOnAction(e -> {
            try {
                application.switchScene("MainMenu");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        leaderBoard.setOnAction(e -> {
            try {
                application.switchScene("leaderBoardEntry", "leaderBoardEntry", String.valueOf(highScore));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public VictoryScreenView getView() {
        return view;
    }
}
