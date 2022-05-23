package com.example.pocketpetlayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 10;
    public static final String DATEBASE_NAME = "PoketPet.db";

    public DBHelper(Context context) {
        super(context, DATEBASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Board.SQL_CREATE_BOARD);
        sqLiteDatabase.execSQL(Comment.SQL_CREATE_COMMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Board.SQL_DELETE_BOARD);
        sqLiteDatabase.execSQL(Comment.SQL_DELETE_COMMENT);
        onCreate(sqLiteDatabase);
    }
}
