package com.example.tictactoe;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;

public class BackgroundService extends Service {
    private static final String TAG = null;
    MediaPlayer menuSong;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        menuSong = MediaPlayer.create(this, R.raw.tictactoemusic);
        menuSong.setLooping(true); // Set looping
        menuSong.setVolume(100,100);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        menuSong.start();
        return 1;
    }
    @Override
    public void onDestroy() {
        menuSong.stop();
        menuSong.release();
    }

    @Override
    public void onLowMemory() {

    }
}

//used following website for resource:https://stackoverflow.com/questions/8209858/android-background-music-service