module TicTacToe
  module GameActions

    def perform_turn(board, player, position, scorer)
      mark = player.mark
      if valid_move?(board, mark, position) && !scorer.is_game_over
        board.mark_position(mark, position)
        opponent_move(board, scorer)
        true
      else
        false
      end
    end

    def opponent_move(board, scorer)
      choice = opponent.get_choice(board)
      unless scorer.is_game_over
        board.mark_position(opponent.mark, choice)
        choice
      else
        nil
      end
    end

    def valid_move?(board, mark, choice)
      Referee.new.validate_move(board, mark, choice)
    end

    def player
      player = HumanPlayer.new(?X)
      opponent = ComputerPlayer.new(?O, MinimaxStrategy.new)
      player.opponent = opponent
      opponent.opponent = player
      player
    end

    def opponent
      player.opponent
    end
  end
end
