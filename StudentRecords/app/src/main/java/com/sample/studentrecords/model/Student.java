package com.sample.studentrecords.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Student Model Class
 */
@Entity
public class Student {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "hobby")
    public String hobby;

    public Student(String id, String name, String hobby){
        this.id = id;
        this.name = name;
        this.hobby = hobby;
    }
}
