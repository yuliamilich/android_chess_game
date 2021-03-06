package com.yulia.milich.chess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


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

    public Cursor getItem(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemLike(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + " LIKE '" + name + "%'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + NAME + " = '" + newName + "' WHERE " + UID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void updatePassword(String newPassword, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + PASSWORD + " = '" + newPassword + "' WHERE " + UID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void updatePassword(String newPassword, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + PASSWORD + " = '" + newPassword + "' WHERE " + NAME + " = '" + name + "'";
        db.execSQL(query);
    }

    public void updateGamesPlayed(String newGamesPlayed, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + GAMESPLAYED + " = '" + newGamesPlayed + "' WHERE " + UID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void updateGamesWon(String newGamesWon, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + GAMESWON + " = '" + newGamesWon + "' WHERE " + UID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void updateManager(String newManager, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + MANAGER + " = '" + newManager + "' WHERE " + UID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void updateID(int id, int newID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + UID + " = '" + newID + "' WHERE " + UID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void deleteName(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + UID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void update(int id, int newId, String newName, String newPassword, String newGamesPlayed, String newGamesWon, String newManager) {
        updateID(id, newId);
        updateName(newName, id);
        updatePassword(newPassword, id);
        updateGamesPlayed(newGamesPlayed, id);
        updateGamesWon(newGamesWon, id);
        updateManager(newManager, id);
    }

    public boolean isNameTaken(String name) {
        boolean ok = false;
        SQLiteDatabase sqdb = this.getWritableDatabase();
//        Cursor c = sqdb.query(DBUsers.TABLE_NAME, null, null, null, null, null, null);
//        c.moveToFirst();
//        while (!c.isAfterLast() && !ok) {
//            int nameColIndex = c.getColumnIndex(NAME);
//
//            String name1 = c.getString(nameColIndex);
//            if (name1.equals(name))
//                ok = true;
//            c.moveToNext();
//        }
//        c.close();
        Cursor c = getItem(name);
        if(c.getCount()>0){
            ok = true;
        }
        c.close();
        return ok;
    }

    public boolean doesUserExists(String name, String password) {
        boolean exist = false;
        boolean ok = false;

        SQLiteDatabase sqdb = this.getWritableDatabase();
        Cursor c = sqdb.query(DBUsers.TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast() && !ok) {
            int nameColIndex = c.getColumnIndex(NAME);
            int passwordColIndex = c.getColumnIndex(PASSWORD);

            String name1 = c.getString(nameColIndex);
            String password1 = c.getString(passwordColIndex);

            if (name1.equals(name) && password1.equals(password)) {
                exist = true;
                ok = true;
            }
            c.moveToNext();
        }

        c.close();
        return exist;
    }
}
