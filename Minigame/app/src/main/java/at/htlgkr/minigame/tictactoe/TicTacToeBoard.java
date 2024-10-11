package at.htlgkr.minigame.tictactoe;

public class TicTacToeBoard {
    private int[][] board = new int[3][3];

    public boolean elementClicked(int yCord, int xCord, int player) {
        if (board[xCord][yCord] == 0) {
            board[xCord][yCord] = player;
            return true;
        }

        return false;
    }

    public int getElement(int yCord, int xCord) {
        return board[xCord][yCord];
    }

}
