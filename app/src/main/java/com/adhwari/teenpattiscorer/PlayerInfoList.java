package com.adhwari.teenpattiscorer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adhkulka on 02-01-2015.
 */
public class PlayerInfoList {
    public static void setTableValue(int tableValue) {
        PlayerInfoList.tableValue = tableValue;
    }

    private static int tableValue;
    private static List<PlayerInformation> listOfPlayers;
    private PlayerInfoList(){};

    public static List<PlayerInformation> getPlayerInfoList(){
        if(listOfPlayers == null){
            listOfPlayers = new ArrayList<PlayerInformation>();
        }
        return listOfPlayers;
    }

    public static int getTableValue() {
        return tableValue;
    }

    public static void clearPlayerData() {
        tableValue = 0;
        for(int i = 0; i < listOfPlayers.size(); i++)
            listOfPlayers.remove(0);
    }
}
