package tictactoe.players.strategies;

import java.util.List;

import tictactoe.game.Board;
import tictactoe.game.TicTacToeScorer;
import tictactoe.players.Player;

public class MinimaxStrategy implements IPlayerStrategy {

    private static int INFINITY = Integer.MAX_VALUE;

    public int calculateBestMove(Board board, Player player) {
        TicTacToeScorer scorer = new TicTacToeScorer(board);

        List<Integer> potentialMoves = board.emptySpaces();

        int bestMove = -1;
        int bestMoveScore = -INFINITY;

        for (int move : potentialMoves) {

            board.markPosition(player.getMark(), move);
            int score = minimax(board, scorer, player, 32);
            board.markPosition('\0', move);

            if (bestMoveScore < score) {
                bestMove = move;
                bestMoveScore = score;
            }
            scorer.reset();
        }

        return bestMove;
    }

    private int minimax(Board board, TicTacToeScorer scorer,
            Player player, int depth) {
        Player opposingPlayer = player.getOpponent();
        if (scorer.isGameOver() || depth <= 0) {
            if (scorer.isDraw()) {
                return 0;
            } else if (scorer.getWinner() == player.getMark()) {
                return INFINITY;
            } else if (scorer.getWinner() == opposingPlayer.getMark()) {
                return -INFINITY;
            } else {
                return 0;
            }
        } else {
            List<Integer> potentialMoves = board.emptySpaces();

            int bestPlayerScore = -INFINITY;
            int bestOpponentScore = -INFINITY;

            for (int move : potentialMoves) {
                board.markPosition(opposingPlayer.getMark(), move);
                bestOpponentScore = Math.max(minimax(board, scorer,
                        opposingPlayer, depth - 1), bestOpponentScore);
                board.markPosition('\0', move);

                bestPlayerScore = -bestOpponentScore;
                scorer.reset();
            }

            // Deeper moves are less "minimal"
            return bestPlayerScore > 0 ? bestPlayerScore - depth
                    : bestPlayerScore + depth;
        }
    }

}
