# Football-World-Cup-Score-Board
------------------------------------------------------
Implementation of a Live Football World Cup ScoreBoard
------------------------------------------------------
createGame - method to create a new game and add it to the scoreboard.
updateGame - method to update a game in progress with new scores.
endGame - method to end a game in progress and remove from the scoreboard.
getSummaryOfGames - method to get the summary of games in progress, ordered by their total score.
The games with the same total score will be returned ordered by the most recently started match in the scoreboard.
------------------------------------------------------
Java
TDD using Junit and Powermock
Maven
------------------------------------------------------
Added some code in the main method to output the scoreboard in the console, for the test data given in the assignment.

The code was added in the main method so that the reviewer can view the output by just running the Java application.

On running the java code, below details will be displayed in the console -
------------------------------------------
Score board of games - 
After creation - From recent to old
------------------------------------------
5. Argentina 0 - Australia 0
4. Uruguay 0 - Italy 0
3. Germany 0 - France 0
2. Spain 0 - Brazil 0
1. Mexico 0 - Canada 0
------------------------------------------
Score board of games - 
After score updation - From recent to old
------------------------------------------
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2
