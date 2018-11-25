package com.yulia.milich.chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(this);

        Button help = (Button) findViewById(R.id.logIn);
        help.setOnClickListener(this);

        Button exit = (Button) findViewById(R.id.exit);
        help.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.play:
                intent = new Intent(this, AddPlayers.class);
                break;
            case R.id.logIn:
                intent = new Intent(this, LogIn.class);
                break;
            case R.id.exit:
                finish();
                break;

            default: finishActivity(1);
                finish();
                break;
        }
        startActivity(intent);
    }
}
