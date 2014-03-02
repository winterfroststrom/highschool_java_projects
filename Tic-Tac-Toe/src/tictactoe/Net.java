/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe;

/**
 *
 * @author Scalene
 */
import java.util.ArrayList;

public class Net {

    public static void main(String[]args){
        Net a = new Net();
        for(int i =0;i<30;i++){
            a.reset(3, 2);
            System.out.println(a.calc());
            a.compare(6);
            a.clear();
        }
        //a.reset(1, 2);
        //System.out.println(a.calc());
    }

    Perceptron a;
    Perceptron b;
    Perceptron c;

    public Net(){
        a = new Perceptron(null);
        b = new Perceptron(null);
        ArrayList<Perceptron> e = new ArrayList<Perceptron>();
        e.add(a);
        ArrayList<Perceptron> r = new ArrayList<Perceptron>();
        r.add(a);
        r.add(b);
        ArrayList<Perceptron> f = new ArrayList<Perceptron>();
        f.add(b);
        ArrayList<Perceptron> t = new ArrayList<Perceptron>();
        t.add(new Perceptron(e));
        t.add(new Perceptron(r));
        t.add(new Perceptron(f));
        c = new Perceptron(t);
    }
    double[] rates = new double[3];

    public void compare(int val){
        double diff = val - c.value;
        double rate = Math.abs(diff/val);
        rates[0] = Math.pow(rate, 2);
        rates[1] = Math.pow(rate, 3);
        rates[2] = Math.pow(rate, 4);
        boolean signed = diff >= 0;
        System.out.println(signed);
        change(c,2,signed);
    }

    private void change(Perceptron p,int rate,boolean signed){
        if(p.input == null)
            return;
        else {
            Perceptron minmax = minmax(p,signed);
            minmax.weight *= (rates[(rate > 0)? rate - 1 : rate] + ((signed)?1:0));
            //System.out.println(minmax.weight);
            for(Perceptron per:p.input){
                if(per != minmax){
                    per.weight *= (rates[rate] + ((signed)?1:0));
                }
                change(per,(rate > 0)? rate - 1 : rate,signed);
            }
        }
    }

    private Perceptron minmax(Perceptron p, boolean signed){
        Perceptron minmax = p.input.get(0);
        for(Perceptron per:p.input){
            if(signed){
                if(minmax.value > per.value)
                    minmax = per;
            } else {
                if(minmax.value < per.value)
                    minmax = per;
            }
        }
        return minmax;
    }

    public void clear(){
        clearing(c);
    }

    private void clearing(Perceptron p){
        if(p.input == null)
            return;
        else{
            for(Perceptron per: p.input){
                if(per.calc){
                    per.reset();
                    clearing(per);
                }
            }
        }
    }

    public int calc(){
        c.calc();
        return c.value;
    }

    public void reset(int q, int w){
        a.value = q;
        b.value = w;
        clear();
        a.calc = true;
        b.calc = true;
    }

    static class Perceptron{
        double weight = 1;
        int value;
        boolean calc = false;
        ArrayList<Perceptron> input;

        public Perceptron(ArrayList<Perceptron> in){
            input = in;
        }

        public void reset(){
            calc = false;
        }

        public void calc(){
            value = sum();
        }

        private int sum(){
            double sum = 0;
            for(Perceptron p:input){
                if(!p.calc)
                    p.calc();
                sum += (p.value*p.weight);
            }
            return (int)sum;
        }
    }
}
