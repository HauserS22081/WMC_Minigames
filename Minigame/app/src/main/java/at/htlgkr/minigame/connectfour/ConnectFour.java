package at.htlgkr.minigame.connectfour;

import java.util.List;

public class ConnectFour {

    private static final int WIDTH = 7;
    private static final int HEIGHT = 6;

    private final ConnectFourBoard board;
    private final List<Integer> colors;
    private final int WHITE;
    private int currentColor;
    private boolean isAnimating = false;

    public ConnectFour() {
        board = new ConnectFourBoard(WIDTH, HEIGHT);
        colors = List.of(board.getRED(), board.getYELLOW());
        currentColor = colors.get(0);
        WHITE = board.getWHITE();

        fillBoard();
    }

    public int play(int xCord, int yCord) {
        // -1 : not placed, 0: should Animate, 1: placed

        if(isAnimating) return placeStone(xCord, yCord);

        int gameField = board.getElement(xCord, 0);
        if (gameField != colors.get(0) && gameField != colors.get(1)) {
            return placeStone(xCord, 0);
        }

        return -1;

    }

    private int placeStone(int xCord, int yCord) {
        // 0: is still animating, 1: is not animating

        int gameField = board.getElement(xCord, yCord);
        if (!(yCord == 0 || !(gameField != colors.get(0) && gameField != colors.get(1) && board.getElement(xCord, yCord - 1) != WHITE))) {
            board.setElement(xCord, yCord, currentColor);
            switchCurrentColor();
            return 1;
        }

        // hier

        isAnimating = true;
        board.setElement(xCord, yCord, currentColor);
        if (!(yCord == 0 || !(board.getElement(xCord, yCord - 1) == colors.get(0) || board.getElement(xCord, yCord - 1) == colors.get(1)))) {
            board.setElement(xCord, yCord - 1, WHITE);
        }
        return 0;
    }

    private void switchCurrentColor() {
        currentColor = colors.get(colors.get(colors.indexOf(currentColor)) + 1 % 2);
    }

    public int getColor(int xCord, int yCord) {
        return board.getElement(xCord, yCord);
    }

    public int getBackground() {
        return WHITE;
    }

    public void fillBoard() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                board.setElement(i, j, WHITE);
            }
        }
    }
}
