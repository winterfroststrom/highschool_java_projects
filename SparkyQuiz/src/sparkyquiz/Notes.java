/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparkyquiz;

/**
 *
 * @author Scalene
 */

 import java.util.Random;
 import java.io.FileReader;
 //import java.util.HashMap;

public class Notes {
    //private HashMap<Integer,ExtraString> words;
    private int[] indexs;
    private ExtraString[] words;
    private Random gen;
    private int index;
    private final int finalSize;
    private final int maxSize;
    private int numberOfTries;

    public Notes(FileReader noteSource){
        index = 0;
        gen = new Random();
        //words = new HashMap<Integer,ExtraString>();
        TextParser wordList = new TextParser(noteSource);
        finalSize = wordList.maxSize()/2;
        maxSize = finalSize*finalSize;
        indexs = new int[finalSize];
        words = new ExtraString[finalSize];
        int i = 0;
        while(wordList.hasNext()){
            //words.put(words.size(),wordList.next());
            indexs[i] = i*finalSize;
            words[i] = wordList.next();
            i++;
        }
    }

    public String index(){
        index = gen.nextInt(finalSize);
        return words[index].definition;
    }

    public boolean match(String guess){
        return right().toLowerCase().equals(guess.toLowerCase());
    }

    public String right(){
        return words[index].word;
    }
}
