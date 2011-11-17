require File.join(File.dirname(__FILE__), 'spec_helper')

describe "TicTacToe" do
  include Rack::Test::Methods

  def app
    TicTacToe::Game.set(:environment, :test)
    TicTacToe::Game.set(:run, false)
    TicTacToe::Game.set(:raise_errors, true)
    TicTacToe::Game.set(:logging, false)
    TicTacToe::Game
  end

  it "should respond with a form and button" do
    get '/'
    last_response.should be_ok
    body = last_response.body
    body.should include('form')
    body.should include('submit')
  end

  it "should respond with an empty board" do
    post '/game/new'
    last_response.should be_redirect

    get last_response["Location"]
    last_response.should be_ok
  end

end
