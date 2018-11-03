package com.yulia.milich.chess;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    EditText etName, etCode, etSumma, etNum;
    Button btnInsert;
    SQLiteDatabase sqdb;
    DBUsers users;
    ArrayList<String> opers = new ArrayList<>();
    ListView lv;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = (EditText) findViewById(R.id.etName);
        etCode = (EditText) findViewById(R.id.etCode);
        etSumma = (EditText) findViewById(R.id.etSumma);
        etNum = (EditText) findViewById(R.id.etNum);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        lv = (ListView) findViewById(R.id.lv);
        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                into_table();
                read_fromDC();
                table_close();
            }
        });
    }

    public void into_table() {
        String stName = etName.getText().toString();
        String stCode = etCode.getText().toString();
        String stSumma = etSumma.getText().toString();
        String stNum = etNum.getText().toString();
        int code = Integer.parseInt(stCode);
        double summa = Double.parseDouble(stSumma);
        int num = Integer.parseInt(stNum);
        ContentValues cv = new ContentValues();
        cv.put(DBUsers.NAME, stName);
        cv.put(DBUsers.CODE, stCode);
        cv.put(DBUsers.SUMMA, stSumma);
        cv.put(DBUsers.NUMBER, stNum);
        sqdb.insert(DBUsers.TABLE_NAME, null, cv);

        etName.setText("");
        etCode.setText("");
        etSumma.setText("");
        etNum.setText("");
    }

    public void table_close() {
        sqdb.close();
        users.close();
    }

    public void read_fromDC() {
        Cursor c = sqdb.query(
                DBUsers.TABLE_NAME, null, null, null, null, null
                , null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int idColIndex = c.getColumnIndex("_id");
            int nameColIndex = c.getColumnIndex("name");
            int codeColIndex = c.getColumnIndex("code");
            int summaColIndex = c.getColumnIndex("summa");
            int numberColIndex = c.getColumnIndex("number");
            String stName = c.getString(nameColIndex);
            int stCode = c.getInt(codeColIndex);
            double stSumma = c.getDouble(summaColIndex);

            int stNumber = c.getInt(numberColIndex);
            String info = stName + " " + stCode + " " + stSumma + " " + stNumber;
            opers.add(info);
            c.moveToNext();
        }
        adp = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, opers);
        lv.setAdapter(adp);
        c.close();
    }
}
