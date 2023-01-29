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

    HBox settings;
    HBox songSelection;
    StackPane coverPreview;
    VBox songInformation;

    HBox songPreview;
    HBox gameControls;


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
        backButton = new Button("Back to view all Playlists");
        settingsButton = new Button("settings");
        backButton.getStyleClass().addAll("backButton", "icon-button");
        settingsButton.getStyleClass().add("icon-button");
        viewName = new Label("Song Selection");

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

        gameControls = new HBox();
        difficulty_easy = new Button("EASY");
        difficulty_medium = new Button("MEDIUM");
        difficulty_hard = new Button("HARD");
        startGame = new Button("Start Game");
        difficulty_easy.getStyleClass().add("round-button");
        difficulty_medium.getStyleClass().add("round-button");
        difficulty_hard.getStyleClass().add("round-button");
        startGame.getStyleClass().add("round-button");

        settings.getChildren().addAll(backButton, settingsButton,viewName);
        coverPreview.getChildren().addAll(hero_view, hero2_view);
        songInformation.getChildren().addAll(songName, artist, duration);
        songPreview.getChildren().addAll(coverPreview, songInformation);
        gameControls.getChildren().addAll(difficulty_easy, difficulty_medium, difficulty_hard, startGame);

        this.setLeft(songSelection);

        this.setTop(settings);
        backButton.setAlignment(Pos.BASELINE_LEFT);
        viewName.setAlignment(Pos.BASELINE_CENTER);
        settingsButton.setAlignment(Pos.BASELINE_RIGHT);


        this.setCenter(songPreview);

        this.setBottom(gameControls);
    }
}
