package presentation.scenes.gameScreen;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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
    Mp3File mp3File;
    String filename;

    public GameScreenController(String filename) throws FileNotFoundException {
        this.view = new GameScreenView();
        this.hero_view = view.hero_view;
        this.filename = filename;
        buttonView = this.view.buttonView;

        buttonSpawn().start();
        intialize();
    }

    public static class MinimInput {
        public String sketchPath(String fileName) {
            return "src/main/resources/application/music/tracks/track1.mp3";
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
            AudioPlayer player;
            int i = 0, j = 0, x = 0, y = 0;


            player = minim.loadFile("src/main/resources/application/music/tracks/track1.mp3", 2048);
            player.play();
            player.setGain(-40);

            BeatDetect beat = new BeatDetect(2048, 44100.0f);
            beat.detectMode(BeatDetect.FREQ_ENERGY);
            beat.setSensitivity(1000);

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
                beat.detect(player.mix);

                if (beat.isRange(15, 20, 4)) {
                    if (i > 0) {
                        i++;

                        x = x + 90;
                        y = y + (int) (Math.random() * 25);
                    } else {
                        i++;
                        x = (int) (Math.random() * view.getWidth() - view.getWidth() / 2 - 250);
                        y = (int) (Math.random() * view.getHeight() - view.getHeight() / 2 - 250);
                    }

                    String id = i + "." + j;
                    Button tap = new Button(String.valueOf(i));
                    Circle timerCircle = new Circle();
                    timerCircle.setRadius(60);
                    timerCircle.getStyleClass().add("timerCircle");
                    timerCircle.setFill(Color.color(1.0, 0,0));
                    buttonManager.put(id, tap);

                    double z = view.getWidth() / 2 - view.boss_view.getFitWidth();

                    tap.setTranslateX(x);
                    tap.setTranslateY(y);
                    timerCircle.setTranslateX(x);
                    timerCircle.setTranslateY(y);
                    view.boss_view.setTranslateX(z);

                    Platform.runLater(() -> {
                        tap.getStyleClass().add("button-click");
                        buttonView.getChildren().add(timerCircle);
                        buttonView.getChildren().add(tap);

                    });

                    Timer buttonDisable = new Timer();
                    buttonDisable.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                if (buttonView.getChildren().contains(buttonManager.get(id))) {
                                    // buttonView.getChildren().remove(buttonManager.get(id));
                                    buttonManager.remove(id);
                                }
                                tap.getStyleClass().add("inactive");
                            });
                        }
                    }, 3000);

                    tap.setId(id);
                    tap.setOnAction(e -> {
                        timerCircle.getStyleClass().add("inactive");
                        tap.getStyleClass().add("inactive");
                        move(hero_view);
                    });

                    if (i == 4) {
                        i = 0;
                        j++;
                    }
                }
            }
        });
    }

    public void move(ImageView image) {
        TranslateTransition translate = new TranslateTransition();

        translate.setNode(image);
        translate.setDuration(Duration.millis(100)); //@todo set duration to spawntime of buttons
        translate.setCycleCount(2);
        translate.setByX(250);
        translate.setAutoReverse(true);
        translate.play();

    }

    public void intialize() {

    }

    public GameScreenView getView() {
        return view;
    }
}
