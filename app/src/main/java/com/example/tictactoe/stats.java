package com.example.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class stats extends AppCompatActivity {
    TextView t1, t2, t3;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        t1 = findViewById(R.id.textView4);
        t2 = findViewById(R.id.textView5);
        t3 = findViewById(R.id.textView6);
        t1.setText("PLAYER 1: " + appdata.p1score);
        t2.setText("PLAYER 2: " + appdata.p2sacore);
        t3.setText("DRAW: " + appdata.dscore);
        reset = findViewById(R.id.button13);
        final SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("pl1score", 0);
                editor.putInt("pl2score", 0);
                editor.putInt("drawscore", 0);
                appdata.p1score = 0;
                appdata.p2sacore = 0;
                appdata.dscore = 0;
                editor.apply();
                startActivity(new Intent(stats.this, stats.class));
                finish();
            }
        });
    }
}
