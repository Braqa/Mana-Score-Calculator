package com.pleuratpula.manascore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pleuratpula.manascore.Models.DatabaseHelper;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity  {

    private ConstraintLayout mainLayout;
    private Button buttonEnter;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        /* inicializimi i mainLayout ne main*/
        mainLayout = findViewById(R.id.mainLayout);

        /* Inicializimi i butonit ne menyne kryesore */
        //Button buttonEnter = findViewById(R.id.buttonEnter);
        GifImageView entergif = findViewById(R.id.entergif);

        entergif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Migrimi prej nje aktiviteti ne aktivitetin tjeter*/
                Intent intent = new Intent(MainActivity.this, Activity_StartMenu.class);
                startActivity(intent);
            }
        });
    }
    public void goToStartMenu(View view) {

    }


}
