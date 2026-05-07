package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    Button[][] buttons;

    @Override
    public void start(Stage stage) {
        // Game screen
        GridPane grid = new GridPane();
        buttons = createButtons(grid);
        addButtonsToGrid(grid, buttons);
        var gameScene = new Scene(grid, 400, 300);


        // Start screen
        TextField gridSizeField = new TextField("3");
        TextField xPlayerNameField = new TextField();
        TextField oPlayerNameField = new TextField();
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            if (!xPlayerNameField.getText().isEmpty() && !oPlayerNameField.getText().isEmpty()) {

                int gridSize = Integer.parseInt(gridSizeField.getText());

                gameBoard = new GameBoard(gridSize);

                size = gridSize;

                stage.setScene(gameScene);
            }
        });

        VBox startContainer = new VBox();
        startContainer.getChildren().add(gridSizeField);
        startContainer.getChildren().add(xPlayerNameField);
        startContainer.getChildren().add(oPlayerNameField);
        startContainer.getChildren().add(startButton);
        Scene startScene = new Scene(startContainer, 400, 300);

        stage.setScene(startScene);
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

    void addButtonsToGrid(GridPane grid, Button[][] buttons) {
        grid.add(buttons[0][0], 0, 0);
        grid.add(buttons[0][1], 1, 0);
        grid.add(buttons[0][2], 2, 0);
        grid.add(buttons[1][0], 0, 1);
        grid.add(buttons[1][1], 1, 1);
        grid.add(buttons[1][2], 2, 1);
        grid.add(buttons[2][0], 0, 2);
        grid.add(buttons[2][1], 1, 2);
        grid.add(buttons[2][2], 2, 2);
    }

    public void handleMove(Button button, int finalRow, int finalCol) {
        gameBoard.makeMove(finalRow, finalCol);
        button.setText(gameBoard.currentPlayer);
        String winner = gameBoard.checkWinner();
        if (!winner.equals(gameBoard.currentPlayer)) {
            gameBoard.switchPlayer();
        }
        else {
            gameBoard.resetBoard();
            gameBoard.resetPlayer();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    buttons[i][j].setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}