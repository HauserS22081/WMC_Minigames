package at.htlgkr.minigame.tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.htlgkr.minigame.MainViewModel;
import at.htlgkr.minigame.R;
import at.htlgkr.minigame.databinding.FragmentTicTacToeBinding;

public class TicTacToeFragment extends Fragment implements View.OnClickListener {

    private FragmentTicTacToeBinding binding;
    private TextView tvTitle;
    private List<Button> buttons = new ArrayList<>();
    private TicTacToe ticTacToe = new TicTacToe();


    public TicTacToeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTicTacToeBinding.inflate(inflater, container, false);

        tvTitle = binding.tvTitle;

        addButtonsToList();
        setOnClickListener();

        clearBoard();

        return binding.getRoot();
    }


    @Override
    public void onClick(View view) {
        Log.d("MAIN_ACTIVITY", "Button was clicked");
        String playerSymbol = ticTacToe.play(String.valueOf(view.getTag()));
        ((Button) view).setText(playerSymbol);

        // no one = 0, player1 = 1, player2 = 2
        int playerThatWon = ticTacToe.checkIfSomeoneWon();
        if (playerThatWon == 1 || playerThatWon == 2) {
            tvTitle.setText("Player " + (playerThatWon == 1 ? "o" : "x") + " won");
            clearBoardAndTicTacToe();
        }

        if (ticTacToe.isBoardFull()) {
            tvTitle.setText("no one won");
            clearBoardAndTicTacToe();
        }
    }

    private void clearBoardAndTicTacToe() {
        ticTacToe.clear();
        clearBoard();
    }

    private void clearBoard() {
        for (Button button : buttons) {
            button.setText("");
        }

    }

    private void setOnClickListener() {
        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    private void addButtonsToList() {
        buttons.add(binding.bt00);
        buttons.add(binding.bt01);
        buttons.add(binding.bt02);
        buttons.add(binding.bt10);
        buttons.add(binding.bt11);
        buttons.add(binding.bt12);
        buttons.add(binding.bt20);
        buttons.add(binding.bt21);
        buttons.add(binding.bt22);
    }
}