/*
* INFO I-211/CSCI C-202
* SpellCheckerMain.java
* Purpose: This program is designed to read in a dictionary as a Binary Search 
* tree according the first letters of each word, read in a file, check to see if 
* each word in the file is found in the dictionary, and increments counters 
* depending on words found, words not found, comparisons found, and 
* comparisons not found. This program applies the use of Binary Search Trees to a 
* real world problem and shows the difference in efficiency compared to using a 
* Linked List to solve this real world problem. 
* 
* Dr. Hettiarachchi
* Cody Main 
* November 3-8, 2016
*/ 


package Program5;

public class SpellCheckerMain 
{
    public static void main(String[] args) 
    {
        long wordsFound;
        long wordsNotFound;
        long compsFound;
        long compsNotFound;
        
        
        SpellChecker a = new SpellChecker();
        
        a.populateDictionary("random_dictionary.txt");
        
        a.populateTextFile("Oliver.txt");
        
        System.out.println("");
        System.out.println("=================================================");
        System.out.println("Counter Display:");
        System.out.println("=================================================");
        System.out.println("");
        System.out.println("Number of Words Found: " + (double) a.wordsFound);
        System.out.println("");
        System.out.println("Number of Words Not Found: " + (double) a.wordsNotFound);
        System.out.println("");
        System.out.println("Average Comparisons Found: " + (double) a.compsFound / (double) a.wordsFound);
        System.out.println("");
        System.out.println("Average Comparisons Not Found: " + (double) a.compsNotFound / (double) a.wordsNotFound);
        System.out.println("");
        System.out.println("=================================================");
    }
}
