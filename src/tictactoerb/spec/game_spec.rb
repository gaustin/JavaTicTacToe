require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::Game do

  it "should load a player" do
    player = TicTacToe::Game.load_player(?X, "1")
    player.mark.should == ?X
    player.class.should == TicTacToe::WebHuman
  end

  it "should load a board" do
    state = "X" * 9
    board = TicTacToe::Game.load_board(state)
    board.spaces.all? {|mark| mark.chr == 'X' }.should be_true
  end

  it "should initialize from a state string" do
    state_string = "XXXXXXXXX,1,0,#{?X}"
    game = TicTacToe::Game.load(state_string)
    
    all_x = game.board.spaces.all? {|mark| mark.chr == 'X' }
    all_x.should be_true
    
    game.x_player.opponent.should_not be_nil
    game.x_player.is_a?(TicTacToe::WebHuman).should be_true
    game.o_player.is_a?(ComputerPlayer).should be_true
    game.next_turn.should == ?X
  end
end
