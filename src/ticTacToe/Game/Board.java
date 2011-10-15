package ticTacToe.Game;

import java.lang.reflect.Array;
import java.util.*;

public class Board<TCell> {
	
	private TCell[] cells = null;
	
	@SuppressWarnings("unchecked")
	public Board(int x, int y) {
		cells = (TCell[])Array.newInstance(Object.class, x*y);
	}

	public boolean full() {
		if (emptySpaces().size() == 0)
			return true;
		else
			return false;
	}
	
	public List<TCell> spaces() {
		ArrayList<TCell> spaces = new ArrayList<TCell>();
		
		for (TCell c : cells)
			spaces.add(c);
		
		return spaces;
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
		return cells[position] != null;
	}

	public boolean isTakenBy(TCell mark, int position) {
		return cells[position] == mark;
	}
	
	public void markPosition(TCell mark, int position) {		
		cells[position] = mark;
	}

	public TCell markAt(int position) {
		return (TCell)cells[position];
	}

}
