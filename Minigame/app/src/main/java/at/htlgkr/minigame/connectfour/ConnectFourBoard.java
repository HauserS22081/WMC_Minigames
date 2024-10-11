package at.htlgkr.minigame.connectfour;

import at.htlgkr.minigame.R;

public class ConnectFourBoard {
    private final int[][] board;
    private final int RED = R.drawable.red_circle;
    private final int YELLOW = R.drawable.yellow_circle;
    private final int WHITE = R.drawable.white_square;

    public ConnectFourBoard(int width, int height) {
        board = new int[width][height];
    }

    public int getElement(int xCord, int yCord) {
        return board[xCord][yCord];
    }

    public void setElement(int xCord, int yCord, int value) {
        board[xCord][yCord] = value;
    }

    public int getRED() {
        return RED;
    }

    public int getYELLOW() {
        return YELLOW;
    }

    public int getWHITE() {
        return WHITE;
    }
}
