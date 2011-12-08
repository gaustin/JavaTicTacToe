module TicTacToe
  class WebPlayerFactory
    def self.create(mark, player_type, messenger=nil)
      if player_type == PlayerTypes::WebHuman
        WebHuman.new(mark)
      else
        PlayerFactory.create(mark, player_type, messenger)
      end
    end
  end
end
