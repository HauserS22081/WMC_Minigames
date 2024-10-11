package at.htlgkr.minigame.memory;

import java.util.List;

import at.htlgkr.minigame.R;

public class MemoryBoard {

    // resources for pictures
    private final int cardBack = R.drawable.pokemoncardback;
    private final int picture0 = R.drawable.pokemoncard0;
    private final int picture1 = R.drawable.pokemoncard1;
    private final int picture2 = R.drawable.pokemoncard2;
    private final int picture3 = R.drawable.pokemoncard3;
    private final int picture4 = R.drawable.pokemoncard4;
    private final int picture5 = R.drawable.pokemoncard5;
    private final int picture6 = R.drawable.pokemoncard6;
    private final int picture7 = R.drawable.pokemoncard7;

    public final int boardLength = 4;
    private final int[][] board = new int[boardLength][boardLength];

    public int getBoardElement(int xCord, int yCord) {
        return board[xCord][yCord];
    }

    public void setBoardElement(int xCord, int yCord, int value) {
        this.board[xCord][yCord] = value;
    }

    public List<Integer> getPictures() {
        return List.of(picture0, picture1, picture2, picture3, picture4, picture5, picture6, picture7);
    }

    public int getBackSidePicture() {
        return cardBack;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                string += board[j][i] + " | ";
            }
        }
        return string;
    }

}
