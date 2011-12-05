include Java
require File.join(File.dirname(__FILE__), '..', '..', '..', '..', 'lib', 'tictactoe.jar')

ComputerPlayer = Java::tictactoe.players::ComputerPlayer
HumanPlayer = Java::tictactoe.players::HumanPlayer
MinimaxStrategy = Java::tictactoe.players.strategies::MinimaxStrategy
TicTacToeScorer = Java::tictactoe.game::TicTacToeScorer
Referee = Java::tictactoe.game::Referee
PlayerFactory = Java::tictactoe.players::PlayerFactory
PlayerTypes = Java::tictactoe.players::PlayerTypes
Board = Java::tictactoe.game::Board

