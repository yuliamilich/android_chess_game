package com.yulia.milich.chess;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBUsers extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "my_account";
    public static final String UID = "_id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String GAMESPLAYED = "games_played";
    public static final String GAMESWON = "games_won";
    public static final String MANAGER = "manager";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT, "
            + PASSWORD + " TEXT, "
            + GAMESPLAYED + " INT, "
            + GAMESWON + " INT, "
            + MANAGER + " TEXT);";

    private static final String
            SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBUsers(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);


    }

    public boolean addData(String name, String password, String manager) {
        SQLiteDatabase sqdb = getWritableDatabase();
//        int summa = Integer.parseInt(gamesPlayer);
//        int num = Integer.parseInt(gamesWon);
        ContentValues cv = new ContentValues();
        cv.put(DBUsers.NAME, name);
        cv.put(DBUsers.PASSWORD, password);
        cv.put(DBUsers.GAMESPLAYED, 0);
        cv.put(DBUsers.GAMESWON, 0);
        cv.put(DBUsers.MANAGER, manager);
        long result = sqdb.insert(DBUsers.TABLE_NAME, null, cv);

        if (result == -1) {
            return false;
        } else return true;


    }

}
