package com.yulia.milich.chess;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserInfo extends AppCompatActivity implements View.OnClickListener{
    private TextView title, played, won;
    private EditText password;
    private Button viewDB, edit;
    private String name, manager;
    private int gamesPlayed, gamesWon;

    SQLiteDatabase sqdb;
    DBUsers users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();

        name = (String) getIntent().getStringExtra("name");

        title = (TextView) findViewById(R.id.title);
        played = (TextView) findViewById(R.id.played);
        won = (TextView) findViewById(R.id.won);

        password = (EditText) findViewById(R.id.password);

        viewDB = (Button) findViewById(R.id.viewDB);
        edit = (Button) findViewById(R.id.edit);

        viewDB.setOnClickListener(this);
        edit.setOnClickListener(this);

        show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.viewDB:
                Intent intent = new Intent(this, ViewDB.class);
                startActivity(intent);
                finish();
                break;
            case R.id.edit:
                    String sName = name;
                    String sPassword = password.getText().toString();
                    users.updatePassword(sPassword, sName);
                    password.setText("");
        }
    }

    public void show(){
        Cursor data = users.getItem(name);

        while(data.moveToNext()){
            int gamesPlayedColIndexx = data.getColumnIndex(users.GAMESPLAYED);
            int gamesWonColIndexx = data.getColumnIndex(users.GAMESWON);
            int managerColIndexx = data.getColumnIndex(users.MANAGER);

            gamesPlayed = data.getInt(gamesPlayedColIndexx);
            gamesWon = data.getInt(gamesWonColIndexx);
            manager = data.getString(managerColIndexx);
        }

        title.setText("Hello " + name);
        played.setText("Games Played: " + gamesPlayed);
        won.setText("Games Won: " + gamesWon);

        if (manager.equals("true")){
            viewDB.setVisibility(View.VISIBLE);
        }
        else {
            viewDB.setVisibility(View.GONE);
        }
    }
}
