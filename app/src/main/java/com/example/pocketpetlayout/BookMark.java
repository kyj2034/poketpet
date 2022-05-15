package com.example.pocketpetlayout;

public class BookMark {
    public static final String TABLE_NAME = "bookmark";
    public static final String BOARD_ID = "board_id";
    public static final String MEMBER_ID = "member_id";

    private static final String SQL_CREATE_BOOKMARK =
            "CREATE TABLE " + BookMark.TABLE_NAME + " (" +
                    BookMark.BOARD_ID + " INTEGER PRIMARY KEY," +
                    BookMark.MEMBER_ID + " TEXT)";

    private static final String SQL_DELETE_BOOKMARK =
            "DROP TABLE IF EXISTS " + BookMark.TABLE_NAME;
}
