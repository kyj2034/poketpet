package com.example.pocketpetlayout;

public class Feed {
    public static final String TABLE_NAME = "feed";
    public static final String FEED_ID = "feed_id";
    public static final String FEED_TITLE = "feed_title";
    public static final String WRITER = "writer";
    public static final String IMAGE = "image";

    private static final String SQL_CREATE_FEED =
            "CREATE TABLE " + Feed.TABLE_NAME + " (" +
                    Feed.FEED_ID + " INTEGER PRIMARY KEY," +
                    Feed.FEED_TITLE + " TEXT," +
                    Feed.WRITER + " TEXT," +
                    Feed.IMAGE + " TEXT)";

    private static final String SQL_DELETE_FEED =
            "DROP TABLE IF EXISTS " + Feed.TABLE_NAME;

}
