# javasudoku

Documentataion:

Sudoku game(lately game) is a progmram written in java and it can be executed in VS code or in GDB online compiler.
Game is classical sudoku representation in terminal with features that allow to track spent time or quit during the game.

How to play?
First of all player should choose level of difficulty(easy medium hard) after that game provides 9*9 grid with some filled cells
ans asks to write an input in form(row column digit). Then game checks if the input was correct else it will tell why it's not correct
which is very convinient because player can see why he made mistake. In such mannner game continues until every cell is filled or player quit 
the game using 0 0 0 input which will bring hin to the main menu. If player won then he can see how much time he spent on that and play again if he wants

Design
Frnakly speaking   i had done simular project but in cpp and took the grid design from there but that cpp project was originally designed by me
equal signs that divide every three rows and double straight slash characters to divide every three columns that was original idea.

About code
As before i used 9*9 arrays in java to write and save model(answer sheet) and arr(player's inputs) also i used loops to show save and check the grids the 
rest data is simply int long numbers for inputs or char for (y/n) question. Code itself is basically (func, func... main{func, func}) it means that we first write
helping fucntions such as consolecleaner or questionasker then we write short main method in class Main with queston and win factor checking script(if 405 is sum of all 
arr cells then player whon else continue). There is a few recursing points where functions do self-reference e.g. play again at the end or level choosing at the begining 

Challenges
Mostly i didn't encounter challlenges with code because already had expirience but struggled to push in github from first try (i deleted 4 repos in that process) 
But with hope and will i finished this project

