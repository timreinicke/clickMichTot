package presentation.scenes.gameScreen;

import application.GameApplication;
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
import javafx.scene.control.Button;
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

    private final GameScreenView view;
    private final ImageView hero_view;
    private final StackPane buttonView;
    private Mp3File mp3File;
    private final String filename;
    private int heroHealth = 10;
    MP3 player;
    GameApplication application;
    int sensitivity;
    boolean inSettings = false;
    private final IntegerProperty highScore = new SimpleIntegerProperty(0);

    VolumeSliderController volume;

    public GameScreenController(String filename, MP3 player, GameApplication application) throws FileNotFoundException {
        this.view = new GameScreenView();
        this.hero_view = view.hero_view;
        this.filename = filename;
        buttonView = view.buttonView;
        this.player = player;
        this.application = application;
        volume = new VolumeSliderController(player);

        view.groupSettings.getChildren().add(volume.getView());

        int f = 1;
        switch (f) {
            case 0:
                sensitivity = 1000;
                break;
            case 1:
                sensitivity = 800;
                break;
            case 2:
                sensitivity = 10;
                break;
        }


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
            AudioPlayer asyncPlayer;
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
                        player.volume(-30);
                    } catch (IOException | SongNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, 2500);

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
                if (beat.isRange(12, 20, 4)) {

                    if (i > 0) {
                        i++;

                        x = x + 90;
                        y = y + (int) (Math.random() * 25);
                    } else {
                        i++;
                        x = (int) (Math.random() * (view.getWidth() - 250) - (view.getWidth() / 2 - 150));
                        y = (int) (Math.random() * (view.getHeight() - 250) - (view.getHeight() / 2 - 150));
                    }

                    String id = i + "." + j;
                    Button tap = new Button(String.valueOf(i));
                    Circle timerCircle = new Circle();
                    timerCircle.setRadius(60);
                    timerCircle.getStyleClass().add("timerCircle");
                    timerCircle.setFill(Color.AQUA);
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
                                if(!tap.getStyleClass().contains("inactive")){
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
                    }, 3000);

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

    public void circleTimerTransition(Circle circle) {
        ScaleTransition sc = new ScaleTransition(Duration.seconds(3), circle);
        sc.setToX(0.66);
        sc.setToY(0.66);
        sc.play();
    }

    public void move(ImageView image) {
        TranslateTransition translate = new TranslateTransition();

        translate.setNode(image);
        translate.setDuration(Duration.millis(100)); //@todo set duration to spawntime of buttons
        translate.setCycleCount(2);
        translate.setByX(1200);
        translate.setAutoReverse(true);
        translate.play();

    }

    public void addHighScore() {
        this.highScore.set(highScore.get() + 100);
    }

    public void intialize() {
        this.highScore.addListener(((observableValue, number, t1) -> {
            view.highScore.setText("Highscore: " + t1);
        }));

        player.currTimeProperty().addListener((observableValue, number, t1) -> {
            System.out.println(t1);
            System.out.println(player.getAktSong().getDuration());
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

        view.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ESCAPE && !inSettings){
                inSettings = true;
                view.getChildren().addAll(view.parentSettings);
            }else if(e.getCode() == KeyCode.ESCAPE){
                player.volume(player.getVolumeProperty());
                inSettings = false;
                view.getChildren().removeAll(view.parentSettings);
            }
        });
    }

    public GameScreenView getView() {
        return view;
    }
}
