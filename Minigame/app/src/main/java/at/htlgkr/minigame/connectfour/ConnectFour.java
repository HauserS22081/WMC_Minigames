package at.htlgkr.minigame.connectfour;

import java.util.List;

public class ConnectFour {

    public static final int WIDTH = 7;
    public static final int HEIGHT = 6;

    private ConnectFourBoard board;
    private final List<Integer> colors;
    private int currentColor;

    public ConnectFour() {
        board = new ConnectFourBoard(WIDTH, HEIGHT);
        colors = List.of(board.getRED(), board.getYELLOW());
        currentColor = colors.get(0);

        resetBoard();
    }

    public int play(int xCord) {
        int yCord = getYCord(xCord);
        if(yCord == -1) return -1;

        board.setElement(xCord, yCord, currentColor);
        switchCurrentColor();
        return yCord;
    }

    public int getYCord(int xCord) {
        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (board.getElement(xCord, i) != colors.get(0) && board.getElement(xCord, i) != colors.get(1)) {
                return i;
            }
        }

        return -1;
    }

    public boolean hasWon(int xCord, int yCord) {

        int sum = 0;
        int color = colors.get((colors.indexOf(currentColor) + 1) % 2);

        // vertical
        for (int i = 0; i < HEIGHT; i++) {
            if(board.getElement(xCord, i) == color) sum++;
            else sum = 0;

            if (sum == 4) return true;
        }

        // horizontal
        sum = 0;
        for (int i = 0; i < WIDTH; i++) {
            if(board.getElement(i, yCord) == color) sum++;
            else sum = 0;

            if (sum == 4) return true;
        }

        // top-left - bottom-right
        if (isOnSide(xCord, yCord) || (board.getElement(xCord + 1, yCord + 1) == color || board.getElement(xCord - 1, yCord - 1) == color)) {
            sum = 1;

            int tempXCord = xCord;
            int tempYCord = yCord;


            while (tempXCord > 0 && tempYCord > 0) {
                if(board.getElement(--tempXCord, --tempYCord) == color) sum++;
                else break;

                if (sum == 4) return true;
            }

            tempXCord = xCord;
            tempYCord = yCord;

            while (tempXCord < WIDTH - 1 && tempYCord < HEIGHT - 1) {
                if(board.getElement(++tempXCord, ++tempYCord) == color) sum++;
                else break;

                if (sum == 4) return true;
            }
        }


        // bottom-left - top-right

        if (isOnSide(xCord, yCord) || (board.getElement(xCord + 1, yCord - 1) == color || board.getElement(xCord - 1, yCord + 1) == color)) {
            sum = 1;

            int tempXCord = xCord;
            int tempYCord = yCord;

            while (tempXCord > 0 && tempYCord < HEIGHT - 1) {
                if (board.getElement(--tempXCord, ++tempYCord) == color) sum++;
                else break;

                if (sum == 4) return true;
            }

            tempXCord = xCord;
            tempYCord = yCord;

            while (tempXCord < WIDTH - 1 && tempYCord > 0) {
                if (board.getElement(++tempXCord, --tempYCord) == color) sum++;
                else break;

                if (sum == 4) return true;
            }
        }

        return false;
    }

    public static boolean isOnSide(int xCord, int yCord) {
        return (xCord == WIDTH - 1 || xCord == 0 || yCord == HEIGHT - 1 || yCord == 0);
    }

//    private boolean won() {
//        resetVariables();
//        return true;
//    }

    public void restart() {
        resetVariables();
        resetBoard();
    }

    private void resetVariables() {
        currentColor = colors.get(0);
        board = new ConnectFourBoard(WIDTH, HEIGHT);
        resetBoard();
    }

    private void switchCurrentColor() {
        currentColor = colors.get((colors.indexOf(currentColor) + 1) % 2);
    }

    public void resetBoard() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                board.setElement(i, j, 0);
            }
        }
    }

    public int getImage(int xCord, int yCord) {
        return board.getElement(xCord, yCord);
    }
}
