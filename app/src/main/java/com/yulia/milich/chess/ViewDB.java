package com.yulia.milich.chess;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDB extends AppCompatActivity {

    SQLiteDatabase sqdb;
    DBUsers users;
    ArrayList<String> opers = new ArrayList<>();
    ArrayAdapter<String> adp;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_db);

        lv = (ListView) findViewById(R.id.lv);

        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();

        read_fromDC();
        table_close();
    }

    public void read_fromDC() {
        Cursor c = sqdb.query(
                DBUsers.TABLE_NAME, null, null, null, null, null
                , null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int idColIndex = c.getColumnIndex("_id");
            int nameColIndex = c.getColumnIndex("name");
            int passwordColIndex = c.getColumnIndex("password");
            int gamesPlayedColIndex = c.getColumnIndex("games_played");
            int gamesWonColIndex = c.getColumnIndex("games_won");
            int managerColIndex = c.getColumnIndex("manager");

            String name = c.getString(nameColIndex);
            String password = c.getString(passwordColIndex);
            int gamesPlayed = c.getInt(gamesPlayedColIndex);
            int gamesWon = c.getInt(gamesWonColIndex);
            String manager = c.getString(managerColIndex);
            String info = name + " " + password + " " + gamesPlayed + " " + gamesWon + " " + manager;
            opers.add(info);
            c.moveToNext();
        }
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opers);
        lv.setAdapter(adp);
        c.close();
    }

    public void table_close() {
        sqdb.close();
        users.close();
    }
}
