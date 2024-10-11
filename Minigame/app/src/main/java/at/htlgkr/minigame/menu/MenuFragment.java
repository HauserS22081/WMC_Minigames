package at.htlgkr.minigame.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.htlgkr.minigame.MainViewModel;
import at.htlgkr.minigame.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment{

    private FragmentMenuBinding binding;

    public MenuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainViewModel viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = FragmentMenuBinding.inflate(inflater, container, false);

        binding.btTictactoe.setOnClickListener(view -> {
            viewModel.showTicTacToe();
        });
        binding.btMemory.setOnClickListener(view -> {
            viewModel.showMemory();
        });
        binding.btConnectFour.setOnClickListener(view -> {
            viewModel.showConnectFour();
        });

        return binding.getRoot();
    }
}