package Dungeon;

//IMPORT ALL PACKAGES -----
import java.io.*;       //-
import java.util.*;     //-
import java.awt.*;      //-
import javax.swing.*;   //-
import Game.*;          //-
import Items.*;         //-
import Monsters.*;      //-
import Shops.*;         //-
import AnnaTools.*;     //-
//-------------------------

public class Map {

    /*
    -1 : WALL
     0 : EMPTY
     1 : LEVEL COMPLETE
     2 : LEVEL INCOMPLETE
     3 : BOSS
     4 : SIDE QUEST
     5 : KEY
     */

    //rows - 5
    //cols - 8

    static Quest10 quest10Object = new Quest10();
    static Quest20 quest20Object = new Quest20();
    static Quest30 quest30Object = new Quest30();
    static Quest40 quest40Object = new Quest40();
    static Quest50 quest50Object = new Quest50();
    static FinalBoss finalBossObject = new FinalBoss();

    public static int[][] map = {
            {0, 0, 0, 2, 0, 5, 0, 3},
            {0, -1, 0, -1, -1, -1, -1, -1},
            {4, -1, 5, 0, 0, 2, 0, 5},
            {-1, -1, -1, -1, -1, -1, -1, 2},
            {0, 0, 0, 2, 0, 0, 0, 0}
    };

    public static int[][] questIDs = {
            {0, 0, 0, 20, 0, 3, 0, 100}, //multiples of 10 are quests
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, //1,2,3 - key 1,2,3,
            {10, 0, 2, 0, 0, 30, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 40},
            {0, 0, 0, 50, 0, 0, 0, 0}
    };

    static int playerRow = map.length-1;
    static int playerCol = 0;

    static int prevPlayerRow = map.length - 1;
    static int prevPlayerCol = 0;

    static int currGlobalRow = 0;
    static int currGlobalCol = map.length-1;

    static void checkCell(int row, int col){

        currGlobalRow = row;
        currGlobalCol = col;

        if(map[row][col] == 1){
            System.out.println("Level Complete");
        }
        if(map[row][col] == 2 || map[row][col] == 3 || map[row][col] == 4){
            System.out.println("Level Incomplete");

            //PULL UP WINDOW
            if(questIDs[row][col] == 10){
                quest10Object.showQuest10();
                MapGUI.hideMapGUI();
            }else if(questIDs[row][col] == 20) {
                quest20Object.showQuest20();
                MapGUI.hideMapGUI();
            }else if(questIDs[row][col] == 30){
                quest30Object.showQuest30();
                MapGUI.hideMapGUI();
            }else if(questIDs[row][col] == 40){
                quest40Object.showQuest40();
                MapGUI.hideMapGUI();
            }else if(questIDs[row][col] == 50){
                quest50Object.showQuest50();
                MapGUI.hideMapGUI();
            }else if(questIDs[row][col] == 100){
                finalBossObject.showFinalBoss();
                MapGUI.hideMapGUI();
            }

           // map[row][col] = 1;
           // System.out.println("Level Complete");
        }
        //if(map[row][col] == 3){
          //  System.out.println("Boss Fight");
            //process
          //  map[row][col] = 1;
         //  System.out.println("Boss Defeated");
        //}
        if(map[row][col] == 5){
            System.out.println("Key Found");
            //process
            map[row][col] = 0;  //KEYY!!!!

            if(questIDs[row][col] == 1){
                GameVars.inventory.add(new DungeonKey("Key 1"));
            }else if(questIDs[row][col] == 2){
                GameVars.inventory.add(new DungeonKey("Key 2"));
            }else if(questIDs[row][col] == 3){
                GameVars.inventory.add(new DungeonKey("Key 3"));
            }


            System.out.println("Key Used");
        }


    }
    static void moveUp(){
        if (playerRow > 0 && map[playerRow - 1][playerCol] != -1) {
            prevPlayerRow = playerRow;
            playerRow--;
        }
        checkCell(playerRow, playerCol);
    }
    static void moveDown(){
        if (playerRow < map.length - 1 && map[playerRow + 1][playerCol] != -1) {
            prevPlayerRow = playerRow;
            playerRow++;
        }
        checkCell(playerRow, playerCol);
    }
    static void moveLeft(){
        if (playerCol > 0 && map[playerRow][playerCol - 1] != -1) {
            prevPlayerCol = playerCol;
            playerCol--;
        }
        checkCell(playerRow, playerCol);
    }
    static void moveRight(){
        if (playerCol < map[0].length - 1 && map[playerRow][playerCol + 1] != -1) {
            prevPlayerCol = playerCol;
            playerCol++;
        }
        checkCell(playerRow, playerCol);
    }

