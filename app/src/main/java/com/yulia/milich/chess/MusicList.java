package com.yulia.milich.chess;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MusicList extends AppCompatActivity {
    private ListView lvSongs;
    private ArrayList<Song> songList;
    private ArrayList<String> songsNames;
    private ArrayAdapter adapter;
    public static final int mPrem = 1; //for premition request

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        songsNames = new ArrayList<String>();
        lvSongs = (ListView) findViewById(R.id.lvSongs);
        songList = new ArrayList<Song>();

        if (ContextCompat.checkSelfPermission(MusicList.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MusicList.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(MusicList.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, mPrem);
            } else {
                ActivityCompat.requestPermissions(MusicList.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, mPrem);
            }

        } else {
            //todo enter to the list
        }


        getSongs();


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songsNames);
        lvSongs.setAdapter(adapter);
        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // change playing song to the chosen song
                MainMenu.musicService.setSong(i);
                MainMenu.musicService.playSong();
            }
        });


    }

    public void getSongs() {

        ContentResolver cr = getContentResolver();       //--allows access to the the phone
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;      //--songUri is the address to the music files in the phone
        Cursor songs = cr.query(songUri, null, null, null, null);
        if (songs != null && songs.moveToFirst()) {
            int songTitle = songs.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songID = songs.getColumnIndex(MediaStore.Audio.Media._ID);

            Song song;

            while (songs.moveToNext()) {
                //long longSongID = songs.getLong(songID);
                String currentTitle = songs.getString(songTitle);
                songsNames.add(currentTitle);
                song = new Song(songID, currentTitle);
                songList.add(song);
            }

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
                intent = new Intent(MusicList.this, MainMenu.class);
                startActivity(intent);
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


    //for music service
    @Override
    public void onResume() {
        super.onResume();
        if (MainMenu.isPlaying)
            MainMenu.musicService.resume();
    }
}

