package at.htlgkr.minigame.connectfour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConnectFourBot {
    private final ConnectFour connectFour;
    private final ConnectFourBoard board;
    private final int myColor;
    private int myXCord;
    private final int opponentColor;
    private int opponentXCord;
    private String[] opponentsBestPlacement;
    private String[] myBestPlacement;

    public ConnectFourBot(ConnectFour connectFour, ConnectFourBoard board, int botColor, int opponentColor) {
        this.connectFour = connectFour;
        this.board = board;
        this.myColor = botColor;
        this.opponentColor = opponentColor;
    }

    public int play() {
        boolean validPlacement = false;
        int xCord = -1;
        int yCord = -1;

        while (!validPlacement) {
            xCord = findBestPlacement();
            yCord = connectFour.play(xCord);
            validPlacement = yCord != -1 && xCord != -1;
        }

        return xCord;
    }

    private int findBestPlacement() {
        if (checkPlayer(opponentColor, opponentXCord, opponentsBestPlacement) == -3) {
            return opponentXCord;
        }

        if (checkPlayer(myColor, myXCord, myBestPlacement) == -3) {
            return myXCord;
        }

        Random random = new Random();
        return random.nextInt(ConnectFour.WIDTH);
    }

    private int checkPlayer(int color, int setXCord, String[] bestPlacement) {
        // -3: 3er Row, -2: 2er Row, -1: no row

        for (int xCord = 0; xCord < ConnectFour.WIDTH; xCord++) {
            int yCord = connectFour.getYCord(xCord);

            List<String> listOfStones = new ArrayList<>();

            // horizontal
            add(listOfStones, xCord, yCord);
            for (int i = xCord + 1; i < ConnectFour.WIDTH - 1; i++) {

                if (board.getElement(i, yCord) == color) {
                    add(listOfStones, i, yCord);
                } else{
                    break;
                }

                if(setOpponentsBestPlacement(listOfStones, i, setXCord, bestPlacement) == -3) return -3;

            }

            for (int i = xCord - 1; i >= 0; i--) {

                if (board.getElement(i, yCord) == color) {
                    add(listOfStones, i, yCord);
                } else {
                    break;
                }

                if(setOpponentsBestPlacement(listOfStones, i, setXCord, bestPlacement) == -3) return -3;

            }


            // vertical
            listOfStones = new ArrayList<>();
            add(listOfStones, xCord, yCord);
            for (int i = yCord + 1; i < ConnectFour.HEIGHT; i++) {

                if (board.getElement(xCord, i) == color) {
                    add(listOfStones, xCord, i);
                } else {
                    break;
                }

                if(setOpponentsBestPlacement(listOfStones, xCord, setXCord, bestPlacement) == -3) return -3;
            }


            // diagonal top-right - bottom-left
            listOfStones = new ArrayList<>();
            add(listOfStones, xCord, yCord);
            if (ConnectFour.isOnSide(xCord, yCord) || (board.getElement(xCord + 1, yCord + 1) == color || board.getElement(xCord - 1, yCord - 1) == color)) {

                int tempXCord = xCord;
                int tempYCord = yCord;

                while (tempXCord > 0 && tempYCord > 0) {
                    if(board.getElement(--tempXCord, --tempYCord) == color) add(listOfStones, tempXCord, tempYCord);
                    else break;

                    if (setOpponentsBestPlacement(listOfStones, tempXCord, setXCord, bestPlacement) == -3) return -3;

                }

                tempXCord = xCord;
                tempYCord = yCord;

                while (tempXCord < ConnectFour.WIDTH - 1 && tempYCord < ConnectFour.HEIGHT - 1) {
                    if(board.getElement(++tempXCord, ++tempYCord) == color) add(listOfStones, tempXCord, tempYCord);
                    else break;

                    if (setOpponentsBestPlacement(listOfStones, tempXCord, setXCord, bestPlacement) == -3) return -3;
                }
            }


            // diagonal bottom-left - top-right
            listOfStones = new ArrayList<>();
            add(listOfStones, xCord, yCord);
            if (ConnectFour.isOnSide(xCord, yCord) || (board.getElement(xCord + 1, yCord - 1) == color || board.getElement(xCord - 1, yCord + 1) == color)) {

                int tempXCord = xCord;
                int tempYCord = yCord;

                while (tempXCord > 0 && tempYCord < ConnectFour.HEIGHT - 1) {
                    if (board.getElement(--tempXCord, ++tempYCord) == color) add(listOfStones, tempXCord, tempYCord);
                    else break;

                    if (setOpponentsBestPlacement(listOfStones, tempXCord, setXCord, bestPlacement) == -3) return -3;
                }

                tempXCord = xCord;
                tempYCord = yCord;

                while (tempXCord < ConnectFour.WIDTH - 1 && tempYCord > 0) {
                    if (board.getElement(++tempXCord, --tempYCord) == color) add(listOfStones, tempXCord, tempYCord);
                    else break;

                    if (setOpponentsBestPlacement(listOfStones, tempXCord, setXCord, bestPlacement) == -3) return -3;
                }
            }
        }

        return -1;
    }

    private void add(List<String> list, int xCord, int yCord) {
        list.add(xCord + "_" + yCord);
    }

    private int setOpponentsBestPlacement(List<String> listOfStones, int xCord, int setXCord, String[] bestPlacement) {

        setXCord = xCord;

        if (listOfStones.size() == 3 || listOfStones.size() == 2) {
            bestPlacement = new String[listOfStones.size()];

            for (int i = 0; i < listOfStones.size(); i++) {
                bestPlacement[i] = listOfStones.get(i);
            }

            if (listOfStones.size() == 3) return -3;
        }

        return -1;
    }

}
