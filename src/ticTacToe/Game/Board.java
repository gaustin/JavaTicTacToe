package ticTacToe.Game;

import java.util.*;

public class Board {
	
	private char[] cells = null;
	
	public Board(int x, int y) {
		cells = new char[x*y];
	}

	public boolean full() {
		if (emptySpaces().size() == 0)
			return true;
		else
			return false;
	}
	
	public List<Integer> emptySpaces() {
		ArrayList<Integer> empties = new ArrayList<Integer>();
		
		for (int i = 0; i < cells.length; i++) {
			if (!isTaken(i))
				empties.add(i);
		}
		
		return empties;
	}

	public boolean isTaken(int position) {
		return cells[position] != 0;
	}

	private boolean isTakenBy(char mark, int position) {
		return cells[position] == mark;
	}
	
	public void markPosition(char mark, int position) throws Exception {		
		cells[position] = mark;
	}

}
