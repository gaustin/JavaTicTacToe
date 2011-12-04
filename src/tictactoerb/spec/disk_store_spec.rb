require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::DiskStore do
  include TicTacToe::DiskStore

  before :each do
    delete_all
  end

  it "should return an id for the state" do
    state = '-' * 9
    game_id = save(state)
    game_id.should_not be_nil
  end

  it "should save state" do
    state = '-' * 9
    game_id = save(state)
    retrieved_state = retrieve(game_id)
    retrieved_state.should == state
  end

  it "should delete a given game" do
    state = 'X' * 9
    game_id = save(state)
    
    filepath = filepath_for(game_id)
    File.exists?(filepath).should be_true
    delete(game_id)
    File.exists?(filepath).should be_false
  end

  it "should update a given game" do
    state = 'XOXOXOXXX'
    game_id = save(state)
    update(game_id, state.reverse)
    retrieved_state = retrieve(game_id)

    retrieved_state.should == state.reverse
  end

  it "should give a valid filepath" do
    game_id = UUIDTools::UUID.timestamp_create
    expected_path = File.join(TicTacToe::DiskStore::GAME_DATA_DIR, game_id.to_s + ".dat")

    filepath_for(game_id).should == expected_path
  end

  it "should indicate that a game exists" do
    game_id = save('')
    exists?(game_id).should be_true
  end

  it "should indicate that a game does not exist" do
    exists?('').should be_false
  end
end
