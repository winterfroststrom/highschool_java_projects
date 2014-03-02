/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator.ArrayCalculator;

/**
 *
 * @author Scalene
 */
public class DoubleString {
    public final String a;
    public final String b;
    public final boolean initialized;

    public DoubleString(String inA, String inB){
        a = inA;
        b = inB;
        initialized = true;
    }

    public DoubleString(){
        a = "";
        b = "";
        initialized = false;
    }
}
