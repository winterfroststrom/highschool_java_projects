/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

/**
 *
 * @author Scalene
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(EulersMethod(1,.5,0,1));
        System.out.println(EulersMethod(2,1,0,.5));
        System.out.println(EulersMethod(2,.5,0,.5));
        System.out.println(EulersMethod(2,.1,0,.5));
        System.out.println(EulersMethod(2,.0000001,0,.5));
    }
    static double EulersMethod(double y, double dx, double start, double end){
        double temp,x;
        for(x = start;x < end;x += dx){
            temp = -y * Math.cos(Math.PI*y/3.6);
            y += temp * dx;
        }
        return y;
    }
}
