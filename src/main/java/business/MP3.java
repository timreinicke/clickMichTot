package business;

import application.GameApplication;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import exceptions.SongNotFoundException;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import presentation.scenes.songMenu.SongMenuController;

import java.io.IOException;

/*
 * Management for songs that are playing and thread management as well as functions needed
 */

public class MP3 extends Thread {

    private final SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private final SimpleIntegerProperty currTime;
    private final SimpleStringProperty songName;
    private final PlaylistManager manager;
    SimpleObjectProperty<Song> aktSong;
    private boolean isPaused;
    private Thread timerThread;
    private Thread playThread;

    SimpleFloatProperty volumeProperty;

    public MP3(GameApplication application) throws IOException {
        minim = new SimpleMinim();
        currTime = new SimpleIntegerProperty(0);
        manager = application.getManager();
        songName = new SimpleStringProperty();
        aktSong = new SimpleObjectProperty<>();
        volumeProperty = new SimpleFloatProperty(-20);
        aktSong.addListener(e -> SongMenuController.reload());
    }
    public void playSong() throws SongNotFoundException, IOException {
        isPaused = false;

        if (audioPlayer == null) {
            audioPlayer = minim.loadMP3File(aktSong.get().getFilename());
        } else if (audioPlayer.isPlaying()) {
            audioPlayer.pause();
            timerThread.interrupt();
        }

        timerThread = createTimerThread();

        play();
    }

    /*
     * created new Timer Thread to keep a second song
     */

    public Thread createTimerThread() {
        return new Thread(() -> {
            while (playThread.isAlive()) {
                try {
                    Thread.sleep(1000);
                    currTime.setValue(currTime.getValue() + 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
    public void play() throws IOException, SongNotFoundException {
        if (isPaused) {
            playAtTime();
            return;
        }
        songName.setValue(aktSong.get().getTitle());
        audioPlayer = minim.loadMP3File(aktSong.get().getFilename());
        currTime.setValue(0);
        playThread = new Thread(() -> audioPlayer.play());

        timerThread = createTimerThread();
        playThread.start();
        timerThread.start();
    }

    /*
     * loads current song at a certain time
     */

    public void playAtTime() {
        audioPlayer = minim.loadMP3File(aktSong.get().getFilename());
        playThread = new Thread(() -> audioPlayer.play(currTime.getValue()));
        timerThread = createTimerThread();
        playThread.start();
        timerThread.start();
    }

    public void pause() throws InterruptedException {
        isPaused = true;
        audioPlayer.pause();
        timerThread.interrupt();
    }

    public void volume(float value) {
        //audioPlayer.setGain(value);
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
    public SimpleAudioPlayer getAudioPlayer(){
        return audioPlayer;
    }

    public void setVolumeProperty(float value){
        volumeProperty.set(value);
    }

    public float getVolumeProperty(){
        return volumeProperty.get();
    }
}


