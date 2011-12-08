require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::PlayerMap do
  it "should return the string representation for a MinimaxComputer" do
    TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer).should == "0"
  end

  it "should return the key for a Human" do
    TicTacToe::PlayerMap.string_for(PlayerTypes::WebHuman).should == "1"
  end

  it "should return the player type for a Computer" do
    TicTacToe::PlayerMap.type_for("0").should == PlayerTypes::MinimaxComputer
  end

  it "should return the player type for a Human" do
    TicTacToe::PlayerMap.type_for("1").should == PlayerTypes::WebHuman
  end

  it "should return the string representation for the player class" do
    TicTacToe::PlayerMap.string_for_class(TicTacToe::WebHuman).should == "1"
  end
end

