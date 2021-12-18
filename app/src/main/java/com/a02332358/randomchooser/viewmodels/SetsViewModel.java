package com.a02332358.randomchooser.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.a02332358.randomchooser.database.AppDatabase;
import com.a02332358.randomchooser.models.Group;

import java.util.ArrayList;
import java.util.List;

public class SetsViewModel extends AndroidViewModel {
    private AppDatabase database;
    private MutableLiveData<Boolean> saving = new MutableLiveData<>();
    private ObservableArrayList<Group> groups = new ObservableArrayList<>();
    private MutableLiveData<Group> currentGroup = new MutableLiveData<>();

    public SetsViewModel(@NonNull Application application) {
        super(application);
        saving.setValue(false);
        database =  Room.databaseBuilder(application, AppDatabase.class, "chooserdb").build();

        new Thread(() ->{
            ArrayList<Group> group = (ArrayList<Group>) database.getgroupDao().getAll();
            groups.addAll(group);
        }).start();

    }

    public MutableLiveData<Group> getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Group currentGroup) {
        this.currentGroup.setValue(currentGroup);
    }

    public MutableLiveData<Boolean> getSaving() {
        return saving;
    }

    public ObservableArrayList<Group> getGroups() {
        return groups;
    }

    public void deleteCurrentGroup(){
        new Thread(()->{
            database.getgroupDao().delete(currentGroup.getValue());
            groups.remove(currentGroup.getValue());
            currentGroup.postValue(null);
        }).start();
    }

    public void saveSet(String name, String items){
        saving.setValue(true);
        new Thread(()->{
            if (currentGroup.getValue() != null){
                Group current = currentGroup.getValue();
                current.title = name;
                current.items = items;
                database.getgroupDao().update(current);
                currentGroup.postValue(current);
                int index = groups.indexOf(current);
                groups.set(index, current);
            } else{
                Group group = new Group();

                group.title = name;
                group.items = items;
                group.id = database.getgroupDao().insert(group);

                groups.add(group);

            }

        }).start();

        saving.postValue(false);
    }
}
