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
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;

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
    private Song prevSong;
    private Song nextSong;
    private Song aktSong;
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

    }

    /*
     * playSong setzt unsere Merkvariable für Pause auf false und setzt letzen, aktuellen und naechsten Song
     * Falls der Audioplayer noch keine Musik spielt, laed er das MP3-File
     * Falls playSong aber schon Musikspielt, wird er pausiert, da sonst die Lieder in verschiedenen Threads uebereinander spielen
     */


    /*
     * erstellt einen neuen Thread um den Timer eines Liedes zu merken
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
                if(aktSong.getDuration() == currTime.getValue() / 1000){
                }
            }
        });
    }

    /*
     * Play fragt ab, ob das Lied pausiert ist, wenn ja spielt er ab dem pausierten Zeitpunkt weiter.
     * Ansonsten laedt er den neuen Song und setzt ihn auf einen neuen Pfad
     */

    public void play() throws IOException, SongNotFoundException {
        if (isPaused) {
            playAtTime();
            return;
        }
        songName.setValue(aktSong.getTitle());
        audioPlayer = minim.loadMP3File(aktSong.getFilename());
        currTime.setValue(0);
        playThread = new Thread(() -> audioPlayer.play());

        timerThread = createTimerThread();
        playThread.start();
        timerThread.start();
    }

    /*
     * Laedt aktuellen Song und spielt ihn mit der letzten gemerkten Zeit ab und startet den Thread
     */

    public void playAtTime() {
        audioPlayer = minim.loadMP3File(aktSong.getFilename());
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
            return aktSong;
        }
        return null;
    }
}

