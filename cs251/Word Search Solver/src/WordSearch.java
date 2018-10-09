/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 7 WordSearch
 * 
 * Searches a given puzzle with a given dictionary and outputs where the words are
 * There are helper classes and data structures used to perform this in an efficient manner
 * Output is given as "word row column direction"
 * 
 * WordSearch.java
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class WordSearch {
    public static ArrayList<String> m_dictList = new ArrayList<String>();
    public static ArrayList<Result> m_resultsList = new ArrayList<Result>();
    public static WordDictionary m_wordDict = new WordDictionary();
    public static char[][] m_puzzArray;
    public static int m_puzzHeight;
    public static int m_puzzWidth;
    
    public WordSearch() {}
    
    public static void main(String[] args) throws Exception {
        
        // Catching if the user doesn't give 2 arguments to the program
        if(args.length != 2) {
            throw new Exception("Call WordSearch with a dictionary and puzzle file");
        }
        
        fillDictList(args[0]);
        fillPuzzleArray(args[1]);
        fillResultsList();
        printResultsList();
    }
    
    
    /**
     * Fills my dictionary data structure with words.
     * @param arg0
     * The file path given to the dictionary file
     */
    public static void fillDictList(String arg0) {
        Path dictionaryFile = Paths.get(arg0);
        BufferedReader reader;
        try {
            InputStream dictFile = Files.newInputStream(dictionaryFile);
            reader = new BufferedReader(new InputStreamReader(dictFile));
            String line = null;
            
            // Reading in the dictionary
            while((line = reader.readLine()) != null) {
                m_dictList.add(line);
            }
        }
        catch (IOException ex) {
            System.out.println("IO Error\n" + ex.getMessage());
        }
    }
    
    /**
     * Fills my puzzle data structure with characters from a puzzle.
     * @param arg0
     * The file path given to the puzzle file
     */
    public static void fillPuzzleArray(String arg1) {
        Path puzzleFile = Paths.get(arg1);
        BufferedReader reader;
        try {
            InputStream puzzFile = Files.newInputStream(puzzleFile);
            // Reading in the puzzle
            reader = new BufferedReader(new InputStreamReader(puzzFile));
            
            String[] firstLine = reader.readLine().split(" ");
            m_puzzHeight = Integer.parseInt(firstLine[0]);
            m_puzzWidth = Integer.parseInt(firstLine[1]);
            
            // Filling the 2D char array with the puzzle
            m_puzzArray = new char[m_puzzWidth][m_puzzHeight];
            for(int i = 0; i < m_puzzHeight; ++i) {
                m_puzzArray[i] = reader.readLine().toCharArray();
            }
            
        }
        catch (IOException ex) {
            System.out.println("IO Error\n" + ex.getMessage());
        }
        catch(NumberFormatException ex) {
            System.out.println("Height and width numbers do not exist or are not numbers" + ex.getMessage());
        }
            
    }
    
    /**
     * This is the primary method of the class. Calls searchForWords to ultimately fill
     * the results list with objects that give all the details of a word found in a puzzle.
     */
    public static void fillResultsList() {
        
        int stringMax = 0;

        // Sorting the m_dictList by alphabetization for speed
        // This is surprisingly significant for optimization
        Collections.sort(m_dictList);
        
        for(String word:m_dictList) {
            m_wordDict.addWord(word);
            if(word.length() > stringMax) {
                stringMax = word.length();
            }
        }
        
        for(int i = 0; i < m_puzzHeight; ++i) {
            for(int j = 0; j < m_puzzWidth; ++j) {
                
                // Searching for words in every direction, TODO: Seems like I could do this a different way...
                for(Direction dir : Direction.values()) {
                    switch(dir) {
                        case N: searchForWords(i, j, -1, 0, stringMax, Direction.N);
                            break;
                        case NE: searchForWords(i, j, -1, 1, stringMax, Direction.NE);
                            break;
                        case E: searchForWords(i, j, 0, 1, stringMax, Direction.E);
                            break;
                        case SE: searchForWords(i, j, 1, 1, stringMax, Direction.SE);
                            break;
                        case S: searchForWords(i, j, 1, 0, stringMax, Direction.S);
                            break;
                        case SW: searchForWords(i, j, 1,-1, stringMax, Direction.SW);
                            break;
                        case W: searchForWords(i, j, 0, -1, stringMax, Direction.W);
                            break;
                        case NW: searchForWords(i, j, -1, -1, stringMax, Direction.NW);
                            break;
                    }
                }
            }
        }    
         
    }
    
    /**
     * Searchs for words in the puzzle. There is some interesting calculations for the maximum
     * search iterations calculated at the beginning so that we don't have to iterate too much.
     * @param row Row we are starting at
     * @param col Column we are starting at
     * @param rowOffset Direction we are headed for rows.
     * @param colOffset Direction we are headed for columns.
     * @param stringMax Maximum length of a string in the entire dictionary file given.
     * @param dir Direction we are going, gets fed into the Result object.
     */
    public static void searchForWords(int row, int col, int rowOffset, int colOffset, int stringMax, Direction dir) {
        int minRowSearch, minColSearch, maxSearch;
        
        // Initializing these to high values in case the offsets are 0 then the maxSearch won't be 0
        minRowSearch = minColSearch = maxSearch = Integer.MAX_VALUE;

        String possibleWord = "";
        
        // Finding how many times we should iterate before we hit a boundary or hit the longest word length
        if(rowOffset == -1) {
            if(row - stringMax < 0) minRowSearch = row + 1;
            else minRowSearch = stringMax;
        }
        else if(rowOffset == 1) {
            if(row + stringMax > m_puzzHeight) minRowSearch = m_puzzHeight - row;
            else minRowSearch = stringMax;
        }
        
        if(colOffset == -1) {
            if(col - stringMax < 0) minColSearch = col + 1;
            else minColSearch = stringMax;
        }
        else if(colOffset == 1) {
            if(col + stringMax > m_puzzWidth) minColSearch = m_puzzWidth - col;
            else minColSearch = stringMax;
        }
        
        // If one of the offsets is set to 0 then we don't care if it's part is against an edge
        if(colOffset == 0) {
            maxSearch = minRowSearch;
        }
        else if (rowOffset == 0) {
            maxSearch = minColSearch;
        }
        else {
            
            // Max search is the lowest number in totality so we don't go
            // out of bounds or exceed the longest word length
            maxSearch = (minRowSearch < minColSearch) ? minRowSearch : minColSearch;
        }
        
        for(int i = 0; i < maxSearch; ++i) {
            possibleWord += m_puzzArray[row + rowOffset*i][col + colOffset*i];
            
            // If a word is found, add it to the list
            if (m_wordDict.search(possibleWord)) {
                m_resultsList.add(new Result(possibleWord, row, col, dir));
                
                // If a word is found, find out if it's a prefix of something else
                // if not, break out of this loop
                if(!m_wordDict.isPrefix(possibleWord)) {
                    break;
                }
            }
        }  
        
    }
    
    /**
     * Sorts the resultsList using the Comparator in Result and prints it to stdout.
     */
    public static void printResultsList() {
        // Sorting the m_resultsList with a custom comparator in Result class
        Collections.sort(m_resultsList, new Result());
        
        for(Result result:m_resultsList) {
            System.out.println(result.toString());
        }
    }
    
}
