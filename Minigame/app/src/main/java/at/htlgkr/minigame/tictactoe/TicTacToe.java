package at.htlgkr.minigame.tictactoe;

public class TicTacToe {

    private boolean player1Turn = true;
    private TicTacToeBoard board = new TicTacToeBoard();

    public String play(String buttonID) {
        int xCord = Integer.parseInt(buttonID.split("_")[0]);
        int yCord = Integer.parseInt(buttonID.split("_")[1]);

        if (player1Turn) {
            boolean validPlacement = board.elementClicked(yCord, xCord, 1);
            if (validPlacement) {
                player1Turn = false;
                return "o";
            }
        } else {
            boolean validPlacement = board.elementClicked(yCord, xCord, 2);
            if (validPlacement) {
                player1Turn = true;
                return "x";
            }
        }

        return board.getElement(yCord, xCord) == 1 ? "o" : "x";
    }

    public int checkIfSomeoneWon() {
        // 0 -> no, 1 -> Player1 won, 2 -> Player2 won


        // vertical
        for (int j =

             0; j < 3; j++) {
            int sum = 1;
            for (int k = 1; k < 3; k++) {
                if (board.getElement(j, k) != 0 && board.getElement(j, k) == board.getElement(j, k - 1)) {
                    sum++;
                }
            }
            if (sum == 3) return board.getElement(j, 0);
        }

        // horizontal
        for (int j = 0; j < 3; j++) {
            int sum = 1;
            for (int k = 1; k < 3; k++) {
                if (board.getElement(k, j) != 0 && board.getElement(k, j) == board.getElement(k-1, j)) {
                    sum++;
                }
            }
            if (sum == 3) return board.getElement(0, j);
        }

        // diagonal
        if (board.getElement(0, 0) == board.getElement(1, 1) && board.getElement(1, 1) == board.getElement(2, 2)) {
            return board.getElement(1, 1);
        }
        if (board.getElement(0, 2) == board.getElement(1, 1) && board.getElement(1, 1) == board.getElement(2, 0)) {
            return board.getElement(1, 1);
        }

        return 0;
    }

    public void clear() {
        player1Turn = true;
        board = new TicTacToeBoard();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getElement(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

