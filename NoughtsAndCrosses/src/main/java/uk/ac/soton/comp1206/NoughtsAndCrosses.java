package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * JavaFX App
 */
public class NoughtsAndCrosses extends Application {
    boolean[] isPlayer1Turn = {true};

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();

        Button[] buttons = createButtons();

        addButtonsToGrid(grid, buttons);

        var scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    void playGame(Button button) {
        if (isPlayer1Turn[0] && button.getText().equals(" ")) {
            button.setText("x");
            isPlayer1Turn[0] = !isPlayer1Turn[0];
        }
        else if (!isPlayer1Turn[0] && button.getText().equals(" ")) {
            button.setText("o");
            isPlayer1Turn[0] = !isPlayer1Turn[0];
        }
    }

    Button[] createButtons() {
        Button[] buttons = new Button[9];
        for (int i = 0; i < 9; i++) {
            Button button = new Button(" ");
            button.setOnAction(e -> {
                playGame(button);
            });
            buttons[i] = button;
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

    void checkWinner(GridPane grid) {
        var gridElements = grid.getChildren();
        System.out.println(gridElements);
    }

    public static void main(String[] args) {
        launch();
    }

}