package presentation.scenes.settings;

public class SettingsController {

    SettingsView view;
    public SettingsController(){
        view = new SettingsView();
    }

    public SettingsView getView(){
        return view;
    }
}
