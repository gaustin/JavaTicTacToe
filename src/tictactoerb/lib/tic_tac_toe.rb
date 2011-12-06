require 'rubygems'
require 'sinatra/base'
$:.unshift(File.dirname(__FILE__))
require 'tictactoe/java_constants'
require 'tictactoe/disk_store'
require 'tictactoe/state'
require 'tictactoe/game_markup'
require 'tictactoe/user_messaging'
require 'tictactoe/turn'
require 'tictactoe/player_map'
require 'tictactoe/game'
require 'tictactoe/web_messenger'

module TicTacToe
  class Web < Sinatra::Base
    helpers GameMarkup, UserMessaging
 
    set :sessions, true

    get '/' do
      clear_message
      clear_error
      erb :new_game
    end

    post '/game/new' do
      clear_message
      clear_error
      redirect "/game/#{@game_id}"
    end

    get '/game/:game_id' do
     erb :player
    end

    post '/game/:game_id/:choice' do
      if Turn.perform(@board, @position, @scorer, @player, @game.next_turn)  
        clear_error
      else
        set_error "Invalid move."
      end
  
      if @scorer.is_game_over
        set_message(@scorer.is_draw ? "The game was a draw!" : "#{@scorer.winner.chr} won!")
        State.delete(@game_id)
      end
      erb :player
    end

    before '/game/:game_id*' do
      if params[:game_id] == 'new'
        start_new_game
      else
        continue_saved_game  
      end
    end

    before '/game/:game_id/:choice' do
      @position = params[:choice].to_i
      @scorer = TicTacToeScorer.new(@board)
    end    

    after '/game/:game_id/:choice' do
      next_turn = @game.next_turn == 'X' ? 'O' : 'X'
      State.update_game(@game_id, @board, @player, @player.opponent, next_turn) if State.exists?(@game_id)
    end

    def start_new_game
      @player = PlayerFactory.create(?X, PlayerMap.type_for(params[:x_player]), nil)
      @player.opponent = PlayerFactory.create(?O, PlayerMap.type_for(params[:o_player]), nil)
      @board = Board.new(9)
      @game_id = State.save_game(@board, @player, @player.opponent)
    end

    def continue_saved_game
      @game_id = params[:game_id]
      @game = State.load_game(@game_id)
      @board = @game.board
      @player = @game.x_player
    end

    run! if app_file == $0
  end
end
