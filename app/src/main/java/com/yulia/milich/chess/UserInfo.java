package com.yulia.milich.chess;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(this);

        show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.viewDB:
                intent = new Intent(this, ViewDB.class);
                startActivity(intent);
                finish();
                break;
            case R.id.edit:
                    String sName = name;
                    String sPassword = password.getText().toString();
                if(password.length() > 5){
                    users.updatePassword(sPassword, sName);
                    password.setText("");
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserInfo.this);

                    builder.setCancelable(true);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setTitle("Bad user password");
                    builder.setMessage("Password should be longer then 5");
                    builder.show();
                }
                break;
            case R.id.home:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
                break;
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

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //when selcting option in the menu
        // main --> go to menu
        //music --> stop/play music
        //instraction --> go to instraction
        // call --> go to phone call
        int id = item.getItemId();
        Intent intent = null;

        switch (id) {
            case R.id.music:
                if (MainMenu.isPlaying)
                    MainMenu.musicService.pause();
                else
                    MainMenu.musicService.resume();
                MainMenu.isPlaying = !MainMenu.isPlaying;
                break;
            case R.id.manu_main:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
                break;

            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ""));
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                //System.exit(0);
                break;
        }
        return true;
    }
}
