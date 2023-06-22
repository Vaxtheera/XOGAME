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


Code Functionality:

1.Tictactoe(int boardSize): This is the constructor of the Tictactoe class. It initializes the game by setting up the GUI components, including the frame, panels, labels, and buttons. It also sets the board size and win condition based on the provided boardSize parameter. Finally, it calls the firstTurn() function to determine which player goes first.
2.actionPerformed(ActionEvent e): This function is triggered when a button is clicked in the game. It handles the player's move by checking which button was clicked, updating the corresponding cell with the player's symbol ('X' or 'O'), and checking for a win or draw condition. It also calls the handleButtonClick() and check() functions to handle the click event and check the game state, respectively.
3.firstTurn(): This function determines which player goes first by randomly selecting between player 1 ('X') and player 2 ('O'). It updates the textfield label to indicate the current player's turn.
4.check(): This function checks for a win or draw condition after each move. It iterates through the game board and checks for horizontal, vertical, and diagonal winning combinations for both 'X' and 'O'. If a win condition is met, it calls the respective xWins() or oWins() function to handle the game outcome. If no win condition is met and the board is full, it calls the draw() function.
5.xWins(int a, int b): This function is called when 'X' wins the game. It highlights the winning cells in green, disables the buttons to prevent further moves, updates the textfield label to indicate the winner, and displays a dialog box to ask if the players want to play again.
6.oWins(int a, int b): This function is similar to xWins(), but it handles the case when 'O' wins the game.
7.draw(): This function is called when the game ends in a draw. It disables the buttons, updates the textfield label to indicate a draw, inserts game data into the database, and displays a dialog box to ask if the players want to play again.
8.disableButtons(): This function disables all the buttons on the game board to prevent further moves.
9.handleButtonClick(int row, int col, String player): This function handles the button click event by adding the clicked position to the positionsList and playersList, which store the positions and corresponding players' moves.
10.restartGame(): This function resets the game state to start a new game. It enables all buttons, clears the button text and background color, clears the position and player lists, and resets the game-related variable



Java class named DatabaseConnector that handles database connectivity and data insertion using JDBC (Java Database Connectivity). 
It connects to a MySQL database, performs insert operations for game results and game moves.
Here's a breakdown of the code:

The class DatabaseConnector declares the necessary instance variables and constants for connecting to the database.
The constructor DatabaseConnector() establishes a connection to the database using the provided URL, username, and password. If the connection fails, an SQLException is caught.
The method insertData(String winner) inserts the winner's name into the game_result table. It uses a prepared statement with a parameterized query to avoid SQL injection. The method returns the generated game ID (auto-incremented primary key) of the inserted row.
The method insertDataGameReplay(List<String> positionsList, List<String> playersList, int boardSize, String winner) inserts game moves into the game_move table. It first calls insertData(String winner) to insert the game result and retrieve the game ID. Then, it iterates over the positions and players lists to insert each move using a prepared statement.




