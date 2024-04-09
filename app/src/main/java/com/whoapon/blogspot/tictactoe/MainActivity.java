package com.whoapon.blogspot.tictactoe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    int flag = 0;
    public static int count = 0;
    int activePlayer = 0;   // 0 is X and 1 is O and 2 is NULL
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @SuppressLint("SetTextI18n")
    public void playerTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameState[tappedImage] == 2) {

            count++;

            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }

        // check if any player has won
        for (int[] winPosition : winPositions) {

            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]]   // somebody has won
                    && gameState[winPosition[0]] != 2) {

                flag = 1;
                gameActive = false;
                String winner;
                if (gameState[winPosition[0]] == 0) {
                    winner = "X has won";
                } else {
                    winner = "O has won";
                }
                showDialog(winner);
            }

        }

        if (count == 9 && flag == 0) {
            gameActive = false;
            showDialog("Match Draw");
        }

    }

    @SuppressLint("SetTextI18n")
    public void gameReset() {

        gameActive = true;
        activePlayer = 0;
        count = 0;
        flag = 0;

        Arrays.fill(gameState, 2);

        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn");

    }

    public void showDialog(String winner) {
        new AlertDialog.Builder(this).setTitle(winner).setPositiveButton("Restart Game", (dialog, which) -> gameReset()).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}