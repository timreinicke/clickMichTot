package presentation.scenes.gameScreen;

import application.GameApplication;
import business.Einstellungen;
import business.MP3;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import exceptions.SongNotFoundException;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import presentation.uicomponents.volume.VolumeSliderController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreenController extends Thread {

    /*
     * Logic behind our Game
     */

    private final GameScreenView view;
    private final ImageView hero_view;
    private final StackPane buttonView;
    private Mp3File mp3File;
    private final String filename;
    private int heroHealth = 10;
    AudioPlayer asyncPlayer;
    MP3 player;
    GameApplication application;
    int sensitivity;
    private boolean isPaused = false;
    private final IntegerProperty highScore = new SimpleIntegerProperty(0);
    Einstellungen settings;

    VolumeSliderController volume;

    public GameScreenController(String filename, MP3 player, GameApplication application, Einstellungen settings) throws FileNotFoundException {
        this.settings = settings;
        this.view = new GameScreenView();
        this.hero_view = view.hero_view;
        this.filename = filename;
        buttonView = view.buttonView;
        this.player = player;
        this.application = application;
        volume = new VolumeSliderController(player);

        view.groupSettings.getChildren().add(volume.getView());

        /*
         *  The numbers are our difficulties, default is easy, 1 is medium and 2 is hard
         */

        switch (settings.getDifficulty()) {
            case 1 -> sensitivity = 800;
            case 2 -> sensitivity = 500;
            default -> sensitivity = 1000;
        }

        view.hero_view.setImage(new Image(new FileInputStream(settings.getHeroDir())));

        buttonSpawn().start();
        intialize();
    }

    public static class MinimInput {
        public String sketchPath(String fileName) {
            return fileName;
        }

        public InputStream createInput(String fileName) {
            InputStream is = null;
            try {
                is = new FileInputStream(sketchPath(fileName));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return is;
        }

        ;
    }

    /*
     * Generates our buttons with a short delay
     * It checks if the beat is in range, if it is it will spawn a button
     * It also despawns our buttons
     */

    public Thread buttonSpawn() {
        return new Thread(() -> {
            try {
                mp3File = new Mp3File(this.filename);
            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                throw new RuntimeException(e);
            }

            Map<String, Button> buttonManager = new HashMap<>();
            Minim minim = new Minim(new MinimInput());
            AudioInput input = minim.getLineIn(Minim.STEREO, 1024);

            int i = 0, j = 0, x = 0, y = 0;

            asyncPlayer = minim.loadFile(this.filename, 2048);
            asyncPlayer.play();
            asyncPlayer.mute();

            BeatDetect beat = new BeatDetect(2048, 44100.0f);
            beat.detectMode(BeatDetect.FREQ_ENERGY);
            beat.setSensitivity(sensitivity);

            Timer startPlayer = new Timer();

            startPlayer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        player.play();
                        player.volume(player.getVolumeProperty());
                    } catch (IOException | SongNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 1500);

            Timer removeButtons = new Timer();
            removeButtons.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        buttonView.getChildren().clear();
                    });
                }
            }, mp3File.getLengthInMilliseconds());


            while (true) {
                beat.detect(asyncPlayer.mix);
                if (beat.isRange(12, 25, 4)) {

                    if (i > 0) {
                        i++;

                        x = x + 90;
                        y = y + (int) (Math.random() * 25);
                    } else {
                        i++;
                        x = (int) (Math.random() * (view.getWidth() - 400) - (view.getWidth() / 2 - 150));
                        y = (int) (Math.random() * (view.getHeight() - 400) - (view.getHeight() / 2 - 150));
                    }

                    String id = i + "." + j;
                    Button tap = new Button(String.valueOf(i));
                    Circle timerCircle = new Circle();
                    timerCircle.setRadius(60);
                    timerCircle.getStyleClass().add("timerCircle");
                    timerCircle.setFill(Color.rgb(70, 182, 160));
                    buttonManager.put(id, tap);

                    tap.setTranslateX(x);
                    tap.setTranslateY(y);
                    timerCircle.setTranslateX(x);
                    timerCircle.setTranslateY(y);


                    Platform.runLater(() -> {
                        tap.getStyleClass().add("button-click");
                        buttonView.getChildren().add(timerCircle);
                        buttonView.getChildren().add(tap);

                    });

                    Timer buttonDisable = new Timer();
                    circleTimerTransition(timerCircle);
                    buttonDisable.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                if (buttonView.getChildren().contains(buttonManager.get(id))) {
                                    buttonManager.remove(id);
                                }
                                if(!tap.getStyleClass().contains("inactive") && !isPaused){
                                    heroHealth--;
                                }

                                if (heroHealth == 0) {
                                    try {
                                        application.switchScene("DefeatScreen", "DefeatScreen", String.valueOf(highScore.get()));
                                        Thread.currentThread().interrupt();
                                        player.pause();
                                    } catch (FileNotFoundException | InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                                view.health.setText("Health: " + heroHealth);
                                tap.getStyleClass().add("inactive");
                                timerCircle.getStyleClass().add("inactive");
                            });
                        }
                    }, 2000);

                    tap.setId(id);
                    tap.setOnAction(e -> {
                        timerCircle.getStyleClass().add("inactive");
                        tap.getStyleClass().add("inactive");
                        move(hero_view);
                        addHighScore();
                    });

                    if (i == 4) {
                        i = 0;
                        j++;
                    }
                }
            }
        });
    }

    /*
     * This generates a blue circle that comes closer to the actual button
     * to represent the time you have to click
     */

    public void circleTimerTransition(Circle circle) {
        ScaleTransition sc = new ScaleTransition(Duration.millis(1500), circle);
        sc.setToX(0.66);
        sc.setToY(0.66);
        sc.play();
    }

    /*
     * move transition for our chracters
     */

    public void move(ImageView image) {
        TranslateTransition translate = new TranslateTransition();

        translate.setNode(image);
        translate.setDuration(Duration.millis(100)); //@todo set duration to spawntime of buttons
        translate.setCycleCount(2);
        translate.setByX(700);
        translate.setAutoReverse(true);
        translate.play();

    }

    public void addHighScore() {
        this.highScore.set(highScore.get() + 100);
    }

    /*
     * logic for our pause screen
     */

    public void intialize() {
        view.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE && !isPaused) {
                
                for (Node n : view.buttonView.getChildren()) {
                    n.getStyleClass().add("inactive");
                }
                
                view.getChildren().addAll(view.parentSettings);
                System.out.println("PAUSE");
                try {
                    player.pause();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                buttonSpawn().interrupt();
                asyncPlayer.pause();
                isPaused = true;
            } else {
                view.getChildren().removeAll(view.parentSettings);
                System.out.println("Weiter");
                Timer startPlayer = new Timer();
                startPlayer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        player.playAtTime();
                        player.volume(player.getVolumeProperty());
                    }
                }, 1500);

                asyncPlayer.play(player.currTimeProperty().get());
                isPaused = false;
            }
        });

        this.highScore.addListener(((observableValue, number, t1) -> {
            view.highScore.setText("Highscore: " + t1);
        }));

        player.currTimeProperty().addListener((observableValue, number, t1) -> {
            if (player.getAktSong().getDuration() * 1000 == (int) t1) {

                try {
                    application.switchScene("VictoryScreen", "VictoryScreen", String.valueOf(this.highScore.get()));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        view.toMainMenu.setOnAction(e->{
            try {
                application.switchScene("MainMenu");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.quitGame.setOnAction(e->{
            System.exit(0);
        });
    }

    public GameScreenView getView() {
        return view;
    }
}