    static String getMapTitle() {
        String title = "<html><p style=\"font-size:9; color:white; font-family: PT Mono; padding-left: 80px;\">";

        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;███╗&nbsp;&nbsp;&nbsp;███╗&nbsp;█████╗&nbsp;██████╗&nbsp;<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;████╗&nbsp;████║██╔══██╗██╔══██╗<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██╔████╔██║███████║██████╔╝<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██║╚██╔╝██║██╔══██║██╔═══╝&nbsp;<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██║&nbsp;╚═╝&nbsp;██║██║&nbsp;&nbsp;██║██║&nbsp;&nbsp;&nbsp;&nbsp;<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;╚═╝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;╚═╝╚═╝&nbsp;&nbsp;╚═╝╚═╝&nbsp;&nbsp;&nbsp;&nbsp;<br>";

        title += "</p></html>";
        return title;
    }

    static String getMapLegend(){
        String legend = "<html><p style=\"font-size:12; color:white; font-family: PT Mono; padding-left: 80px;\">";
        legend += "&#x1FAA8; : Wall<br>";
        legend += "&#x1F7E1; : Empty<br>";
        legend += "&#x1F7E2; : Level Complete<br>";
        legend += "&#x1F534; : Level Incomplete<br>";
        legend += "&#x1F7E3; : Boss<br>";
        legend += "&#x1f535; : Side Quest<br>";
        legend += "&#x1F511; : Key";
        legend += "<p style=\"font-size:9; color:white; font-family: PT Mono; padding-left: 80px;\">Collect all keys to unlock the final boss</p><br>";
        legend += "</p></html>";
        return legend;
    }
    static String getMapAsciiString(){
        String mapString = "<html><p style=\"font-size:30; text-align: center;\" >";

        for(int i = 0; i < map[0].length + 2; i++){
            //wall
            mapString += "&#x1FAA8;";
        }
        mapString += "<br>";

        for(int i = 0; i < map.length; i++) {
            mapString += "&#x1FAA8;";
            for (int j = 0; j < map[0].length; j++) {
                if (i == playerRow && j == playerCol) {
                    mapString += "&#x1F642;";
                }else if(map[i][j] == -1){ //wall
                    mapString += "&#x1FAA8;";
                }else if(map[i][j] == 0) { //empty
                    mapString += "&#x1F7E1";
                }else if(map[i][j] == 1) { //level complete
                    mapString += "&#x1F7E2;";
                }else if(map[i][j] == 2) { //level incomplete
                    mapString += "&#x1F534;";
                }else if(map[i][j] == 3) { //boss
                    mapString += "&#x1F7E3;";
                }else if(map[i][j] == 4) { //side quest
                    mapString += "&#x1f535;";
                }else if(map[i][j] == 5) { //key

                    mapString += "&#x1F511;";
                }

            }
            mapString += "&#x1FAA8;";
            mapString += "<br>";
        }

        for(int i = 0; i < map[0].length + 2; i++){
            //wall
            mapString += "&#x1FAA8;";
        }
        mapString += "<br>";

        mapString += "</p></html>";

        return mapString;
    }


    public static void main(String[] args) {
        Map m = new Map();

        System.out.print(m.getMapAsciiString());
        System.out.print(m.getMapAsciiString());
        m.moveRight();
        System.out.print(m.getMapAsciiString());
        m.moveRight();
        System.out.print(m.getMapAsciiString());
        m.moveUp();
        System.out.print(m.getMapAsciiString());
        m.moveRight();
        System.out.print(m.getMapAsciiString());
        m.moveUp();
        System.out.print(m.getMapAsciiString());
        m.moveLeft();
        System.out.print(m.getMapAsciiString());
        m.moveLeft();
        System.out.print(m.getMapAsciiString());




    }

    public static void dealWithMapLevelCompletion(int ID){

        if(ID == 10){
            if(quest10Object.complete){
                map[currGlobalRow][currGlobalCol] = 1;
            }else{
                playerRow = prevPlayerRow;
                playerCol = prevPlayerCol;
            }
        }else if(ID == 20) {
            if(quest20Object.complete){
                map[currGlobalRow][currGlobalCol] = 1;
            }else{
                playerRow = prevPlayerRow;
                playerCol = prevPlayerCol;
            }
        }else if(ID == 30){
            if(quest30Object.complete){
                map[currGlobalRow][currGlobalCol] = 1;
            }else{
                playerRow = prevPlayerRow;
                playerCol = prevPlayerCol;
            }
        }else if(ID == 40){
            if(quest40Object.complete){
                map[currGlobalRow][currGlobalCol] = 1;
            }else{
                playerRow = prevPlayerRow;
                playerCol = prevPlayerCol;
            }
        }else if(ID == 50){
            if(quest50Object.complete){
                map[currGlobalRow][currGlobalCol] = 1;
            }else{
                playerRow = prevPlayerRow;
                playerCol = prevPlayerCol;
            }
        }else if(ID == 100) {
            if (finalBossObject.complete) {
                map[currGlobalRow][currGlobalCol] = 1;
            } else {
                playerRow = prevPlayerRow;
                playerCol = prevPlayerCol;
            }
        }

    }


}
