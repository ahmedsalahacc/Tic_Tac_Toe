package TicTacToe;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Tictactoe
 */
public class Tictactoe extends Application{

    protected static boolean x_turn = true;
    protected static boolean playable = true;
    private Tile [][]board;
    private static List<Combo> combos;
    protected static Pane pane;

    // method to set pain with size 600 x 600
    Parent createContent() {
        board = new Tile[3][3];
        combos = new ArrayList<>();
        pane = new Pane();
        pane.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(i * 200);
                tile.setTranslateY(j * 200);
                pane.getChildren().add(tile);
                board[j][i] = tile;
            }
        }
        // horizontal check
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }
        // vertical check
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }
        // diagnolas
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

        return pane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    public static void switch_flag() {
        Tictactoe.x_turn = !Tictactoe.x_turn;
    }

    public static void game_status() {
        Tictactoe.x_turn = !Tictactoe.x_turn;
    }

    public static void checkState() {
        for (Combo combo : Tictactoe.combos) {
            if (combo.isComplete()) {
                playable = false;
                playWinAnimation(combo);
                break;
            }
        }
    }

    private static void playWinAnimation(Combo comb) {
        Line line = new Line();
        line.setStartX(comb.tiles[0].getCenterX());
        line.setStartY(comb.tiles[0].getCenterY());
        line.setEndX(comb.tiles[0].getCenterX());
        line.setEndY(comb.tiles[0].getCenterY());
        pane.getChildren().add(line);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
            new KeyValue(line.endXProperty(),comb.tiles[2].getCenterX()),
            new KeyValue(line.endYProperty(), comb.tiles[2].getCenterY())));
        timeline.play();
    }
    public static void main(String[] args){
        launch(args);
    }

}