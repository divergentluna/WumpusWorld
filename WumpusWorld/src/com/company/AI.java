package com.company;

public class AI {

    Sensors myTable [][] = new Sensors[4][4];       //table to play in

    int previousPositionX = -1;
    int previousPositionY = -1;
    Boolean shot = false;       //to kill wumpus just once
    Wumpus wumpus = new Wumpus();
    Boolean end = false;

    //get a wumpus class to solve the table
    void solve(Wumpus w){
        for (int i=0 ; i<4 ; i++) {
            for (int j=0 ; j<4 ; j++) {
                myTable [i][j] = new Sensors();
            }
        }
        wumpus = w ;
        int result = move(3,0);
        if (result == 1)
            System.out.println("I won!");
        else
            System.out.println("I lost!");
    }

    //checks if moves happens in table
    Boolean checkMoves (int x , int y){
        if( x < 0 || x>3 || y<0 || y>3 )
            return false;
        return true;
    }

    //update every time to move to a new place in four directions
    int move(int x , int y){
        updateSensors(x,y);
        print(x,y);
        int status = wumpus.checkPlayerPosition(x,y);
        if (status == 0)
            return 0;
        else if (status == 3) {//means you are in gold
            System.out.println("Grabbed Gold!");
            wumpus.Gold = true;
        }
        else if (status == 2){ // means you are in end point
            end = true;
            return 1;
        }

        //goes up
        if (!end) {
            if (checkMoves(x - 1, y) && !previousPos(x-1,y)) {
                if (checkSafe(x - 1, y)) {
                    previousPositionX = x;
                    previousPositionY = y;
                    move(x - 1, y);
                }
            }
        }

        //goes right
        if (!end) {
            if (checkMoves(x, y + 1) && !previousPos(x,y + 1)) {
                if (checkSafe(x, y + 1)) {
                    previousPositionX = x;
                    previousPositionY = y;
                    move(x, y + 1);
                }
            }
        }

        //goes left
        if(!end)
        if (checkMoves(x,y-1) && !previousPos(x,y-1)){
            if (checkSafe(x,y-1)) {
                previousPositionX = x;
                previousPositionY = y;
                move(x,y-1);
            }
        }

        //goes down
        if (!end) {
            if (checkMoves(x + 1, y) && !previousPos(x+1,y)) {
                if (checkSafe(x + 1, y)) {
                    previousPositionX = x;
                    previousPositionY = y;
                    move(x + 1, y);
                }
            }
        }

        if(end)
            return 1;
        else {
            System.out.println("I can't move.");
            return 0;
        }
    }

    //checks where the player was in last move
    Boolean previousPos(int x , int y){
        if (x == previousPositionX && y==previousPositionY)
            return true;
        else
            return false;
    }

