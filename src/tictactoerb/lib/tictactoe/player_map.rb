module TicTacToe
  class PlayerMap
    STRING_PLAYER_TYPE = {
      "0" => PlayerTypes::MinimaxComputer,
      "1" => PlayerTypes::WebHuman
    }

    CLASS_STRING = {
      WebHuman => "1",
      ComputerPlayer => "0"
    }

    def self.type_for(string)
      STRING_PLAYER_TYPE[string]
    end

    def self.string_for(player_type)
      STRING_PLAYER_TYPE.invert[player_type]
    end

    def self.string_for_class(klass)
      CLASS_STRING[klass]
    end
  end
end
