module TicTacToe
  class Turn
    def self.attempt_all(board, scorer, x_player, o_player, turn_mark=?X, position=nil)
      player = self.get_player_for_turn(x_player, o_player, turn_mark)
      choice = position || player.get_choice(board)
      while choice && !scorer.is_game_over
        return if !self.valid_move?(board, player.mark, choice)
        board.mark_position(player.mark, choice)
        player = player.opponent
        turn_mark = player.mark
        choice = player.get_choice(board)
      end
      turn_mark
    end

    def self.get_player_for_turn(x_player, o_player, turn_mark)
      turn_mark == ?X ? x_player : o_player
    end

    def self.valid_move?(board, mark, choice)
      Referee.new.validate_move(board, mark, choice)
    end
  end
end
