/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator.ArrayCalculator;

/**
 *
 * @author Scalene
 */
import java.util.LinkedList;

public class Solver {
    private LinkedList inputList;
    private LinkedList<LinkedList> toSimplify;

    public Solver(String input){
        inputList = Splitter.splitByEquals(input);
    }

    public String solve(){
        format();
        toSimplify = Splitter.splitByParentheses(inputList);
        //////////////////////////////////////////////////////////////////////////////
        return toString();
    }

    @Override
    public String toString(){
        String output = "";
        for(Object a:inputList){
            output = output + (String) a;
        }
        return output;
    }

    public void format(){
        inputList = Splitter.format(inputList);
    }

    public boolean isCorrectInput(){
        return true;
    }
    
    public void reconstruct(String input){
        inputList = Splitter.splitByEquals(input);
    }
}
