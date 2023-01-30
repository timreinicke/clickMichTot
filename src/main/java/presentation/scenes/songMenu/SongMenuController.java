package presentation.scenes.songMenu;

import application.GameApplication;
import business.MP3;
import business.PlaylistManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import listener.SonglistenerInterface;
import presentation.uicomponents.ImageViewPane;
import presentation.uicomponents.songlistShow.SonglistController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SongMenuController {
        private static SongMenuView view;
        GameApplication application;
        PlaylistManager manager;
        static MP3 player;

        SonglistenerInterface songchange;
        SonglistController songContent;

        ImageViewPane coverViewPane;

        public static Label songName;
        public static Label artist;
        public static Label duration;
        public Button startGame;
        protected Button backButton;

        protected Button settings;
        protected String coverDir = "src/main/resources/application/music/album_artworks/";

        public SongMenuController(GameApplication application, PlaylistManager manager, MP3 player) throws FileNotFoundException {

            this.manager = manager;
            this.application = application;
            this.player = player;

            this.view = new SongMenuView();

            songName = view.songName;
            artist = view.artist;
            duration = view.duration;

            backButton = view.backButton;
            settings = view.settingsButton;
            startGame = view.startGame;

            view.coverView.setImage(new Image(new FileInputStream("src/main/resources/application/images/game_arena.png")));


            songContent = new SonglistController(manager,application, player);
            view.songSelection.getChildren().add(songContent.getSongView());

            initialize();
        }

        public void initialize(){
            backButton.setOnAction(e -> {
                try {
                    application.switchScene("PlaylistScreen");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            settings.setOnAction(e-> {
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

