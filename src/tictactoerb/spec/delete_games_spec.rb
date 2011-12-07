require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::Tasks::DeleteGames do

  it "should delete all of the files in the game directory" do
    create_files(0..5) 

    TicTacToe::Tasks::DeleteGames.do(0)

    Dir.glob("#{TicTacToe::DiskStore::GAME_DATA_DIR}/*").size.should == 0
  end

  it "should not delete files newer than the threshold" do
    create_files(0..5)
   
    TicTacToe::Tasks::DeleteGames.do(60*60*3)

    Dir.glob("#{TicTacToe::DiskStore::GAME_DATA_DIR}/*").size.should == 6 
  end

  def create_files(range)
    range.each do |i|
      `touch #{TicTacToe::DiskStore::GAME_DATA_DIR}/#{i}.dat`
    end
  end
end

