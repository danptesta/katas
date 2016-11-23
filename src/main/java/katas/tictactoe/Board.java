package katas.tictactoe;

import java.util.Arrays;

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

    private Marker[][] grid;
    
    public Board() {
        this(new Marker[3][3]);
    }
    
    public Board(Marker[][] grid) {
        assertGrid(grid);
        this.grid = grid;
    }

    private void assertGrid(Marker[][] grid) {
        assertRows(grid);
        assertColumns(grid);
    }

    private void assertColumns(Marker[][] grid) {
        for (Marker[] row : grid)
            if (row.length != 3)
                throw new IllegalArgumentException("invalid number of columns in grid");
    }

    private void assertRows(Marker[][] grid) {
        if (grid.length != 3)
            throw new IllegalArgumentException("invalid number of rows in grid");
    }

    void play(Marker marker, Square square) {
        assertSquareEmpty(square);
        fillSquare(marker, square);
    }

    private void assertSquareEmpty(Square square) {
        if (grid[square.getRow().index][square.getColumn().index] != null)
            throw new SauareFilled();
    }

    private void fillSquare(Marker marker, Square square) {
        grid[square.getRow().index][square.getColumn().index] = marker;
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
        for (Marker[] row : grid)
            if (isRowWinner(row))
                return true;
        return false;
    }

    private boolean isRowWinner(Marker[] row) {
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

    public class SauareFilled extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    @Override
    public String toString() {
        return "Board [grid=" + Arrays.deepToString(grid) + "]";
    }

    public Marker getValue(Square square) {
        return grid[square.getRow().index][square.getColumn().index];
    }

    public Boolean isFull() {
        for(Marker[] row : grid) 
            for(int i=0; i<3; i++)
                if(row[i] == null)
                    return false;
        return true;
    }
}