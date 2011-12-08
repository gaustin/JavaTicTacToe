require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::Turn do

  before :each do
    @board = Board.new(9)
    @scorer = TicTacToeScorer.new(@board)
    @player = PlayerFactory.create(?X, PlayerTypes::Human, nil)
    @player.opponent = PlayerFactory.create(?O, PlayerTypes::MinimaxComputer, nil)
  end

  it "should discern a valid move" do
    TicTacToe::Turn.valid_move?(@board, ?X, 0).should be_true
  end

  it "should discern an invalid move" do
    @board.mark_position(?X, 0)
    TicTacToe::Turn.valid_move?(@board, ?O, 0).should be_false
    
    @board.mark_position(?O, 5)
    TicTacToe::Turn.attempt_all(@board, @scorer, @player, @player.opponent, ?X, 0).should == nil 
  end

  it "should make two moves when performing a turn with a computer player" do
    success = TicTacToe::Turn.attempt_all(@board, @scorer, @player, @player.opponent, ?X, 0)
    success.should be_true

    @board.empty_spaces.size.should == 7
  end

  it "should make only one move when performing a turn with two human players" do
    @player.opponent = PlayerFactory.create(?O, PlayerTypes::Human, nil)
    
    next_turn = TicTacToe::Turn.attempt_all(@board, @scorer, @player, @player.opponent, ?X, 0)
    @board.empty_spaces.size.should == 8 
    next_turn.should == ?O
  end

  it "should make all moves when performing a turn with two computer players" do
    @x_player = PlayerFactory.create(?X, PlayerTypes::MinimaxComputer, nil)
    @o_player = PlayerFactory.create(?O, PlayerTypes::MinimaxComputer, nil)
    @x_player.opponent = @o_player

    TicTacToe::Turn.attempt_all(@board, @scorer, @x_player, @o_player)
    @board.empty_spaces.size.should == 0
    @scorer.is_game_over.should be_true
    @scorer.is_draw.should be_true
  end

  it "should perform one move when the computer is X and the player is O" do
    @x_player = PlayerFactory.create(?X, PlayerTypes::MinimaxComputer, nil)
    @o_player = PlayerFactory.create(?O, PlayerTypes::Human, nil)
    @x_player.opponent = @o_player

    TicTacToe::Turn.attempt_all(@board, @scorer, @x_player, @o_player)
    @board.empty_spaces.size.should == 8
    @scorer.is_game_over.should be_false
  end
end
