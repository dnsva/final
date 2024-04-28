package Dungeon;

import Game.GameVars;

import java.util.*;

public class Map {

    /*
    -1 : WALL
     0 : EMPTY
     1 : LEVEL COMPLETE
     2 : LEVEL INCOMPLETE
     3 : BOSS
     4 : SIDE QUEST
     */

    //rows - 8
    //cols - 5

    public static int[][] map = {
            {4, -1, 0, 2, 0, 2, 0, 3},
            {0, -1, 0, -1, -1, -1, -1, -1},
            {0, 0, 2, 0, 0, 0, 0, 4},
            {-1,-1, -1, -1, -1, 2, -1, -1},
            {0, 0, 0, 2, 0, 0, 0, 4}
    };

    public static int[][] questIDs = {
            {10, 0, 0, 20, 0, 30, 0, 40},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 20, 0, 0, 0, 0, 0, 50},
            {0, 0, 0, 0, 0, 60, 0, 0},
            {0, 0, 0, 70, 0, 0, 0, 80}
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
        String mapString = "<html>";

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

        mapString += "</html>";

        return mapString;
    }

    public static void main(String[] args) {
        Map m = new Map();
        /*
        System.out.print(m.getMapAsciiString());
        m.moveRight();
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

         */
    }


}
