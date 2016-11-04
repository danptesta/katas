package katas.tictactoe;

import static katas.tictactoe.Board.Column.LEFT;
import static katas.tictactoe.Player.O;
import static katas.tictactoe.Player.X;
import static katas.tictactoe.Board.Row.TOP;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import katas.tictactoe.Board.Column;
import katas.tictactoe.Board.Row;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TicTacToeTest.DegenerateTests.class,
    TicTacToeTest.GoldTests.class
})

public class TicTacToeTest {
    public static class DegenerateTests {
        private Game g;

        @Before
        public void setUp() {
            g = new Game();
        }

        @Test
        public void newGame_boardIsEmpty() {
            assertThat(g.showBoard(), equalTo(new Board()));
        }
        
        @Test(expected=katas.tictactoe.Game.OutOfTurn.class)
        public void playerOPlaysFirst_throwsOutOfTurn() {
            g.play(O, TOP, LEFT);
        }
        
        @Test(expected=katas.tictactoe.Game.OutOfTurn.class)
        public void playConsecutiveTurns_throwsOutOfTurn() {
            g.play(X, TOP, LEFT);
            g.play(X, TOP, Column.MIDDLE);
        }
        
        @Test(expected=katas.tictactoe.Board.SauareOccupied.class)
        public void playOccupiedSquare_throwsSquareOccupied() {
            g.play(X, TOP, LEFT);
            g.play(O, TOP, LEFT);
        }
    }

    public static class GoldTests {
        private Game g;

        @Before
        public void setUp() {
            g = new Game();
        }

        @Test
        public void playValidSquare_returnsBoardWithSquareFilled() {
            Board board = new Board(new Player[][] {
                {X, O, null},
                {null, null, null},
                {null, null, null}
            });
            
            g.play(X, TOP, LEFT);
            g.play(O, TOP, Column.MIDDLE);
            
            assertThat(g.showBoard(), equalTo(board));
        }
    }
}
