package com.a02332358.randomchooser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.a02332358.randomchooser.models.Group;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
    ObservableArrayList<Group> groups;

    public interface OnGroupsEntryClicked {
        public void onClick(Group group);
    }

    OnGroupsEntryClicked listener;

    public GroupsAdapter(ObservableArrayList<Group> groups, OnGroupsEntryClicked listener){
        this.groups = groups;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.listTitle);
        textView.setText(groups.get(position).title);
        holder.itemView.setOnClickListener(view -> {
            listener.onClick(groups.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
