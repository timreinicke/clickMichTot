package presentation.scenes.gameScreen;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreenController extends Thread {

    private GameScreenView view;
    private ImageView hero_view;
    private Canvas gameCanvas;

    public Thread buttonSpawn() {
        return new Thread(() -> {
            Map<String, Button> buttonManager = new HashMap<>();

            for (int i = 0; i < 10; i++) {
                for (int j = 1; j < 5; j++) {
                    String id = i + "." + j;
                    Button tap = new Button(String.valueOf(j));
                    buttonManager.put(id, tap);

                    /*
                    int x = (int)(Math.random() * view.getWidth());
                    int y = (int)(Math.random() * view.getHeight());
                    tap.setLayoutX(x);
                    tap.setLayoutX(y);*/

                    int x = (int) (Math.random() * view.getWidth() - view.getWidth()/2);
                    int y = (int) (Math.random() * view.getHeight() - view.getHeight()/2);
                    double z = view.getWidth()/2 - view.boss_view.getFitWidth();

                    tap.setTranslateX(x);
                    tap.setTranslateY(y);
                    view.boss_view.setTranslateX(z);

                    Platform.runLater(() -> {
                        tap.getStyleClass().add("button-click");
                        view.getChildren().add(tap);
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
                                if (view.getChildren().contains(buttonManager.get(id))) {
                                    view.getChildren().remove(buttonManager.get(id));
                                }
                            });
                        }
                    }, 3000);

                    tap.setId(i + "." + j);
                    tap.setOnAction(e -> {
                        move(hero_view);
                        view.getChildren().remove(tap);
                        buttonManager.remove(id);

                    });

                }

            }
        });
    }

    public GameScreenController() throws FileNotFoundException {
        this.view = new GameScreenView();
        this.hero_view = view.hero_view;
        this.gameCanvas = view.gameCanvas;

        buttonSpawn().start();
        intialize();
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
