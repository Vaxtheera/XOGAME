# XOGAME
test

This is a simple implementation of the Tic-Tac-Toe game written in Java. It allows two players to take turns marking spaces on a grid until one player wins or the game ends in a draw.

Prerequisites

Java Development Kit (JDK) installed on your system.

How to Run

1.Compile the source code using the following command: javac Tictactoe.java
2.Run the compiled Java class with the following command:java Tictactoe

Game Rules

1.The game is played on a square grid.
2.Two players take turns marking spaces on the grid, one using "X" and the other using "O".
3.The first player to get a continuous line of their symbol (horizontally, vertically, or diagonally) wins the game.
4.If all spaces on the grid are filled and no player has won, the game ends in a draw.

Gameplay

1.When you run the game, a GUI window will appear displaying the Tic-Tac-Toe grid.
2.The current player's turn is indicated at the top of the window.
3.Click on an empty space in the grid to mark it with the player's symbol.
4.The game will check for a winner or a draw after each move.
5.If a player wins or the game ends in a draw, a dialog box will appear asking if you want to play again.
6.Click "Yes" to restart the game.
7.Click "No" to exit the game.

Customization

You can customize the game by modifying the following parameters in the Tictactoe class:
boardSize: Set the size of the game board (e.g., 3 for a 3x3 grid).
winCondition: Set the number of symbols required to win the game (e.g., 3 for three-in-a-row).

Database Integration

The code includes a DatabaseConnector class for connecting to a database. It currently contains commented out lines for inserting data into the database. You can uncomment these lines and modify the database connection settings according to your needs.

Please note that you need to have a compatible database set up and the necessary JDBC driver in your classpath for the database integration to work properly.

License

This project is licensed under the MIT License.





