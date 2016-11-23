package katas.tictactoe;

public class Player {
    Marker marker;
    boolean played = false;

    public Player(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return this.marker;
    }
    
    public void play(Board board) {
        
    }
}
