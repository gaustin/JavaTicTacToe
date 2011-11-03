#!/bin/bash

if [ -f "build/tictactoe/ui/TicTacToe.class" ]
then
 echo "Running TicTacToe"
else
 echo "Compiling TicTacToe"
 eval "ant compile"
fi

# pass all arguments on to TTT
java -cp build tictactoe.ui.TicTacToe $@
