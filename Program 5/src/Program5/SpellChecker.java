/*
* INFO I-211/CSCI C-202
* SpellChecker.java
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

import java.util.*;
import java.io.*;

public class SpellChecker 
{
    protected long wordsFound;
    protected long wordsNotFound;
    protected long compsFound;
    protected long compsNotFound;
    BinarySearchTree[] dictionary = new BinarySearchTree[26];
    int[] array = new int[1];

    
    public SpellChecker() 
    {
        for (int i = 0; i < dictionary.length; i++) 
        {
            dictionary[i] = new BinarySearchTree<String>();
        }
    }

    
    /**
    * @requires the string version of the file name of the dictionary that 
    * is desired to be read into the program.
    * @ensures the dictionary will be read into the 26 Binary Search Trees 
    * according to the first letter of each word.
    */
    public void populateDictionary(String fname) 
    {
        File f = new File(fname);

        try 
        {
            Scanner input = new Scanner(f);

            while (input.hasNext()) 
            {
                String word = input.nextLine().toLowerCase();

                dictionary[word.charAt(0) - 97].insert(word);
            }
            input.close();
        } 
        catch (IOException e) 
        {
            System.out.println("File reading failed.");
        }
    }

    
    /**
    * @requires the string version of the file name of the file that is desired
    * to be read into the program.
    * @ensures the file will be read in, each word is compared in the
    * dictionary to find a match, and the counters are incremented depending on
    * the results.
    * 
    *    Idea to use more than one delimeter of just whitespace came from:
    * http://pages.cs.wisc.edu/~hasti/cs302/examples/Parsing/parseString.html
    */
    public void populateTextFile(String fname) 
    {
        File f = new File(fname);

        try 
        {
            Scanner input = new Scanner(f);

            while (input.hasNext()) 
            {
                String delimiters = "[ '-_.,?!]+";
                String word = input.nextLine().toLowerCase();

                String[] splitLine = word.split(delimiters);

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < splitLine.length; i++) 
                {
                    for (int j = 0; j < splitLine[i].length(); j++) 
                    {
                        if (Character.isLetter(splitLine[i].charAt(j)))
                        {
                            sb.append(splitLine[i].charAt(j));
                        }
                    }
                    
                    if (!sb.toString().isEmpty()) 
                    {
                        if (dictionary[sb.toString().charAt(0) - 97].search(sb.toString(), array)) 
                        {
                            wordsFound++;
                            compsFound += array[0];
                        } 
                        else 
                        {
                            wordsNotFound++;
                            compsNotFound += array[0];
                        }
                    }
                    sb.setLength(0);
                }
            }
            input.close();
        } 
        catch (IOException e) 
        {
            System.out.println("File reading failed.");
        }
    }
}
