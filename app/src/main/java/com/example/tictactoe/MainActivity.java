package com.example.tictactoe;

import com.example.tictactoe.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    View view;
    private Button next;
    MediaPlayer menuSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondScreen();
            }
        });
        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.Orange);
        menuSong = MediaPlayer.create(getApplicationContext(), R.raw.tictactoemusic);
        menuSong.start();
        Intent svc = new Intent(this, BackgroundService.class);
        startService(svc);
    }

    public void openSecondScreen() {
        Intent intent = new Intent(this, SecondScreen.class);
        startActivity(intent);
    }


    protected void onPause() {
        super.onPause();
        if (menuSong != null) {
            menuSong.stop();
            if (isFinishing()) {
                menuSong.stop();
                menuSong.release();
            }
        }
    }
}
