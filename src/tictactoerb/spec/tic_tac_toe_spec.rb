require File.join(File.dirname(__FILE__), 'spec_helper')

describe "TicTacToe" do
  include Rack::Test::Methods
  include Webrat::Methods
  include Webrat::Matchers

  def app
    TicTacToe.set(:environment, :test)
    TicTacToe.set(:run, false)
    TicTacToe.set(:raise_errors, true)
    TicTacToe.set(:logging, false)
    TicTacToe
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

  it "should mark an X in the first position and O should make a mark" do
    post '/game/new'
    last_response.should be_redirect

    post last_response["Location"], { :choice => 0 }
    last_response.should be_ok
  end

  it "should start a game" do
    visit '/'
    click_button 'New Game'
  end

  #TODO: Add a webrat test to play the entire game through.
end
