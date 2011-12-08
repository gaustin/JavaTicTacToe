require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::State do
  it "should load a board" do
    state = 'X' * 9
    game_id = TicTacToe::State.save(state)
    
    board = TicTacToe::State.load_board(game_id)
    all_x = board.spaces.all? { |mark| mark.chr == 'X' }
    all_x.should be_true
  end

  it "should save a board" do
    board = Board.new(9)
    (0..8).each do |i|
      board.mark_position(?X, i)
    end
    
    TicTacToe::State.save_board(board).should_not be_nil
  end

  it "should update a board" do
    board = Board.new(9)
   
    game_id = TicTacToe::State.save_board(board)
    
    (0..8).each do |i|
      board.mark_position(?X, i)
    end
    TicTacToe::State.update_board(game_id, board)
    state = TicTacToe::State.load_board(game_id)
    state.spaces.all? {|mark| mark.chr == "X" }.should be_true
  end

  it "should save a board and the player types" do
    board = Board.new(9)
    x_player = HumanPlayer.new(?X)
    o_player = ComputerPlayer.new(?O, MinimaxStrategy.new)
   
    game_id = TicTacToe::State.save_game(board, x_player, o_player, ?X) 
    game_id.should_not be_nil
  end

  it "should load a board and the appropriate player" do
    board = Board.new(9)
    (0..8).each do |i|
      board.mark_position(?X, i)
    end

    game_id = TicTacToe::State.save_game(board, TicTacToe::WebHuman.new(?X), ComputerPlayer.new(?O, MinimaxStrategy.new), ?X)

    state = TicTacToe::State.load_game(game_id)

    all_x = state.board.spaces.all? { |mark| mark.chr == 'X' }    
    all_x.should be_true

    state.x_player.is_a?(TicTacToe::WebHuman).should be_true
    state.o_player.is_a?(ComputerPlayer).should be_true
    state.next_turn.should == ?X 
  end

  it "should update a game" do
    board = Board.new(9)
    board.mark_position(?X, 0);
    x_player = TicTacToe::WebPlayerFactory.create(?X, PlayerTypes::MinimaxComputer, nil)
    x_player.opponent = TicTacToe::WebPlayerFactory.create(?O, PlayerTypes::MinimaxComputer, nil)
    game_id = TicTacToe::State.save_game(board, x_player, x_player.opponent, ?X)

    state = TicTacToe::State.load_game(game_id)

    choice = state.x_player.getChoice(state.board);
    state.board.mark_position(?X, choice);

    TicTacToe::State.update_game(game_id, state.board, x_player, x_player.opponent, ?O)

    state = TicTacToe::State.load_game(game_id)
    state.board.empty_spaces.size.should == 7
    state.next_turn.should == ?O
  end
end
