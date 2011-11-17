require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::GameKeeper do
  include TicTacToe::GameKeeper

  before :each do
    clear
  end

  it "should save state" do
    state = '-' * 9
    save(state)
    retrieved_state = retrieve
    retrieved_state.should == state
  end
end
