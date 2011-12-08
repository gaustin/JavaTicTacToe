module TicTacToe
  class Game
    attr_accessor :board, :x_player, :o_player, :next_turn

    def initialize(board, x_player, o_player, turn_mark)
      @board = board
      @x_player = x_player
      @o_player = o_player
      @next_turn = turn_mark
    end

    def self.load(state)
      state_list = state.split(",")
      return if state_list.size != 4

      board = self.load_board(state_list.shift)
      x_player = self.load_player(?X, state_list.shift)
      o_player = self.load_player(?O, state_list.shift)
      x_player.opponent = o_player

      Game.new(board, x_player, o_player, (state_list.shift).to_i)
    end

    def self.load_board(state)
      board = Board.new(state.size)
      board.load_state(state)
      board
    end

    def self.load_player(mark, player_type)
      PlayerFactory.create(mark, PlayerMap.type_for(player_type), nil)
    end
  end
end
