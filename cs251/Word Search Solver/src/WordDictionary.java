/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 7 WordSearch
 * 
 * This code was utilized from:
 * http://www.programcreek.com/2014/05/leetcode-add-and-search-word-data-structure-design-java/
 * It has been highly modified from it's original, with additional methods and altered methods
 * 
 * Builds nodes consisting of characters into whole words. Recursive functionality to search for
 * a word via the tree structure as well as check whether it is a prefix of another word.
 * 
 * WordDictionary.java
 */

import java.util.HashMap;

public class WordDictionary {
    private TrieNode root;
 
    public WordDictionary(){
        root = new TrieNode();
    }
 
    // Adds a word into the data structure.
    public void addWord(String word) {
        HashMap<Character, TrieNode> children = root.children;
 
        for(int i=0; i< word.length(); i++){
            char c = word.charAt(i);
 
            TrieNode t = null;
            if(children.containsKey(c)){
                t = children.get(c);
            }
            else{
                t = new TrieNode(c);
                children.put(c,t);
            }
 
            children = t.children;
 
            if(i == word.length()-1){
                t.isLeaf = true;
            }
        }
        
    }
 
    // Returns if the word is in the data structure.
    public boolean search(String word) {
       return dfsSearch(root.children, word, 0);
 
    }
 
    public boolean dfsSearch(HashMap<Character, TrieNode> children, String word, int start) {
        
        if(start == word.length()){
            if(children.size()==0) {
                return true;
            }
            else {
                return false;
            }
                
        }
 
        char c = word.charAt(start);
 
        if(children.containsKey(c)){
            if(start == word.length()-1 && children.get(c).isLeaf){
                return true;
            }
 
            return dfsSearch(children.get(c).children, word, start+1);
        }
        else{
            return false;
        }
    }
    
    // Checking to see if a word is a prefix to continue searching
    public boolean isPrefix(String word) {
        return dfsPrefix(root.children, word, 0);
    }
    
    public boolean dfsPrefix(HashMap<Character, TrieNode> children, String word, int start) {
        
        // If the word has no children then it isn't a prefix of another
        if(start == word.length() && children.size() == 0){
            return false;
            
        }
        else if (start != word.length()) {
            char c = word.charAt(start);
            return dfsPrefix(children.get(c).children, word, start + 1);
            
        }
        else {
            return true;
        }
        
    }
}