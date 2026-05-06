package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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

        Text text = new Text("hahaha");
        Button[] buttons = createButtons(grid, text);


        addButtonsToGrid(grid, buttons);
        grid.add(text, 0, 3);

        var scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    void playGame(Button button) {
        if (isPlayer1Turn[0] && button.getText().equals(" ")) {
            button.setText("X");
            isPlayer1Turn[0] = !isPlayer1Turn[0];
        }
        else if (!isPlayer1Turn[0] && button.getText().equals(" ")) {
            button.setText("O");
            isPlayer1Turn[0] = !isPlayer1Turn[0];
        }
    }

    Button[] createButtons(GridPane grid, Text text) {
        Button[] buttons = new Button[9];
        for (int i = 0; i < 9; i++) {
            Button button = new Button(" ");
            button.setPrefSize(80, 80);
            button.setOnAction(e -> {
                playGame(button);
                String winner = "";
                checkWinner(grid);
                if (!winner.equals("")) {
                    text.setText(winner + " wins");
                }
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
        String winner = "";
        String[][] buttonValues2DArray = get2DArrayFromGrid(grid);
        for (int i = 0; i < 3; i++) {
            checkHorizontalWin(buttonValues2DArray, i, "X", "O", winner);
            checkVerticalWin(buttonValues2DArray, i, "X", "O");
        }
        checkLeftToRightDiagonalWin(buttonValues2DArray, "X", "O");
        checkRightToLeftDiagonalWin(buttonValues2DArray, "X", "O");
    }

    void checkHorizontalWin(String[][] twoDArray, int rowNumber, String sign1, String sign2, String winner) {
        if (twoDArray[rowNumber][0].equals(sign1) && twoDArray[rowNumber][1].equals(sign1) && twoDArray[rowNumber][2].equals(sign1)) {
            winner = sign1;
        }
        else if (twoDArray[rowNumber][0].equals(sign2) && twoDArray[rowNumber][1].equals(sign2) && twoDArray[rowNumber][2].equals(sign2)) {
            winner = sign2;
        }
    }

    void checkVerticalWin(String[][] twoDArray, int columnNumber, String sign1, String sign2) {
        if (twoDArray[0][columnNumber].equals(sign1) && twoDArray[1][columnNumber].equals(sign1) && twoDArray[2][columnNumber].equals(sign1)) {
            System.out.println(sign1 + " wins");
        }
        else if (twoDArray[0][columnNumber].equals(sign2) && twoDArray[1][columnNumber].equals(sign2) && twoDArray[2][columnNumber].equals(sign2)) {
            System.out.println(sign2 + " wins");
        }
    }

    void checkLeftToRightDiagonalWin(String[][] twoDArray, String sign1, String sign2) {
        if (twoDArray[0][0].equals(sign1) && twoDArray[1][1].equals(sign1) && twoDArray[2][2].equals(sign1)) {
            System.out.println(sign1 + " wins");
        }
        else if (twoDArray[0][0].equals(sign2) && twoDArray[1][1].equals(sign2) && twoDArray[2][2].equals(sign2)) {
            System.out.println(sign2 + " wins");
        }
    }

    void checkRightToLeftDiagonalWin(String[][] twoDArray, String sign1, String sign2) {
        if (twoDArray[0][2].equals(sign1) && twoDArray[1][1].equals(sign1) && twoDArray[2][0].equals(sign1)) {
            System.out.println(sign1 + " wins");
        }
        else if (twoDArray[0][2].equals(sign2) && twoDArray[1][1].equals(sign2) && twoDArray[2][0].equals(sign2)) {
            System.out.println(sign2 + " wins");
        }
    }

    ArrayList<String> putGridValuesInList(GridPane grid) {
        ArrayList<String> buttonValues = new ArrayList();
        var gridElements = grid.getChildren();
        for (int i = 0; i < 9; i++) {
            Button currentButton = (Button) gridElements.get(i);
            String currentButtonText = currentButton.getText();
            buttonValues.add(currentButtonText);
        }
        return buttonValues;
    }

    ArrayList<String> getCurrentRow(ArrayList<String> buttonValues, int i) {
        ArrayList<String> currentRow = new ArrayList<>();
        currentRow.add(buttonValues.get(i));
        currentRow.add(buttonValues.get(i + 1));
        currentRow.add(buttonValues.get(i + 2));
        return currentRow;
    }

    void getRowInto2DArray(ArrayList<String> row, String[][] twoDArray, int twoDArrayRowNumber) {
        twoDArray[twoDArrayRowNumber][0] = row.get(0);
        twoDArray[twoDArrayRowNumber][1] = row.get(1);
        twoDArray[twoDArrayRowNumber][2] = row.get(2);
    }

    String[][] get2DArrayFromGrid(GridPane grid) {
        ArrayList<String> buttonValues = putGridValuesInList(grid);
        String[][] buttonValues2DArray = new String[3][3];
        for (int i = 0, twoDArrayRowNumber = 0; i < 9; i = i + 3) {
            ArrayList<String> currentRow = getCurrentRow(buttonValues, i);
            getRowInto2DArray(currentRow, buttonValues2DArray, twoDArrayRowNumber);
            twoDArrayRowNumber++;
        }
        return buttonValues2DArray;
    }

    public static void main(String[] args) {
        launch();
    }

}