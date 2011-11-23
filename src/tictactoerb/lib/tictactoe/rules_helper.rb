module TicTacToe
  module RulesHelper

    def perform_turn(board, player, position, scorer)
      mark = player.get_mark
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
        board.mark_position(opponent.get_mark, choice)
        choice
      else
        nil
      end
    end

    def valid_move?(board, mark, choice)
      Referee.new.validate_move(board, mark, choice)
    end

    def player
      @player ||= HumanPlayer.new(?X)
    end

    def opponent
      @opponent ||= ComputerPlayer.new(?O, MinimaxStrategy.new)
      @opponent.set_opponent(player)
      player.set_opponent(@opponent)
      @opponent
    end
  end
end
