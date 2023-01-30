package presentation.scenes.defeatScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DefeatScreenView extends Pane {

    Text headline;
    Text highScore;
    Button mainMenu;
    VBox contentContainer;

    public DefeatScreenView() {
        headline = new Text("Failure!");
        this.getStyleClass().addAll("title", "center");
        highScore = new Text();
        mainMenu = new Button("Main Menu");
        mainMenu.getStyleClass().add("menu-button");
        contentContainer = new VBox();
        StackPane.setAlignment(contentContainer, Pos.CENTER);
        contentContainer.getChildren().addAll(headline, highScore, mainMenu);
        this.getStyleClass().add("container");
        this.getChildren().add(contentContainer);

    }
}
