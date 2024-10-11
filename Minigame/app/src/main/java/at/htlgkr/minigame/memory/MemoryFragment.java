package at.htlgkr.minigame.memory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import at.htlgkr.minigame.R;
import at.htlgkr.minigame.databinding.FragmentMemoryBinding;

public class MemoryFragment extends Fragment implements View.OnClickListener {

    private FragmentMemoryBinding binding;

    private Memory memory;
    private ImageView firstCard;
    private ImageView secondCard;
    private boolean isProcessing = false;
    private List<ImageView> imageViews = new ArrayList<>();

    public MemoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMemoryBinding.inflate(inflater, container, false);

        addBindingsToList();

        for (ImageView picture : imageViews) {
            picture.setOnClickListener(this);
        }

        memory = new Memory();
        setBackSideImages();

        binding.btRestart.setOnClickListener(view -> {
            memory.restart();
            setBackSideImages();
        });

        return binding.getRoot();
    }


    @Override
    public void onClick(View view) {

        if(isProcessing) return;

        isProcessing = true;

        ImageView card = (ImageView) view;

        if(secondCard != null) return;
        if(firstCard == card) return;
        if(memory.isCardOpen(String.valueOf(card.getContentDescription()))) return;


        int imageInt = memory.getImage(String.valueOf(view.getContentDescription()));
        card.setImageResource(imageInt);

        int result = memory.play(String.valueOf(view.getContentDescription()));

        switch (result) {
            case 0: firstCard = card; isProcessing = false; return;
            case 1:{
                resetVariables();
                if (memory.checkifWon()) {
                    setBackSideImages();
                    showWinSnackbar();
                }
                isProcessing = false;
                return;
            }
            case 2:{
                secondCard = card;

                new android.os.Handler().postDelayed(() -> {
                    secondCard.setImageResource(memory.getBackSidePicture());
                    firstCard.setImageResource(memory.getBackSidePicture());

                    resetVariables();
                    isProcessing = false;
                }, 1000);
            }
        }
    }

    private void showWinSnackbar() {
        new android.os.Handler().postDelayed(() -> {
            Snackbar.make(binding.getRoot(), "gewonnen", Snackbar.LENGTH_SHORT).show();
        }, 1000);
    }


    private void resetVariables() {
        firstCard = null;
        secondCard = null;
        isProcessing = false;
    }

    private void setBackSideImages() {
        resetVariables();

        int backSide = memory.getBackSidePicture();

        for (ImageView picture : imageViews) {
            picture.setImageResource(backSide);
        }

    }

    private void addBindingsToList() {
        imageViews.add(binding.ivImage00);
        imageViews.add(binding.ivImage10);
        imageViews.add(binding.ivImage20);
        imageViews.add(binding.ivImage30);
        imageViews.add(binding.ivImage01);
        imageViews.add(binding.ivImage11);
        imageViews.add(binding.ivImage21);
        imageViews.add(binding.ivImage31);
        imageViews.add(binding.ivImage02);
        imageViews.add(binding.ivImage12);
        imageViews.add(binding.ivImage22);
        imageViews.add(binding.ivImage32);
        imageViews.add(binding.ivImage03);
        imageViews.add(binding.ivImage13);
        imageViews.add(binding.ivImage23);
        imageViews.add(binding.ivImage33);
    }
}