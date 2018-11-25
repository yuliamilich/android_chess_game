package com.yulia.milich.chess;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LogIn extends AppCompatActivity  implements View.OnClickListener{
    private EditText name, password;
    SQLiteDatabase sqdb;
    DBUsers users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(this);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);


        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.done:
                if(users.doesUserExists(name.getText().toString(), password.getText().toString())) {
                    intent = new Intent(this, UserInfo.class);
                    intent.putExtra("name", name.getText().toString());
                    startActivity(intent);
                    finish();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LogIn.this);

                    builder.setCancelable(true);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.setTitle("User not found");
                    builder.setMessage("Name or password wrong!");

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
}
