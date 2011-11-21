module TicTacToe
  module State
    include TicTacToe::GameKeeper

    def save_board(board)
      state = board.serialize_state
      save(state)
    end

    def update_board(game_id, board)
      state = board.serialize_state
      update(game_id, state)
    end

    def load_board(game_id)
      board = Board.new(9)
      state = retrieve(game_id)
      board.load_state(state)
      board
    end
  end
end
