/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

/**
 *
 * @author Scalene
 */
import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {
       
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        
        while(!input.equals("quit")){
            if(input.equals("")){
            } else
                System.out.println(Displayer.display(Analyzer.analyze(Interpreter.interpret(input))));
            input = scanner.nextLine();
        }

        /*fixparenthesis
         run:
((
asd
+(
quit
BUILD SUCCESSFUL (total time: 4 seconds)

         */
    }
}
