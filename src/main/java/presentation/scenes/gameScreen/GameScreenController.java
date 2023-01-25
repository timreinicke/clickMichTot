package presentation.scenes.gameScreen;

import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
    StackPane buttonView;

    public GameScreenController() throws FileNotFoundException {
        this.view = new GameScreenView();
        this.hero_view = view.hero_view;

        buttonView = this.view.buttonView;

        buttonSpawn().start();
        intialize();
    }

    public InputStream createInput(String filename) {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }

    public Thread buttonSpawn() {
        return new Thread(() -> {

            Map<String, Button> buttonManager = new HashMap<>();
            Minim minim = new Minim(createInput("src/main/resources/application/music/tracks/blook.mp3"));
            AudioInput input = minim.getLineIn(Minim.STEREO, 1024);

            BeatDetect beat = new BeatDetect(1024, 44100.0f);
            beat.setSensitivity(1000);
            beat.detect(input.mix);
            if (beat.isHat()) {
                System.out.println("Hat");
            }
            for (int i = 0; i < 1; i++) {


                for (int j = 1; j < 5; j++) {

                    String id = i + "." + j;
                    Button tap = new Button(String.valueOf(j));
                    buttonManager.put(id, tap);

                    int x = (int) (Math.random() * view.getWidth() - view.getWidth() / 2);
                    int y = (int) (Math.random() * view.getHeight() - view.getHeight() / 2);


                    double z = view.getWidth() / 2 - view.boss_view.getFitWidth();

                    tap.setTranslateX(x);
                    tap.setTranslateY(y);
                    view.boss_view.setTranslateX(z);

                    Platform.runLater(() -> {
                        tap.getStyleClass().add("button-click");
                        // view.getChildren().add(tap);
                        buttonView.getChildren().add(tap);
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Timer buttonDisable = new Timer();
                    buttonDisable.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                if (buttonView.getChildren().contains(buttonManager.get(id))) {
                                   // buttonView.getChildren().remove(buttonManager.get(id));
                                    buttonManager.remove(id);

                                }
                                tap.setDisable(true);
                            });

                        }
                    }, 3000);

                    tap.setId(i + "." + j);
                    tap.setOnAction(e -> {
                        Platform.runLater(() -> {
                            move(hero_view);
                           /* view.getChildren().remove(tap);
                            buttonManager.remove(id);*/
                            tap.setDisable(true);
                        });
                    });

                }
            }

            Timer removeButtons = new Timer();
            removeButtons.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        buttonView.getChildren().clear();
                    });
                }
            }, 12000);
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
