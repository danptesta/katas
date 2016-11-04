package katas.tictactoe;

public enum Player {
    X("X"), O("O");
    
    String value;
    Player(String value) {
        this.value = value;
    }
    
    public String toString() {
        return value.toString();
    }
}