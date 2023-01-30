package presentation.scenes.victoryScreen;

import javafx.scene.text.Text;

public class VictoryScreenController {
    VictoryScreenView view;
    public VictoryScreenController(int highScore) {
        this.view = new VictoryScreenView();
        view.highScore.setText("Score: " + highScore);
    }

    public VictoryScreenView getView() {
        return view;
    }
}
