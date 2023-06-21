/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author macvan
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Tictactoe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons;
    boolean player1_turn;
    int boardSize;
    int winCondition;
    boolean gameEnded = false;
    DatabaseConnector dbConnector;
    java.util.List<Point> positions = new ArrayList<>();
    java.util.List<String> positionsList = new ArrayList<>();
    java.util.List<String> playersList = new ArrayList<>();
    java.util.List<Integer> sizesList = new ArrayList<>();
    
    Tictactoe(int boardSize) {
        this.boardSize = boardSize;
        this.winCondition = boardSize;
        dbConnector = new DatabaseConnector();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100 * boardSize, 100 * boardSize);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 100 * boardSize, 100);

        button_panel.setLayout(new GridLayout(boardSize, boardSize));
        button_panel.setBackground(new Color(150, 150, 150));

        buttons = new JButton[boardSize * boardSize];
        for (int i = 0; i < boardSize * boardSize; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

   @Override
public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < boardSize * boardSize; i++) {
        if (e.getSource() == buttons[i]) {
            int row = i / boardSize;
            int col = i % boardSize;
            positions.add(new Point(row, col));

            if (player1_turn) {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setForeground(new Color(255, 0, 0));
                    buttons[i].setText("X");
                    player1_turn = false;
                    handleButtonClick(row, col, "x");
                    textfield.setText("O turn");
                    check(); // เพิ่มการเรียกใช้เมท็อด check() ที่นี่
                }
            } else {
                if (buttons[i].getText().equals("")) {
                    buttons[i].setForeground(new Color(0, 0, 255));
                    buttons[i].setText("O");
                    player1_turn = true;
                    handleButtonClick(row, col, "o");
                    textfield.setText("X turn");
                    check(); // เพิ่มการเรียกใช้เมท็อด check() ที่นี่
                }
            }
        }
    }
}


    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

public void check() {
    // Check X win conditions
    if (!positions.isEmpty()) {
        for (Point position : positions) {
        int row = position.x;
        int col = position.y;
        String symbol = buttons[row * boardSize + col].getText();
        String setPosition = "Symbol " + symbol + " is at position (" + row + ", " + col + ")";
        }
    }
    for (int i = 0; i < boardSize; i++) {
        // Horizontal wins
        for (int j = 0; j <= boardSize - winCondition; j++) {
            boolean xWin = true;
            for (int k = 0; k < winCondition; k++) {
                if (!buttons[i * boardSize + j + k].getText().equals("X")) {
                    xWin = false;
                    break;
                }
            }
            if (xWin) {
                xWins(i * boardSize + j, i * boardSize + j + winCondition - 1);
                return;
            }
        }

        // Vertical wins
        for (int j = 0; j <= boardSize - winCondition; j++) {
            boolean xWin = true;
            for (int k = 0; k < winCondition; k++) {
                if (!buttons[i + j * boardSize + k * boardSize].getText().equals("X")) {
                    xWin = false;
                    break;
                }
            }
            if (xWin) {
                xWins(i + j * boardSize, i + j * boardSize + (winCondition - 1) * boardSize);
                return;
            }
        }

        // Diagonal wins (top left to bottom right)
        for (int j = 0; j <= boardSize - winCondition; j++) {
            boolean xWin = true;
            for (int k = 0; k < winCondition; k++) {
                if (!buttons[i + k + j * (boardSize + 1)].getText().equals("X")) {
                    xWin = false;
                    break;
                }
            }
            if (xWin) {
                xWins(i + j * (boardSize + 1), i + j * (boardSize + 1) + (winCondition - 1) * (boardSize + 1));
                return;
            }
        }

        // Diagonal wins (top right to bottom left)
        for (int j = winCondition - 1; j < boardSize; j++) {
            boolean xWin = true;
            for (int k = 0; k < winCondition; k++) {
                if (!buttons[i + k + j * (boardSize - 1)].getText().equals("X")) {
                    xWin = false;
                    break;
                }
            }
            if (xWin) {
                xWins(i + j * (boardSize - 1), i + j * (boardSize - 1) + (winCondition - 1) * (boardSize - 1));
                return;
            }
        }
    }

    // Check O win conditions
    for (int i = 0; i < boardSize; i++) {
        // Horizontal wins
        for (int j = 0; j <= boardSize - winCondition; j++) {
            boolean oWin = true;
            for (int k = 0; k < winCondition; k++) {
                if (!buttons[i * boardSize + j + k].getText().equals("O")) {
                    oWin = false;
                    break;
                }
            }
            if (oWin) {
                oWins(i * boardSize + j, i * boardSize + j + winCondition - 1);
                return;
            }
        }

        // Vertical wins
        for (int j = 0; j <= boardSize - winCondition; j++) {
            boolean oWin = true;
            for (int k = 0; k < winCondition; k++) {
                if (!buttons[i + j * boardSize + k * boardSize].getText().equals("O")) {
                    oWin = false;
                    break;
                }
            }
            if (oWin) {
                oWins(i + j * boardSize, i + j * boardSize + (winCondition - 1) * boardSize);
                return;
            }
        }

        // Diagonal wins (top left to bottom right)
            for (int j = 0; j <= boardSize - winCondition; j++) {
        boolean xWin = true;
        boolean oWin = true;
        for (int k = 0; k < winCondition; k++) {
        if (!buttons[(i + k) * boardSize + (j + k)].getText().equals("X")) {
            xWin = false;
        }
        if (!buttons[(i + k) * boardSize + (j + k)].getText().equals("O")) {
            oWin = false;
        }
    }

    if (xWin) {
        xWins((i + j) * boardSize + j, (i + j + winCondition - 1) * boardSize + j + winCondition - 1);
        return;
    }
    if (oWin) {
        oWins((i + j) * boardSize + j, (i + j + winCondition - 1) * boardSize + j + winCondition - 1);
        return;
    }
}
      // Diagonal wins (top right to bottom left)
        for (int j = boardSize - winCondition; j >= 0; j--) {
            boolean xWin = true;
        boolean oWin = true;
        for (int k = 0; k < winCondition; k++) {
        if (!buttons[(i + k) * boardSize + (j + winCondition - k - 1)].getText().equals("X")) {
            xWin = false;
        }
        if (!buttons[(i + k) * boardSize + (j + winCondition - k - 1)].getText().equals("O")) {
            oWin = false;
        }
    }
    if (xWin) {
        xWins((i + j) * boardSize + (j + winCondition - 1), (i + j + winCondition - 1) * boardSize + j);
        return;
    }
    if (oWin) {
        oWins((i + j) * boardSize + (j + winCondition - 1), (i + j + winCondition - 1) * boardSize + j);
        return;
    }
}

    }

    // Check draw condition
    boolean draw = true;
    for (int i = 0; i < boardSize * boardSize; i++) {
        if (buttons[i].getText().equals("")) {
            draw = false;
            break;
        }
    }
    if (draw) {
        draw();
    }
}

