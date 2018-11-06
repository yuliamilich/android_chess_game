package com.yulia.milich.chess;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    private EditText userName, password;
    SQLiteDatabase sqdb;
    DBUsers users;
    boolean isManager = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        isManager = checkBox.isChecked();

        final Intent intent = new Intent(this, AddPlayers.class);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                into_table();
                table_close();

                startActivity(intent);
            }
        });
    }

    public void into_table() {
        String stName = userName.getText().toString();
        String stPassword = password.getText().toString();
        String manager = "false";
        if (isManager)
            manager = "true";
        users.addData(stName,stPassword,manager);
//        int code = Integer.parseInt(stCode);
//        double summa = Double.parseDouble(stSumma);
//        int num = Integer.parseInt(stNum);
//        ContentValues cv = new ContentValues();
//        cv.put(DBUsers.NAME, stName);
//        cv.put(DBUsers.CODE, stCode);
//        cv.put(DBUsers.SUMMA, stSumma);
//        cv.put(DBUsers.NUMBER, stNum);
//        sqdb.insert(DBUsers.TABLE_NAME, null, cv);

        userName.setText("");
        password.setText("");
    }

    public void table_close() {
        sqdb.close();
        users.close();
    }

}
