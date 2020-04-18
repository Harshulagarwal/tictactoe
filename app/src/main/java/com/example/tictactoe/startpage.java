package com.example.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class startpage extends AppCompatActivity {
    Button startgame, viewscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        startgame = findViewById(R.id.button11);
        viewscore = findViewById(R.id.button12);
        appdata.p1score = sharedPreferences.getInt("pl1score", 0);
        appdata.p2sacore = sharedPreferences.getInt("pl2score", 0);
        appdata.dscore = sharedPreferences.getInt("drawscore", 0);
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startpage.this, MainActivity.class));
            }
        });
        viewscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startpage.this, stats.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

        System.exit(0);
    }
}
