package com.a02332358.randomchooser;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.a02332358.randomchooser.viewmodels.SetsViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class singleGroupFragment extends Fragment {
    private String[] arrItems;
    private Random rand = new Random();
    public singleGroupFragment(){
        super(R.layout.fragment_display_group);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetsViewModel viewModel = new ViewModelProvider(getActivity()).get(SetsViewModel.class);

        viewModel.getCurrentGroup().observe(getViewLifecycleOwner(), (entry) ->{
            if(entry == null){
                getActivity().getSupportFragmentManager().popBackStack();
            } else{
                TextView header = view.findViewById(R.id.header);
                TextView contents = view.findViewById(R.id.contents);

                header.setText(entry.title);
                contents.setText("This set contains: " + entry.items);
                arrItems = entry.items.split(",");
            }
        });

        view.findViewById(R.id.deleteButton).setOnClickListener((fab)->{
            viewModel.deleteCurrentGroup();
        });
        view.findViewById(R.id.editButton).setOnClickListener((editFab)->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, CreateSetFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });
        view.findViewById(R.id.chooseButton).setOnClickListener((choose)->{
            int index = rand.nextInt(arrItems.length);
            TextView random = view.findViewById(R.id.RandomItem);
            random.setText("Random item: " + arrItems[index]);
        });

    }
}
