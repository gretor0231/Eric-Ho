
// This code was utilized from:
// http://www.programcreek.com/2014/05/leetcode-add-and-search-word-data-structure-design-java/

import java.util.HashMap;


public class TrieNode{
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
 
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
}
