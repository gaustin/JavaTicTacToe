require File.join(File.dirname(__FILE__), 'spec_helper')

describe HumanPlayer do
  it "should return nil for get_choice" do
    HumanPlayer.new(?X).get_choice(Board.new(9)).should be_nil
  end
end