    //to check and intiation sensors after moves
    void updateSensors (int x , int y) {
        wumpus.getPlayerTable(x, y, myTable);

        if (myTable[x][y].breeze == 1 && myTable[x][y].stench == 0) {
            if (checkMoves(x + 1, y)) {
                myTable[x + 1][y].pit += 0.5;
                myTable[x + 1][y].wumpus -= 0.5;
            }
            if (checkMoves(x - 1, y)) {
                myTable[x - 1][y].pit += 0.5;
                myTable[x - 1][y].wumpus -= 0.5;
            }
            if (checkMoves(x, y + 1)) {
                myTable[x][y + 1].pit += 0.5;
                myTable[x][y + 1].wumpus -= 0.5;
            }
            if (checkMoves(x, y - 1)) {
                myTable[x][y - 1].pit += 0.5;
                myTable[x][y - 1].wumpus -= 0.5;
            }
        }

            if (myTable[x][y].stench == 1 && myTable[x][y].breeze == 0) {
                if (checkMoves(x + 1, y)) {
                    myTable[x + 1][y].pit -= 0.5;
                    if(!wumpus.deadWumpuse)
                        myTable[x + 1][y].wumpus += 0.5;
                    else myTable[x + 1][y].wumpus -= 0.5;
                }
                if (checkMoves(x - 1, y)) {
                    myTable[x - 1][y].pit -= 0.5;
                    if(!wumpus.deadWumpuse)
                        myTable[x - 1][y].wumpus += 0.5;
                    else myTable[x - 1][y].wumpus -= 0.5;
                }
                if (checkMoves(x, y + 1)) {
                    myTable[x][y + 1].pit -= 0.5;
                    if(!wumpus.deadWumpuse)
                        myTable[x][y + 1].wumpus += 0.5;
                    else myTable[x][y + 1].wumpus -= 0.5;
                }
                if (checkMoves(x, y - 1)) {
                    myTable[x][y - 1].pit -= 0.5;
                    if(!wumpus.deadWumpuse)
                         myTable[x][y - 1].wumpus += 0.5;
                    else myTable[x][y - 1].wumpus -= 0.5;
                }
            }

            if (myTable[x][y].breeze == 1 && myTable[x][y].stench == 1) {
                if (checkMoves(x + 1, y)) {
                    myTable[x + 1][y].pit += 0.5;
                    if(!wumpus.deadWumpuse)
                        myTable[x + 1][y].wumpus += 0.5;
                    else myTable[x + 1][y].wumpus -= 0.5;
                }
                if (checkMoves(x - 1, y)) {
                    myTable[x - 1][y].pit += 0.5;
                    if(!wumpus.deadWumpuse)
                        myTable[x - 1][y].wumpus += 0.5;
                    else myTable[x - 1][y].wumpus -= 0.5;
                }
                if (checkMoves(x, y + 1)) {
                    myTable[x][y + 1].pit += 0.5;
                    if(!wumpus.deadWumpuse)
                        myTable[x][y + 1].wumpus += 0.5;
                    else myTable[x][y + 1].wumpus -= 0.5;
                }
                if (checkMoves(x, y - 1)) {
                    myTable[x][y - 1].pit += 0.5;
                    if(!wumpus.deadWumpuse)
                        myTable[x][y - 1].wumpus += 0.5;
                    else myTable[x][y - 1].wumpus -= 0.5;
                }
            }

            if (myTable[x][y].breeze == 0 && myTable[x][y].stench == 0 ){
                if(checkMoves(x+1,y)){
                    myTable[x+1][y].pit -= 0.5;
                    myTable[x+1][y].wumpus -= 0.5;
                }
                if(checkMoves(x-1,y)){
                    myTable[x-1][y].pit -= 0.5;
                    myTable[x-1][y].wumpus -= 0.5;
                }
                if(checkMoves(x,y+1)){
                    myTable[x][y+1].pit -= 0.5;
                    myTable[x][y+1].wumpus -= 0.5;
                }
                if(checkMoves(x,y-1)){
                    myTable[x][y-1].pit -= 0.5;
                    myTable[x][y-1].wumpus -= 0.5;
                }
            }
            myTable[0][3].pit =0;
            myTable[0][3].wumpus=0;

        }

    // to cheack if the next place is empty of any wumpus
    Boolean checkSafe (int x,int y){
        if(myTable[x][y].wumpus >= 1) {
            shot = true;
            myTable[x][y].wumpus = 0;
            System.out.println("Oh you here die!!!");
            wumpus.deadWumpuse = true;
        }
            if (myTable[x][y].wumpus <=0 && myTable[x][y].pit <= 0)
                return true;
            else
                return false;
    }

    //print game play enviroment
    void print(int x , int y){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        String cell;
        for(int i=0;i<4;i++){
            System.out.format("+-------+-------+-------+-------+%n");
            for(int j=0;j<4;j++){
                cell = "";
                if (i==x && j==y) cell=cell+"$";
                if(myTable[i][j].breeze == 1){cell = cell + "B";}
                if(myTable[i][j].stench == 1){cell = cell + "S";}
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


}
