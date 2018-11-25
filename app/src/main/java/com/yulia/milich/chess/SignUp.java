package com.yulia.milich.chess;

import android.content.ContentValues;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText userName, password;
    SQLiteDatabase sqdb;
    DBUsers users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);

        Intent i = null;

        if(getIntent().getStringExtra("from").equals("AddPlayers")){
            i = new Intent(this, AddPlayers.class);
        }
        else if(getIntent().getStringExtra("from").equals("ViewDB")){
            i = new Intent(this, ViewDB.class);
        }

        final Intent intent = i;

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(into_table()) {
                    table_close();

                    startActivity(intent);
                    finish();
                }
            }
        });


        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(this);
    }

    public boolean into_table() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        boolean isManager = checkBox.isChecked();

        String stName = userName.getText().toString();
        String stPassword = password.getText().toString();
        String manager = "false";
        if (isManager)
            manager = "true";
        if(!users.isNameTaken(stName) && isPasswordValid(stPassword)) {
            users.addData(stName, stPassword, manager);

            userName.setText("");
            password.setText("");
            return true;
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

            builder.setCancelable(true);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            if(!isPasswordValid(stPassword)){
                builder.setTitle("Bad user password");
                builder.setMessage("Password should be longer then 5");
            }
            else {
                builder.setTitle("Bad user name");
                builder.setMessage("This name is taken!");
            }

            builder.show();
            return false;
        }
    }

    public void table_close() {
        sqdb.close();
        users.close();
    }

    public boolean isPasswordValid(String password){
        if(password.length() > 5){
            return true;
        }
        else return false;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.home:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
                break;

        }
    }
}