public void xWins(int a, int b) {
    for (int i = a; i <= b; i++) {
        buttons[i].setBackground(Color.GREEN);
    }
    disableButtons();
    textfield.setText("X wins");
    String winner = "X";
    // dbConnector.insertData(winner);
    dbConnector.insertDataGameReplay(positionsList,playersList,boardSize,winner);
    int option = JOptionPane.showConfirmDialog(frame, "X wins!\n\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
     if (option == JOptionPane.YES_OPTION) {
         restartGame();
     } else {
         System.exit(0);
     }
    return;
}

public void oWins(int a, int b) {
    for (int i = a; i <= b; i++) {
        buttons[i].setBackground(Color.GREEN);
    }
    disableButtons();
    textfield.setText("O wins");
    String winner = "O";
    // dbConnector.insertData(winner);
//    System.out.println(playersList.size());
    dbConnector.insertDataGameReplay(positionsList,playersList,boardSize,winner);

    int option = JOptionPane.showConfirmDialog(frame, "O wins!\n\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
     if (option == JOptionPane.YES_OPTION) {
         restartGame();
     } else {
         System.exit(0);
     }
    return;
}



public void draw() {
    disableButtons();
    String winner = "draw";
    dbConnector.insertData(winner);
    dbConnector.insertDataGameReplay(positionsList,playersList,boardSize,winner);
    textfield.setText("Draw");
    int option = JOptionPane.showConfirmDialog(frame, "It's a draw!\n\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
        return;
}

public void disableButtons() {
    for (int i = 0; i < boardSize * boardSize; i++) {
        buttons[i].setEnabled(false);
    }
}
public void handleButtonClick(int row, int col,String player) {
    String setPosition = "is at position (" + row + ", " + col + ")";
    positionsList.add(setPosition);
    playersList.add(player); 
}
public void restartGame() {
    // รีเซ็ตสถานะเกมที่เกี่ยวข้อง
    player1_turn = true;
    gameEnded = false;
    textfield.setText("Tic-Tac-Toe");

    // รีเซ็ตตัวอักษรบนปุ่มในกริด
    for (JButton button : buttons) {
        button.setText("");
        button.setEnabled(true);
        button.setBackground(null);
    }

    // ล้างรายชื่อตำแหน่งที่บันทึกไว้
    positions.clear();
    positionsList.clear();
    playersList.clear();
    sizesList.clear();
}


}
