/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

/**
 *
 * @author Scalene
 */
import java.util.ListIterator;

public class Analyzer {
    public static InterpretedString analyze(InterpretedString input){
        input.addFirst(new Symbol("+"));
        easyReduction(input);
        extraReduction(input);
        addImplicitMultiplication(input);
        fixParenthesis(input);
//        order(input);
        //findUranary(input);
        easyAddition(input);
        return input;
    }

    private static void order(InterpretedString input){
        
    }

    private static void easyReduction(InterpretedString input){
        //remove [+-]+ combos
        for(ListIterator<Symbol> it = input.listIterator();it.hasNext();){
            Symbol current = it.next();
            if(it.hasPrevious()){
                Symbol last = it.previous();
                it.next();
                if(last.type == Symbol.pm && current.type == Symbol.pm){
                    it.set(last.string.equals(current.string)?new Symbol("+"):new Symbol("-"));
                    it.previous();
                    it.remove();
                }
            }
        }
    }

    private static void extraReduction(InterpretedString input){
        //remove stuff [+-] void combos to implement
        for(ListIterator<Symbol> it = input.listIterator();it.hasNext();){
            Symbol current = it.next();
            if(current.type == Symbol.op||current.type == Symbol.pm){
                if(it.hasNext()){
                    Symbol check = it.next();
                    it.previous();
                    if((check.type == Symbol.op || current.type == Symbol.pm || check.type == Symbol.ep) &&
                            !(current.type == Symbol.ep || current.type == Symbol.sp))
                        it.remove();
                } else
                    it.remove();
            }
        }
    }

    private static void addImplicitMultiplication(InterpretedString input){
        // add * in var/num var/num and var/num ( and ) var/num
        //var num
        //var var
        //num var
        //num num
        //num (
        //) var
        //) num
        //) (
        //var ( DNE -> function
        //( ) DNE -> invalid
        //var ) DNE -> no need
        //num ) DNE -> no need
        //) ) DNE -> no need
        //( ( DNE -> no need
        //( var DNE -> no need
        //( num DNE -> no need
        for(ListIterator<Symbol> it = input.listIterator();it.hasNext();){
            Symbol current = it.next();
            if(it.hasNext()){
                Symbol next = it.next();
                if(current.type == Symbol.v){
                    if(next.type == Symbol.v || next.type == Symbol.n){
                        it.previous();
                        it.add(new Symbol("*"));
                    }
                } else if(current.type == Symbol.n || current.type == Symbol.ep){
                    if(next.type == Symbol.v || next.type == Symbol.n || next.type == Symbol.sp){
                        it.previous();
                        it.add(new Symbol("*"));
                    }
                } else {
                    it.previous();
                }
            }
        }

    }

    private static void fixParenthesis(InterpretedString input){
        int p = 0;
        ListIterator<Symbol> it = input.listIterator();
        Symbol current = null;
        while(it.hasNext()){
            current = it.next();
            if(current.type == Symbol.sp){
                p++;
            } else if(current.type == Symbol.ep){
                p--;
                if(p < 0)
                    it.remove();
            }
        }
        if(current.type == Symbol.sp)
            it.remove();
        while(it.hasPrevious()){
            current = it.previous();
            if(current.type == Symbol.ep){
                p++;
            } else if(current.type == Symbol.sp){
                p--;
                System.out.println("asd");
                if(p < 0)
                    it.remove();
            }
        }
    }

    private static void findUranary(InterpretedString input){
        
    }

    private static void easyAddition(InterpretedString input){
        for(ListIterator<Symbol> it = input.listIterator();it.hasNext();){
            Symbol current = it.next();
            Symbol previous = null;
            Symbol next = null;
            boolean valid1 = false;
            boolean valid2 = false;
            if(current.type == Symbol.pm){
                if(it.hasPrevious()){
                    previous = it.previous();
                    if(previous.type == Symbol.n){
                        if(it.hasPrevious()){
                            previous = it.previous();
                            if(previous.type == Symbol.pm)
                                valid1 = true;
                            previous = it.next();
                        } else if(it.previousIndex() < 0)
                            valid1 = true;
                    }
                    it.next();
                }

                if(it.hasNext()){
                    next = it.next();
                    if(next.type == Symbol.n){
                        if(it.hasNext()){
                            next = it.next();
                            if(next.type == Symbol.pm)
                                valid2 = true;
                            next = it.previous();
                        } else if(it.nextIndex() >= input.size-1)
                            valid2 = true;
                    }
                    it.previous();
                }

                if(valid1 && valid2){
                    it.next();
                    it.remove();
                    it.previous();
                    it.previous();
                    it.remove();
                    it.next();
                    it.set(new Symbol(String.valueOf(add(previous.string,current.string,next.string))));
                }
            }
                
        }
        
    }

    private static double add(String a, String o, String b){
        if(a.matches("[-+]?\\d*\\.?\\d+") && b.matches("[-+]?\\d*\\.?\\d+") && o.matches("[+-]")){
            int sign = 0;
            if(o.equals("+"))
                sign = 1;
            else sign = -1;
            return Double.parseDouble(a) + sign*Double.parseDouble(b);
        }
        return Integer.MIN_VALUE;
    }

    private static InterpretedString equationSolver(InterpretedString input){
        return null;
    }

    private static InterpretedString expressionSolver(InterpretedString input){
        return null;
    }
}
