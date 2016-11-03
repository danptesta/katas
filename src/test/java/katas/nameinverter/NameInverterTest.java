package katas.nameinverter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NameInverterTest {
    private NameInverter nameInverter;
    
    @Before
    public void setup() {
        nameInverter = new NameInverter();
    }
    
    private void assertInverted(String original, String inverted) {
        assertThat(nameInverter.invertName(original), equalTo(inverted));
    }
    
    @Test
    public void givenNull_returnEmptyString() {
        assertInverted(null, "");
    }
    
    @Test
    public void givenEmptyString_returnEmptyString() {
        assertInverted("","");
    }
    
    @Test
    public void givenSimpleName_returnSimpleName() {
        assertInverted("Name", "Name");
    }
    
    @Test
    public void givenSimpleNameWithSpaces_returnSimpleNameWithoutSpaces() {
        assertInverted(" Name ", "Name");
    }
    
    @Test
    public void givenFirstLast_returnFirstLast() {
        assertInverted("First Last", "Last, First");
    }
    
    @Test
    public void givenFirstLastWithExtraSpaces_returnLastFirstWithoutSpaces() {
        assertInverted("  First  Last  ", "Last, First");
    }
    
    @Test
    public void ignoreHonorific() {
        assertInverted("Mr. First Last", "Last, First");
        assertInverted("Mrs. First Last", "Last, First");
        assertInverted("Mrs. Last", "Last");
    }
    
    @Test
    public void postNominals_stayAtEnd() {
        assertInverted("First Last Sr.", "Last, First Sr.");
        assertInverted("First Last BS. Phd.", "Last, First BS. Phd.");
    }
    
    @Test
    public void integration() {
        assertInverted("  Daniel  Testa  BS.  esq.  ", "Testa, Daniel BS. esq.");
    }
}
