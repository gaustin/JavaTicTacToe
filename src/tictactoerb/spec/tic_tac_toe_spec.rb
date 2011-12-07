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

  it "should show the result of a Computer vs. Computer game" do
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}"

    location = last_response["Location"]
    get location

    last_response.body.should include("draw")
  end

  it "should allow two humans to play" do
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}" 

    location = last_response["Location"]
    game_id = get_game_id(location)

    post "#{location}/0"
    post "#{location}/1"

    game = TicTacToe::State.load_game(game_id)
    board = game.board
    board.mark_at(0).should == ?X
    board.mark_at(1).should == ?O
  end

  it "should create an empty game board" do
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}" 

    location = last_response["Location"]
    game_id = get_game_id(location)
    game = TicTacToe::State.load_game(game_id)
    all_null = game.board.spaces.all? { |mark| mark == 0 }  
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
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}" 

    game_url = last_response["Location"]
    post game_url + "/0"

    post game_url + "/0"
    last_response.body.should include("Invalid")
  end

  it "should clear the error for a new game" do
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}"

    game_url = last_response["Location"]
    post game_url + "/0"

    post game_url + "/0"
    last_response.body.should include("Invalid")
    
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}"
    last_response.body.should_not include("Invalid")
  end

  it "should clear an error when a valid move is made after an invalid one" do
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}"

    game_url = last_response["Location"]
    post "#{game_url}/0"
    post "#{game_url}/0"
    
    game_id = get_game_id(game_url)
    game = TicTacToe::State.load_game(game_id)
    choice = game.board.empty_spaces.first
    post "#{game_url}/#{choice}"
    last_response.body.should_not include("Invalid")
  end

  it "should indicate the game result and give a new game button" do
    post "/game/new?x_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::Human)}&o_player=#{TicTacToe::PlayerMap.string_for(PlayerTypes::MinimaxComputer)}"

    game_url = last_response["Location"]
    game_id = get_game_id(game_url)

    game = TicTacToe::State.load_game(game_id)
    board = game.board
    (0..1).each do |i|
      board.mark_position(?X, i)
    end
    TicTacToe::State.update_game(game_id, board, game.x_player, game.o_player, ?X)
    
    post "#{game_url}/2"
    last_response.body.should include("won!")
    last_response.body.should include("New Game")
  end

  def get_game_id(url)
    url.split("/").last
  end
end
