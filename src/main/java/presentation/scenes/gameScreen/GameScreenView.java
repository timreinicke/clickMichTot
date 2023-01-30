package presentation.scenes.gameScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import presentation.uicomponents.volume.VolumeSliderController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreenView extends StackPane {
    public ImageView hero_view;
    public  ImageView boss_view;
    public StackPane buttonView;
    public Text highScore;
    public Text health;

    public Rectangle transparentPane;

    public StackPane parentSettings;
    public VBox groupSettings;
    public Rectangle gameSettings;
    public Button toMainMenu;
    public Button quitGame;
    public VolumeSliderController volume;
    public GameScreenView() throws FileNotFoundException {

        parentSettings = new StackPane();
        groupSettings = new VBox();
        groupSettings.setPrefHeight(this.getHeight()/2);
        groupSettings.setPrefWidth(this.getWidth()/2);

        transparentPane = new Rectangle(this.getWidth(),this.getHeight(), Color.rgb(0, 0, 0, 0.5));

        toMainMenu = new Button("Main Menu");
        toMainMenu.getStyleClass().add("round-button");

        quitGame = new Button("QUIT GAME");
        quitGame.getStyleClass().add("round-button");

        groupSettings.getChildren().addAll(toMainMenu, quitGame);
        parentSettings.getChildren().addAll(transparentPane, groupSettings);

        groupSettings.setSpacing(10);
        groupSettings.setPadding(new Insets(10));
        groupSettings.setAlignment(Pos.CENTER);
        parentSettings.setAlignment(Pos.CENTER);

        //gameSettings = new Rectangle(this.getWidth()/2,this.getHeight()/2, Color.rgb(255,255,255));


        highScore = new Text("Highscore: 0");
        highScore.getStyleClass().add("highscore");
        StackPane.setAlignment(highScore, Pos.TOP_RIGHT);

        health = new Text("Health: 10");
        health.getStyleClass().add("highscore");
        StackPane.setAlignment(health, Pos.BOTTOM_LEFT);

        hero_view = new ImageView();
        hero_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/hero_male.png")));
        StackPane.setAlignment(hero_view, Pos.CENTER_LEFT);

        boss_view = new ImageView();
        boss_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/boss.png")));
        StackPane.setAlignment(boss_view, Pos.CENTER_RIGHT);

        buttonView = new StackPane();

        this.getStyleClass().add("gamescreen");
        this.getChildren().addAll(hero_view, boss_view, buttonView, highScore, health);
    }
}
