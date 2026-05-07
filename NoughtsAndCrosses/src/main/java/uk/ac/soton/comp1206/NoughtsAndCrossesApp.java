package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    VBox gameContainer;
    Text player1Name;
    Text player2Name;
    Text playerTurn;
    Button scoresButton;
    int[] scores = {0, 0};

    @Override
    public void start(Stage stage) {

        // Start screen
        Text gridSizeLabel = new Text("Grid size");
        TextField gridSizeField = new TextField("3");
        Text xPlayerNameLabel = new Text("X player name");
        TextField xPlayerNameField = new TextField();
        Text oPlayerNameLabel = new Text("O player name");
        TextField oPlayerNameField = new TextField();
        Button startButton = new Button("Start");

        // Create empty game grid first
        GridPane grid = new GridPane();
        VBox gameContainer = new VBox();

        // Create game scene
        Scene gameScene = new Scene(gameContainer, 400, 300);


        startButton.setOnAction(e -> {
            if (!xPlayerNameField.getText().isEmpty() && !oPlayerNameField.getText().isEmpty()) {

                int gridSize = Integer.parseInt(gridSizeField.getText());

                gameBoard = new GameBoard(gridSize);

                size = gridSize;

                grid.getChildren().clear();

                player1Name = new Text(xPlayerNameField.getText());
                player2Name = new Text(oPlayerNameField.getText());

                buttons = createButtons(grid);

                addButtonsToGrid(grid, buttons);

                playerTurn = new Text("Player turn: ");

                scoresButton = new Button("Display scoreboard");
                scoresButton.setOnAction(k -> {
                    Alert scoreboardAlert = new Alert(Alert.AlertType.INFORMATION);

                    scoreboardAlert.setTitle("Scoreboard");
                    scoreboardAlert.setHeaderText("Current Scores");

                    scoreboardAlert.setContentText(
                            player1Name.getText() + ": " + scores[0] + "\n" +
                                    player2Name.getText() + ": " + scores[1]
                    );

                    scoreboardAlert.showAndWait();
                });

                gameContainer.getChildren().add(player1Name);
                gameContainer.getChildren().add(player2Name);
                gameContainer.getChildren().add(playerTurn);
                gameContainer.getChildren().add(grid);
                gameContainer.getChildren().add(scoresButton);


                stage.setScene(gameScene);
            }
        });
        VBox startContainer = new VBox();
        startContainer.getChildren().add(gridSizeLabel);
        startContainer.getChildren().add(gridSizeField);
        startContainer.getChildren().add(xPlayerNameLabel);
        startContainer.getChildren().add(xPlayerNameField);
        startContainer.getChildren().add(oPlayerNameLabel);
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.add(buttons[i][j], j, i);
            }
        }
    }

    public void handleMove(Button button, int finalRow, int finalCol) {
        gameBoard.makeMove(finalRow, finalCol);
        button.setText(gameBoard.currentPlayer);
        String winner = gameBoard.checkWinner();
        if (!winner.equals(gameBoard.currentPlayer)) {
            gameBoard.switchPlayer();
            if (gameBoard.currentPlayer.equals("X")) {
                playerTurn.setText("Player turn: " + player1Name.getText());
            }
            else {
                playerTurn.setText("Player turn: " + player2Name.getText());
            }

        }
        else {
            String winnerName;

            if (winner.equals("X")) {
                winnerName = player1Name.getText();
                scores[0]++;
            }
            else {
                winnerName = player2Name.getText();
                scores[1]++;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Game Over");
            alert.setHeaderText("Winner!");
            alert.setContentText(winnerName + " wins!");

            alert.showAndWait();

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