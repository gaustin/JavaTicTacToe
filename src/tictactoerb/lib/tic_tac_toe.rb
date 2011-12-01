require 'rubygems'
require 'sinatra/base'
$:.unshift(File.dirname(__FILE__))
require 'tictactoe/java_constants'
require 'tictactoe/disk_store'
require 'tictactoe/state'
require 'tictactoe/game_markup'
require 'tictactoe/error_handling'
require 'tictactoe/game_actions'

module TicTacToe
  class Web < Sinatra::Base
    helpers GameMarkup, ErrorHandling, GameActions
 
    set :sessions, true

    get '/' do
      clear_error
      erb :new_game
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
        erb :new_game
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
