package tictactoe.game;

import java.util.*;

public class Board {
	
	private char[] cells = null;
	
	public void reset() {
		cells = new char[cells.length];
	}
	
	public Board(int x, int y) {
		cells = new char[x*y];
	}
	
	public Board(int spacesInBoard) {
		cells = new char[spacesInBoard];
	}

	public boolean full() {
		if (emptySpaces().size() == 0)
			return true;
		else
			return false;
	}
	
	public char[] getSpaces() {
		return cells;
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

	public boolean isTakenBy(char mark, int position) {
		return cells[position] == mark;
	}
	
	public void markPosition(char mark, int position) {		
		cells[position] = mark;
	}

	public char markAt(int position) {
		return (char)cells[position];
	}
	
	public Board copy() {
		Board clonedBoard = new Board(cells.length);
		for (int i = 0; i < cells.length; i++) {
			clonedBoard.markPosition(cells[i], i);
		}
		return clonedBoard;
	}
	
	public void clear() {
		cells = new char[cells.length];
	}
	
	public char markOrDashAt(int position) {
		char mark = cells[position];
		String pos = String.valueOf(position);

		return mark == 0 ? pos.toCharArray()[0] : mark;
	}
	
    public String toString() {
    	String board =
    		" " + markOrDashAt(0) + " | " + markOrDashAt(1) + " | " + markOrDashAt(2) + " \n" +
            "-----------\n" +
            " " + markOrDashAt(3) + " | " + markOrDashAt(4) + " | " + markOrDashAt(5) + " \n" +
            "-----------\n" +
            " " + markOrDashAt(6) + " | " + markOrDashAt(7) + " | " + markOrDashAt(8) + " \n";
    	return board;
    }
}
