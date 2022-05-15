package com.example.pocketpetlayout;

public class FeedItem {
    private int id;
    private String title;
    private String writer;
    private String ImgName;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getImgName() {
        return ImgName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setImgName(String mImgName) {
        this.ImgName = mImgName;
    }

}

