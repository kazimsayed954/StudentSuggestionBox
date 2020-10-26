package com.kazim.studentsuggestionbox;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="mydata")
public class LikeDislike_List {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "user_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
