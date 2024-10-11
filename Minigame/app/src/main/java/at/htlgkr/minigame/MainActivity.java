package at.htlgkr.minigame;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import at.htlgkr.minigame.connectfour.ConnectFourFragment;
import at.htlgkr.minigame.databinding.ActivityMainBinding;
import at.htlgkr.minigame.memory.MemoryFragment;
import at.htlgkr.minigame.menu.MenuFragment;
import at.htlgkr.minigame.tictactoe.TicTacToeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.state.observe(this, state -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (state) {
                case MainViewModel.MENU: {
                    fragmentTransaction.add(R.id.main, new MenuFragment(), "FRAGMENT MENU");
                    break;
                }

                case MainViewModel.TICTACTOE: {
                    fragmentTransaction.replace(R.id.main, new TicTacToeFragment(), "FRAGMENT TICTACTOE");
                    fragmentTransaction.addToBackStack("");
                    break;
                }

                case MainViewModel.MEMORY: {
                    fragmentTransaction.add(R.id.main, new MemoryFragment(), "FRAGMENT MEMORY");
                    fragmentTransaction.addToBackStack("");
                    break;
                }

                case MainViewModel.CONNECTFOUR: {
                    fragmentTransaction.replace(R.id.main, new ConnectFourFragment(), "FRAGMENT CONNECT FOUR");
                    fragmentTransaction.addToBackStack("");
                }
            }

            fragmentTransaction.commit();
        });
    }
}