package katas.tictactoe;

public class Game {
    public static enum Player {
        X("X"), O("O");
        
        String value;
        Player(String value) {
            this.value = value;
        }
        
        public String toString() {
            return value.toString();
        }
    }
    private Player[][] board = new Player[3][3];

    public Player[][] showBoard() {
        return board;
    }
    
    Player lastPlayer = null;

    public void play(Player player, int row, int column) {
        assertPlayingInTurn(player);
        assertPlayingValidSquare(row, column);
        assertPlayingAvailableSquare(row, column);
        
        board[row][column] = player;
        lastPlayer = player;
    }

    private void assertPlayingAvailableSquare(int row, int column) {
        if (board[row][column] != null)
            throw new SquareTaken();
    }

    private void assertPlayingValidSquare(int row, int column) {
        if (invalidPosition(row) || invalidPosition(column))
            throw new InvalidSquare();
    }

    private void assertPlayingInTurn(Player player) {
        if (playerOStarting(player) || samePlayerPlayingConsecutively(player))
            throw new OutOfTurn();
    }

    private boolean invalidPosition(int position) {
        return position < 0 || position > 2;
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

    public class InvalidSquare extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public class OutOfTurn extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public class SquareTaken extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
}
