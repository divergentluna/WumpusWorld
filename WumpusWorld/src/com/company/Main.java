package com.company;

public class Main {

    public static void main(String[] args) {
        Wumpus w = new Wumpus();
        w.getTable();
        w.print();
        AI ai = new AI();
        ai.solve(w);
    }
}
