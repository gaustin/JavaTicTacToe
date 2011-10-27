package tictactoe.players;

import java.util.List;
import java.util.Random;

import tictactoe.game.Board;

public class CheatingPlayer extends Player {

    public CheatingPlayer(char mark) {
        super(mark);
    }

    @Override
    public int getChoice(Board board) {
        Random generator = new Random();
        List<Integer> empties = board.emptySpaces();
        
        int choice = empties.get(generator.nextInt(empties.size()));
        board.markPosition(this.getMark(), choice);
        
        return choice;
    }

}
