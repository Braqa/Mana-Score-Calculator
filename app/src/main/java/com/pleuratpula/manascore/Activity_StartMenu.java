package com.pleuratpula.manascore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pleuratpula.manascore.Adapters.MyAdapter;
import com.pleuratpula.manascore.Models.DatabaseHelper;
import com.pleuratpula.manascore.Models.ResultGame;

import java.util.ArrayList;
import java.util.List;

public class Activity_StartMenu extends AppCompatActivity {

    RecyclerView rv;
    ListView listView;
    List<ResultGame> resultGameList;
    LinearLayout parentLayout ;
    LayoutInflater layoutInflater;
    ArrayList<ResultGame> matches = new ArrayList<>();
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__start_menu);


        myDb = new DatabaseHelper(this);
        matches= myDb.GetAllMatches();

        myDb = new DatabaseHelper(this);
        matches= myDb.GetAllMatches();

        if(matches.size()!=0)
        {
            Toast.makeText(getApplicationContext(),"Matches are NOT empty",Toast.LENGTH_LONG).show();

            parentLayout=findViewById(R.id.allMatches);

            layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View matchview = layoutInflater.inflate(R.layout.layout_matches,null,false);
            ((TextView)matchview.findViewById(R.id.MATCHID)).setText("MATCH ID");
            ((TextView)matchview.findViewById(R.id.MATCHPLAYERNO)).setText("NO OF PLAYERS");
            parentLayout.addView(matchview);
            for(int i= 0;i<matches.size();i++)
            {
                String idofmatch =String.valueOf(matches.get(i).getId());
                String players= String.valueOf(matches.get(i).getPlayersGame());

                matchview = layoutInflater.inflate(R.layout.layout_matches,null,false);
                matchview.setId(i);
                ((TextView)matchview.findViewById(R.id.MATCHID)).setText(idofmatch);
                ((TextView)matchview.findViewById(R.id.MATCHPLAYERNO)).setText(players);
                parentLayout.addView(matchview);
            }
        }
        else
            Toast.makeText(getApplicationContext(),"Matches are EMPTY",Toast.LENGTH_LONG).show();




        //ArrayList<ResultGame> results = (ArrayList<ResultGame>)getAllResultsInfo();
        //ResultGameAdapter adapter=new ResultGameAdapter(this,0,results);
        //ListView listTransaction=findViewById(R.id.listView);
        //listTransaction.setAdapter(adapter);

        TextView startNewGame = findViewById(R.id.startNewGame);
        startNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_StartMenu.this, Act_no_players.class);
                startActivity(intent);
            }
        });

        // Button for API
        Button testing = findViewById(R.id.testing);
        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_StartMenu.this, ActivityBoardGames.class);
                startActivity(intent);

            }
        });
    }


    /*   Krijimi i menyse per three dots   */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    /*   Itemat e menyres three dots     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
                Toast.makeText(this, "Share selected", Toast.LENGTH_SHORT).show();
            case R.id.item3:
                Toast.makeText(this, "Export Results selected", Toast.LENGTH_SHORT).show();
            case R.id.item4:
                Toast.makeText(this, "Game Settings selected", Toast.LENGTH_SHORT).show();
            case R.id.item5:
                Toast.makeText(this, "About selected", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

//    //   API Get Results
//    public List<ResultGame> getAllResultsInfo() {
//
//        Retrofit.Builder builder = new Retrofit.Builder().
//                baseUrl("http://192.168.0.28:45455/api/").
//                addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit = builder.build();
//        retrofit.create(ManaScoreService.class);
//        ManaScoreService client = retrofit.create(ManaScoreService.class);
//        Call<List<ResultGame>> call = client.resultsRepos();
//        call.enqueue(new Callback<List<ResultGame>>() {
//            @Override
//            public void onResponse( Call<List<ResultGame>> call, Response<List<ResultGame>> response) {
//                resultGameList = response.body();
//            }
//
//            @Override
//            public void onFailure( Call<List<ResultGame>> call, Throwable t) {
//                Toast.makeText(Activity_StartMenu.this,
//                        "Something went wrong! Please try again.", Toast.LENGTH_SHORT);
//            }
//        });
//        return resultGameList;
//    }


}


