require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::WebMessenger do
  it "should raise an exception when asked to prompt a player for a move" do
    player = PlayerFactory.create(?X, PlayerTypes::Human, TicTacToe::WebMessenger.new)
    lambda { player.getChoice(Board.new(9)) }.should raise_error(ArgumentError, "Deferred For Humans")
  end
end
