package com.pleuratpula.manascore.Models;

import java.io.Serializable;
import java.sql.Struct;

public class Player implements Serializable

{
    private String name;
    private int points;
    public  Player(){}
    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public  String getName()
    {
        return name;
    }

    public  void  setName(String newName)
    {
        this.name= newName;
    }

    public  void  addPoint()
    {
        this.points+=1;
    }

    public  void  removePoint()
    {
        this.points-=1;
    }

    public  void setPoints(int newPoints)
    {
        this.points=newPoints;
    }

    public int getPoints()
    {
        return  points;
    }

    public String getPointsToString()
    {
        return Integer.toString(this.points);
    }


}
