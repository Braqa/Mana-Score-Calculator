package com.pleuratpula.manascore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.pleuratpula.manascore.Adapters.BoardGameAdapter;
import com.pleuratpula.manascore.Models.BoardGames;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityBoardGames extends AppCompatActivity {

    private List<BoardGames> boardGameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_games);
        new FetchTask().execute();
    }



    private class FetchTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL("https://www.boardgameatlas.com/api/search?client_id=SB1VGnDv7M");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Log the server response code
                int responseCode = connection.getResponseCode();

                // And if the code was HTTP_OK then parse the contents
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    // Convert request content to string
                    InputStream is = connection.getInputStream();
                    BufferedReader r = new BufferedReader(new InputStreamReader(is));
                    String line = "";

                    StringBuilder responseStrBuilder = new StringBuilder();
                    while((line =  r.readLine()) != null){

                        responseStrBuilder.append(line);
                    }
                    is.close();
                    return responseStrBuilder.toString() ;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObj= new JSONObject(result);
                JSONArray gamesArray = jsonObj.getJSONArray("games");
                for(int i=0; i<50; i++) {
                    boardGameList.add(new BoardGames(gamesArray.getJSONObject(i)));
                }

                RecyclerView rvGames = (RecyclerView) findViewById(R.id.recViewAPI);
                BoardGameAdapter adapter = new BoardGameAdapter(boardGameList);
                rvGames.setAdapter(adapter);
                rvGames.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}