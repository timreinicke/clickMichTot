package presentation.scenes.victoryScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.border.Border;

/*
 * view for victory screen
 */
public class VictoryScreenView extends BorderPane {
    Label headline;
    Label highScore;
    Button restart;
    Button leaderboard;
    Button mainMenu;
    VBox container;
    HBox buttonContainer;

    /*
     * View for victory screen
     */

    public VictoryScreenView() {
        container = new VBox();

        headline = new Label("Success!");
        headline.getStyleClass().add("title");

        highScore = new Label();
        highScore.getStyleClass().add("title");

        mainMenu = new Button("Main Menu");
        mainMenu.getStyleClass().add("return-button");

        leaderboard = new Button("Enter Leaderboard");
        leaderboard.getStyleClass().add("return-button");

        buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(mainMenu, leaderboard);
        buttonContainer.getStyleClass().add("center");
        buttonContainer.setSpacing(20);

        container.getChildren().addAll(headline, highScore, buttonContainer);
        container.getStyleClass().add("center");
        container.setSpacing(20);

        BorderPane.setAlignment(container, Pos.CENTER);
        this.setCenter(container);
        this.getStyleClass().addAll("container");
    }
}
