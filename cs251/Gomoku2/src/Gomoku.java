

/**
 * Gomoku.java
 * CS 251 Lab 2
 * Cheng En Ho
 * Fri 02,2018
 */

import cs251.lab2.GomokuGUI;
import cs251.lab2.GomokuModel;

import java.util.Random;


public class Gomoku implements GomokuModel{


    private Square moves [][];
    private StringBuilder sb;
    private int playerNum = 0;
    private boolean computerAI = false;



    /** initial the game, if arg[0]="COMPUTER" then go computer mode, other go two player mode.
     * if arg[0] != "COMPUTER" then go to two player mode.
     */



    public static void main( String [] args ) {
        Gomoku game = new Gomoku ();
        if(args.length > 0) {
            game.setComputerPlayer(args[0]);
        }
        GomokuGUI.showGUI(game);
    }


    public Gomoku(){

        initMovesArray();
        createBoardString();

    }

    /**
     * This initializes a 2d array with <code>Square.EMPTY</code>.
     */

    private void initMovesArray() {

        moves = new Square[getNumRows()][getNumCols()];

        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumCols(); j++){
                moves[i][j] = Square.EMPTY;
            }
        }

    }
    /**
     * This initializes the board string based on the
     * <code>getNumRows</code> and <code>getNumCols</code>.
     * Rather than a straight concat this uses the a StringBuilder.
     */



    private void createBoardString() {

        StringBuilder _sb = new StringBuilder();

        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumCols(); j++){
                _sb.append(moves[i][j].toChar());
            }
            _sb.append("\n");
        }
        sb = _sb;
    }


    /**
     * Do chick, if it is not EMPTY then print filled. If meet win condition then go End of game.
     */


    @Override
    public Outcome handleClickAt(int row, int col) {

        if(computerAI){
            if (moves[row][col] != Square.EMPTY) {
                System.out.println("Filled");
                return Outcome.GAME_NOT_OVER;
            }
                moves[row][col] = moves[row][col].RING;
                findRanEmpty(row,col);
                createBoardString();
                if(isWin()){
                    return Outcome.RING_WINS;
                }

        }else {


            if (moves[row][col] != Square.EMPTY) {
                System.out.println("Filled");

            } else {
                if (playerNum % 2 == 0) {
                    moves[row][col] = moves[row][col].RING;

                } else {
                    moves[row][col] = moves[row][col].CROSS;
                }
                playerNum++;
            }

            createBoardString();

            if (isWin()) {
                if (playerNum % 2 == 1) {
                    return Outcome.RING_WINS;
                } else {
                    return Outcome.CROSS_WINS;
                }
            }
        }
        return Outcome.GAME_NOT_OVER;
    }




    /** check win condition by using rDiagonal,lDiagonal, rightward, and downward

     /* Visually inspect how to check for a win
     *                  TABLE 1
     *                      [x,y]  [x,y]  [x,y]
     * 1) rightward         [3,3], [4,3], [5,3]
     * 2) downward          [3,3], [3,4], [3,5]
     * 3) right-diagonal    [3,3], [4,4], [5,5]
     * 4) left-diagonal     [3,3], [2,4], [1,5]
     */

    /**
     * this checks for a win and returns if a win was detected.
     * works by scanning the board top down, when a non-empty square found it looks in all directions.
     * @return true if win detected
     *
     */



    private boolean isWin() {
        for(int i = 0; i < getNumRows(); i++){
            for(int j = 0;j < getNumCols();j++){
                if(moves[i][j].toChar() != Square.EMPTY.toChar()){
                    if(downward(i,j)||rightward(i,j)||lDiagonal(i,j)||rDiagonal(i,j)){
                        System.out.println("win");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * check rightward Num in line for win.
     */


    private boolean rightward(int r, int c) {
        Square _sq = moves[r][c];
        int w = 0;
        for(int i = c; i <= getNumCols(); i++){
            if(_sq.toChar() == moves[r][i].toChar()){
                w++;
            }else {
                break;
            }
        }
        return (w >= getNumInLineForWin());
    }


    /**
     * check downward Num in line for win.
     */

    private boolean downward(int r, int c)  {
        Square _sq = moves[r][c];
        int w = 0;
        for(int i = r; i <= getNumRows(); i++){
            if(_sq.toChar() == moves[i][c].toChar()){
                w++;
            }else {
                break;
            }
        }
        return (w >= getNumInLineForWin());
    }
    /**
     * check left Diagonal Num in line for win.
     */

    private boolean lDiagonal(int r, int c) {
        Square _sq = moves[r][c];
        int w = 0;
        for(int x = c, y = r; x <= getNumRows() && y <= getNumRows(); x--,y++){
            if(_sq.toChar() == moves[y][x].toChar()){
                w++;
            }else {
                break;
            }
        }
        return (w >= getNumInLineForWin());
    }
    /**
     * check right Diagonal Num in line for win.
     */

    private boolean rDiagonal(int r, int c) {
        Square _sq = moves[r][c];
        int w = 0;
        for(int x = c, y = r; x <= getNumRows() && y <= getNumRows(); x++,y++){
            if(_sq.toChar() == moves[y][x].toChar()){
                w++;
            }else {
                break;
            }
        }
        return (w >= getNumInLineForWin());
    }




    /**
     * Create a new board,,and start a New game .
     */


    @Override
    public void startNewGame() {
        initMovesArray();
        createBoardString();
        playerNum = 0;

    }

    /**
     * returns a string that represents the board
     */

    @Override
    public String getBoardString() {
        return sb.toString();
    }



    /**
     * Check the input string, NONE or COMPUTER mode.
     */

    @Override
    public void setComputerPlayer(String s) {
        if(s.equals("NONE")){
            System.out.println("This is Two player Mode!!");
        }else if(s.equals("COMPUTER")){
            computerAI = true;
            System.out.println("This is COMPUTER Mode!!");
        }else {
            System.out.println("Do not recognized mode!!");
        }


    }

    /**
     * Computer mode AI, it use random function.
     */

    private void findRanEmpty(int x,int y) {
            Random rand = new Random();
            int  i = rand.nextInt (20)+5;
            int  j = rand.nextInt(20)+5;
            //int i,j;

            while (moves[i][j].toChar() != Square.EMPTY.toChar()){
                  i = rand.nextInt (20)+5;
                  j = rand.nextInt(20)+5;
            }
            if(moves[x+1][y+1].toChar() == Square.EMPTY.toChar()){
            moves[x+1][y+1] = moves[x+1][y+1].CROSS;
            }else if(moves[x+1][y].toChar() == Square.EMPTY.toChar()){
            moves[x+1][y] = moves[x+1][y].CROSS;
            }else if(moves[x+1][y-1].toChar() == Square.EMPTY.toChar()){
            moves[x+1][y-1] = moves[x+1][y-1].CROSS;
            }else if(moves[x][y+1].toChar() == Square.EMPTY.toChar()){
            moves[x][y+1] = moves[x][y+1].CROSS;
            }else if(moves[x][y-1].toChar() == Square.EMPTY.toChar()){
            moves[x][y-1] = moves[x][y-1].CROSS;
            }else if(moves[x-1][y+1].toChar() == Square.EMPTY.toChar()){
            moves[x-1][y+1] = moves[x-1][y+1].CROSS;
            }else if(moves[x-1][y].toChar() == Square.EMPTY.toChar()){
            moves[x-1][y] = moves[x-1][y].CROSS;
            }else if(moves[x-1][y-1].toChar() == Square.EMPTY.toChar()){
            moves[x-1][y-1] = moves[x-1][y-1].CROSS;
            }else{
            moves[i][j] = moves[i][j].CROSS;
            }

    }

    /**
     * Set the Cols Num by default is 30 .
     */

    @Override
    public int getNumCols() {
        return DEFAULT_NUM_COLS;
    }
    /**
     * Set the Rows Num by default is 30 .
     */

    @Override
    public int getNumRows() {
        return DEFAULT_NUM_ROWS;
    }

    /**
     * Set the Num of Line for win by default is 5.
     */

    @Override
    public int getNumInLineForWin() {
        return SQUARES_IN_LINE_FOR_WIN;
    }
}
