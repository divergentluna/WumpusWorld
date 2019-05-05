package com.company;

import java.util.Scanner;

public class Wumpus {

    Sensors gameTable [][] = new Sensors[4][4];
    Sensors playerViewTable [][] = new Sensors[4][4];
    Boolean Gold = true;
    Boolean deadWumpuse = false;        //wumpus should die once

    //fills gametable and after that
    //fills palyerviewtable for only breeze and stench
    void getTable (){

        Scanner input = new Scanner(System.in);
        String in = "";
        System.out.println("Use 'p' as if you want to add a pit to this place or 'w' for a wumpus and 'g' for Gold!");
        for (int i=0 ; i<4 ; i++) {
            for (int j=0 ; j<4 ; j++) {
                gameTable [i][j] = new Sensors();
                playerViewTable[i][j] = new Sensors();
            }}
        for (int i=3 ; i>-1 ; i--) {
            for (int j=0 ; j<4 ; j++) {
                System.out.printf("You are entering row %d and col %d :" , i , j);
                in = input.next();
                if(in.equals("p")) {
                    gameTable[i][j].pit = 1;
                    try {
                        if (i+1 < 4) {
                            gameTable[i + 1][j].breeze = 1;
                            playerViewTable[i + 1][j].breeze = 1;
                        }
                        if (i-1 > -1) {
                            gameTable[i - 1][j].breeze = 1;
                            playerViewTable[i - 1][j].breeze = 1;
                        }
                        if (j-1 > -1) {
                            gameTable[i][j - 1].breeze = 1;
                            playerViewTable[i][j - 1].breeze = 1;
                        }
                        if (j+1 < 4){
                            gameTable[i][j + 1].breeze = 1;
                            playerViewTable[i][j + 1].breeze = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }
                }
                else if (in.equals("w")) {
                    gameTable[i][j].wumpus = 1;
                    try {
                        if (i+1 < 4){
                            gameTable[i + 1][j].stench = 1;
                            playerViewTable[i + 1][j].stench = 1;
                        }
                        if (i-1 > -1){
                            gameTable[i - 1][j].stench = 1;
                            playerViewTable[i - 1][j].stench = 1;
                        }
                        if (j-1 > -1){
                            gameTable[i][j - 1].stench = 1;
                            playerViewTable[i][j - 1].stench = 1;
                        }
                        if (j+1 < 4){
                            gameTable[i][j + 1].stench = 1;
                            playerViewTable[i][j + 1].stench = 1;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }
                }
                else if (in.equals("g")){
                    gameTable[i][j].gold = 1;
                }
            }
        }
    }

    //checks if player not to goes out of table
    int checkPlayerPosition (int x , int y){

        if (gameTable[x][y].pit == 1)
            return 0;
        if (gameTable[x][y].wumpus == 1 && !deadWumpuse )
            return 0;
        if ( x == 0 && y == 3)
            return 2;
        if (gameTable[x][y].gold == 1)
            return 3;
        else
            return 1 ;
    }

    void getPalyersNextMove (int x , int y , Boolean shot ){
        if(gameTable[x][y].wumpus == 1 && shot == true){
            deadWumpuse = true;
            System.out.println("Now wumpuse is dead now B)");
        }
    }

    //print the whole gametable
    void print (){
        String cell = "";
        for(int i=0;i<4;i++){
            System.out.format("+-------+-------+-------+-------+%n");
            for(int j=0;j<4;j++){
                cell = "";
                if(gameTable[i][j].wumpus == 1){cell = cell + "W";}
                if(gameTable[i][j].breeze == 1){cell = cell + "B";}
                if(gameTable[i][j].stench == 1){cell = cell + "S";}
                if(gameTable[i][j].pit == 1){cell = cell + "P";}
                if(gameTable[i][j].gold == 1){cell = cell + "G";}

                if (cell.length() == 1){cell = "   " + cell + "   ";}
                else if (cell.length() == 2){cell = "  " + cell + "   ";}
                else if (cell.length() == 3){cell = "  " + cell + "  ";}
                else {cell = "       ";}
                System.out.format("|" + cell);
            }
            System.out.format("|%n");
        }
        System.out.format("+-------+-------+-------+-------+%n");

    }

    //for player view (do not show where pits,wumpus and gold are )
    void getPlayerTable(int x , int y , Sensors table[][]){
        if (x+1 <4 ) {
            if (playerViewTable[x+1][y].breeze == 1)
                table[x + 1][y].breeze = 1;
        }
        if (x-1 > -1) {
            if (playerViewTable[x-1][y].breeze == 1)
                table[x - 1][y].breeze = 1;
        }
        if(y-1 > -1) {
            if (playerViewTable[x][y-1].breeze == 1)
                table[x][y - 1].breeze = 1;
        }
        if(y+1 < 4){
            if (playerViewTable[x][y+1].breeze == 1)
                table[x][y + 1].breeze = 1;
        }

        if (x+1 <4 ) {
            if (playerViewTable[x+1][y].stench == 1)
                table[x + 1][y].stench = 1;
        }
        if (x-1 > -1) {
            if (playerViewTable[x-1][y].stench == 1)
                table[x - 1][y].stench = 1;
        }
        if(y-1 > -1) {
            if (playerViewTable[x][y-1].stench == 1)
                table[x][y - 1].stench = 1;
        }
        if(y+1 < 4){
            if (playerViewTable[x][y+1].stench == 1)
                table[x][y + 1].stench = 1;
        }
    }

}
