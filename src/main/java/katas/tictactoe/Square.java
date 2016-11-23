package katas.tictactoe;

public class Square {
    private Board.Row row;
    private Board.Column column;
    
    public Square(Board.Row row, Board.Column column) {
        this.row = row;
        this.column = column;
    }

    public Board.Row getRow() {
        return row;
    }

    public Board.Column getColumn() {
        return column;
    }
}