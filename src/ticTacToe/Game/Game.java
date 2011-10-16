package ticTacToe.Game;

import ticTacToe.Players.Player;

public class Game {
	private Board board;
	private Referee referee;
	private IScorer scorer;
	private Player xPlayer;
	private Player oPlayer;
	
	public Game(Board board, Referee referee, IScorer scorer,
			Player xPlayer, Player oPlayer) {
		this.board = board;
		this.referee = referee;
		this.scorer = scorer;
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
	}
	
	public void play() {
		Player[] players = { xPlayer, oPlayer };
		int turn = 0;
		
		while (!scorer.isGameOver()) {
			Player player = players[turn % 2];
			Board boardCopy = board.clone();
			int choice = player.getChoice(boardCopy);
			
			while (!referee.validateMove(board, player.getMark(), choice)) {
				choice = player.getChoice(boardCopy);
			}
			
			board.markPosition(player.getMark(), choice);
			
			turn++;
		}
		
		System.out.println(board.toString());
	}
}
