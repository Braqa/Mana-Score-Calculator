package com.pleuratpula.manascore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pleuratpula.manascore.Models.Player;


public class Activity_players_names extends AppCompatActivity {

    List<Player> playerList = new ArrayList<Player>();
    Player player = null;
    LinearLayout parentLayout;
    LayoutInflater layoutInflater;
    int NumberOfPlayers;
    int[] playerColours= {R.drawable.red,R.drawable.blue,R.drawable.green,R.drawable.orange,R.drawable.yellow,R.drawable.pink,R.drawable.lightblue,R.drawable.grey};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_names);

        parentLayout = findViewById(R.id.playersListLinearLayout);
        layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        Bundle extras = getIntent().getExtras();

        if(extras!= null)
        {
            NumberOfPlayers= extras.getInt("NO_OF_PLAYERS");
        }


        for(int i=0;i<NumberOfPlayers;i++)

        {
            int playernumber = i+1;
            View playerView = layoutInflater.inflate(R.layout.layout_playername,null,false);
            ((ImageView)playerView.findViewById(R.id.playerNameColour)).setImageResource(playerColours[i]);
            playerView.setId(playernumber);
            ((EditText)playerView.findViewById(R.id.playername_editext)).setText("PLAYER "+ playernumber);
            parentLayout.addView(playerView);
        }

        // Button
        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set time of vibration
                vibrateNow(1000);

                Intent intent = new Intent(Activity_players_names.this, Activity_results.class);
                intent.putExtra("NO_OF_PLAYERS",NumberOfPlayers);
                for(int i=0;i<NumberOfPlayers;i++)
                {
                    View view = findViewById(i+1);
                    String name = ((EditText)view.findViewById(R.id.playername_editext)).getText().toString();
                    player = new Player(name, 0);
                    player.setName(name);
                    player.setPoints(0);
                    playerList.add(player);

//                    Context contex=getApplicationContext();
//                    Toast toast = Toast.makeText(getApplicationContext(),"Player:-> " + player.getName() +" "+player.getPointsToString(),Toast.LENGTH_LONG);
//                    toast.show();

                }
                Bundle bundle=new Bundle();
                bundle.putSerializable("PLAYERS", (Serializable) playerList);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }

    // Vibration API Method for different versions
    private void vibrateNow(long millisecond) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(millisecond, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(millisecond);
        }


    }
}
