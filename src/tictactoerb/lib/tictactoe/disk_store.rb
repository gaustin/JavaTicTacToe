require 'uuidtools'

module TicTacToe
  module DiskStore
    GAME_DATA_DIR = File.join(File.dirname(__FILE__), '..', '..', 'db')

    unless File.exists?(GAME_DATA_DIR)
      Dir.mkdir(GAME_DATA_DIR)
    end

    def save(state)
      game_id = find_available_filename
      File.open(filepath_for(game_id), "w+") do |f|
        f.puts(state)
      end
      game_id
    end

    def update(game_id, state)
      File.open(filepath_for(game_id), "w+") do |f|
        f.puts(state)
      end
      true
    end

    def retrieve(game_id)
      state = File.open(filepath_for(game_id), "r") do |f|
        f.readline.chomp
      end
      state
    end

    def delete(game_id)
      path = filepath_for(game_id)
      if File.exists?(path)
        File.unlink(path)
      end
    end

    def delete_all
      Dir.glob("#{GAME_DATA_DIR}/*").each do |filename|
        File.unlink(filename) unless File.directory?(filename)
      end
    end

    def find_available_filename
      game_id = UUIDTools::UUID.timestamp_create
      while File.exists?(filepath_for(game_id))
        game_id = UUIDTools::UUID.timestamp_create
      end
      game_id
    end

    def filepath_for(game_id)
      File.join(GAME_DATA_DIR, game_id.to_s + ".dat")
    end
  end
end
