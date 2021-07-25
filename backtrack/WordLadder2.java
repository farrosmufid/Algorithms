package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
    private Map<String, List<String>> adjList = new HashMap<>();
    List<String> currPath = new ArrayList<>();
    List<List<String>> shortestPaths = new ArrayList<List<String>>();
    
    private void backtrack(String source, String destination) {
        if (source.equals(destination)) {
            /*
                Create a copy of currPath 
                and add to shortestPaths
            */
            
            List<String> tempPath = new ArrayList<>(currPath);
            shortestPaths.add(tempPath);
        }
        
        /*
            Return if source not found in adjList
        */
        
        if (adjList.containsKey(source) == false) {
            return;
        }
        
        for (int i = 0; i < adjList.get(source).size(); i++) {
            currPath.add(adjList.get(source).get(i));
            
            backtrack(adjList.get(source).get(i), destination);
            
            currPath.remove(currPath.size() - 1);
        }
        
        
    }
    
    private List<String> findNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char charList[] = word.toCharArray();
        
        /* 
            Check every letter in every position, if present
            in wordSet, add to neighbors list
        */
        
        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];
            
            for (char c = 'a'; c <='z'; c++) {
                charList[i] = c;
                
                if (c == oldChar || wordSet.contains(String.valueOf(charList)) == false) {
                    continue;
                }
                
                neighbors.add(String.valueOf(charList));
            }
            
            charList[i] = oldChar;
        }
        
        return neighbors;
    }
    
    public void bfs(String beginWord, String endWord, Set<String> wordSet) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        
        if (wordSet.contains(beginWord)) {
            wordSet.remove(beginWord);
        }
        
        Set<String> isEnqueued = new HashSet<>();
        isEnqueued.add(beginWord);
        
        while (q.size() > 0) {
            List<String> visited = new ArrayList<>();
            
            for (int i = q.size() - 1; i >= 0 ; i--) {
                String currWord = q.poll();
                
                List<String> neighbors = findNeighbors(currWord, wordSet);
                
                for (String word: neighbors) {
                    visited.add(word);
                    
                    if (adjList.containsKey(currWord) == false) {
                        adjList.put(currWord, new ArrayList<String>());
                    }
                    
                    adjList.get(currWord).add(word);
                    
                    if (isEnqueued.contains(word) == false)  {
                        q.add(word);
                        isEnqueued.add(word);
                    }
                }
            }
            
            // remove words from set if already visited from previous layer
            for (int i = 0; i < visited.size(); i++) {
                if (wordSet.contains(visited.get(i))) {
                    wordSet.remove(visited.get(i));
                }
            }
        }
        
        
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        
        bfs(beginWord, endWord, wordSet);
        
        currPath.add(beginWord);
        
        backtrack(beginWord, endWord);
        
        return shortestPaths;
    }
}

public class WordLadder2 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));

        List<List<String>> output = solution.findLadders(beginWord, endWord, wordList);

        System.out.println("Output: ");

        for (List<String> outputList: output) {
            for (String word: outputList) {
                System.out.print(word + " ");
            }
            System.out.println();
        }

    }
}
