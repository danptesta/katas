package katas.tictactoe;

import java.io.Console;
import java.util.Locale;

import katas.tictactoe.Board.Column;
import katas.tictactoe.Board.Row;

/*
 * Console:  Welcome to Tic Tac Toe!
 * Console:  Please select your marker (X or O):
 * User:  Selects X
 * Console:  Create new game (User = Player X, Computer = Player O)
 * Console:  Show board to User, prompt to play
 * User:  Selects a square to play
 * Console:  Play game (User = Player X, square)
 * Console:  If game over, display results 
 * Console:  Else Show board to computer, prompt to play
 * Computer:      Selects a square to play
 * Console:       Play game (Computer = Player O, row, column)
 */

/*
 * Start Game
 * ----------
 * Console:  Welcome to Tic Tac Toe!
 * Console:  Create new game
 * 
 * Play Turn
 * ---------
 * Console:  Show board to User1, prompt to play
 * User1:  Selects a square to play
 * Console:  Play game (Marker X, selected square)
 * Console:  If game over, display results 
 * Console:  Else play next turn
 */
public class GameController {
    Console console = null;
    Game game = null;
    
    public GameController() {
        game = new Game();
        initializeConsole();
    }

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.setupGame();
        controller.playGame();
    }

    private void printWinner() {
        printBoard();
        System.out.println("");
        System.out.println("=======================");
        if(game.getWinner() != null)
            System.out.printf("Game Over.  %s is the winner!\n", game.getWinner());
        else
            System.out.println("Game Over.  Game is a draw (no winner).");
    }

    private void playGame() {
        do {
            try {
                playTurn();
            } catch (Board.SauareFilled e) {
                System.out.println("\nSorry, that square is already taken.  Please try again.");
            }
        } while(!game.isOver());
        printWinner();
    }

    private void playTurn() {
        printBoard();
        System.out.println("");
        game.play(selectSquare());
    }

    private Square selectSquare() {
        return new Square(selectRow(), selectColumn());
    }

    private void printBoard() {
        Board board = game.showBoard();
        System.out.println("");
        printRow(board, Row.TOP);
        printRowDivider();
        printRow(board, Row.MIDDLE);
        printRowDivider();
        printRow(board, Row.BOTTOM);
    }

    private void printRowDivider() {
        System.out.println("-----------");
    }

    private void printRow(Board board, Row row) {
        System.out.printf(" %s | %s | %s \n",
                getValue(board, new Square(row, Column.LEFT)),
                getValue(board, new Square(row, Column.MIDDLE)),
                getValue(board, new Square(row, Column.RIGHT))
                );
    }
    
    private String getValue(Board board, Square square) {
        Marker marker = board.getValue(square);
        return (marker != null) ? marker.toString() : " ";
    }

    private Row selectRow() {
        Row row = null;
        do {
            try {
                System.out.print("Please select a row (top, middle, bottom): ");
                row = Row.valueOf(console.readLine().trim().toUpperCase(Locale.US));            
            } catch(IllegalArgumentException e) {
                System.out.println("Invalid choice, please try again.");
            }
        } while (row == null);
        return row;
    }

    private Column selectColumn() {
        Column column = null;
        do {
            try {
                System.out.print("Please select a column (left, middle, right): ");
                column = Column.valueOf(console.readLine().trim().toUpperCase(Locale.US));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice, please try again.");
            }
        } while (column == null);
        return column;
    }

    private void setupGame() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("=======================");
    }

    private void initializeConsole() {
        console = System.console();
        if (console == null) {
            System.out.println("No console: not in interactive mode!");
            System.exit(0);
        }
    }
}
