include Java
require File.join(File.dirname(__FILE__), '..', '..', '..', 'lib', 'tictactoe.jar')
require 'rubygems'
require 'sinatra/base'
$:.unshift(File.dirname(__FILE__))
require 'tictactoe/game_keeper'

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

module TicTacToe
  class Game < Sinatra::Base
    include TicTacToe::GameKeeper
    set :sessions, true
    def initialize
      super
    end

    get '/' do
      erb :index
    end

    post '/game/new' do
      reset
      save(Board.new(9).serialize_state)
      redirect "/game"
    end

    get '/game' do
      @board = load_board
      erb :player
    end

    post '/game/:choice' do
      position = params[:choice].to_i
      @board = load_board

      @scorer = TicTacToeScorer.new(@board)

      if valid_move?(@board, ?X, position) && !@scorer.is_game_over
        @board.mark_position(?X, position)
        @board.mark_position(?O, opponent.get_choice(@board)) unless @scorer.is_game_over
        clear_error
      else
        set_error "Invalid move."
      end

      save(@board.serialize_state)

      if @scorer.is_game_over
        erb :game_over
      else
        erb :player
      end
    end

    def reset
      clear_error
      clear
    end

    def load_board
      board = Board.new(9)
      state = retrieve
      board.load_state(state)
      board
    end

    def valid_move?(board, mark, choice)
      Referee.new.validate_move(board, mark, choice)
    end

    def opponent
      player = HumanPlayer.new(?X)
      opponent = ComputerPlayer.new(?O, MinimaxStrategy.new)
      opponent.set_opponent(player)
      player.set_opponent(opponent)
      opponent
    end

    def set_error(message)
      session[:error_statement] = message
    end

    def clear_error
      set_error nil
    end

    helpers do
      def markup_for_position(board, position)
        choice = board.mark_at(position)
        if choice == 0
          "<form method='POST' action='/game/#{position}'>" +
            "<input type='submit' value='-' />" +
          "</form>"
        else
          "<span>#{choice.chr}</span>"
        end
      end
    end

    run! if app_file == $0
  end
end
