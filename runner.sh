#!/bin/bash

if [ -f "build/ticTacToe/UI/TicTacToe.class" ]
then
 echo "Running TicTacToe"
else
 echo "Compiling TicTacToe"
 eval "ant compile"
fi


java -cp build ticTacToe.UI.TicTacToe
