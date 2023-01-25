package presentation.scenes.menuScreen;

import application.GameApplication;
import business.Playlist;
import business.PlaylistManager;
import javafx.scene.layout.Pane;
import presentation.uicomponents.menu.MenuView;
import presentation.uicomponents.playlistShow.PlaylistShowController;

public class MenuScreenController {
    private MenuScreenView view;
    GameApplication application;
    PlaylistManager manager;
    PlaylistShowController playlistView;
    public MenuScreenController(GameApplication application, PlaylistManager manager){
        this.manager = manager;
        this.application = application;
        view = new MenuScreenView();
        playlistView = new PlaylistShowController(manager, application);
    }

    public MenuScreenView getView(){return view;}
}
