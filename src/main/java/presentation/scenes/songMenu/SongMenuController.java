package presentation.scenes.songMenu;

import application.GameApplication;
import business.MP3;
import business.PlaylistManager;
import presentation.uicomponents.playlistShow.PlaylistShowController;
import presentation.uicomponents.songlistShow.SonglistController;

public class SongMenuController {
        private static SongMenuView view;
        GameApplication application;
        PlaylistManager manager;
        MP3 player;
        SonglistController songView;

        public SongMenuController(GameApplication application, PlaylistManager manager, MP3 player){

            this.manager = manager;
            this.application = application;
            this.player = player;
            this.view = new SongMenuView();
            songView = new SonglistController(manager,application, player);

            view.getChildren().add(songView.getSongView());
        }

        public static SongMenuView getView(){
            return view;
        }
}

