package com.pleuratpula.manascore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pleuratpula.manascore.Models.DatabaseHelper;
import com.pleuratpula.manascore.Models.ManaScoreService;
import com.pleuratpula.manascore.Models.Player;
import com.pleuratpula.manascore.Models.ResultGame;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Integer.parseInt;

public class Activity_results extends AppCompatActivity {

// teeeeeeest
    LinearLayout parentLayout;
    LayoutInflater layoutInflater;
    int NumberOfPlayers;
    ArrayList<Player> playerList = new ArrayList<Player>();


    // Variable for Alert Dialog Box Pop-Up Notification
    private TextView alertTextView;

    int[] playerColours= {R.drawable.red,R.drawable.blue,R.drawable.green,R.drawable.orange,R.drawable.yellow,R.drawable.pink,R.drawable.lightblue,R.drawable.grey};
    private DatabaseHelper myDb;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);



        myDb = new DatabaseHelper(this);

        parentLayout = findViewById(R.id.playerLayoutStack);
        layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        Bundle extras = getIntent().getExtras();

        if(extras!= null)
        {
            NumberOfPlayers= extras.getInt("NO_OF_PLAYERS");
            playerList=(ArrayList<Player>)extras.getSerializable("PLAYERS");
        }

        for(int i = 0; i<NumberOfPlayers; i++)
        {
            final int playernumber = i+1;
            View playerView = layoutInflater.inflate(R.layout.layout_player,null,false);
            playerView.setId(playernumber);
            //playerView.setTag(playerList.get(i));
            ((ImageView)playerView.findViewById(R.id.playerColour)).setImageResource(playerColours[i]);
            parentLayout.addView(playerView);

            final View view = findViewById(playernumber);
            if(playerList!=null)
            {
                ((TextView)view.findViewById(R.id.playerName)).setText(playerList.get(i).getName());
                ((TextView)view.findViewById(R.id.points)).setText(playerList.get(i).getPointsToString());
                Button minuspointButton = ((Button)view.findViewById(R.id.minuspoints));
                minuspointButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playerList.get(playernumber-1).removePoint();
                        ((TextView)view.findViewById(R.id.points)).setText(playerList.get(playernumber-1).getPointsToString());
                    }
                });

                Button pluspointButton = ((Button)view.findViewById(R.id.pluspoints));
                pluspointButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playerList.get(playernumber-1).addPoint();
                        ((TextView)view.findViewById(R.id.points)).setText(playerList.get(playernumber-1).getPointsToString());
                    }
                });
            }
            else    {
                Context contex=getApplicationContext();
                Toast toast = Toast.makeText(getApplicationContext(),"List is empty",Toast.LENGTH_LONG);
                toast.show();
            }
        }

        // Alert Dialog Box Implementation
        alertTextView = (TextView) findViewById(R.id.AlertTextView);
        alertTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_results.this, Activity_StartMenu.class);
                startActivity(intent);
            }
        });

        Button btnSaveResult = (Button) findViewById(R.id.btnSaveResult);
        btnSaveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Perform of Alert Dialog Box / Pop-Up Notification
                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_results.this);

                    builder.setCancelable(true);
                    builder.setTitle("Are your drunk enough !? Look below.");
                    builder.setMessage("Are you sure you want to save results ?");

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.cancel();
                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            alertTextView.setVisibility(View.VISIBLE);

                        }
                    });
                    builder.show();


                    ResultGame game = new ResultGame();
                    game.setPlayersNumber(NumberOfPlayers);
                    game.setPlayersGame(playerList);

                    boolean isInserted = myDb.insertData(game.getPlayersGame(),game.getPlayers());

                    if(isInserted==true)
                        Toast.makeText(Activity_results.this,"Match saved", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Activity_results.this,"Match NOT saved", Toast.LENGTH_LONG).show();

//                  ResultGame game = new ResultGame();
//                  game.setPlayersNumber(NumberOfPlayers);
//
//                  game.setPlayersGame(playerList);
//
//                  Player temp = null;
//
//                  for(int i =0; i<game.getPlayersGame();i++)
//                  {
//                      game.Players.get(i).setName(playerList.get(i).getName());
//                      game.Players.get(i).setPoints(playerList.get(i).getPoints());
//                  }

//                ArrayList<Player> players = new ArrayList<>();
//                for(int i =0; i< game.getPlayersGame();i++){
//                    Player temp = new Player();
//                    TextView pName= (TextView) findViewById(R.id.playerName);
//                    temp.setName(pName.getText().toString());
//                    TextView pPoints = (TextView) findViewById(R.id.points);
//                    temp.setPoints(parseInt(pPoints.getText().toString()));
//                    players.add(temp);
////                }
//                try{
//                    setAllResultInfo(game);
//                }catch (Exception ex){
//                    Toast.makeText(Activity_results.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }



    // API Set Results to Data Base
    public void setAllResultInfo (final ResultGame transaction){
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("https://manascoreapi.conveyor.cloud/api/").
                addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        retrofit.create(ManaScoreService.class);
        ManaScoreService client=retrofit.create(ManaScoreService.class);
        Call<ResultGame> call= client.saveResult(transaction);
        call.enqueue(new Callback<ResultGame>() {
            @Override
            public void onResponse(Call<ResultGame> call, Response<ResultGame> response) {
                ResultGame tempTransaction=response.body();
                if (tempTransaction == null){
                    Toast.makeText(Activity_results.this,
                            "Something went wrong!", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(Activity_results.this,
                            "Succesfully saved!", Toast.LENGTH_SHORT);
                }
            }


            @Override
            public void onFailure(Call<ResultGame> call, Throwable t) {
                Toast.makeText(Activity_results.this,
                        "Something went wrong! Please try again.", Toast.LENGTH_SHORT);
            }
        });
    }
}
