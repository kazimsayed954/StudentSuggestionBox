package com.kazim.studentsuggestionbox;

public class MyListData {
    private int id;
    private String imageurl;
    private String likecount;
    private String description;
    private String Status;

    public MyListData(int id, String imageurl, String likecount,String description,String Status) {
        this.id = id;
        this.imageurl = imageurl;
        this.likecount = likecount;
        this.description=description;
        this.Status=Status;
    }

    public int getId() {
        return id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getLikecount() {
        return likecount;
    }
    public String getdescription() {
        return description;
    }
    public String getStatus() {
        return Status;
    }
}