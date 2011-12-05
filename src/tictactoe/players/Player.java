package tictactoe.players;

import tictactoe.game.Board;

public abstract class Player {

    public Player(char mark) {
        m_mark = mark;
    }
    
    private char m_mark;
    public char getMark() {
        return m_mark;
    }
    
    protected Player opponent = null;
    
    public Player getOpponent() {
        return opponent;
    }
    
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
        opponent.opponent = this;
    }
    
    public abstract int getChoice(Board board);

}
