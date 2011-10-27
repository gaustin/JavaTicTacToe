package tictactoe.tests;

import tictactoe.game.Board;


public class Test4X4Board extends Test3X3Board {
    public void setUp() {
        super.setUp();
        board = new Board(20);
        boardSize = 20;
    }
}
