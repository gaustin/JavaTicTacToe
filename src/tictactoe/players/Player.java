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
	
	public abstract int getChoice(Board board);

}
