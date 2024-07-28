package com.pleuratpula.manascore.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResultGame implements Serializable {
    int Id;
    int PlayersGame;
    LocalDate date;

    public ArrayList<Player> Players = new ArrayList<>();

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }
    public void setPlayersNumber(int PlayersNo)
    {
        this.PlayersGame=PlayersNo;
    }

    public int getPlayersGame() {
        return PlayersGame;
    }

    public void setPlayersGame(ArrayList<Player> players) {
        this.Players = players;
    }


    public int getId() {
        return Id;
    }


    public void setId(int id) {
        Id = id;
    }
}
