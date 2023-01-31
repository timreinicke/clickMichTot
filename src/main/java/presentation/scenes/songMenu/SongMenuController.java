package presentation.scenes.songMenu;

import application.GameApplication;
import business.Einstellungen;
import business.MP3;
import business.PlaylistManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import presentation.uicomponents.ImageViewPane;
import presentation.uicomponents.songlistShow.SonglistController;

import java.io.FileNotFoundException;

public class SongMenuController {
        private static SongMenuView view;
        GameApplication application;
        PlaylistManager manager;
        static MP3 player;
        SonglistController songContent;

        ImageViewPane coverViewPane;

        public static Label songName;
        public static Label artist;
        public static Label duration;
        public Button startGame;
        protected Button backButton;

        public Button difficulty_easy;
        public Button difficulty_medium;
        public Button difficulty_hard;
        Einstellungen settings;
        protected Button settingsButton;
        protected String coverDir = "src/main/resources/application/music/album_artworks/";
        protected VBox heroview;
        protected VBox heroview2;


        public SongMenuController(GameApplication application, PlaylistManager manager, MP3 player, Einstellungen settings) throws FileNotFoundException {

            this.manager = manager;
            this.application = application;
            this.player = player;
            this.settings = settings;
            this.view = new SongMenuView();

            heroview = view.femHero;
            heroview2 = view.maleHero;

            songName = view.songName;
            artist = view.artist;
            duration = view.duration;

            backButton = view.backButton;
            settingsButton = view.settingsButton;
            startGame = view.startGame;

            difficulty_easy = view.difficulty_easy;
            difficulty_medium = view.difficulty_medium;
            difficulty_hard = view.difficulty_hard;



            songContent = new SonglistController(manager,application, player);
            view.songSelection.getChildren().add(songContent.getSongView());

            initialize();
        }

        public void initialize(){
            heroview.setOnMouseClicked(e -> {
                settings.setHeroDir("src/main/resources/application/images/hero_female.png");
                view.heroName.setText("You Picked Sophia!");
            });

            heroview2.setOnMouseClicked(e -> {
                settings.setHeroDir("src/main/resources/application/images/hero_male.png");
                view.heroName.setText("You Picked Tim!");
            });

            difficulty_easy.setOnAction(e -> {
                settings.setDifficulty(0);
                difficulty_easy.getStyleClass().add("active");
                difficulty_medium.getStyleClass().remove("active");
                difficulty_hard.getStyleClass().remove("active");
            });

            difficulty_medium.setOnAction(e -> {
                settings.setDifficulty(1);
                difficulty_easy.getStyleClass().remove("active");
                difficulty_medium.getStyleClass().add("active");
                difficulty_hard.getStyleClass().remove("active");
            });

            difficulty_hard.setOnAction(e -> {
                settings.setDifficulty(2);
                difficulty_easy.getStyleClass().remove("active");
                difficulty_medium.getStyleClass().remove("active");
                difficulty_hard.getStyleClass().add("active");
            });

            backButton.setOnAction(e -> {
                try {
                    application.switchScene("PlaylistScreen");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            settingsButton.setOnAction(e-> {
                    try {
                        application.switchScene("Settings");
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
            });

            startGame.setOnAction(e -> {
                try {
                    application.switchScene("GameScreen", "GameScreen", player.getAktSong().getFilename());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

        }
        public static void reload() {
            songName.setText("Title : " + player.getAktSong().getTitle());
            artist.setText("Artist : " + player.getAktSong().getArtist());
            duration.setText("Duration : " + player.getAktSong().getDuration() / 60 + ":" + player.getAktSong().getDuration() % 60 + " minutes");
        }
        public static SongMenuView getView(){
            return view;
        }

}

