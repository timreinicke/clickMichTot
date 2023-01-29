package business;

import application.GameApplication;
import application.GameApplication;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import exceptions.SongNotFoundException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import listener.SonglistenerInterface;
import presentation.scenes.songMenu.SongMenuController;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

/*
 * Thread und Playerverwaltung, um Einstellungen auf Musik vorzunehmen (skip, play, pause..)
 */

public class MP3 extends Thread {

    private final SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private final SimpleIntegerProperty currTime;
    private final SimpleStringProperty songName;
    boolean shuffle = false;
    boolean repeat = false;
    private final PlaylistManager manager;
    SimpleObjectProperty<Song> aktSong;
    private boolean isPaused;
    private Thread timerThread;
    private Thread playThread;

    public MP3(GameApplication application) throws IOException {
        minim = new SimpleMinim();
        /*audioPlayer = minim.loadMP3File("src/main/resources/application/music/tracks/on_sale.mp3");
        audioPlayer.play();

        AudioInput auInput = minim.getLineIn(Minim.STEREO, 1024);
        BeatDetect beatDetect = new BeatDetect(1024, 44100.0f);
        beatDetect.setSensitivity(1000);
        */
        currTime = new SimpleIntegerProperty(0);
        manager = application.getManager();
        songName = new SimpleStringProperty();
        aktSong = new SimpleObjectProperty<>();

        aktSong.addListener(e -> SongMenuController.reload());
    }

    public void pause() throws InterruptedException {
        isPaused = true;
        audioPlayer.pause();
        timerThread.interrupt();
    }

    public void volume(float value) {
        audioPlayer.setGain(value);
    }

    public SimpleIntegerProperty currTimeProperty() {
        return currTime;
    }

    public SimpleStringProperty songNameProperty() {
        return songName;
    }

    public Song getAktSong() {
        if (aktSong != null) {
            return aktSong.getValue();
        }
        return null;
    }

    public void setAktSong(Song s){
        this.aktSong.setValue(s);
    }
}

