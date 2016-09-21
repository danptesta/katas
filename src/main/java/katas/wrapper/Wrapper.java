package katas.wrapper;

public class Wrapper {
    public static String wrap(String s, int col) {
        return new Wrapper(col).wrap(s);
    }
    
    private int col;
    
    private Wrapper(int col) {
        
        if(col < 1)
            throw new IllegalArgumentException("col cannot be < 1");

        this.col = col;
    }
    
    private String wrap(String s) {
        
        if(s == null)
            throw new IllegalArgumentException("s cannot be null");
                
        if(s.length() <= col)
            return s;
        
        int space = s.substring(0, col).lastIndexOf(' ');
        
        if(space != -1)
            return breakLine(s, space, 1);
        else if(s.charAt(col) == ' ')
            return breakLine(s, col, 1);
        else
            return breakLine(s, col, 0);
    }
    
    private String breakLine(String s, int pos, int gap) {
        return s.substring(0, pos) + "\n" + wrap(s.substring(pos + gap), col);
    }
}
