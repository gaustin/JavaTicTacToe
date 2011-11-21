require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::State do
  include TicTacToe::State

  it "should load a board" do
    state = 'X' * 9
    game_id = save(state)
    
    board = load_board(game_id)
    board.get_spaces.all? { |mark| mark.chr == 'X' }
  end

  it "should save a board" do
    board = Board.new(9)
    (0..8).each do |i|
      board.mark_position(?X, i)
    end
    
    save_board(board).should_not be_nil
  end

  it "should update a board" do
    board = Board.new(9)
   
    game_id = save_board(board)
    
    (0..8).each do |i|
      board.mark_position(?X, i)
    end
    update_board(game_id, board)
  end
end
