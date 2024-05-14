package Dungeon;

import Game.*;
import Items.*;

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

    public static int[][] map = {
            {4, -1, 0, 2, 0, 5, 0, 3},
            {0, -1, 0, -1, -1, -1, -1, -1},
            {0, 0, 5, 0, 0, 2, 0, 5},
            {-1, -1, -1, -1, -1, -1, -1, 2},
            {0, 0, 0, 2, 0, 0, 0, 0}
    };

    public static int[][] questIDs = {
            {10, 0, 0, 20, 0, 3, 0, 100}, //multiples of 10 are quests
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, //1,2,3 - key 1,2,3,
            {0, 0, 2, 0, 0, 30, 0, 1},
            {0, 0, 0, 0, 40, 0, 0, 0},
            {0, 0, 0, 50, 0, 0, 0, 0}
    };

    static int playerRow = map.length-1;
    static int playerCol = 0;

    static void checkCell(int row, int col){
        if(map[row][col] == 1){
            System.out.println("Level Complete");
        }
        if(map[row][col] == 2){
            System.out.println("Level Incomplete");
            //process
            map[row][col] = 1;
            System.out.println("Level Complete");
        }
        if(map[row][col] == 3){
            System.out.println("Boss Fight");
            //process
            map[row][col] = 1;
            System.out.println("Boss Defeated");
        }
        if(map[row][col] == 4) {
            System.out.println("Side Quest");
            //process
            map[row][col] = 1;
            System.out.println("Side Quest Complete");
        }
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
            playerRow--;
        }
        checkCell(playerRow, playerCol);
    }
    static void moveDown(){
        if (playerRow < map.length - 1 && map[playerRow + 1][playerCol] != -1) {
            playerRow++;
        }
        checkCell(playerRow, playerCol);
    }
    static void moveLeft(){
        if (playerCol > 0 && map[playerRow][playerCol - 1] != -1) {
            playerCol--;
        }
        checkCell(playerRow, playerCol);
    }
    static void moveRight(){
        if (playerCol < map[0].length - 1 && map[playerRow][playerCol + 1] != -1) {
            playerCol++;
        }
        checkCell(playerRow, playerCol);
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


}
