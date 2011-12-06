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
  end

  it "should let the computer make a move" do
    choice = TicTacToe::Turn.opponent_move(@board, @scorer, @player.opponent)
    choice.should_not be_nil
  end 

  it "should prevent the computer from making a move" do
    (0..8).each { |i| @board.mark_position(?X, i)  }
    choice = TicTacToe::Turn.opponent_move(@board, @scorer, @player.opponent)
    choice.should be_nil
  end

  it "should make two moves when performing a turn with a computer player" do
    success = TicTacToe::Turn.perform(@board, 0, @scorer, @player, 'O')
    success.should be_true

    @board.empty_spaces.size.should == 7
  end
end
