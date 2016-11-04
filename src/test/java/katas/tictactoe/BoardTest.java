package katas.tictactoe;

import static katas.tictactoe.Player.O;
import static katas.tictactoe.Player.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoardTest {
    private void assertWinner(Board board) {
        assertThat(board.hasWinner(), equalTo(true));
    }

    private void assertNoWinner(Board board) {
        assertThat(board.hasWinner(), equalTo(false));
    }

    @Test
    public void noWinner() {
        assertNoWinner(new Board(new Player[][] {
            {null, null, null},
            {null, null, null},
            {null, null, null}
        }));

        assertNoWinner(new Board(new Player[][] {
            {X, O, null},
            {X, null, X},
            {null, O, O}
        }));

        assertNoWinner(new Board(new Player[][] {
            {X, O, X},
            {X, O, X},
            {O, X, O}
        }));
    }
    
    @Test
    public void rowWinners() {
        assertWinner(new Board(new Player[][] {
            {X, X, X},
            {null, null, null},
            {null, null, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, null, null},
            {X, X, X},
            {null, null, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, null, null},
            {null, null, null},
            {X, X, X}
        }));
        
        assertWinner(new Board(new Player[][] {
            {O, O, O},
            {null, null, null},
            {null, null, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, null, null},
            {O, O, O},
            {null, null, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, null, null},
            {null, null, null},
            {O, O, O}
        }));
    }
    
    @Test
    public void columnWinners() {
        assertWinner(new Board(new Player[][] {
            {X, null, null},
            {X, null, null},
            {X, null, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, X, null},
            {null, X, null},
            {null, X, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, null, X},
            {null, null, X},
            {null, null, X}
        }));

        assertWinner(new Board(new Player[][] {
            {O, null, null},
            {O, null, null},
            {O, null, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, O, null},
            {null, O, null},
            {null, O, null}
        }));

        assertWinner(new Board(new Player[][] {
            {null, null, O},
            {null, null, O},
            {null, null, O}
        }));
    }
    
    @Test
    public void diagonalWinners() {
        assertWinner(new Board(new Player[][] {
            {X, null, null},
            {null, X, null},
            {null, null, X}
        }));
        
        assertWinner(new Board(new Player[][] {
            {null, null, X},
            {null, X, null},
            {X, null, null}
        }));

        assertWinner(new Board(new Player[][] {
            {O, null, null},
            {null, O, null},
            {null, null, O}
        }));
        
        assertWinner(new Board(new Player[][] {
            {null, null, O},
            {null, O, null},
            {O, null, null}
        }));
    }
}
