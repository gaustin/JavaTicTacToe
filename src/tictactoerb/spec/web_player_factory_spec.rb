require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::WebPlayerFactory do
  it "should create a WebHuman" do
    TicTacToe::WebPlayerFactory.create(?X, PlayerTypes::WebHuman)
  end
end

