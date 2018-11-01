package com.yulia.milich.chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPlayers extends AppCompatActivity implements View.OnClickListener{
    private EditText playerWhite;
    private EditText playerBlack;
    private String playerBlackStr,playerWhiteStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        playerWhite = (EditText) findViewById(R.id.playerWhite);

        playerBlack = (EditText) findViewById(R.id.playerBlack);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(this);

        Button newUser = (Button) findViewById(R.id.newUser);
        newUser.setOnClickListener(this);
    }


    //opens the chess game and sends the names to the next activity (which is The Chess Game)
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.done:
                playerWhiteStr = playerWhite.getText().toString();
                playerBlackStr = playerBlack.getText().toString();
                intent = new Intent(this, TheChessGame.class);
                intent.putExtra("playerWhiteStr", playerWhiteStr);
                intent.putExtra("playerBlackStr", playerBlackStr);
                break;
            case R.id.newUser:
                intent = new Intent(this, SignUp.class);

//            default: finishActivity(1);
//                finish();
//                break;
        }
        startActivity(intent);
        finish();
    }
}
