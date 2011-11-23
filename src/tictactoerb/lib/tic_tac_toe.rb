include Java
require File.join(File.dirname(__FILE__), '..', '..', '..', 'lib', 'tictactoe.jar')
require 'rubygems'
require 'sinatra/base'
$:.unshift(File.dirname(__FILE__))
require 'tictactoe/disk_store'
require 'tictactoe/state'
require 'tictactoe/game_markup'
require 'tictactoe/error_handling'
require 'tictactoe/rules_helper'

ComputerPlayer = Java::tictactoe.players::ComputerPlayer
HumanPlayer = Java::tictactoe.players::HumanPlayer
MinimaxStrategy = Java::tictactoe.players.strategies.MinimaxStrategy
TicTacToeScorer = Java::tictactoe.game::TicTacToeScorer
Referee = Java::tictactoe.game::Referee
PlayerFactory = Java::tictactoe.players::PlayerFactory
PlayerTypes = Java::tictactoe.players::PlayerTypes
Board = Java::tictactoe.game::Board

#TODOS:
# Test error setting and clearing
# Factor out constants into separate module

module TicTacToe
  class Web < Sinatra::Base
    helpers GameMarkup, ErrorHandling, RulesHelper
 
    set :sessions, true

    def initialize
      super
    end

    get '/' do
      erb :index
    end

    post '/game/new' do
      clear_error
      redirect "/game/#{@game_id}"
    end

    get '/game/:game_id' do
     erb :player
    end

    post '/game/:game_id/:choice' do
      if perform_turn(@board, player, @position, @scorer)  
        clear_error
      else
        set_error "Invalid move."
      end

      if @scorer.is_game_over
        erb :game_over
      else
        erb :player
      end
    end

    before '/game/:game_id*' do
      if params[:game_id] == 'new'
        @board = Board.new(9)
        @game_id = State.save_board(@board)
      else
        @game_id = params[:game_id]
        @board = State.load_board(@game_id)
      end
    end

    before '/game/:game_id/:choice' do
      @position = params[:choice].to_i
      @scorer = TicTacToeScorer.new(@board)
    end    

    after '/game/:game_id/:choice' do
      State.update_board(@game_id, @board)
    end

    run! if app_file == $0
  end
end
