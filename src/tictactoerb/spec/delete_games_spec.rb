require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::Tasks::DeleteGames do

  it "should delete all of the files in the game directory" do
    (0..5).each do |i|
      `touch #{TicTacToe::DiskStore::GAME_DATA_DIR}/#{i}.dat`
    end

    TicTacToe::Tasks::DeleteGames.do(0)

    Dir.glob("#{TicTacToe::DiskStore::GAME_DATA_DIR}/*").size.should == 0
  end
end

