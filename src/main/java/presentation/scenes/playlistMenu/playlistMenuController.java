package presentation.scenes.playlistMenu;

import application.GameApplication;
import business.PlaylistManager;
import presentation.uicomponents.playlistShow.PlaylistShowController;

public class playlistMenuController {
    private playlistMenuView view;
    GameApplication application;
    PlaylistManager manager;
    PlaylistShowController playlistView;
    public playlistMenuController(GameApplication application, PlaylistManager manager){
        this.manager = manager;
        this.application = application;
        this.view = new playlistMenuView();
        playlistView = new PlaylistShowController(manager, application);

        view.getChildren().add(playlistView.getPlaylistWindow());
    }

    public playlistMenuView getView(){return view;}
}
