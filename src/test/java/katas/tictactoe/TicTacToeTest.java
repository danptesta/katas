package katas.tictactoe;

import static katas.tictactoe.Game.Player.O;
import static katas.tictactoe.Game.Player.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import katas.tictactoe.Game.Player;

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

        private Player[][] emptyBoard() {
            return new Player[3][3];
        }
        
        @Test
        public void newGame_boardIsEmpty() {
            assertThat(g.showBoard(), equalTo(emptyBoard()));
        }
        
        @Test(expected=katas.tictactoe.Game.OutOfTurn.class)
        public void playerOPlaysFirst_throwsOutOfTurn() {
            g.play(O, 0, 0);
        }
        
        @Test(expected=katas.tictactoe.Game.InvalidSquare.class)
        public void playNegativeColumn_throwsInvalidSquare() {
            g.play(X, 0, -1);
        }
        
        @Test(expected=katas.tictactoe.Game.InvalidSquare.class)
        public void playNegativeRow_throwsInvalidSquare() {
            g.play(X, -1, 0);
        }
        
        @Test(expected=katas.tictactoe.Game.InvalidSquare.class)
        public void playTooLargeRow_throwsInvalidSquare() {
            g.play(X, 3, 0);
        }
        
        @Test(expected=katas.tictactoe.Game.InvalidSquare.class)
        public void playTooLargeColumn_throwsInvalidSquare() {
            g.play(X, 0, 3);
        }
        
        @Test(expected=katas.tictactoe.Game.OutOfTurn.class)
        public void playConsecutiveTurns_throwsOutOfTurn() {
            g.play(X, 0, 0);
            g.play(X, 0, 1);
        }
        
        @Test
        public void oPlaysSecond() {
            g.play(X, 0, 0);
            g.play(O, 0, 1);
        }
        
        @Test(expected=katas.tictactoe.Game.SquareTaken.class)
        public void playTakenSquare_throwsSquareTaken() {
            g.play(X, 0, 0);
            g.play(O, 0, 0);
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
            Player[][] board = new Player[][] {
                {X, O, null},
                {null, null, null},
                {null, null, null}
            };
            
            g.play(X, 0, 0);
            g.play(O, 0, 1);
            
            assertThat(g.showBoard(), equalTo(board));
        }
        
        @Test
        public void playThreeXsInOneRow_winsGame() {
        }
    }
}
