require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::WebHuman do
  it "should return nil for get_choice" do
    TicTacToe::WebHuman.new(?X).get_choice(Board.new(9)).should be_nil
  end
end
