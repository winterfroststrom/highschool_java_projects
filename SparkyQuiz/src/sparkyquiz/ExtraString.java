/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparkyquiz;

/**
 *
 * @author Scalene
 */
public class ExtraString {
    public String word;
    public String definition;
    public int difficulty;

    public ExtraString(String first, String second, int divisions){
    	word = first;
    	definition = second;
        difficulty = divisions;
    }
}
