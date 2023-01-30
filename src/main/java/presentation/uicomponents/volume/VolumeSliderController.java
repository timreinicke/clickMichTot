package presentation.uicomponents.volume;

import business.MP3;
import business.PlaylistManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class VolumeSliderController {

    Slider volumeSlider;
    VolumeSlider view;
    MP3 player;
    PlaylistManager manager;

    public VolumeSliderController(MP3 player){
        view = new VolumeSlider();
        this.volumeSlider = view.volumeSlider;
        this.player = player;
        initialize();
    }
    public void volume (){
    volumeSlider.valueProperty().addListener(
        new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number nV) {
                float volume;
                volume = (float) nV.doubleValue();
                player.setVolumeProperty(volume);
            }
        });
    }

    public void initialize (){
        volume();
    }

    public HBox getView() {
        return view;
    }
}
