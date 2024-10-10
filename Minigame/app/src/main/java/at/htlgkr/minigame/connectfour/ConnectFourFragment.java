package at.htlgkr.minigame.connectfour;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import at.htlgkr.minigame.databinding.FragmentConnectFourBinding;

public class ConnectFourFragment extends Fragment implements View.OnClickListener {

    private FragmentConnectFourBinding binding;
    private final List<ImageView> imageViews = new ArrayList<>();
    private final List<LinearLayout> linearLayouts = new ArrayList<>();
    private final ConnectFour connectFour = new ConnectFour();
    private boolean processing = false;
    private boolean hasWon = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentConnectFourBinding.inflate(inflater, container, false);
        // binding.etName.getText().toString();

        addImageViewsToList();
        addLinearLayoutsToList();
        Button restartButton = binding.btRestart;

        for (LinearLayout linearLayout : linearLayouts) {
            linearLayout.setOnClickListener(this);
        }


        setBackgroundImages();

        restartButton.setOnClickListener(view -> restart());

        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(processing ||hasWon) return;

        processing = true;

        String description = String.valueOf(view.getContentDescription());
        int xCord = Integer.parseInt(description.split("_")[0]);

        int yCord = connectFour.play(xCord);
        if(yCord == -1){
            processing = false;
            return;
        }


        int image = connectFour.getImage(xCord, yCord);

        ImageView slot = getImageView(xCord, yCord);
        if(slot == null){
            processing = false;
            return;
        }

        slot.setTranslationY(-1000f);
        setColor(slot, image);
        slot.animate().translationYBy(1000f).setDuration(500);

        if (connectFour.hasWon(xCord, yCord)) won();

        processing = false;

    }

    private void won() {
        Snackbar.make(binding.getRoot(), "Gewonnen", 1000).show();
        hasWon = true;
    }

    private void restart() {
        hasWon = false;
        processing = false;
        setBackgroundImages();
        connectFour.restart();
    }

    private ImageView getImageView(int xCord, int yCord) {
        for (int i = 0; i < imageViews.size(); i++) {
            if(imageViews.get(i).getContentDescription().equals(xCord + "_" + yCord))
                return imageViews.get(i);
        }
        return null;
    }

    private void setColor(ImageView view, int color) {
        view.setImageResource(color);
    }

    private void addLinearLayoutsToList() {
        linearLayouts.add(binding.llVertical0);
        linearLayouts.add(binding.llVertical1);
        linearLayouts.add(binding.llVertical2);
        linearLayouts.add(binding.llVertical3);
        linearLayouts.add(binding.llVertical4);
        linearLayouts.add(binding.llVertical5);
        linearLayouts.add(binding.llVertical6);

    }

    private void addImageViewsToList() {

        imageViews.add(binding.iv00);
        imageViews.add(binding.iv01);
        imageViews.add(binding.iv02);
        imageViews.add(binding.iv03);
        imageViews.add(binding.iv04);
        imageViews.add(binding.iv05);
        imageViews.add(binding.iv10);
        imageViews.add(binding.iv11);
        imageViews.add(binding.iv12);
        imageViews.add(binding.iv13);
        imageViews.add(binding.iv14);
        imageViews.add(binding.iv15);
        imageViews.add(binding.iv20);
        imageViews.add(binding.iv21);
        imageViews.add(binding.iv22);
        imageViews.add(binding.iv23);
        imageViews.add(binding.iv24);
        imageViews.add(binding.iv25);
        imageViews.add(binding.iv30);
        imageViews.add(binding.iv31);
        imageViews.add(binding.iv32);
        imageViews.add(binding.iv33);
        imageViews.add(binding.iv34);
        imageViews.add(binding.iv35);
        imageViews.add(binding.iv40);
        imageViews.add(binding.iv41);
        imageViews.add(binding.iv42);
        imageViews.add(binding.iv43);
        imageViews.add(binding.iv44);
        imageViews.add(binding.iv45);
        imageViews.add(binding.iv50);
        imageViews.add(binding.iv51);
        imageViews.add(binding.iv52);
        imageViews.add(binding.iv53);
        imageViews.add(binding.iv54);
        imageViews.add(binding.iv55);
        imageViews.add(binding.iv60);
        imageViews.add(binding.iv61);
        imageViews.add(binding.iv62);
        imageViews.add(binding.iv63);
        imageViews.add(binding.iv64);
        imageViews.add(binding.iv65);

    }

    private void setBackgroundImages() {
        imageViews.forEach(i -> setColor(i, 0));
    }
}
