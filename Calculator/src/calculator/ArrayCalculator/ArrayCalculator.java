/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator.ArrayCalculator;

/**
 *
 * @author Scalene
 */
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class ArrayCalculator {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList first = new ArrayList();
        ArrayList second = new ArrayList();
        Scanner scanner = new Scanner(System.in);

    	
        Solver solver = new Solver(scanner.nextLine());
        while(!solver.isCorrectInput()){
            solver.reconstruct(scanner.nextLine());
        }
        String output = solver.solve();

        Pattern equationSplit = Pattern.compile("[a-zA-Z0-9]+|[\\+\\*-/^()]");
/*        Matcher firstMatcher = equationSplit.matcher(firstHalf);
        Matcher secondMatcher = equationSplit.matcher(secondHalf);

        while(firstMatcher.find()){
            first.add(firstHalf.substring(firstMatcher.start(),firstMatcher.end()));
        }
        while(secondMatcher.find()){
            second.add(secondHalf.substring(secondMatcher.start(),secondMatcher.end()));
        }
*/
            /*for(Object a: first){
                System.out.println(a);
            }
            System.out.println();
            for(Object a: second){
                System.out.println(a);
            }*/

        for(Object a: first){
                
        }
    }
}
