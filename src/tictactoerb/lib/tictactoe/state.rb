module TicTacToe
  class State
    extend TicTacToe::DiskStore

    # Public API for persisting state.

    def self.save_board(board)
      state = board.serialize_state
      save(state)
    end

    def self.update_board(game_id, board)
      state = board.serialize_state
      update(game_id, state)
    end

    def self.load_board(game_id)
      board = Board.new(9)
      state = retrieve(game_id)
      board.load_state(state)
      board
    end

    def self.save_game(board, x_player, o_player, turn_mark)
      save(self.as_state(board, x_player, o_player, turn_mark))
    end

    def self.load_game(game_id)
      state = retrieve(game_id)
      game = Game.load(state)
      game
    end

    def self.update_game(game_id, board, x_player, o_player, turn_mark)
      update(game_id, self.as_state(board, x_player, o_player, turn_mark))
    end

    def self.as_state(board, x_player, o_player, turn_mark)
      "#{board.serialize_state},#{PlayerMap.string_for_class(x_player.class)},#{PlayerMap.string_for_class(o_player.class)},#{turn_mark}"
    end
  end
end
