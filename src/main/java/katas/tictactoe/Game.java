package katas.tictactoe;

public class Game {    
    public class GameOver extends RuntimeException {

    }

    private Board board = new Board();

    public Board showBoard() {
        return board;
    }
    
    Player lastPlayer = null;
    private Player winner = null;

    public void play(Player player, Board.Row row, Board.Column column) {
        assertGameNotOver();
        assertPlayingInTurn(player);
        board.playSquare(player, row, column);
        lastPlayer = player;
        checkWinner(player);
    }

    private void assertGameNotOver() {
        if (winner != null)
            throw new GameOver();
    }

    private void checkWinner(Player player) {
        if (board.hasWinner())
            winner = player;
    }

    private void assertPlayingInTurn(Player player) {
        if (playerOStarting(player) || samePlayerPlayingConsecutively(player))
            throw new OutOfTurn();
    }

    private boolean samePlayerPlayingConsecutively(Player player) {
        return player == lastPlayer;
    }

    private boolean playerOStarting(Player player) {
        return !gameStarted() && player == Player.O;
    }

    private boolean gameStarted() {
        return lastPlayer != null;
    }

    public class OutOfTurn extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public Player getWinner() {
        return winner;
    }
}
