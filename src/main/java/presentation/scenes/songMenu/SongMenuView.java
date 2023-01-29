package presentation.scenes.songMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import presentation.uicomponents.ImageViewPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SongMenuView extends BorderPane {

    VBox settings;
    HBox songSelection;
    StackPane coverPreview;
    VBox songInformation;

    HBox songPreview;
    VBox gameControls;


    Button backButton;
    Button settingsButton;

    String coverDir = "src/main/resources/application/music/album_artworks/";
    ImageViewPane coverViewPane;
    public ImageView hero_view;
    public ImageView hero2_view;

    Label songName;
    Label artist;
    Label duration;

    Button difficulty_easy;
    Button difficulty_medium;
    Button difficulty_hard;
    Button startGame;
    public SongMenuView() throws FileNotFoundException {
        settings = new VBox();
        backButton = new Button("Back to view all Playlists");
        settingsButton = new Button("settings");
        backButton.getStyleClass().addAll("backButton", "icon-button");
        settingsButton.getStyleClass().add("icon-button");

        songSelection = new HBox();

        coverPreview = new StackPane();
        hero_view = new ImageView();
        hero2_view = new ImageView();
        hero_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/hero_male.png")));
        hero2_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/boss1.png")));


        songInformation = new VBox();
        songName = new Label();
        artist = new Label();
        duration = new Label();

        songPreview = new HBox();

        gameControls = new VBox();
        difficulty_easy = new Button("EASY");
        difficulty_medium = new Button("MEDIUM");
        difficulty_hard = new Button("HARD");
        startGame = new Button("Start Game");
        difficulty_easy.getStyleClass().add("round-button");
        difficulty_medium.getStyleClass().add("round-button");
        difficulty_hard.getStyleClass().add("round-button");
       startGame.getStyleClass().add("round-button");

        settings.getChildren().addAll(backButton, settingsButton);
        coverPreview.getChildren().addAll(hero_view, hero2_view);
        songInformation.getChildren().addAll(songName, artist, duration);
        songPreview.getChildren().addAll(coverPreview, songInformation);
        gameControls.getChildren().addAll(difficulty_easy, difficulty_medium, difficulty_hard, startGame);
        this.getChildren().addAll(settings, songSelection, songPreview, gameControls);

        /*settings.setAlignment(Pos.TOP_CENTER);
        songSelection.setAlignment(Pos.CENTER_LEFT);
        songPreview.setAlignment(Pos.CENTER_RIGHT);
        gameControls.setAlignment(Pos.BOTTOM_CENTER);*/
    }
}
