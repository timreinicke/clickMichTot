package presentation.uicomponents.volume;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class VolumeSlider extends HBox {

    final double MAXVALUE = 50;
    final double MINVALUE = -50;
    final double STARTVALUE = -20;
    double currVolume;
    Slider volumeSlider;
    Button volumeButton;
    public VolumeSlider () {
        //this.getStylesheets().add(getClass().getResource("playlistoverview.css").toExternalForm());
        volumeSlider = new Slider(MINVALUE, MAXVALUE, STARTVALUE);
        volumeButton = new Button();
        volumeButton.getStyleClass().addAll("icon-button", "volumeButton");
        this.getStyleClass().addAll("center");
        this.getChildren().addAll(volumeButton, volumeSlider);
    }

}
