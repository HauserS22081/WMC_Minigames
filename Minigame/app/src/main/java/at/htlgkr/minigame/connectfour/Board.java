package at.htlgkr.minigame.connectfour;

import at.htlgkr.minigame.R;

public class Board {
    private int[][] board;
    private final int RED = R.drawable.red_circle;
    private final int YELLOW = R.drawable.yellow_circle;
    // private final int WHITE = R.drawable.white_square;

    public Board(int width, int height) {
        board = new int[width][height];
    }

    public int getElement(int xCord, int yCord) {
        return board[xCord][yCord];
    }

    public void setElement(int xCord, int yCord, int value) {
        board[xCord][yCord] = value;
    }
}
