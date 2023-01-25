package presentation.scenes.songMenu;

import application.GameApplication;
import business.PlaylistManager;
import presentation.scenes.playlistMenu.playlistMenuView;
import presentation.uicomponents.playlistShow.PlaylistShowController;

public class songMenuController {
        private songMenuView view;
        GameApplication application;
        PlaylistManager manager;
        PlaylistShowController playlistView;

        public songMenuController(GameApplication application, PlaylistManager manager){

            this.manager = manager;
            this.application = application;
            this.view = new songMenuView();
            playlistView = new PlaylistShowController(manager, application);

            view.getChildren().add(playlistView.getPlaylistWindow());
        }

        public songMenuView getView(){
            return view;
        }
}

