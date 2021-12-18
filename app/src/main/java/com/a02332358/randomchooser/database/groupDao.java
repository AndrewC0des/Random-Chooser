package com.a02332358.randomchooser.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.a02332358.randomchooser.models.Group;

import java.util.List;


@Dao
public interface groupDao {
    @Insert
    public long insert(Group group);

    @Query("SELECT * FROM `group`")
    public List<Group> getAll();

    @Query("SELECT * FROM `group` WHERE id = :id LIMIT 1")
    public Group findById(long id);

    @Update
    public void update(Group group);

    @Delete
    public void delete(Group group);

}
