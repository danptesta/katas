package katas.wrapper;

import org.junit.Test;

import static katas.wrapper.Wrapper2.wrap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;

public class WrapperTest2 {
    private void assertWrap(String unwrapped, int cols, String wrapped) {
        assertThat(wrap(unwrapped, cols), equalTo(wrapped));
    }
    
    @Test
    public void whenNull_thenEmptyString() {
        assertWrap(null, 10, "");
    }
    
    @Test
    public void whenEmptyString_thenEmptyString() {
        assertWrap("", 10, "");
    }
    
    @Test(expected=katas.wrapper.Wrapper2.ColumnsTooSmall.class)
    public void whenColsLessThanOne_thenThrowColsTooSmall() {
        wrap("x", 0);
    }
    
    @Test
    public void whenStringShorterThanCol_thenDoesNotWrap() {
        assertWrap("x", 10, "x");
    }
    
    @Test
    public void whenOneWordLongerThanColumns_thenWordBreaks() {
        assertWrap("longword", 4, "long\nword");
        assertWrap("longerword", 6, "longer\nword");
    }
    
    @Test
    public void whenOneWordLongerThanTwiceColumns_thenWordBreaksTwice() {
        assertWrap("verylongword", 4, "very\nlong\nword");
    }
    
    @Test
    public void whenTwoWordsLongerThanColumns_thenWrap() {
        assertWrap("word word", 6, "word\nword");
        assertWrap("wrap here", 6, "wrap\nhere");
    }
    
    @Test
    public void whenThreeWordsLongerThanTwiceColumns_thenEachWordWraps() {
        assertWrap("word word word", 6, "word\nword\nword");
    }
    
    @Test
    public void whenThreeWordsJustOverColumns_thenBreakOnSecondWord() {
        assertWrap("word word word", 11, "word word\nword");
    }
    
    @Test
    public void whenTwoWordsTheFirstEndingAtTheLimit_thenBreakBetweenWords() {
        assertWrap("word word", 4, "word\nword");
    }
}
