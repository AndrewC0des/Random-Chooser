package com.a02332358.randomchooser.models;

import java.util.ArrayList;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Group {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public String items;
}
