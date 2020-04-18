package com.example.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button[] button = new Button[9];
    TextView textView;
    int[][] game = new int[3][3];
    boolean cont;
    int turn = 0, winner = 0;
    int[] a2 = {0, 1, 2, 0, 1, 2, 0, 1, 2};
    int[] a1 = {0, 0, 0, 1, 1, 1, 2, 2, 2};
    Button buttonstart;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        appdata.p1score = sharedPreferences.getInt("pl1score", 0);
        appdata.p2sacore = sharedPreferences.getInt("pl2score", 0);
        appdata.dscore = sharedPreferences.getInt("drawscore", 0);
        button[0] = findViewById(R.id.button);
        button[1] = findViewById(R.id.button2);
        button[2] = findViewById(R.id.button3);
        button[3] = findViewById(R.id.button4);
        button[4] = findViewById(R.id.button5);
        button[5] = findViewById(R.id.button6);
        button[6] = findViewById(R.id.button7);
        button[7] = findViewById(R.id.button8);
        button[8] = findViewById(R.id.button9);
        textView = findViewById(R.id.textView);
        buttonstart = findViewById(R.id.button10);
        buttonstart.setVisibility(View.GONE);
        for (int i = 0; i < 9; i++) {
            final int finalI = i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    buttonfunction(button[finalI], finalI);
                }
            });
        }

        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public void buttonfunction(Button btn, int pos) {
        if (winner == 0) {
            if (btn.getText().toString().equals("")) {
                if (turn == 1) {
                    btn.setText("X");
                    btn.setBackgroundColor(Color.RED);
                    game[a1[pos]][a2[pos]] = 1;
                    turn = 0;
                    textView.setText("PLAYER1 TURN");
                } else {
                    btn.setText("0");
                    game[a1[pos]][a2[pos]] = -1;
                    turn = 1;
                    btn.setBackgroundColor(Color.GREEN);
                    textView.setText("PLAYER2 TURN");
                }
                btn.setEnabled(false);

            }
            gamelogic();

        }
        if (winner == 1) {
            textView.setText("PLAYER2 WINS");
            buttonstart.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("pl2score", appdata.p2sacore + 1);
            editor.commit();

        }
        if (winner == -1) {
            textView.setText("PLAYER1 WINS");
            buttonstart.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("pl1score", appdata.p1score + 1);
            editor.commit();

        }
        if (!isincomplete() && winner == 0) {
            textView.setText("ITS A DRAW");
            buttonstart.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("drawscore", appdata.dscore + 1);
            editor.commit();
        }

    }

    public boolean isincomplete() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == 0) {
                    cont = true;
                    return cont;
                }
            }
        }
        cont = false;
        return cont;
    }

    public void gamelogic() {
        if (game[0][0] == game[0][1] && game[0][1] == game[0][2]) {
            winner = game[0][0];
        } else if (game[1][0] == game[1][1] && game[1][1] == game[1][2]) {
            winner = game[1][0];
        } else if (game[2][0] == game[2][1] && game[2][1] == game[2][2]) {
            winner = game[2][0];
        } else if (game[0][0] == game[1][0] && game[1][0] == game[2][0]) {
            winner = game[0][0];
        } else if (game[0][1] == game[1][1] && game[1][1] == game[2][1]) {
            winner = game[0][1];
        } else if (game[0][2] == game[1][2] && game[1][2] == game[2][2]) {
            winner = game[0][2];
        } else if (game[0][0] == game[1][1] && game[1][1] == game[2][2]) {
            winner = game[0][0];
        } else if (game[2][0] == game[1][1] && game[1][1] == game[0][2]) {
            winner = game[0][2];
        }

    }


}
