package presentation.scenes.defeatScreen;


import application.GameApplication;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class DefeatScreenController {

    /*
     * Defeat Scene in case you lose all your health
     */
    DefeatScreenView view;
     Button mainMenu;
     GameApplication application;
    public DefeatScreenController(int highScore, GameApplication application) {
        this.view =  new DefeatScreenView();
        this.mainMenu = view.mainMenu;
        this.application = application;
        view.highScore.setText("Score: " + highScore);
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
    }

    public DefeatScreenView getView() {
        return view;
    }
}
