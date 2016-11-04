package katas.tictactoe;

import static katas.tictactoe.Board.Column.LEFT;
import static katas.tictactoe.Board.Column.RIGHT;
import static katas.tictactoe.Player.O;
import static katas.tictactoe.Player.X;
import static katas.tictactoe.Board.Row.TOP;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
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
        
        @Test
        public void newGame_hasNoWinner() {
            assertThat(g.getWinner(), equalTo(null));
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
            g.play(X, TOP, LEFT);
            g.play(O, TOP, Column.MIDDLE);
            assertThat(g.showBoard(), equalTo(new Board(new Player[][] {
                {X, O, null},
                {null, null, null},
                {null, null, null}
            })));
        }
        
        @Test
        public void XWinsRow_returnsWinnerX() {
            g.play(X, TOP, LEFT);
            g.play(O, Row.MIDDLE, LEFT);
            g.play(X, TOP, Column.MIDDLE);
            g.play(O, Row.MIDDLE, Column.MIDDLE);
            g.play(X, TOP, RIGHT);            
            assertThat(g.getWinner(), equalTo(X));
        }
        
        @Test
        public void OWinsRow_returnsWinnerO() {
            g.play(X, TOP, LEFT);
            g.play(O, Row.MIDDLE, LEFT);
            g.play(X, TOP, Column.MIDDLE);
            g.play(O, Row.MIDDLE, Column.MIDDLE);
            g.play(X, Row.BOTTOM, RIGHT);
            g.play(O, Row.MIDDLE, RIGHT);
            assertThat(g.getWinner(), equalTo(O));
        }
        
        @Test(expected=katas.tictactoe.Game.GameOver.class)
        public void playAfterGameWon_throwsGameOver() {
            g.play(X, TOP, LEFT);
            g.play(O, Row.MIDDLE, LEFT);
            g.play(X, TOP, Column.MIDDLE);
            g.play(O, Row.MIDDLE, Column.MIDDLE);
            g.play(X, TOP, RIGHT);            
            g.play(O, Row.MIDDLE, RIGHT);
        }
    }
}
