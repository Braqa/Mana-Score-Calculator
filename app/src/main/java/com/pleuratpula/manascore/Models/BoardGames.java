package com.pleuratpula.manascore.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class BoardGames {

    // Variables
    private String name;
    private int year;
    private int maxPlayer;
    private String price;

    // Constructor
    public BoardGames(JSONObject jsonObj) throws JSONException {
        this.name = jsonObj.getString("name");
        this.year = jsonObj.getInt("year_published");
        this.maxPlayer = jsonObj.getInt("max_players");
        this.price = jsonObj.getString("price"); ;
    }


    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMaxPlayer() { return maxPlayer; }
    public void setMaxPlayer(int maxPlayer) { this.maxPlayer = maxPlayer; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}
