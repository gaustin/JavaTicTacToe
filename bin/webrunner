#!/bin/bash

if [ -f "lib/tictactoe.jar" ]
then
 echo "Running TicTacToe"
else
 echo "Compiling TicTacToe"
 eval "ant build-jar"
fi

jruby src/tictactoerb/lib/tic_tac_toe.rb