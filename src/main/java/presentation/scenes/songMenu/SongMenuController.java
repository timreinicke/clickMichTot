package presentation.scenes.songMenu;

import application.GameApplication;
import business.MP3;
import business.PlaylistManager;
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

        public SongMenuController(GameApplication application, PlaylistManager manager, MP3 player) throws FileNotFoundException {

            this.manager = manager;
            this.application = application;
            this.player = player;

            this.view = new SongMenuView();

            songName = view.songName;
            artist = view.artist;
            duration = view.duration;

            songContent = new SonglistController(manager,application, player);
            view.songSelection.getChildren().add(songContent.getSongView());

           /* view.settings.getChildren().addAll(view.backButton, view.settingsButton);
            view.songSelection.getChildren().add(songContent.getSongView());
            view.coverPreview.getChildren().addAll(view.hero_view, view.hero2_view);
            view.songInformation.getChildren().addAll(songName, artist, duration);
            view.songPreview.getChildren().addAll(view.coverPreview, view.songInformation);
            view.gameControls.getChildren().addAll(view.difficulty_easy, view.difficulty_medium, view.difficulty_hard, view.startGame);
            view.getChildren().addAll(view.settings, view.songSelection, view.songPreview, view.gameControls);

            view.settings.setAlignment(Pos.TOP_CENTER);
            view.songSelection.setAlignment(Pos.CENTER_LEFT);
            view.songPreview.setAlignment(Pos.CENTER_RIGHT);
            view.gameControls.setAlignment(Pos.BOTTOM_CENTER);*/
        }

        public void initialize(){
            view.backButton.setOnAction(e -> {
                try {
                    application.switchScene("PlaylistScreen");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });




        }
        public static void reload() {
            songName.setText(player.getAktSong().getTitle());
            artist.setText(player.getAktSong().getArtist());
            duration.setText(Integer.toString(player.getAktSong().getDuration()));
        }
        public static SongMenuView getView(){
            return view;
        }
}

