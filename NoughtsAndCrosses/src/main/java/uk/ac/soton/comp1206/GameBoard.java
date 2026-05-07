package uk.ac.soton.comp1206;

import javafx.scene.layout.GridPane;

public class GameBoard {
    int size;
    String [][] board;
    String currentPlayer = "X";

    public GameBoard(int size) {
        this.size = size;
        this.board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
    }

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
    }

    public void switchPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        }
        else {
            currentPlayer = "X";
        }

    }

    public void resetPlayer() {
        currentPlayer = "X";
    }

    public void makeMove(int row, int col) {
        if (board[row][col].equals("")) {
            board[row][col] = currentPlayer;
        }
    }

    public String checkWinner() {
        String winner = "";
        winner = checkHorizontalWin(board);
        if (winner.equals("")) {
            winner = checkVerticalWin(board);
        }
        if (winner.equals("")) {
            winner = checkLeftToRightDiagonalWin(board);
        }
        if (winner.equals("")) {
            winner = checkRightToLeftDiagonalWin(board);
        }
        return winner;
    }

    String checkHorizontalWin(String[][] twoDArray) {

        for (int i = 0; i < size; i++) {

            String first = twoDArray[i][0];

            if (first.equals("")) {
                continue;
            }

            boolean win = true;

            for (int j = 1; j < size; j++) {
                if (!twoDArray[i][j].equals(first)) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return first;
            }
        }

        return "";
    }

    String checkVerticalWin(String[][] twoDArray) {

        for (int i = 0; i < size; i++) {

            String first = twoDArray[0][i];

            if (first.equals("")) {
                continue;
            }

            boolean win = true;

            for (int j = 1; j < size; j++) {
                if (!twoDArray[j][i].equals(first)) {
                    win = false;
                    break;
                }
            }

            if (win) {
                return first;
            }
        }

        return "";
    }

    String checkLeftToRightDiagonalWin(String[][] twoDArray) {

        String first = twoDArray[0][0];

        if (first.equals("")) {
            return "";
        }

        for (int i = 1; i < size; i++) {
            if (!twoDArray[i][i].equals(first)) {
                return "";
            }
        }

        return first;
    }

    String checkRightToLeftDiagonalWin(String[][] twoDArray) {

        String first = twoDArray[0][size - 1];

        if (first.equals("")) {
            return "";
        }

        for (int i = 1; i < size; i++) {
            if (!twoDArray[i][size - 1 - i].equals(first)) {
                return "";
            }
        }

        return first;
    }
}
