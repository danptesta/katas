package katas.tictactoe;

import java.util.Arrays;

import katas.tictactoe.Board.Column;
import katas.tictactoe.Board.Row;

public class Board {
    public static enum Column {
        LEFT(0), MIDDLE(1), RIGHT(2);
        
        private int index;
        Column(int index) {
            this.index = index;
        }
    }
    
    public static enum Row {
        TOP(0), MIDDLE(1), BOTTOM(2);
        
        private int index;
        Row(int index) {
            this.index = index;
        }
    }

    private Player[][] grid;
    
    public Board() {
        this(new Player[3][3]);
    }
    
    public Board(Player[][] grid) {
        assertGrid(grid);
        this.grid = grid;
    }

    private void assertGrid(Player[][] grid) {
        assertRows(grid);
        assertColumns(grid);
    }

    private void assertColumns(Player[][] grid) {
        for (Player[] row : grid)
            if (row.length != 3)
                throw new IllegalArgumentException("invalid number of columns in grid");
    }

    private void assertRows(Player[][] grid) {
        if (grid.length != 3)
            throw new IllegalArgumentException("invalid number of rows in grid");
    }

    void playSquare(Player player, Row row, Column column) {
        assertSquareVacant(row, column);
        occupySquare(player, row, column);
    }

    private void occupySquare(Player player, Row row, Column column) {
        grid[row.index][column.index] = player;
    }

    private void assertSquareVacant(Row row, Column column) {
        if (grid[row.index][column.index] != null)
            throw new SauareOccupied();
    }

    public boolean hasWinner() {
        if (hasRowWinner())
            return true;
        if (hasColumnWinner())
            return true;
        if (hasDiagonalWinner())
            return true;
        return false;
    }

    private boolean hasDiagonalWinner() {
        return isCenterOccupied() &&
                (isLeftDiagonalWinner() || isRightDiagonalWinner());
    }

    private boolean isCenterOccupied() {
        return grid[1][1] != null;
    }

    private boolean isRightDiagonalWinner() {
        return grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0];
    }

    private boolean isLeftDiagonalWinner() {
        return grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2];
    }

    private boolean hasRowWinner() {
        for (Player[] row : grid)
            if (isRowWinner(row))
                return true;
        return false;
    }

    private boolean isRowWinner(Player[] row) {
        return row[0] != null && row[0] == row[1] && row[0] == row[2];
    }

    private boolean hasColumnWinner() {
        for (int column = 0; column < grid.length; column++)
            if (isColumnWinner(column))
                return true;
        return false;
    }

    private boolean isColumnWinner(int column) {
        return grid[0][column] != null && grid[0][column] == grid[1][column] && grid[0][column] == grid[2][column];
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(grid);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;
        if (!Arrays.deepEquals(grid, other.grid))
            return false;
        return true;
    }

    public class SauareOccupied extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
}