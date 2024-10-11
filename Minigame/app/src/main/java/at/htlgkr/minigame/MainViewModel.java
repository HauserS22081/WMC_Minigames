package at.htlgkr.minigame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public static final int MENU = 0;
    public static final int TICTACTOE = 1;
    public static final int MEMORY = 2;
    public static final int CONNECTFOUR = 3;


    private MutableLiveData<Integer> _state = new MutableLiveData<>(MENU);
    public LiveData<Integer> state = _state;

    public void showTicTacToe() {
        _state.postValue(TICTACTOE);
    }

    public void showMemory() {
        _state.postValue(MEMORY);
    }

    public void showConnectFour() {
        _state.postValue(CONNECTFOUR);
    }

}
