module TicTacToe
  module GameMarkup
    def markup_for_position(board, position, game_id)
      choice = board.mark_at(position)
      if choice == 0
        "<form method='POST' action='/game/#{game_id}/#{position}'>" +
          "<input type='submit' value='-' />" +
        "</form>"
      else
        "<span>#{choice.chr}</span>"
      end
    end
  end
end
