package katas.wrapper;

import static katas.wrapper.Wrapper.wrap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  WrapperTest.DegenerateTests.class,
  WrapperTest.SplitWordTests.class,
  WrapperTest.WrapTwoWordsTests.class
})

public class WrapperTest {
	
	public static class DegenerateTests {
	    
		@Test
		public void emptyString_shouldBeEmpty() throws Exception {
			assertThat(wrap("", 1), equalTo(""));
		}
		
		@Test
		public void stringShorterThanCol_shouldNotWrap() throws Exception {
		    assertThat(wrap("this", 10), equalTo("this"));
		}
        
        @Test(expected=java.lang.IllegalArgumentException.class)
        public void nullString_shouldThrowIllegalArgumentException() throws Exception {
            wrap(null, 1);
        }
        
        @Test(expected=java.lang.IllegalArgumentException.class)
        public void colLessThanOne_shouldThrowIllegalArgumentException() throws Exception {
            wrap("this", 0);
        }
	}
	
	public static class SplitWordTests {
	    
	    @Test
	    public void splitOneWord() throws Exception {
	        assertThat(wrap("word", 2), equalTo("wo\nrd"));
	    }
	    
	    @Test
	    public void splitOneWordManyTimes() throws Exception {
	        assertThat(wrap("abcdefghij", 3), equalTo("abc\ndef\nghi\nj"));
	    }
	}
	
	public static class WrapTwoWordsTests {
	    
		@Test
		public void wrapOnWordBoundary() throws Exception {
		    assertThat(wrap("word word", 5), equalTo("word\nword"));
		}
		
		@Test
		public void wrapAfterWordBoundary() throws Exception {
		    assertThat(wrap("word word", 6), equalTo("word\nword"));
		}
		
		@Test
	    public void wrapWellBeforeWordBoundary() throws Exception {
	      assertThat(wrap("word word", 3), equalTo("wor\nd\nwor\nd"));
	    }
		
		@Test
	    public void wrapJustBeforeWordBoundary() throws Exception {
	      assertThat(wrap("word word", 4), equalTo("word\nword"));
	    }
	}
}
