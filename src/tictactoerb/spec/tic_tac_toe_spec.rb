require File.join(File.dirname(__FILE__), 'spec_helper')

describe "TicTacToe" do
  include Rack::Test::Methods

  def app
    TicTacToe::Web.set(:environment, :test)
    TicTacToe::Web.set(:run, false)
    TicTacToe::Web.set(:raise_errors, true)
    TicTacToe::Web.set(:logging, false)
    TicTacToe::Web
  end

  before :each do
    TicTacToe::State.delete_all
  end

  it "should create an empty game board" do
    post '/game/new'

    location = last_response["Location"]
    game_id = get_game_id(location)
    board = TicTacToe::State.load_board(game_id)
    all_null = board.get_spaces.all? { |mark| mark == 0 }  
    all_null.should be_true
  end

  it "should respond with a form and button" do
    get '/'
    last_response.should be_ok
    body = last_response.body
    body.should include('form')
    body.should include('submit')
    body.should include('New Game')
  end

  it "should set an error on an invalid move" do
    post '/game/new'

    game_url = last_response["Location"]
    post game_url + "/0"

    post game_url + "/0"
    last_response.body.should include("Invalid")
  end

  it "should clear an error when a valid move is made after an invalid one" do
    post '/game/new'

    game_url = last_response["Location"]
    post "#{game_url}/0"
    post "#{game_url}/0"
    
    game_id = get_game_id(game_url)
    board = TicTacToe::State.load_board(game_id)
    choice = board.empty_spaces.first
    post "#{game_url}/#{choice}"
    last_response.body.should_not include("Invalid")
  end

  it "should indicate the game result" do
    post '/game/new'

    game_url = last_response["Location"]
    game_id = get_game_id(game_url)

    board = TicTacToe::State.load_board(game_id)
    (0..1).each do |i|
      board.mark_position(?X, i)
    end
    TicTacToe::State.update_board(game_id, board)
    
    post "#{game_url}/2"
    last_response.body.should include("won!")
  end

  def get_game_id(url)
    url.split("/").last
  end
end
