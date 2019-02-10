package com.yulia.milich.chess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        Button battery = (Button) findViewById(R.id.battery);
        battery.setOnClickListener(this);
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
            case R.id.battery:
                intent = new Intent(this, BatteryLevel.class);
                break;

            default: finishActivity(1);
                finish();
                break;
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMusic:
                // User chose the "btnMusic" item, show the app settings UI...
                return true;

            case R.id.btnMute:
                // User chose the "btnMute" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
