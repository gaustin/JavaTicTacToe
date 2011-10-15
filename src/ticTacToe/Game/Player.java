package ticTacToe.Game;

public abstract class Player<TMark> {

	public Player(TMark mark) {
		m_mark = mark;
	}
	
	public TMark m_mark;
	public TMark getMark() {
		return m_mark;
	}
	
	public abstract int getChoice(Board<TMark> board);
}
