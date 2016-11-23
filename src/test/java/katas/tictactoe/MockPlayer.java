package katas.tictactoe;

import java.util.List;

public class MockPlayer extends Player {
    List<Square> moves;
    int i = 0;
    
    public MockPlayer(Marker marker, List<Square> moves) {
        super(marker);
        this.moves = moves;
    }
    
    public void play(Board board) {
        board.play(this.getMarker(), moves.get(i++));
    }

}
