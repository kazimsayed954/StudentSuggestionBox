package com.kazim.studentsuggestionbox;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={LikeDislike_List.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract LikeDislikeDao myDao();
}