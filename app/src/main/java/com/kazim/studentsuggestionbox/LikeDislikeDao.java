package com.kazim.studentsuggestionbox;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LikeDislikeDao {
    @Insert
    public void addData(LikeDislike_List likeDislikeList);

    @Query("select * from mydata")
    public List<LikeDislike_List>getMyData();

    @Query("SELECT EXISTS (SELECT 1 FROM mydata WHERE id=:id)")
    public int isLiked(int id);

    @Delete
    public void delete(LikeDislike_List likeDislikeList);


}
