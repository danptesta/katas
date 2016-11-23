package katas.tictactoe;

import static katas.tictactoe.Board.Column.LEFT;
import static katas.tictactoe.Board.Column.RIGHT;
import static katas.tictactoe.Board.Row.BOTTOM;
import static katas.tictactoe.Board.Row.TOP;
import static katas.tictactoe.Marker.O;
import static katas.tictactoe.Marker.X;
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
    GameTest.DegenerateTests.class,
    GameTest.GoldTests.class
})

public class GameTest {
    public static class DegenerateTests {
        private Game g;

        @Before
        public void setUp() {
            g = new Game();
        }

        private void playSquare(Row row, Column column) {
            g.play(new Square(row, column));
        }

        @Test
        public void newGame_boardIsEmpty() {
            assertThat(g.showBoard(), equalTo(new Board()));
        }
        
        @Test
        public void playGame_playerXGoesFirst() {
            assertThat(g.nextMarker(), equalTo(X));
        }
        
        @Test
        public void play_changesNextMarker() {
            assertThat(g.nextMarker(), equalTo(X));
            playSquare(TOP, LEFT);
            assertThat(g.nextMarker(), equalTo(O));
            playSquare(TOP, Column.MIDDLE);
            assertThat(g.nextMarker(), equalTo(X));
        }
        
        @Test
        public void newGame_hasNoWinner() {
            assertThat(g.getWinner(), equalTo(null));
        }
        
        @Test
        public void startGame_gameIsNotOver() {
            assertThat(g.isOver(), equalTo(false));
        }
    }

    public static class GoldTests {
        private Game g;

        @Before
        public void setUp() {
            g = new Game();
        }

        private void playSquare(Row row, Column column) {
            g.play(new Square(row, column));
        }

        @Test
        public void XWins_returnsWinnerX() {
            playSquare(TOP, LEFT);
            playSquare(Row.MIDDLE, LEFT);
            playSquare(TOP, Column.MIDDLE);
            playSquare(Row.MIDDLE, Column.MIDDLE);
            playSquare(TOP, RIGHT);
            assertThat(g.getWinner(), equalTo(X));
        }
        
        @Test
        public void OWins_returnsWinnerO() {
            playSquare(TOP, LEFT);
            playSquare(Row.MIDDLE, LEFT);
            playSquare(TOP, Column.MIDDLE);
            playSquare(Row.MIDDLE, Column.MIDDLE);
            playSquare(Row.BOTTOM, RIGHT);
            playSquare(Row.MIDDLE, RIGHT);
            assertThat(g.getWinner(), equalTo(O));
        }
        
        @Test(expected=katas.tictactoe.Game.GameOver.class)
        public void playAfterGameWon_throwsGameOver() {
            playSquare(TOP, LEFT);
            playSquare(Row.MIDDLE, LEFT);
            playSquare(TOP, Column.MIDDLE);
            playSquare(Row.MIDDLE, Column.MIDDLE);
            playSquare(TOP, RIGHT);            
            playSquare(Row.MIDDLE, RIGHT);
        }
        
        @Test
        public void whenGameWon_gameIsOver() {
            playSquare(TOP, LEFT);
            playSquare(Row.MIDDLE, LEFT);
            playSquare(TOP, Column.MIDDLE);
            playSquare(Row.MIDDLE, Column.MIDDLE);
            playSquare(TOP, RIGHT);            
            assertThat(g.isOver(), equalTo(true));
        }
        
        @Test
        public void whenAllSquaresFilled_gameIsOver() {
            playSquare(TOP, LEFT);
            playSquare(TOP, RIGHT);
            playSquare(TOP, Column.MIDDLE);
            playSquare(Row.MIDDLE, LEFT);
            playSquare(Row.MIDDLE, Column.RIGHT);
            playSquare(Row.MIDDLE, Column.MIDDLE);
            playSquare(BOTTOM, Column.LEFT);
            playSquare(BOTTOM, Column.MIDDLE);
            playSquare(BOTTOM, Column.RIGHT);
            assertThat(g.isOver(), equalTo(true));
        }
    }
}
