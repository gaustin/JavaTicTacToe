require File.join(File.dirname(__FILE__), 'spec_helper')

describe "TicTacToe" do
  include Rack::Test::Methods
  include TicTacToe::State

  def app
    TicTacToe::Game.set(:environment, :test)
    TicTacToe::Game.set(:run, false)
    TicTacToe::Game.set(:raise_errors, true)
    TicTacToe::Game.set(:logging, false)
    TicTacToe::Game
  end

  before :each do
    delete_all
  end

  it "should create an empty game board" do
    post '/game/new'

    location = last_response["Location"]
    game_id = location.split("/").last
    board = load_board(game_id)
    all_null = board.get_spaces.all? { |mark| mark == 0 }  
    all_null.should be_true
  end

  it "should respond with a form and button" do
    get '/'
    last_response.should be_ok
    body = last_response.body
    body.should include('form')
    body.should include('submit')
  end

  it "should set an error on an invalid move" do
    pending
  end

end
