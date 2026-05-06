package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * JavaFX App
 */
public class NoughtsAndCrossesApp extends Application {
    GameBoard gameBoard = new GameBoard(3);
    int size = gameBoard.size;
    String currentPlayer = gameBoard.currentPlayer;

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();

        Button[] buttons = createButtons(grid);

        addButtonsToGrid(grid, buttons);

        var scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    Button[][] createButtons(GridPane grid) {
        Button[][] buttons = new Button[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int finalRow = i;
                int finalCol = j;

                Button button = new Button("");
                button.setPrefSize(80, 80);
                button.setOnAction(e -> {
                    handleMove(button, finalRow, finalCol);
                });
                buttons[i][j] = button;
            }
        }
        return buttons;
    }

    void addButtonsToGrid(GridPane grid, Button[] buttons) {
        grid.add(buttons[0], 0, 0);
        grid.add(buttons[1], 1, 0);
        grid.add(buttons[2], 2, 0);
        grid.add(buttons[3], 0, 1);
        grid.add(buttons[4], 1, 1);
        grid.add(buttons[5], 2, 1);
        grid.add(buttons[6], 0, 2);
        grid.add(buttons[7], 1, 2);
        grid.add(buttons[8], 2, 2);
    }

    public void handleMove(Button button, int finalRow, int finalCol) {
        gameBoard.makeMove(finalRow, finalCol);
        button.setText(currentPlayer);
        gameBoard.checkWinner
    }

    public static void main(String[] args) {
        launch();
    }

}