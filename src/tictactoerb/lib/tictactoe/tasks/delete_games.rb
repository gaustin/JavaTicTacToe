module TicTacToe
  module Tasks
    class DeleteGames
      def self.do(threshold_in_seconds)
        now = Time.now
        save_path = TicTacToe::DiskStore::GAME_DATA_DIR
        
        file_names = Dir.glob("#{save_path}/*")

        file_names.each do |file_name|
          difference_in_seconds = now - File.stat(file_name).mtime
          File.unlink(file_name) if difference_in_seconds >= threshold_in_seconds
        end
      end
    end
  end
end
