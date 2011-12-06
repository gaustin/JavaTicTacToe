module TicTacToe
  class WebMessenger
    java_implements Java::tictactoe.game::IMessenger

    def getMoveFromPlayer(player)
       raise ArgumentError, "Deferred For Humans"
    end
  end
end
