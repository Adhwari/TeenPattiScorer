package com.adhwari.teenpattiscorer;

import android.widget.Button;
import android.widget.EditText;

/**
 * Created by adhkulka on 02-01-2015.
 */
public class PlayerInformation {
    private String playerName;
    private int currentScore;

    public PlayerInformation(String name, int score) {
        this.playerName = name;
        this.currentScore = score;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setCurrentScore(int score){
        this.currentScore = score;
    }

    public int getCurrentScore()
    {
        return currentScore;
    }

    public void addCurrentScore(int score) {
        currentScore += score;
    }
}



