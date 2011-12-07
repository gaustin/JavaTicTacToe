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
require 'tictactoe/tasks'

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
      do_turn
      evaluate_turn      
      erb :player
    end

    post '/game/:game_id/:choice' do
      do_turn      
      evaluate_turn
      erb :player
    end

    before '/game/:game_id*' do
      if params[:game_id] == 'new'
        start_new_game
      else
        continue_saved_game  
      end
      @scorer = TicTacToeScorer.new(@board)
    end

    before '/game/:game_id/:choice' do
      @position = params[:choice].to_i
      @scorer = TicTacToeScorer.new(@board)
    end    

    after '/game/:game_id*' do
      State.update_game(@game_id, @board, @x_player, @o_player, @game.next_turn) if State.exists?(@game_id)
    end

    def do_turn
      turn_mark = Turn.attempt_all(@board, @scorer, @x_player, @o_player, @game.next_turn, @position) 
      if !turn_mark.nil?
        @game.next_turn = turn_mark
        clear_error
      else
        set_error "Invalid move."
      end
    end

    def evaluate_turn
      if @scorer.is_game_over
        set_message(@scorer.is_draw ? "The game was a draw!" : "#{@scorer.winner.chr} won!")
      end
    end

    def start_new_game
      @x_player = PlayerFactory.create(?X, PlayerMap.type_for(params[:x_player]), WebMessenger.new)
      @o_player = PlayerFactory.create(?O, PlayerMap.type_for(params[:o_player]), WebMessenger.new)
      @x_player.opponent = @o_player
      @board = Board.new(9)
      @game_id = State.save_game(@board, @x_player, @o_player, ?X)
      @game = State.load_game(@game_id)
    end

    def continue_saved_game
      @game_id = params[:game_id]
      @game = State.load_game(@game_id)
      @board = @game.board
      @x_player = @game.x_player
      @o_player = @game.o_player
    end

    run! if app_file == $0
  end
end
