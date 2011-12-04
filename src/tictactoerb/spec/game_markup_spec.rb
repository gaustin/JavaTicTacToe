require File.join(File.dirname(__FILE__), 'spec_helper')

describe TicTacToe::GameMarkup do
  include TicTacToe::GameMarkup

  before :each do
    @board = Board.new(9)
  end

  it "should return a span with an X" do
    @board.mark_position(?X, 0)
    
    expected_markup = "<span class='marked_space'>X</span>"
    markup = markup_for_position(@board, 0, "game_id_is_nada")
    markup.should == expected_markup
  end

  it "should return a form and input button" do
    expected_markup = "<form method='POST' action='/game/game_id_is_nada/0'>" +
      "<input class='move_button' type='submit' value='-' />" +
      "</form>"

    markup = markup_for_position(@board, 0, "game_id_is_nada")
    markup.should == expected_markup
  end
end
