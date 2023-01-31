package presentation.scenes.defeatScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DefeatScreenView extends BorderPane {
    Label headline;
    Label highScore;
    Button mainMenu;
    VBox contentContainer;

    public DefeatScreenView() {
        headline = new Label("Failure!");
        headline.getStyleClass().addAll("title");

        highScore = new Label();
        highScore.getStyleClass().addAll("title");

        mainMenu = new Button("Main Menu");
        mainMenu.getStyleClass().add("menu-button");

        contentContainer = new VBox();
        BorderPane.setAlignment(contentContainer, Pos.CENTER);
        contentContainer.getChildren().addAll(headline, highScore, mainMenu);
        contentContainer.getStyleClass().addAll("center");
        contentContainer.setSpacing(20);

        this.getStyleClass().add("container");
        this.setCenter(contentContainer);
    }
}
