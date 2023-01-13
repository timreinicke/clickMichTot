package presentation.uicomponents.menu;

import application.GameApplication;
import business.MP3;

/*
 * Controller fuer das Menu, ist verantwortlich fuer Szenenaenderung
 */

public class MenuViewController {
    private final GameApplication application;
    private final MP3 player;
    private final MenuView view = new MenuView();



    public MenuViewController (GameApplication application, MP3 player){
        this.player = player;
        this.application = application;
        initialize();
    }

    /*
     * Eventhandler fuer unsere verschiedenen Buttons, um in der MP3PlayerApp application Szene zu aendern
     */

    public void initialize(){

        view.buttonPlaylist.setOnAction(mouseEvent -> {
            application.switchScene("PlaylistOverView");
        });

        view.buttonPlayer.setOnAction(mouseEvent -> {
            application.switchScene("PlayerView");
        });

        view.buttonSettings.setOnAction(mouseEvent -> {
            application.switchScene("SettingsView");
        });
    }

    public MenuView getView(){
        return this.view;
    }
}
