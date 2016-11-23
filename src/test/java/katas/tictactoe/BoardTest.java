package katas.tictactoe;

import static katas.tictactoe.Board.Column.LEFT;
import static katas.tictactoe.Board.Column.RIGHT;
import static katas.tictactoe.Board.Row.BOTTOM;
import static katas.tictactoe.Board.Row.TOP;
import static katas.tictactoe.Marker.O;
import static katas.tictactoe.Marker.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    BoardTest.DegenerateTests.class,
    BoardTest.PlaySquareTests.class,
    BoardTest.WinnerTests.class
})

public class BoardTest {
    public static class DegenerateTests {
        @Test(expected=java.lang.IllegalArgumentException.class)
        public void createBoardWithIncorrectRows_throwsIllegalArgumentException() {
            new Board(new Marker[][] {
                {null, null, null},
                {null, null, null}
            });
        }
        
        @Test(expected=java.lang.IllegalArgumentException.class)
        public void createBoardWithIncorrectColumns_throwsIllegalArgumentException() {
            new Board(new Marker[][] {
                {null, null},
                {null, null},
                {null, null}
            });
        }
    }
    
    public static class PlaySquareTests {
        private void assertSquareFilledAfterPlay(Board expected, Marker marker, Square square) {
            Board actual = new Board();
            actual.play(marker, square);
            assertThat(expected, equalTo(actual));
            assertThat(actual.getValue(square), equalTo(marker));
        }

        @Test(expected=katas.tictactoe.Board.SauareFilled.class)
        public void playFilledSquare_throwsSquareFilled() {
            Board b = new Board();
            b.play(X, new Square(TOP, LEFT));
            b.play(X, new Square(TOP, LEFT));            
        }
        
        @Test
        public void playSquare_fillsSquareOnBoard() {
            assertSquareFilledAfterPlay(new Board(new Marker[][] {
                {X, null, null},
                {null, null, null},
                {null, null, null}
            }), X, new Square(TOP, LEFT));

            assertSquareFilledAfterPlay(new Board(new Marker[][] {
                {null, null, null},
                {null, null, null},
                {null, null, O}
            }), O, new Square(BOTTOM, RIGHT));
        }
        
        @Test
        public void notAllSquaresFilled_boardIsNotFull() {
            Board b = new Board(new Marker[][] {
                {X, O, X},
                {X, O, X},
                {O, null, O}
            });
            assertThat(b.isFull(), equalTo(false));
        }
        
        @Test
        public void allSquaresFilled_boardIsFull() {
            Board b = new Board(new Marker[][] {
                {X, O, X},
                {X, O, X},
                {O, X, O}
            });
            assertThat(b.isFull(), equalTo(true));
        }
    }
    
    public static class WinnerTests {
        private void assertWinner(Board board) {
            assertThat(board.hasWinner(), equalTo(true));
        }

        private void assertNoWinner(Board board) {
            assertThat(board.hasWinner(), equalTo(false));
        }
        
        @Test
        public void noWinner() {
            assertNoWinner(new Board(new Marker[][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }));

            assertNoWinner(new Board(new Marker[][] {
                {X, O, null},
                {X, null, X},
                {null, O, O}
            }));

            assertNoWinner(new Board(new Marker[][] {
                {X, O, X},
                {X, O, X},
                {O, X, O}
            }));
        }
        
        @Test
        public void rowWinners() {
            assertWinner(new Board(new Marker[][] {
                {X, X, X},
                {null, null, null},
                {null, null, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, null, null},
                {X, X, X},
                {null, null, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, null, null},
                {null, null, null},
                {X, X, X}
            }));
            
            assertWinner(new Board(new Marker[][] {
                {O, O, O},
                {null, null, null},
                {null, null, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, null, null},
                {O, O, O},
                {null, null, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, null, null},
                {null, null, null},
                {O, O, O}
            }));
        }
        
        @Test
        public void columnWinners() {
            assertWinner(new Board(new Marker[][] {
                {X, null, null},
                {X, null, null},
                {X, null, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, X, null},
                {null, X, null},
                {null, X, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, null, X},
                {null, null, X},
                {null, null, X}
            }));

            assertWinner(new Board(new Marker[][] {
                {O, null, null},
                {O, null, null},
                {O, null, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, O, null},
                {null, O, null},
                {null, O, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {null, null, O},
                {null, null, O},
                {null, null, O}
            }));
        }
        
        @Test
        public void diagonalWinners() {
            assertWinner(new Board(new Marker[][] {
                {X, null, null},
                {null, X, null},
                {null, null, X}
            }));
            
            assertWinner(new Board(new Marker[][] {
                {null, null, X},
                {null, X, null},
                {X, null, null}
            }));

            assertWinner(new Board(new Marker[][] {
                {O, null, null},
                {null, O, null},
                {null, null, O}
            }));
            
            assertWinner(new Board(new Marker[][] {
                {null, null, O},
                {null, O, null},
                {O, null, null}
            }));
        }
        
        @Ignore
        @Test
        public void allSquaresFilled_boardIsFull() {
            
        }
    }
}
