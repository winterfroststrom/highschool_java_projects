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

public class Compression {
    static String a = "What endless ways make themself known to minds that lack any purpose but instead wander.";
    static final String[] alpha = {"_","d","p","v","o"};
    static final String[] replace = {"a","b","c","e","f","g","h"};
    
    public static void main(String[]args){
        int rIndex = 0;
        System.out.println(a);
        a = simplify(a);
        System.out.println(a);
        String[] sea = {"oo","ov","vo","do","dd","od","vv","po","op","pv","vp"};
        for(int j =0;j<7;j++){
        System.out.println(combination(a,sea));
        a = a.replaceAll(combination(a,sea), replace[rIndex]);
        rIndex++;
        //System.out.println(a);
        }
        System.out.println(a);
    }

    static String[] findSearches(String in, int size){
        String[] out = {};

        return out;
    }

    static String combination(String in, String[] searches){
        int i = 0;
        String out = searches[0];
        int max = 0;
        for(String s:searches){
            i++;
            int temp = find(in,s);
            if(temp > max){
                out = s;
                max = temp;
            }
            if(i > 5){
                i = 0;
//                System.out.println();
            }
        }
//        System.out.println();
        return out;
    }

    static int find(String in, String search){
        String temp = in;
        Matcher m = Pattern.compile(search).matcher(temp);
        int i = 0;
        while(m.find())
            i++;
        return i;
//        temp = search + ": " + i;
//        String temper = "";
//        for(i = temp.length();i<15;i++)
//            temper = temper.concat(" ");
//        System.out.print(temp + temper);
    }

    static String simplify(String in){
        String out = in.replaceAll("[^\\w]", "_").toLowerCase();
        out = out.replaceAll("[b|f|h|k|l|t]", "d");
        out = out.replaceAll("[g|j|q|y]", "p");
        out = out.replaceAll("[m|n|u|v|w]", "v");
        out = out.replaceAll("[a|c|e|i|r|s|x|z]", "o");
        return out;
    }
}
