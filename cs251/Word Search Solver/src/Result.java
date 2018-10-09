

import java.util.Comparator;

public class Result implements Comparator<Result> {
    
    private String word;
    private int row;
    private int col;
    private Direction dir;
    
    public Result() {
    }
    
    public Result(String word, int row, int col, Direction dir) {
        this.word = word;
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
    
    @Override
    public String toString() {
        return word + " " + row + " " + col + " " + dir;
    }
    
    public int compare(Result r1, Result r2) {
        
        
        
        if(r1.word.equals(r2.word)) {
            if(r1.row == r2.row) {
                // Sort by direction
                if(r1.col == r2.col) {
                    return r1.dir.ordinal() - r2.dir.ordinal();
                }
                // Sort by column
                else {
                    return r1.col - r2.col;
                }
            }
            // Sort by row
            else {
                return r1.row - r2.row;
            }
        }
        // Sort by word
        else {
            return r1.word.compareTo(r2.word);  
        }
        
    }

}

