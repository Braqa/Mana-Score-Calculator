package com.pleuratpula.manascore;

import android.media.Image;

import java.io.Serializable;

public class Players implements Serializable {
    private String playerName;
    private Image playerColo;



    public Players(String playerName, Image playerColo) {
        this.playerName = playerName;
        this.playerColo = playerColo;
    }

    // Getter and Setter
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Image getPlayerColo() {
        return playerColo;
    }

    public void setPlayerColo(Image playerColo) {
        this.playerColo = playerColo;
    }
}
