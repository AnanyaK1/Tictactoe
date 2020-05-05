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
        Intent media = new Intent(this, BackgroundService.class);
        startService(media);
    }

    public void openSecondScreen() {
        Intent intent = new Intent(this, SecondScreen.class);
        startActivity(intent);
    }

}
