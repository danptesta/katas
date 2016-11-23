package katas.tictactoe;

import static katas.tictactoe.Marker.O;
import static katas.tictactoe.Marker.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PlayerTest {
    private void assertCreatePlayerWithMarker(Marker marker) {
        Player p = new Player(marker);
        assertThat(p.getMarker(), equalTo(marker));
    }
    
    @Test
    public void createPlayerWithX_returnsX() {
        assertCreatePlayerWithMarker(X);
        assertCreatePlayerWithMarker(O);
    }
}
