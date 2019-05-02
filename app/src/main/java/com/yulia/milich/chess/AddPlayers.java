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

public class AddPlayers extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase sqdb;
    DBUsers users;
    private EditText playerWhite, passwordWhite;
    private EditText playerBlack, passwordBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        users = new DBUsers(this);
        sqdb = users.getWritableDatabase();

        playerWhite = (EditText) findViewById(R.id.playerWhite);

        passwordWhite = (EditText) findViewById(R.id.passwordWhite);

        playerBlack = (EditText) findViewById(R.id.playerBlack);

        passwordBlack = (EditText) findViewById(R.id.passwordBlack);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(this);

        Button newUser = (Button) findViewById(R.id.newUser);
        newUser.setOnClickListener(this);

        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.done:
                String playerWhiteStr = playerWhite.getText().toString();
                String playerBlackStr = playerBlack.getText().toString();
                String passwordWhiteStr = passwordWhite.getText().toString();
                String passwordBlackStr = passwordBlack.getText().toString();

                // if both the players are the same then you need to change one of them
                if(playerBlackStr.equals(passwordWhiteStr)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddPlayers.this);

                    builder.setCancelable(true);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.setTitle("Don't play against yourself!");
                    builder.setMessage(":P");

                    builder.show();
                }
                else{
                    // if both users exist --> go the the next activity
                    if(users.doesUserExists(playerWhiteStr, passwordWhiteStr) && users.doesUserExists(playerBlackStr, passwordBlackStr)) {
                        intent = new Intent(this, TheChessGame.class);
                        intent.putExtra("playerWhiteStr", playerWhiteStr);
                        intent.putExtra("playerBlackStr", playerBlackStr);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        // if one of the users is wrong then a message will appear
                        String title = "";
                        String message = "";

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddPlayers.this);

                        builder.setCancelable(true);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        if(!users.doesUserExists(playerWhiteStr, passwordWhiteStr)){
                            title += "White player not found. ";
                            message += "White player name or password wrong!";
                        }

                        if(!users.doesUserExists(playerBlackStr, passwordBlackStr)){
                            title += "Black player not found. ";
                            message += "Black player name or password wrong!";
                        }

                        builder.setTitle(title);
                        builder.setMessage(message);

                        builder.show();
                    }
                }
                break;
            case R.id.newUser:
                intent = new Intent(this, SignUp.class);
                intent.putExtra("from", "AddPlayers");
                startActivity(intent);
                finish();
                break;
            case R.id.home:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
