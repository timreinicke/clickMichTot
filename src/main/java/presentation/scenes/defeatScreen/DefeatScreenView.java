package presentation.scenes.defeatScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DefeatScreenView extends StackPane {

    Text headline;
    Text highScore;
    Button mainMenu;
    VBox contentContainer;

    public DefeatScreenView() {
        headline = new Text("Failure!");
        highScore = new Text();
        mainMenu = new Button("Main Menu");
        contentContainer = new VBox();
        StackPane.setAlignment(contentContainer, Pos.CENTER);
        contentContainer.getChildren().addAll(headline, highScore, mainMenu);
        this.getChildren().add(contentContainer);

    }
}
