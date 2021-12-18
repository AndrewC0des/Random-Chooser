package com.a02332358.randomchooser.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.a02332358.randomchooser.models.Group;

@Database(entities = {Group.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract groupDao getgroupDao();
}
