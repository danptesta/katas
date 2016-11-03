package katas.bowling;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private Game g;
    
    @Before
    public void setUp() {
        g = new Game();
    }

    private void rollMany(int pins, int n) {
        for(int i=0; i<n; i++)
            g.roll(pins);
    }

    private void rollSpare() {
        g.roll(5);
        g.roll(5);
    }

    private void rollStrike() {
        g.roll(10);
    }

    @Test
    public void gutterGame() {
        rollMany(0, 20);
        assertThat(g.score(), equalTo(0));
    }
    
    @Test
    public void allOnes() {
        rollMany(1, 20);
        assertThat(g.score(), equalTo(20));
    }
    
    @Test
    public void oneSpare() {
        rollSpare();
        g.roll(3);
        rollMany(17, 0);
        assertThat(g.score(), equalTo(16));
    }
    
    @Test
    public void oneStrike() {
        rollStrike();
        g.roll(3);
        g.roll(4);
        rollMany(16,0);
        assertThat(g.score(), equalTo(24));
    }
    
    @Test
    public void perfectGame() {
        rollMany(10, 12);
        assertThat(g.score(), equalTo(300));
    }
}
