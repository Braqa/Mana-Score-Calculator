package com.pleuratpula.manascore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Act_no_players extends AppCompatActivity {

    private EditText playersName;
    private Image playerColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_no_players);

        // Media Voice play on click
        final MediaPlayer sipp = MediaPlayer.create(this, R.raw.sipping);

        // shembull butoni per 6 lojtare
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(Act_no_players.this, Activity_players_names.class);

                // Launch of voice
                sipp.start();

                obj.putExtra("NO_OF_PLAYERS",6);

                startActivity(obj);
            }
        });

    }

    // Krijimi i 2 rubrikave per 2 lojtare
    public void createTwoPlayers(View view) {

        Intent intent = new Intent(Act_no_players.this, Activity_players_names.class);

        intent.putExtra("NO_OF_PLAYERS",2);

        startActivity(intent);

        Button button4 = findViewById(R.id.button4);
    }

    // Krijimi i 2 rubrikave per 3 lojtare
    public void createThreePlayers(View view) {

        Intent intent = new Intent(Act_no_players.this, Activity_players_names.class);

        intent.putExtra("NO_OF_PLAYERS",3);

        startActivity(intent);



    }

    // Krijimi i 2 rubrikave per 4 lojtare
    public void createFourPlayers(View view) {

        Intent intent = new Intent(Act_no_players.this, Activity_players_names.class);

        intent.putExtra("NO_OF_PLAYERS",4);

        startActivity(intent);
    }

    // Krijimi i 2 rubrikave per 5 lojtare
    public void createFivePlayers(View view) {

        Intent intent = new Intent(Act_no_players.this, Activity_players_names.class);

        intent.putExtra("NO_OF_PLAYERS",5);

        startActivity(intent);
    }

    // Krijimi i 2 rubrikave per 6 lojtare
    public void createSixPlayers(View view) {

        Intent intent = new Intent(Act_no_players.this, Activity_players_names.class);

        intent.putExtra("NO_OF_PLAYERS",6);

        startActivity(intent);
    }

    // Krijimi i 2 rubrikave per 7 lojtare
    public void createSevenPlayers(View view) {

        Intent intent = new Intent(Act_no_players.this, Activity_players_names.class);

        intent.putExtra("NO_OF_PLAYERS",7);

        startActivity(intent);
    }

    // Krijimi i 2 rubrikave per 8 lojtare
    public void createEightPlayers(View view) {

        Intent intent = new Intent(Act_no_players.this, Activity_players_names.class);

        intent.putExtra("NO_OF_PLAYERS",8);

        startActivity(intent);
    }
}
