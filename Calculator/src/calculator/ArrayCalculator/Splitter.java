/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator.ArrayCalculator;

/**
 *
 * @author Scalene
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.ListIterator;

public class Splitter {
    
    public static LinkedList format(LinkedList input){
        ListIterator iter = input.listIterator();
        while(iter.hasNext()){
            String entry = (String) iter.next();
            entry = entry.replaceAll("\\s+","");
            iter.set(entry);
        }
        return input;
    }

    public static LinkedList splitByEquals(String input){
        LinkedList split = new LinkedList();
        String a,b;
        Pattern splitBy = Pattern.compile("[=]");
        Matcher spliter = splitBy.matcher(input);
        if(spliter.find()){
            int j = spliter.start();
            a = input.substring(0,j);
            b = input.substring(j+1);
            split.add(a);
            split.add(b);
        }
        return split;
    }

    public static LinkedList<LinkedList> splitByParentheses(LinkedList input){
        String a,b;
        int i,j,k = 0;

        LinkedList<LinkedList> output = new LinkedList<LinkedList>();
        ListIterator iter = input.listIterator();

        Pattern splitByForward = Pattern.compile("[(]");
        Pattern splitByBackward = Pattern.compile("[)]");

        while(iter.hasNext()){    
            String entry = (String) iter.next();
            Matcher spliterForward = splitByForward.matcher(entry);
            Matcher spliterBackward = splitByBackward.matcher(entry);
            while(spliterForward.find() && spliterBackward.find()){
                 i = spliterForward.start();
                 j = spliterBackward.end();
                 iter.set(entry.substring(k, i));
                 iter.add(entry.substring(i, j));
                 k = j;
            }
        }
        return input;
    }
}
