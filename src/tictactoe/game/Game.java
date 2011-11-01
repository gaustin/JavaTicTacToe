package tictactoe.game;

import tictactoe.players.Player;

public class Game {
    private Board board;
    private IReferee referee;
    private IScorer scorer;
    private Player xPlayer;
    private Player oPlayer;
    private IMessenger messenger;
    
    public Game(Board board, IMessenger messenger, IReferee referee, IScorer scorer,
            Player xPlayer, Player oPlayer) {
        this.board = board;
        this.messenger = messenger;
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
            Board boardCopy = board.copy();
            
            messenger.updateBoardDisplay();
            messenger.promptPlayerForMove(player);
            int choice = player.getChoice(boardCopy);
            
            while (!referee.validateMove(board, player.getMark(), choice)) {
                messenger.informPlayerOfInvalidChoice();
                messenger.updateBoardDisplay();
                messenger.promptPlayerForMove(player);
                choice = player.getChoice(boardCopy);
            }
            
            board.markPosition(player.getMark(), choice);
            
            turn++;
        }
    }

    public void reset() {
        board.reset();
        scorer.reset();
    }
}
