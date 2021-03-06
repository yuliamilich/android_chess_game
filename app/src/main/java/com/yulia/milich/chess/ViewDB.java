package com.yulia.milich.chess;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDB extends AppCompatActivity implements View.OnClickListener{

    SQLiteDatabase sqdb;
    DBUsers users;
    ArrayList<String> opers = new ArrayList<>();
    ArrayAdapter<String> adp;
    ListView lv;
    int idOfUser = -1;
    String name = "";
    private EditText edId, edName, edPassword, edGamesPlayed, edGamesWon, edManager;
    private Button searchBtn;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_db);

        lv = (ListView) findViewById(R.id.lv);

        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();

        Button newUser = (Button) findViewById(R.id.newUser);
        newUser.setOnClickListener(this);

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);

        edId = (EditText) findViewById(R.id.id);
        edName = (EditText) findViewById(R.id.name);
        edPassword = (EditText) findViewById(R.id.password);
        edGamesPlayed = (EditText) findViewById(R.id.gamesPlayed);
        edGamesWon = (EditText) findViewById(R.id.gamesWon);
        edManager = (EditText) findViewById(R.id.manager);

        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);
        search = (EditText) findViewById(R.id.search);

        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(this);


        read_fromDC("");
        //table_close();

    }

    public void read_fromDC(String searchWord) {
        opers.clear();

        Cursor c = users.getItemLike(searchWord);
        //Cursor c = sqdb.query(DBUsers.TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int idColIndex = c.getColumnIndex(users.UID);
            int nameColIndex = c.getColumnIndex(users.NAME);
            int passwordColIndex = c.getColumnIndex(users.PASSWORD);
            int gamesPlayedColIndex = c.getColumnIndex(users.GAMESPLAYED);
            int gamesWonColIndex = c.getColumnIndex(users.GAMESWON);
            int managerColIndex = c.getColumnIndex(users.MANAGER);

            String id = c.getString((idColIndex));
            String name1 = c.getString(nameColIndex);
            String password = c.getString(passwordColIndex);
            int gamesPlayed = c.getInt(gamesPlayedColIndex);
            int gamesWon = c.getInt(gamesWonColIndex);
            String manager = c.getString(managerColIndex);
            //String info = id + " " + name + " " + password + " " + gamesPlayed + " " + gamesWon + " " + manager;
            String info = name1;
            opers.add(info);
            c.moveToNext();
        }
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opers);
        lv.setAdapter(adp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = adp.getItem(position).toString();

                Cursor data = users.getItem(name);

                int itemID = -1;
                String password = "";
                int gamesPlayed = -1;
                int gamesWon = -1;
                String manager = "";
                while(data.moveToNext()){
                    int idColIndexx = data.getColumnIndex(users.UID);
                    int nameColIndexx = data.getColumnIndex(users.NAME);
                    int passwordColIndexx = data.getColumnIndex(users.PASSWORD);
                    int gamesPlayedColIndexx = data.getColumnIndex(users.GAMESPLAYED);
                    int gamesWonColIndexx = data.getColumnIndex(users.GAMESWON);
                    int managerColIndexx = data.getColumnIndex(users.MANAGER);

                    itemID = data.getInt(0);
                    password = data.getString(passwordColIndexx);
                    gamesPlayed = data.getInt(gamesPlayedColIndexx);
                    gamesWon = data.getInt(gamesWonColIndexx);
                    manager = data.getString(managerColIndexx);

                }
                if(itemID > -1){
                    //what i want to do with the user
                    Context context = getApplicationContext();
                    CharSequence text1 = "id found " + itemID;
                    idOfUser = itemID;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast1 = Toast.makeText(context, text1, duration);
                    toast1.show();

                    edId.setText(String.valueOf(itemID));
                    edName.setText(name);
                    edPassword.setText(password);
                    edGamesPlayed.setText(String.valueOf(gamesPlayed));
                    edGamesWon.setText(String.valueOf(gamesWon));
                    edManager.setText(manager);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "No ID associated with that name";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast2 = Toast.makeText(context, text, duration);
                    toast2.show();
                }
            }
        });
        c.close();
    }

    public void table_close() {
        sqdb.close();
        users.close();
    }

    public void delete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewDB.this);
                builder.setCancelable(true);
                if(idOfUser != -1){
                    builder.setTitle("Delete "+name);
                    builder.setMessage("Are you sure you want to delete it?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //delete the user
                            users.deleteName(idOfUser);
                            dialog.cancel();
                        }
                    });
                }
                else{
                    builder.setTitle("Delete all the users");
                    builder.setMessage("Are you sure you want to delete EVERYTHING?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //delete all the data
                            users.onUpgrade(sqdb,0,0);
                            dialog.cancel();
                        }
                    });

                }

        builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
                builder.show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.delete:
                delete();
                read_fromDC(search.getText().toString());
                break;
            case R.id.newUser:
                intent = new Intent(this, SignUp.class);
                intent.putExtra("from", "ViewDB");
                startActivity(intent);
                break;
            case R.id.update:
                if(idOfUser != -1) {
                    String sID = edId.getText().toString();
                    String sName = edName.getText().toString();
                    String sPassword = edPassword.getText().toString();
                    String sGamesPlayed = edGamesPlayed.getText().toString();
                    String sGamesWon = edGamesWon.getText().toString();
                    String sManager = edManager.getText().toString();
                    users.update(idOfUser, Integer.parseInt(sID), sName, sPassword, sGamesPlayed, sGamesWon, sManager);
                    read_fromDC(search.getText().toString());
                    edId.setText("Id");
                    edName.setText("Name");
                    edPassword.setText("Password");
                    edGamesPlayed.setText("Games Played");
                    edGamesWon.setText("Games Won");
                    edManager.setText("Manager?");
                }
                break;
            case R.id.searchBtn:
                String searchWord = search.getText().toString();
                read_fromDC(searchWord);
                break;
            case R.id.home:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
                break;
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
