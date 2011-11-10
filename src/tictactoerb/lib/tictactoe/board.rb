include Java

Board = Java::tictactoe.game::Board

class Board
  def load_state(state)
    state.split(//).each_with_index do |mark, idx|
      char_mark = if mark == 'X'
        ?X
      elsif mark == 'O'
        ?O
      else
        0
      end
      mark_position(char_mark, idx)
    end
    self
  end

  def serialize_state
    spaces = get_spaces.reduce("") do |memo, c| c == 0 ? memo + '-' : memo + c.chr end
    spaces
  end
end