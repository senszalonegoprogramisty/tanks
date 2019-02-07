import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;


public class TanksApp extends Application {

    private Pane root = new Pane();
    Sprite player;

  //  private Sprite player = new Sprite(180, 180, 40, 40, "Tank", Color.GREEN);

    private Parent createContent() {
        root.setPrefSize(400, 400);

        player = drawMap();

        return root;
    }

    //0 MOZNA PO TYM JEZDZIC
    //1 PRZESZKODY
    //2 enemy
    //3 pLAyER
    private static int map[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 0, 2, 2, 0, 2, 0, 1},
            {1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 1, 0, 2, 1},
            {1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 2, 0, 1, 1, 0, 3, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private Sprite drawMap() {
        Sprite player = null;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (map[y][x] == 1) {
                    Sprite s = new Sprite(x*40,y*40 , 40,40, "obstacle", Color.GREY);
                    root.getChildren().add(s);
                } else if (map[y][x] == 2) {
                    Sprite s = new Sprite(x*40,y*40 , 40,40, "land", Color.RED);
                    root.getChildren().add(s);
                } else if (map[y][x] == 3) {
                    player = new Sprite(x*40, y*40, 40, 40, "Tank", Color.GREEN);
                    root.getChildren().add(player);
                }
                else {
                    Sprite s = new Sprite(x*40,y*40 , 40,40, "land", Color.YELLOW);
                    root.getChildren().add(s);
                }
            }
        }
        return player;
    }

    //TODO dodac ruch na skos lul
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;
                case W:
                    player.moveUp();
                    break;
                case S:
                    player.moveDown();
                    break;
                case SPACE:
                    player.attack();
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private static class Sprite extends Rectangle {
        boolean dead = false;
        final String type;


        Sprite(int x, int y, int w, int h, String type, Color color) {
            super(w, h, color);
            this.type = type;
            setTranslateX(x);
            setTranslateY(y);
        }

        void moveLeft() {
            setTranslateX(getTranslateX() - 5);
        }

        void moveRight() {
            setTranslateX(getTranslateX() + 5);
        }

        void moveUp() {
            setTranslateY(getTranslateY() - 5);
        }

        void moveDown() {
            setTranslateY(getTranslateY() + 5);
        }

        //autor to ma z jakiegos powodu w parencie
        void attack() {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
