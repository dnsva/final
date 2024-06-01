package Dungeon;

/*
name: Anna
date: May 31, 2024
title: Map
description: This class is the map of the game. It is a 2D array that stores the different types of things on the map.
*/

//IMPORT ALL PACKAGES -----
import Game.*;          //-
import Items.*;         //-

import javax.swing.*;
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
     6 : Treasure
     */

    //rows - 5
    //cols - 8

    /* The following code creates all the objects for every class */
    static Quest10 quest10Object = new Quest10();
    static Quest20 quest20Object = new Quest20();
    static Quest30 quest30Object = new Quest30();
    static Quest40 quest40Object = new Quest40();
    static Quest50 quest50Object = new Quest50();
    static FinalBoss finalBossObject = new FinalBoss();

    public static int[][] map = { //this array stores the different types of things on the map
            {0, 0, 0, 2, 0, 5, 3, 6},
            {0, -1, 0, -1, -1, -1, -1, -1},
            {4, -1, 5, 0, 0, 2, 0, 5},
            {-1, -1, -1, -1, -1, -1, -1, 2},
            {0, 0, 0, 2, 0, 0, 0, 0}
    };

    public static int[][] questIDs = { //this array stores the IDs of every quest. id 100 is final boss
            {0, 0, 0, 20, 0, 3, 100, 0}, //multiples of 10 are quests
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, //1,2,3 - key 1,2,3,
            {10, 0, 2, 0, 0, 30, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 40},
            {0, 0, 0, 50, 0, 0, 0, 0}
    };

    public static int playerRow = map.length-1; //MAKE THIS map.length-1
    public static int playerCol = 0; //MAKE THIS 0

    public static int prevPlayerRow = map.length - 1; //where the player is located
    public static int prevPlayerCol = 0; //where the player is located

    static int currGlobalRow = 0; //for inner loop stuff
    static int currGlobalCol = map.length-1; //for inner loop stuff

    static void checkCell(int row, int col){ //check what is located on the current cell

        currGlobalRow = row; //okay
        currGlobalCol = col; //okay

        if(map[row][col] == 1){ //if the cell is level complete
           // System.out.println("Level Complete");
        }
        if(map[row][col] == 2 || map[row][col] == 3 || map[row][col] == 4){ //if the cell is level incomplete
            //System.out.println("Level Incomplete");

            //PULL UP WINDOWS
            if(questIDs[row][col] == 10){ //if the quest is 10
                quest10Object.showQuest10(); //show the quest 10 window
                MapGUI.hideMapGUI(); //hide the map
            }else if(questIDs[row][col] == 20) { //if the quest is 20
                quest20Object.showQuest20(); //show the quest 20 window
                MapGUI.hideMapGUI(); //hide the map
            }else if(questIDs[row][col] == 30){ //if the quest is 30
                quest30Object.showQuest30(); //show the quest 30 window
                MapGUI.hideMapGUI(); //hide the map
            }else if(questIDs[row][col] == 40){ //if the quest is 40
                quest40Object.showQuest40(); //show the quest 40 window
                MapGUI.hideMapGUI(); //hide the map
            }else if(questIDs[row][col] == 50){ //if the quest is 50
                quest50Object.showQuest50(); //show the quest 50 window
                MapGUI.hideMapGUI(); //hide the map
            }else if(questIDs[row][col] == 100){ //if the quest is 100
                finalBossObject.showFinalBoss(); //show the final boss window
                MapGUI.hideMapGUI(); //hide the map
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
        if(map[row][col] == 5){ //KEY
            //System.out.println("Key Found");
            //process
            map[row][col] = 0;  //KEYY!!!! - set to 0 since key found

            if(questIDs[row][col] == 1){ //if the first one
                GameVars.inventory.add(new DungeonKey("Key 1"));
            }else if(questIDs[row][col] == 2){ //if the second one
                GameVars.inventory.add(new DungeonKey("Key 2"));
            }else if(questIDs[row][col] == 3){ //if the third one
                GameVars.inventory.add(new DungeonKey("Key 3"));
            }
            //System.out.println("Key Used");
        }else if(map[row][col] == 6){ //The final treasure
            //System.out.println("Treasure Found");
            //process
            GameVars.inventory.add(new DungeonKey("Treasure: The friends you made the along the way")); //add to inventory
            JOptionPane.showMessageDialog(null, "You found the treasure: The friends you made along the way");
            map[row][col] = 0; //set to zero
           // System.out.println("Treasure Used");
        }


    }
    static void moveUp(){ //move up
        if (playerRow > 0 && map[playerRow - 1][playerCol] != -1) { //if the player is not at the top and the cell is not a wall
            prevPlayerRow = playerRow; //set the previous player row
            playerRow--; //update the row
        }
        checkCell(playerRow, playerCol); //check if anything new
    }
    static void moveDown(){ //move down
        if (playerRow < map.length - 1 && map[playerRow + 1][playerCol] != -1) { //if the player is not at the bottom and the cell is not a wall
            prevPlayerRow = playerRow; //set the previous player row
            playerRow++; //update the row
        }
        checkCell(playerRow, playerCol); //check if anything new
    }
    static void moveLeft(){ //move left
        if (playerCol > 0 && map[playerRow][playerCol - 1] != -1) { //if the player is not at the left and the cell is not a wall
            prevPlayerCol = playerCol; //set the previous player col
            playerCol--; //update the col
        }
        checkCell(playerRow, playerCol); //check if anything new
    }
    static void moveRight(){ //move right
        if (playerCol < map[0].length - 1 && map[playerRow][playerCol + 1] != -1) { //if the player is not at the right and the cell is not a wall
            prevPlayerCol = playerCol; //set the previous player col
            playerCol++; //update the col
        }
        checkCell(playerRow, playerCol); //check if anything new
    }

    static String getMapTitle() {   //get the title of the map ASCII

        /* The following code returns a html styled ascii string saying MAP*/
        String title = "<html><p style=\"font-size:9; color:white; font-family: PT Mono; padding-left: 80px;\">";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;███╗&nbsp;&nbsp;&nbsp;███╗&nbsp;█████╗&nbsp;██████╗&nbsp;<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;████╗&nbsp;████║██╔══██╗██╔══██╗<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██╔████╔██║███████║██████╔╝<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██║╚██╔╝██║██╔══██║██╔═══╝&nbsp;<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;██║&nbsp;╚═╝&nbsp;██║██║&nbsp;&nbsp;██║██║&nbsp;&nbsp;&nbsp;&nbsp;<br>";
        title += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;╚═╝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;╚═╝╚═╝&nbsp;&nbsp;╚═╝╚═╝&nbsp;&nbsp;&nbsp;&nbsp;<br>";
        title += "</p></html>";
        return title; //return the string
    }

    static String getMapLegend(){ //the text below the map
        /* The following code returns a html styled ascii string
        With different symbols representing different things on the map
         */
        String legend = "<html><p style=\"font-size:10; color:white; font-family: PT Mono; padding-left: 80px;\">";
        legend += "&#x1FAA8; : Wall<br>";
        legend += "&#x1F7E1; : Empty<br>";
        legend += "&#x1F7E2; : Level Complete<br>";
        legend += "&#x1F534; : Level Incomplete<br>";
        legend += "&#x1F7E3; : Boss<br>";
        legend += "&#x1f535; : Side Quest<br>";
        legend += "&#x1F511; : Key";
        legend += "<p style=\"font-size:9; color:white; font-family: PT Mono; padding-left: 80px;\">Collect all keys to unlock the final boss</p><br>";
        legend += "</p></html>";
        return legend; //return the string
    }
    static String getMapAsciiString(){ //this is the ascii of the actual map

        String mapString = "<html><p style=\"font-size:28; text-align: center;\" >"; //html style

        for(int i = 0; i < map[0].length + 2; i++){  //wall for top
            mapString += "&#x1FAA8;"; //wall
        }
        mapString += "<br>"; //new line

        for(int i = 0; i < map.length; i++) { //for every row
            mapString += "&#x1FAA8;"; //wall
            for (int j = 0; j < map[0].length; j++) { //for every col
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
                }else if(map[i][j] == 6) { //treasure
                    mapString += "&#x1F3C6;";
                }

            }
            mapString += "&#x1FAA8;"; //wall
            mapString += "<br>"; //newline
        }

        for(int i = 0; i < map[0].length + 2; i++){ //map row
            mapString += "&#x1FAA8;"; //wall
        }
        mapString += "<br>"; //newline
        mapString += "</p></html>"; //end the html formatting

        return mapString; //return string
    }

    public static void dealWithMapLevelCompletion(int ID){ //this is called by the quest classes

        /*
        Depending on the ID (quest), the map is updated so that
        the level is marked as complete (red -> green).
        This code is very repetitive and the same so I will only comment the id 20 checker.
        The other code works the same.
         */
        if(ID == 10){ //If the quest is labeled 10
            //this is the sidequest so actually completion doesnt exist
            /*
            if(quest10Object.complete){ //if the quest is complete
                map[currGlobalRow][currGlobalCol] = 1; //update the map values
            }else{ //otherwise
                playerRow = prevPlayerRow; //move back
                playerCol = prevPlayerCol; //move back
            }*/
        }else if(ID == 20) { //if quest id 20
            if(quest20Object.complete){ //check if complete
                map[currGlobalRow][currGlobalCol] = 1; //set it to complete
            }else{ //otherwise
                playerRow = prevPlayerRow; //move back
                playerCol = prevPlayerCol; //move back
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
        //all ids possible checked
    }

    /* All testing
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
    }*/
}
