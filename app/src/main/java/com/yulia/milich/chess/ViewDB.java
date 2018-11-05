package com.yulia.milich.chess;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDB extends AppCompatActivity implements View.OnClickListener{

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

//        users.onUpgrade(sqdb,1,2);

        Button newUser = (Button) findViewById(R.id.newUser);
        newUser.setOnClickListener(this);

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);

        read_fromDC();
        //table_close();
    }

    public void read_fromDC() {
        Cursor c = sqdb.query(DBUsers.TABLE_NAME, null, null, null, null, null, null);
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

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.delete:
//                AlertDialog.Builder builder = new AlertDialog.Builder(ViewDB.this);
//                builder.setCancelable(true);
//                if(userChosen){
//                    builder.setTitle("Delete the user you chose");
//                    builder.setMessage("Are you sure you want to delete it?");
//                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //delete the user
//                            dialog.cancel();
//                        }
//                    });
//                    builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                }
//                else{
//                    builder.setTitle("Delete all the users");
//                    builder.setMessage("Are you sure you want to delete EVERYTHING?");
//                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //delete all the data
//                            users.onUpgrade(sqdb,0,0);
//                            dialog.cancel();
//                        }
//                    });
//                    builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                }
//
//                    builder.show();
                break;
            case R.id.newUser:
                intent = new Intent(this, SignUp.class);

//            default: finishActivity(1);
//                finish();
//                break;
        }
        startActivity(intent);
    }
}
