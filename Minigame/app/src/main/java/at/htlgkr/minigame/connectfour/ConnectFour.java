package at.htlgkr.minigame.connectfour;

import java.util.List;

public class ConnectFour {

    private static final int WIDTH = 7;
    private static final int HEIGHT = 6;

    private ConnectFourBoard board;
    private List<Integer> colors;
    private int currentColor;
    private boolean isAnimating = false;

    public ConnectFour() {
        colors = List.of(board.getRED(), board.getYELLOW());
        currentColor = colors.get(0);
        board = new ConnectFourBoard(WIDTH, HEIGHT);
    }

    public int play(int xCord, int yCord) {
        // -1 : not placed, 0: should Animate,

        if(isAnimating) return animating(xCord, yCord);

        int gameField = board.getElement(xCord, yCord);
        if (gameField == colors.get(0) || gameField == colors.get(1)){
            switchCurrentColor();
            return -1;
        }

//        gameField = board.getElement(xCord, yCord - 1);
//        if (gameField == colors.get(0) || gameField == colors.get(1)) {
//            board.setElement(xCord, yCord, currentColor);
//            switchCurrentColor();
//            return 1;
//        }

        return animating(xCord, yCord);

    }

    private int animating(int xCord, int yCord) {
        // 0: is still animating, 1: is not animating

        int gameField = board.getElement(xCord, yCord);
        if (gameField == colors.get(0) || gameField == colors.get(1)) {
            board.setElement(xCord, yCord, currentColor);
            switchCurrentColor();
            return 1;
        }

        // hier

        isAnimating = true;
        board.setElement(xCord, yCord, currentColor);

    }

    private void switchCurrentColor() {
        currentColor = colors.get(colors.get(colors.indexOf(currentColor)) + 1 % 2);
    }

    public int getColor(int xCord, int yCord) {
        return board.getElement(xCord, yCord);
    }
}
