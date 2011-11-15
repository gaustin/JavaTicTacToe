require File.join(File.dirname(__FILE__), 'spec_helper')

describe "Board" do
  before :each do
    @board = Board.new(9)
  end

  it "should serialize state" do |i|
    (0..8).each do |i|
        @board.mark_position(?X, i)
    end

    state = @board.serialize_state
    state.should == "X" * 9
  end

  it "should load state" do |i|
    @board.load_state "X" * 9

    (0..8).each do |i|
        mark = @board.mark_at(i)
        mark.should == ?X
    end
  end
end
