package presentation.scenes.songMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.FileInputStream;
import javafx.scene.layout.*;
import presentation.uicomponents.ImageViewPane;
import java.io.FileNotFoundException;


public class SongMenuView extends BorderPane {

    HBox settings;
    HBox songSelection;
    Pane coverPreview;
    VBox songInformation;

    VBox songPreview;
    HBox gameControls;
    HBox difficultyControls;


    Button backButton;
    Button settingsButton;
    Label viewName;
    Label heroName;
    HBox gamePrev;
    public ImageView hero_view;
    public ImageView hero2_view;
    public VBox femHero;
    public VBox maleHero;
    public ImageView coverView;

    Label songName;
    Label artist;
    Label duration;

    Button difficulty_easy;
    Button difficulty_medium;
    Button difficulty_hard;
    Button startGame;

    public SongMenuView() throws FileNotFoundException {

        Region fillerRegion1 = new Region();
        Region fillerRegion2 = new Region();
        HBox.setHgrow(fillerRegion1, Priority.ALWAYS);
        HBox.setHgrow(fillerRegion2, Priority.ALWAYS);

        coverPreview = new Pane();
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

        gamePrev = new HBox();

        hero_view = new ImageView();
        hero2_view = new ImageView();

        hero_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/hero_view_highres.png")));
        hero2_view.setImage(new Image(new FileInputStream("src/main/resources/application/images/hero_view_highres2.png")));

        femHero = new VBox();
        femHero.getChildren().add(hero2_view);
        hero2_view.getStyleClass().add("character");

        maleHero = new VBox();
        maleHero.getChildren().add(hero_view);
        hero_view.getStyleClass().add("character");

        heroName = new Label("Pick your Hero!");
        heroName.getStyleClass().add("list-cell_label");

        gamePrev.getChildren().addAll(femHero, maleHero);
        gamePrev.getStyleClass().add("gameBackground");

        gamePrev.setPrefWidth(600);
        gamePrev.setPrefHeight(600);

        gamePrev.setAlignment(Pos.CENTER);

        songInformation = new VBox();
        songName = new Label();
        songName.getStyleClass().add("list-cell_label");
        artist = new Label();
        artist.getStyleClass().add("list-cell_label");
        duration = new Label();
        duration.getStyleClass().add("list-cell_label");

        songPreview = new VBox();

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

        settings.getChildren().addAll(backButton, fillerRegion1, viewName, fillerRegion2, settingsButton);

        backButton.setAlignment(Pos.CENTER_RIGHT);
        settingsButton.setAlignment(Pos.CENTER_LEFT);

        songInformation.getChildren().addAll(songName, artist, duration);
        songInformation.setPadding(new Insets(20));

        songPreview.getChildren().addAll(gamePrev, heroName, songInformation);
        songPreview.setPadding(new Insets(20,300,20,20));
        songPreview.setAlignment(Pos.CENTER);

        difficultyControls.getChildren().addAll(difficulty_easy, difficulty_medium, difficulty_hard);
        gameControls.getChildren().addAll(difficultyControls, fillerRegion1, startGame);

        gameControls.setPadding(new Insets(20));
        difficultyControls.setSpacing(20);
        difficultyControls.setPadding(new Insets(20));
        gameControls.setPadding(new Insets(20));

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
