package at.htlgkr.minigame.connectfour;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import at.htlgkr.minigame.databinding.FragmentConnectFourBinding;

public class ConnectFourFragment extends Fragment implements View.OnClickListener {

    private FragmentConnectFourBinding binding;
    private List<ImageView> imageViews = new ArrayList<>();

    public ConnectFourFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentConnectFourBinding.inflate(inflater, container, false);
        // binding.etName.getText().toString();

        addImageViewsToList();
        for (ImageView imageView : imageViews) {
            imageView.setOnClickListener(this);
        }


        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {

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
}