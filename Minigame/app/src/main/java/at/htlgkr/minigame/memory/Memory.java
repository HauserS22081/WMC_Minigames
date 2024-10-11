package at.htlgkr.minigame.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Memory {
    private int firstIDX = -1;
    private int firstIDY = -1;
    private final MemoryBoard board = new MemoryBoard();

    public Memory() {
        fillBoard();
    }

    public int play(String description) {

        // returnValues: 0: firstIDSet, 1: 2 pictures are same, 2: 2 pictures are not same

        int xCord = Integer.parseInt(description.split("_")[0]);
        int yCord = Integer.parseInt(description.split("_")[1]);

        if (firstIDX == -1) {
            firstIDX = xCord;
            firstIDY = yCord;
            return 0;
        }

        if (board.getBoardElement(firstIDX, firstIDY) == board.getBoardElement(xCord, yCord)) {

            // set cards open in board
            board.setBoardElement(firstIDX, firstIDY, -1);
            board.setBoardElement(xCord, yCord, -1);

            resetVariables();
            return 1;
        }

        resetVariables();
        return 2;
    }

    public boolean isCardOpen(String description) {
        int xCord = 0;
        int yCord = 0;
        try {
            xCord = Integer.parseInt(description.split("_")[0]);
            yCord = Integer.parseInt(description.split("_")[1]);
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Error in isCardOpen: description: " + description + " board: " );
            System.exit(-1);
        }

        return board.getBoardElement(xCord, yCord) == -1;
    }

    public boolean checkifWon() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if(board.getBoardElement(j, i) != -1) return false;

            }
        }

        fillBoard();

        return true;
    }

    public int getImage(String description) {
        int xCord = Integer.parseInt(description.split("_")[0]);
        int yCord = Integer.parseInt(description.split("_")[1]);
        return board.getBoardElement(xCord, yCord);
    }

    private void resetVariables() {
        firstIDX = -1;
        firstIDY = -1;
    }

    public void fillBoard() {

        List<Integer> pictures = board.getPictures();
        List<Integer> pictures16 = new ArrayList<>();
        pictures16.addAll(pictures);
        pictures16.addAll(pictures);

        Collections.shuffle(pictures16);
        int idx = 0;

        for (int i = 0; i < board.boardLength; i++) {
            for (int j = 0; j < board.boardLength; j++) {
                board.setBoardElement(j, i, pictures16.get(idx++));
            }
        }
    }

    public int getBackSidePicture() {
        return board.getBackSidePicture();
    }

    public void restart() {
        fillBoard();
        resetVariables();
    }
}
