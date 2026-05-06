package uk.ac.soton.comp1206;

import javafx.scene.layout.GridPane;

public class GameBoard {
    int size;
    String [][] board;
    String currentPlayer = "X";

    public GameBoard(int size) {
        this.size = size;
        this.board = new String[size][size];
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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

    public void makeMove(int row, int col) {
        if (board[row][col].equals("")) {
            board[row][col] = currentPlayer;
        }
    }

    public String checkWinner() {
        String winner = "";
        winner = checkHorizontalWin(board, "X", "O");
        if (winner.equals("")) {
            winner = checkVerticalWin(board, "X", "O");
        }
        if (winner.equals("")) {
            winner = checkLeftToRightDiagonalWin(board, "X", "O");
        }
        if (winner.equals("")) {
            winner = checkRightToLeftDiagonalWin(board, "X", "O");
        }
        return winner;
    }

    String checkHorizontalWin(String[][] twoDArray, String sign1, String sign2) {
        String winner = "";
        for (int i = 0; i < 3; i++) {
            if (twoDArray[i][0].equals(sign1) && twoDArray[i][1].equals(sign1) && twoDArray[i][2].equals(sign1)) {
                winner = sign1;
                break;
            }
            else if (twoDArray[i][0].equals(sign2) && twoDArray[i][1].equals(sign2) && twoDArray[i][2].equals(sign2)) {
                winner = sign2;
                break;
            }
        }
        return winner;
    }

    String checkVerticalWin(String[][] twoDArray, String sign1, String sign2) {
        String winner = "";
        for (int i = 0; i < 3; i++) {
            if (twoDArray[0][i].equals(sign1) && twoDArray[1][i].equals(sign1) && twoDArray[2][i].equals(sign1)) {
                winner = sign1;
                break;
            }
            else if (twoDArray[0][i].equals(sign2) && twoDArray[1][i].equals(sign2) && twoDArray[2][i].equals(sign2)) {
                winner = sign2;
                break;
            }
        }
        return winner;
    }

    String checkLeftToRightDiagonalWin(String[][] twoDArray, String sign1, String sign2) {
        String winner = "";
        if (twoDArray[0][0].equals(sign1) && twoDArray[1][1].equals(sign1) && twoDArray[2][2].equals(sign1)) {
            winner = sign1;
        }
        else if (twoDArray[0][0].equals(sign2) && twoDArray[1][1].equals(sign2) && twoDArray[2][2].equals(sign2)) {
            winner = sign2;
        }
        return winner;
    }

    String checkRightToLeftDiagonalWin(String[][] twoDArray, String sign1, String sign2) {
        String winner = "";
        if (twoDArray[0][2].equals(sign1) && twoDArray[1][1].equals(sign1) && twoDArray[2][0].equals(sign1)) {
            winner = sign1;
        }
        else if (twoDArray[0][2].equals(sign2) && twoDArray[1][1].equals(sign2) && twoDArray[2][0].equals(sign2)) {
            winner = sign2;
        }
        return winner;
    }
}
