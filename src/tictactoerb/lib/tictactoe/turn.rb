module TicTacToe
  class Turn
    # Perform is the only method that is expected to be public
    def self.perform(board, position, scorer, player, turn_mark)
      mark = turn_mark[0]
      if valid_move?(board, mark, position) && !scorer.is_game_over
        board.mark_position(mark, position)
        opponent_move(board, scorer, player.opponent) if player.opponent.is_a?(ComputerPlayer)
        true
      else
        false
      end
    end

    # Following methods considered private.
    def self.opponent_move(board, scorer, opponent)
      choice = opponent.get_choice(board)
      unless scorer.is_game_over
        board.mark_position(opponent.mark, choice)
        choice
      else
        nil
      end
    end

    def self.valid_move?(board, mark, choice)
      Referee.new.validate_move(board, mark, choice)
    end
  end
end
