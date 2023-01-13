package presentation.scenes.gameScreen;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreenView extends StackPane {

    Button moveButton;

    public ImageView hero_view;
    public  ImageView boss_view;
    public GameScreenView() throws FileNotFoundException {
        hero_view = new ImageView();
        hero_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/character1.png")));

        boss_view = new ImageView();
        boss_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/boss1.png")));

        this.getStyleClass().add("gamescreen");
        this.getChildren().add(hero_view);
    }
}
