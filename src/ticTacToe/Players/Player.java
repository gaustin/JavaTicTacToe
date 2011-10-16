package ticTacToe.Players;

import ticTacToe.Game.Board;

public abstract class Player {

	public Player(char mark) {
		m_mark = mark;
	}
	
	public char m_mark;
	public char getMark() {
		return m_mark;
	}
	
	public abstract int getChoice(Board board);
}
