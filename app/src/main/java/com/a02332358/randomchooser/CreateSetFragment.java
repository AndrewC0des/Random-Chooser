package com.a02332358.randomchooser;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.a02332358.randomchooser.viewmodels.SetsViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CreateSetFragment extends Fragment {
    private boolean previousSavingState = false;
    String items = "";
    public CreateSetFragment(){super(R.layout.fragment_create_set);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetsViewModel viewModel = new ViewModelProvider(getActivity()).get(SetsViewModel.class);
        viewModel.getCurrentGroup().observe(getViewLifecycleOwner(), (entry)->{
            if (entry != null){
                TextInputEditText name = view.findViewById(R.id.nameText);
                name.setText(entry.title);

                AppCompatTextView textView = view.findViewById(R.id.textView);
                textView.setText("Current set: " + entry.items);
                items = entry.items;
            }
        });
        viewModel.getSaving().observe(getViewLifecycleOwner(), (saving)->{
            if(saving && !previousSavingState){
                MaterialButton button = view.findViewById(R.id.SaveButton);
                button.setEnabled(false);
                button.setText("Saving...");
                previousSavingState = saving;
            } else if(previousSavingState && !saving){
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        view.findViewById(R.id.saveItem).setOnClickListener(saveItem ->{
            TextInputEditText item = view.findViewById(R.id.itemText);
            items = (items  + item.getText() + ",");
            AppCompatTextView textView = view.findViewById(R.id.textView);
            textView.setText(textView.getText() + item.getText().toString() + ",");
            item.getText().clear();

        });
        view.findViewById(R.id.SaveButton).setOnClickListener(saveButton ->{
            TextInputEditText name = view.findViewById(R.id.nameText);
            viewModel.saveSet(name.getText().toString(), items);
        });
    }
}
