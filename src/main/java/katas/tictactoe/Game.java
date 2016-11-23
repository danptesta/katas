package katas.tictactoe;

public class Game {    
    private Board board = new Board();
    private Marker lastPlayer = null;
    private Marker winner = null;

    public Board showBoard() {
        return board;
    }

    public void play(Square square) {
        assertGameNotOver();
        Marker marker = nextMarker();
        board.play(marker, square);
        lastPlayer = marker;
        checkWinner(marker);
    }

    private void assertGameNotOver() {
        if (getWinner() != null)
            throw new GameOver();
    }
    
    public Marker nextMarker() {
        return (lastPlayer != Marker.X) ? Marker.X : Marker.O;
    }

    private void checkWinner(Marker player) {
        if (board.hasWinner())
            winner = player;
    }

    public Marker getWinner() {
        return winner;
    }

    public class GameOver extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public Boolean isOver() {
        return (getWinner() != null || board.isFull());
    }
}
