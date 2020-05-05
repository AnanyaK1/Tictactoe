package com.example.tictactoe;

import com.example.tictactoe.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondScreen extends AppCompatActivity implements View.OnClickListener{
    private Button[][] grid = new Button[3][3];
    private boolean player1Turn = true;

    private int roundCount;

    private int score1;
    private int score2;

    private TextView Player1;
    private TextView Player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.secondscreen);

        Player1 = findViewById(R.id.player1);
        Player2 = findViewById(R.id.player2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                grid[i][j] = findViewById(resID);
                grid[i][j].setOnClickListener(this);
            }
        }

        Button Reset = findViewById(R.id.reset);
        Reset.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        View view2 = this.getWindow().getDecorView();
        view2.setBackgroundResource(R.color.NavyBlue);
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin() == true) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] status = new String[3][3];

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                status[i][j] = grid[i][j].getText().toString();
            }
        }

       for (int i = 0; i < 3; i++) {
//
            if (status[i][0].equals(status[i][1]) && status[i][0].equals(status[i][2]) && !status[i][0].equals("")) {
                return true;
            } else if (status[0][i].equals(status[1][i]) && status[0][i].equals(status[2][i]) && !status[0][i].equals("")) {
                return true;
            } else if (status[0][0].equals(status[1][1]) && status[0][0].equals(status[2][2]) && !status[0][0].equals("")) {
                return true;
            } else if (status[0][2].equals(status[1][1]) && status[0][2].equals(status[2][0]) && !status[0][2].equals("")) {
                return true;
            }
        }
        return false;
    }


    private void player1Wins() {
        score1++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        //updatePointsText();
        ScoreUpdate();
        resetBoard();
    }

    private void player2Wins() {
        score2++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        //updatePointsText();
        ScoreUpdate();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void ScoreUpdate() {
        Player1.setText("PLAYER 1: " + score1);
        Player2.setText("PLAYER 2: " + score2);
    }
//    private void ScoreUpdate() {
//        System.out.print("Player 1: " + score1);
//        System.out.print("Player 2: " + score2);
//
//    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        score1 = 0;
        score2 = 0;
        //updatePointsText(
        ScoreUpdate();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", score1);
        outState.putInt("player2Points", score2);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        score1 = savedInstanceState.getInt("player1Points");
        score2 = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }

}

