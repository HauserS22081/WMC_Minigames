package at.htlgkr.minigame.connectfour;

import java.util.List;

public class ConnectFour {

    private static final int WIDTH = 7;
    private static final int HEIGHT = 6;

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
        // return: -1 if no free slot, yCord of free slot


        for (int i = HEIGHT - 1; i >= 0; i--) {
            if (board.getElement(xCord, i) != colors.get(0) && board.getElement(xCord, i) != colors.get(1)) {
                board.setElement(xCord, i, currentColor);
                switchCurrentColor();
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

            if (sum == 4) return won();
        }

        // horizontal
        sum = 0;
        for (int i = 0; i < WIDTH; i++) {
            if(board.getElement(i, yCord) == color) sum++;
            else sum = 0;

            if (sum == 4) return won();
        }

//        // diagonal lefttop - rightbottom
//
//        for (int i = 0; i < WIDTH - 3; i++) {
//            sum = 0;
//            int tempXCord = 0;
//            for (int j = i; j < HEIGHT - 3 && tempXCord < WIDTH - 3; j++) {
//                if(board.getElement(tempXCord++, j) == color) sum++;
//                else sum = 0;
//
//                if(sum == 4) return won();
//            }
//        }
//
//        for (int i = 0; i < HEIGHT - 3; i++) {
//            sum = 0;
//            int tempYCord = 0;
//            for (int j = i; j < WIDTH - 3 && tempYCord < HEIGHT - 3; j++) {
//                if(board.getElement(j, tempYCord++) == color) sum++;
//                else sum = 0;
//
//                if (sum == 4) return won();
//            }
//        }
//
//        // diagonal leftbottom - righttop
//
//        for (int i = 0; i < WIDTH - 3; i++) {
//            sum = 0;
//            int tempXCord = 0;
//            for (int j = HEIGHT - i - 1; j > 2 && tempXCord < WIDTH - 3; j--) {
//                if (board.getElement(tempXCord++, j) == color) sum++;
//                else sum = 0;
//
//                if(sum == 4) return won();
//            }
//
//        }
//
//        for (int i = HEIGHT; i > 2; i--) {
//            sum = 0;
//            int tempYCord = HEIGHT - 1;
//            for (int j = HEIGHT - i; j < WIDTH - 3 && tempYCord > 2; j++) {
//                if (board.getElement(j, tempYCord--) == color) sum++;
//                else sum = 0;
//
//                if (sum == 4) return won();
//            }
//        }


        // top-left - bottom-right
        if (isOnSide(xCord, yCord) || (board.getElement(xCord + 1, yCord + 1) == color || board.getElement(xCord - 1, yCord - 1) == color)) {
            sum = 0;

            int tempXCord = xCord;
            int tempYCord = yCord;


            while (tempXCord > 0 && tempYCord > 0) {
                if(board.getElement(--tempXCord, --tempYCord) == color) sum++;
                else sum = 0;

                if (sum == 4) return won();

            }

            tempXCord = xCord;
            tempYCord = yCord;
            sum++;
            if (sum == 4) return won();

            while (tempXCord < WIDTH - 1 && tempYCord < HEIGHT - 1) {
                if(board.getElement(++tempXCord, ++tempYCord) == color) sum++;
                else sum = 0;

                if (sum == 4) return won();
            }
        }


        // bottom-left - top-right

        if (isOnSide(xCord, yCord) || (board.getElement(xCord + 1, yCord - 1) == color || board.getElement(xCord - 1, yCord + 1) == color)) {
            sum = 0;

            int tempXCord = xCord;
            int tempYCord = yCord;

            while (tempXCord > 0 && tempYCord < HEIGHT - 1) {
                if (board.getElement(--tempXCord, ++tempYCord) == color) sum++;
                else sum = 0;

                if (sum == 4) return won();
            }

            tempXCord = xCord;
            tempYCord = yCord;
            sum++;
            if (sum == 4) return won();

            while (tempXCord < WIDTH - 1 && tempYCord > 0) {
                if (board.getElement(++tempXCord, --tempYCord) == color) sum++;
                else sum = 0;

                if (sum == 4) return won();
            }
        }

        return false;
    }

    private boolean isOnSide(int xCord, int yCord) {
        return (xCord == WIDTH - 1 || xCord == 0 || yCord == HEIGHT - 1 || yCord == 0);
    }

    private boolean won() {
        resetVariables();
        return true;
    }

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
