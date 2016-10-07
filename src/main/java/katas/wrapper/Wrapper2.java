package katas.wrapper;

public class Wrapper2 {
    private int length;
    
    private Wrapper2(int columns) {
        this.length = columns;
    }
    
    public static String wrap(String unwrapped, int length) {
        return new Wrapper2(length).wrap(unwrapped);
    }

    private String wrap(String unwrapped) {
        if (length < 1)
            throw new ColumnsTooSmall();
        if (unwrapped == null)
            return "";
        
        if (unwrapped.length() <= length)
            return unwrapped;
        else {
            int space = unwrapped.substring(0, length+1).lastIndexOf(" ");
            if (space >= 0) {
                return breakLine(unwrapped, space, 1);
            } else
                return breakLine(unwrapped, length, 0);
        }
    }

    private String breakLine(String unwrapped, int breakIndex, int gap) {
        return unwrapped.substring(0, breakIndex) + "\n" + wrap(unwrapped.substring(breakIndex+gap));
    }
    
    public class ColumnsTooSmall extends RuntimeException {
        
    }

}
