/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

/**
 *
 * @author Scalene
 */
public class Symbol {
    final String string;
    final static int op = 0;
    final static int pm = 1;
    final static int n = 2;
    final static int v = 3;
    final static int sp = 4;
    final static int ep = 5;
    final static int o = 6;
    final int type;

    public Symbol(String string) {
        this.string = string;
        if(isPlusMinus(string)){
            type = pm;
        } else if(isEndParenthesis(string)){
            type = ep;
        } else if(isStartParenthesis(string)){
            type = sp;
        } else if(isNumber(string)){
            type = n;
        } else if(isVariable(string)){
            type = v;
        } else if(isOperator(string)){
            type = op;
        } else {
            type = o;
        }
        //System.out.println("Symbol: " + type);
    }

    public static boolean isOperator(String string){
        return string.matches("[+\\-\\*%/\\\\]");
    }

    public static boolean isPlusMinus(String string){
        return string.matches("[+-]");
    }

    public static boolean isNumber(String string){
        return  string.matches("[-+]?\\d*\\.?\\d+");
    }

    public static boolean isVariable(String string){
        return  string.matches("\\w+");
    }

    public static boolean isStartParenthesis(String string){
        return  string.matches("[(]");
    }

    public static boolean isEndParenthesis(String string){
        return  string.matches("[)]");
    }

    public static boolean isParenthesis(String string){
        return  string.matches("[()]");
    }

    public static boolean other(String string){
        return !isNumber(string)&&!isOperator(string) && !isParenthesis(string);
    }

    public static Symbol operate(Symbol s1, Symbol s2, Symbol s3){
        if(s1 != null && s2 != null && s3 != null){
            if((s1.type == Symbol.op || s1.type == Symbol.pm) && s2.type == Symbol.n && s3.type == Symbol.n){
                double d1 = Double.parseDouble(s2.string);
                double d2 = Double.parseDouble(s3.string);
                if(s1.string.matches("[+]")){
                    return new Symbol(String.valueOf(d1 + d2));
                } else if(s1.string.matches("[-]")){
                    return new Symbol(String.valueOf(d1 - d2));
                } else if(s1.string.matches("[*]")){
                    return new Symbol(String.valueOf(d1 * d2));
                } else if(s1.string.matches("[/]")){
                    return new Symbol(String.valueOf(d1 / d2));
                } else if(s1.string.matches("[^]")){
                    return new Symbol(String.valueOf(Math.pow(d1, d2)));
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return string;
    }
}
