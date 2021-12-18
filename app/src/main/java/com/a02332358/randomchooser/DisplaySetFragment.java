package com.a02332358.randomchooser;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a02332358.randomchooser.viewmodels.SetsViewModel;

public class DisplaySetFragment extends Fragment {
    public DisplaySetFragment(){super(R.layout.fragment_display_set);}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetsViewModel viewModel = new ViewModelProvider(getActivity()).get(SetsViewModel.class);
        ObservableArrayList groups = viewModel.getGroups();
        GroupsAdapter groupsAdapter = new GroupsAdapter(
                groups,
                entry ->{
                    viewModel.setCurrentGroup(entry);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, singleGroupFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
                );

        groups.addOnListChangedCallback(new ObservableList.OnListChangedCallback() {
            @Override
            public void onChanged(ObservableList sender) {
                getActivity().runOnUiThread(() ->{

                });
                groupsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(() ->{
                    groupsAdapter.notifyItemRangeChanged(positionStart, itemCount);

                });
            }

            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(() ->{
                    groupsAdapter.notifyItemRangeInserted(positionStart, itemCount);

                });
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
                getActivity().runOnUiThread(() ->{
                    groupsAdapter.notifyItemMoved(fromPosition,toPosition);

                });
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                getActivity().runOnUiThread(() ->{
                    groupsAdapter.notifyItemRangeRemoved(positionStart, itemCount);

                });
            }
        });


        RecyclerView recyclerView = view.findViewById(R.id.groups_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(groupsAdapter);
        view.findViewById(R.id.fab).setOnClickListener(fab -> {
            viewModel.setCurrentGroup(null);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, CreateSetFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();

        });
    }
}
