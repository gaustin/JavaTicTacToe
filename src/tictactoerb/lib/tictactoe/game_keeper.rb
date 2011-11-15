module GameKeeper
  GAME_DATA_FILE = File.join(File.dirname(__FILE__), '..', '..', 'game_data.txt')

  def save(state)
    file = File.open(GAME_DATA_FILE, "w+")
    file.write(state + "\n")
    file.close
    true
  end

  def retrieve
    file = File.open(GAME_DATA_FILE, "r")
    state = file.readline.chomp
    file.close
    state
  end

  def clear
    if File.exists?(GAME_DATA_FILE)
      File.unlink(GAME_DATA_FILE)
    end
  end
end
