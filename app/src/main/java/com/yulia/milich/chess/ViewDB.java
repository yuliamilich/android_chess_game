package com.yulia.milich.chess;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText edId, edName, edPassword, edGamesPlayed, edGamesWon, edManager;

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

        edId = (EditText) findViewById(R.id.id);
        edName = (EditText) findViewById(R.id.name);
        edPassword = (EditText) findViewById(R.id.password);
        edGamesPlayed = (EditText) findViewById(R.id.gamesPlayed);
        edGamesWon = (EditText) findViewById(R.id.gamesWon);
        edManager = (EditText) findViewById(R.id.manager);

        read_fromDC();
        //table_close();

        //users.deleteName(7);
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

            String id = c.getString((idColIndex));
            String name = c.getString(nameColIndex);
            String password = c.getString(passwordColIndex);
            int gamesPlayed = c.getInt(gamesPlayedColIndex);
            int gamesWon = c.getInt(gamesWonColIndex);
            String manager = c.getString(managerColIndex);
            //String info = id + " " + name + " " + password + " " + gamesPlayed + " " + gamesWon + " " + manager;
            String info = name;
            opers.add(info);
            c.moveToNext();
        }
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opers);
        lv.setAdapter(adp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = adp.getItem(position).toString();

                Cursor data = users.getItemID(name);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    //what i want to do with the user
                    Context context = getApplicationContext();
                    CharSequence text1 = "id found " + itemID;
                    idOfUser = itemID;
//                    CharSequence text1 = "id found ";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast1 = Toast.makeText(context, text1, duration);
                    toast1.show();
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
                    builder.setTitle("Delete the user you chose");
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
//        if (idOfUser == -1) {
//            Context context = getApplicationContext();
//            CharSequence text = "Are you sure you want to delete all?";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast2 = Toast.makeText(context, text, duration);
//            toast2.show();
//        }
//        else{
//            Context context = getApplicationContext();
//            CharSequence text = "Are you sure you want to delete it?";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast2 = Toast.makeText(context, text, duration);
//            toast2.show();
//        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.delete:
                delete();
                break;
            case R.id.newUser:
                intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;

//            default: finishActivity(1);
//                finish();
//                break;
        }

    }
}
