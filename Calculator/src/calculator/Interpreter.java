/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

/**
 *
 * @author Scalene
 */
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class Interpreter {

    public static InterpretedString interpret(String input){
//    	  Matcher matcher = Pattern.compile("\\s+").matcher(input);
//        input = matcher.replaceAll("");
        InterpretedString is = new InterpretedString();

        String in = input.replaceAll("\\s+", "*");
        while(in.length() > 0)
            in = interpretNext(in,is);
        return is;
    }

    public static String interpretNext(String in,InterpretedString query){
        int i = parseNumber(in);
        if(i < 1)
            i = parseVariable(in);
        if(i < 1)
            i = parseOperator(in);
        if(i < 1){
            return in.substring(1);
        }
        query.addLast(new Symbol(in.substring(0,i)));
        in = in.substring(i);
        return in;
    }

    private static int parseNumber(String in){
        boolean once = true;
        int i;
        for(i = 0;in.length() > i && (Character.isDigit(in.charAt(i)) || ( once && in.charAt(i) == '.' ));i++){
            if(in.charAt(i) == '.')
                once = false;
        }
        if(!in.substring(0, i).matches("[-+]?\\d*\\.?\\d+"))
            i--;
        return i;
    }

    private static int parseVariable(String in){
        char next = in.charAt(0);
        int i;
        if(!Character.isLetter(next))
            return 0;
        for(i = 0;Character.isLetterOrDigit(next) && in.length() > i;i++){
            next = in.charAt(i);
        }
        if(!Character.isLetterOrDigit(next))
            i--;
        return i;
    }

    private static int parseOperator(String in){
        char next = in.charAt(0);
        if(     next == '+' ||
                next == '-'||
                next == '*'||
                next == '%'||
                next == '/'||
                next == '\\'||
                next == '('||
                next == ')')
            return 1;
        return 0;
    }
}
