package presentation.scenes.gameScreen;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreenView extends StackPane {
    public ImageView hero_view;
    public  ImageView boss_view;
    public StackPane buttonView;
    public Text highScore;
    public Text health;

    public GameScreenView() throws FileNotFoundException {

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
        boss_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/boss1.png")));
        StackPane.setAlignment(boss_view, Pos.CENTER_RIGHT);

        buttonView = new StackPane();

        this.getStyleClass().add("gamescreen");
        this.getChildren().addAll(hero_view, boss_view, buttonView, highScore, health);
    }
}
