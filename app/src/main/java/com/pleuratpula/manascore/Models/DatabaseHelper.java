package com.pleuratpula.manascore.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "manascores.db";
    public static final String TABLE_NAME = "matches_data";
    public  static final String MATCH_ID="MATCH_ID";
    public static final String PLAYERS_NO="PLAYERS_NO";
    public static final String PLAYER1_NAME= "P1_NAME";
    public static final String PLAYER1_SCORES ="P1_SCORES";
    public static final String PLAYER2_NAME= "P2_NAME";
    public static final String PLAYER2_SCORES ="P2_SCORES";
    public static final String PLAYER3_NAME= "P3_NAME";
    public static final String PLAYER3_SCORES ="P3_SCORES";
    public static final String PLAYER4_NAME= "P4_NAME";
    public static final String PLAYER4_SCORES ="P4_SCORES";
    public static final String PLAYER5_NAME= "P5_NAME";
    public static final String PLAYER5_SCORES ="P5_SCORES";
    public static final String PLAYER6_NAME= "P6_NAME";
    public static final String PLAYER6_SCORES ="P6_SCORES";
    public static final String PLAYER7_NAME= "P7_NAME";
    public static final String PLAYER7_SCORES ="P7_SCORES";
    public static final String PLAYER8_NAME= "P8_NAME";
    public static final String PLAYER8_SCORES ="P8_SCORES";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable =
                "CREATE TABLE " +TABLE_NAME +"" +
                        "("+ MATCH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        PLAYERS_NO+" INTEGER, "+
                        PLAYER1_NAME+" TEXT,"+
                        PLAYER1_SCORES+" INTEGER,"+
                        PLAYER2_NAME+" TEXT,"+
                        PLAYER2_SCORES+" INTEGER,"+
                        PLAYER3_NAME+" TEXT,"+
                        PLAYER3_SCORES+" INTEGER,"+
                        PLAYER4_NAME+" TEXT,"+
                        PLAYER4_SCORES+" INTEGER,"+
                        PLAYER5_NAME+" TEXT,"+
                        PLAYER5_SCORES+" INTEGER,"+
                        PLAYER6_NAME+" TEXT,"+
                        PLAYER6_SCORES+" INTEGER,"+
                        PLAYER7_NAME+" TEXT,"+
                        PLAYER7_SCORES+" INTEGER,"+
                        PLAYER8_NAME+" TEXT,"+
                        PLAYER8_SCORES+" INTEGER)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropTablestring = "DROP"+" IF " + "TABLE EXISTS " + TABLE_NAME;
        db.execSQL(dropTablestring);
        onCreate(db);
    }

    public ResultGame getMatch(int matchid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ResultGame game = new ResultGame();
        Cursor result = db.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE "+MATCH_ID+"="+matchid,null );
        result.moveToFirst();
        while (result.isAfterLast()==false)
        {
            game.Id=result.getInt(result.getColumnIndex(MATCH_ID));
            game.PlayersGame=result.getInt(result.getColumnIndex(PLAYERS_NO));
            for(int i=0;i<game.getPlayersGame();i++)
            {
                int playerindex =i+1;
                game.Players.get(i).setName(result.getString(result.getColumnIndex("P"+playerindex+"_NAME")));
                game.Players.get(i).setPoints(result.getColumnIndex("P"+playerindex+"_SCORES"));
            }
            result.moveToNext();
        }
        return game;
    }

    public ArrayList<ResultGame> GetAllMatches()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ResultGame> matches= new ArrayList<>();
        ResultGame game= null;
        ArrayList<Player> players = null;
        Player player = null;

        Cursor result = db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);

        result.moveToFirst();
        while (result.isAfterLast()==false)
        {
            game = new ResultGame();

            game.setId(result.getInt(result.getColumnIndex(MATCH_ID)));
            game.setPlayersNumber(result.getInt(result.getColumnIndex(PLAYERS_NO)));

            players = new ArrayList<>(game.getPlayersGame());

            for(int i=0;i<game.getPlayersGame();i++)
            {
                player = new Player();

                int playerindex=i+1;
                player.setName(result.getString(result.getColumnIndex("P"+playerindex+"_NAME")));
                player.setPoints(result.getInt(result.getColumnIndex("P"+playerindex+"_SCORES")));
                players.add(player);
            }

            game.Players=players;
            matches.add(game);
            result.moveToNext();
        }
        return  matches;
    }



    public boolean insertData(int playersNo, ArrayList<Player> players)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        for(int i=0;i<playersNo;i++)
        {
            int playerindex=i+1;
            String matchpplayersno =PLAYERS_NO;
            String playerColumn  ="P"+playerindex+"_NAME";
            String playersScores ="P"+playerindex+"_SCORES";
            contentValues.put(matchpplayersno,playersNo);
            contentValues.put(playerColumn,players.get(i).getName());
            contentValues.put(playersScores,players.get(i).getPoints());
        }

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return  false;
        else
            return true;
    }
}
