package presentation.uicomponents.menu;

import application.GameApplication;
import business.MP3;

import java.io.FileNotFoundException;

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
            try {
                application.switchScene("PlaylistOverView");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        view.buttonPlayer.setOnAction(mouseEvent -> {
            try {
                application.switchScene("PlayerView");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        view.buttonSettings.setOnAction(mouseEvent -> {
            try {
                application.switchScene("SettingsView");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public MenuView getView(){
        return this.view;
    }
}
