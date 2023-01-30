package presentation.scenes.songMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import presentation.uicomponents.ImageViewPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SongMenuView extends BorderPane {

    HBox settings;
    HBox songSelection;
    Pane coverPreview;
    VBox songInformation;

    HBox songPreview;
    HBox gameControls;
    HBox difficultyControls;


    Button backButton;
    Button settingsButton;
    Label viewName;

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

        settings = new HBox();

        difficultyControls = new HBox();
        backButton = new Button("Back to view all Playlists");
        backButton.getStyleClass().addAll("return-button");

        settingsButton = new Button("SETTINGS");
        settingsButton.getStyleClass().add("return-button");
        settingsButton.setAlignment(Pos.CENTER_RIGHT);


        viewName = new Label("Song Selection");
        viewName.getStyleClass().addAll("title", "container");

        songSelection = new HBox();

        coverPreview = new Pane();
        hero_view = new ImageView();
        hero2_view = new ImageView();
        hero_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/hero_male.png")));
        hero2_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/hero_female.png")));


        songInformation = new VBox();
        songName = new Label();
        artist = new Label();
        duration = new Label();

        songPreview = new HBox();

        gameControls = new HBox();
        difficulty_easy = new Button("EASY");
        difficulty_medium = new Button("MEDIUM");
        difficulty_hard = new Button("HARD");
        startGame = new Button("START GAME");
        difficulty_easy.getStyleClass().add("round-button");
        difficulty_medium.getStyleClass().add("round-button");
        difficulty_hard.getStyleClass().add("round-button");
        startGame.getStyleClass().add("round-button");

        settings.setSpacing(20);
        settings.setPadding(new Insets(20));
        settings.getChildren().addAll(backButton, viewName, settingsButton);
        coverPreview.getChildren().addAll(hero_view, hero2_view);
        songInformation.getChildren().addAll(songName, artist, duration);
        songPreview.getChildren().addAll(coverPreview, songInformation);
        difficultyControls.getChildren().addAll(difficulty_easy, difficulty_medium, difficulty_hard);
        gameControls.getChildren().addAll(difficultyControls, startGame);

        this.setLeft(songSelection);

        this.setTop(settings);

        backButton.setAlignment(Pos.CENTER_LEFT);
        viewName.setAlignment(Pos.CENTER);
        settingsButton.setAlignment(Pos.CENTER_RIGHT);

        startGame.setAlignment(Pos.CENTER_RIGHT);
        this.setCenter(songPreview);
        this.setBottom(gameControls);

        this.getStyleClass().add("container");
    }
}
