/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author Sparky
 */
import java.util.LinkedList;
import java.util.Scanner;
//import javax.swing.JFileChooser;

public class MiscFunctions {
    public static void main(String[] args){
        //palindrome();//7
        //palindrome2();//13
        //mess();//15
        //place();//11
        //summ();
        //summ2()
        System.out.println(3 & 9);
    }
    public static int sdfa(String x, String y){
        int[] money ={100,200,300,500,1000,2000,4000,8000,16000,32000,64000,125000,250000,500000,1000000};
        if(y.charAt(y.length()-1) == 'W'){
            return money[y.length()-1];
        } else if(y.length() == 15){
            return money[15];
        } else if(y.length() > 10){
            return 32000;
        } else if(y.length() > 5){
            return 1000;
        }
        return 0;
        
    }
    
    public static void summ2(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("a?");
        String input1 = scanner.nextLine();
        System.out.println("b?");
        String input2 = scanner.nextLine();
        sum2(Integer.parseInt(input1),Integer.parseInt(input2));
    
    }
    public static void sum2(int x, int y){
        int sum = 0;
        for(int i = x; i <= y; i++){
            String s = String.valueOf(i);
            if(palin(s)){
                for(int j = 0; j < s.length();j++){
                    sum += Integer.parseInt(String.valueOf(s.charAt(j)));
                }
            }
        }
        System.out.println(sum);
    }
    public static void summ(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("a?");
        String input1 = scanner.nextLine();
        System.out.println("b?");
        String input2 = scanner.nextLine();
        System.out.println("n?");
        String input3 = scanner.nextLine();
        sum(Integer.parseInt(input1),Integer.parseInt(input2),Integer.parseInt(input3));
    
    }
    public static void sum(int x, int y, int n){
        int count = 0;
        for(int i = x; i <= y; i++){
            String s = String.valueOf(i);
            int sum = 0;
            for(int j = 0; j < s.length();j++){
                sum += Integer.parseInt(String.valueOf(s.charAt(j)));
            }
            if(sum % n == 0){
                count++;
            }
        }
        System.out.println(count);
    }
    
    public static void place(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("X?");
        String input1 = scanner.nextLine();
        System.out.println("Y?");
        String input2 = scanner.nextLine();
        prob(Integer.parseInt(input1),Integer.parseInt(input2));
    }
    
    public static void prob(int x, int y){
        int d = y - x;
        int[][] p = new int[6][6];
        for(int a = 1; a <=6;a++ ){
            for(int b = 1; b <=6;b++ ){
                p[a-1][b-1] = a + b;
            }
        }
        int count = 0;
        for(int a = 1; a <=6;a++ ){
            for(int b = 1; b <=6;b++ ){
                if(p[a-1][b-1] == d){
                    count++;
                }
            }
        }
        System.out.println(count/36.0);
    }
    
    
   public static void mess(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the message?");
        String input1 = scanner.nextLine();
        encrp(input1);
    }
    public static void encrp(String in){
        char[] c = in.toCharArray();
        for(int i = 0; i < c.length;i++){
            c[i] = (char)(((int)c[i]) + 1);
        }
        System.out.println(c);
    }
    
    public static void palindrome(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter Input Line by Line (Enter q to finish):");
        System.out.println("Enter X:");
        String input1 = scanner.nextLine();
        int i = Integer.parseInt(input1);
        String out = "";
        for(int j = 0; j <= i;j++){
            if(palin(String.valueOf(j))){
                out += j + " ";
            }   
        }
        System.out.println(out);
    }
    
    public static void palindrome2(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter Input Line by Line (Enter q to finish):");
        System.out.println("Enter palindrome:");
        String input1 = scanner.nextLine();
        if(palin(input1.replaceAll("\\w", ""))){
            System.out.println("Yes, Buzz, you have created a palindrome");
        } else {
            System.out.println("Sorry Buzz, head back to the drawing board");
        }
    }
    
    public static boolean palin(String s){
        for(int i = 0; i < s.length();i++){
            if(s.charAt(i) != s.charAt(s.length() - i - 1))
                return false;
        }
        return true;
    }
    
    public static void d(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter Input Line by Line (Enter q to finish):");
        System.out.println("Enter Distance:");
        String input1 = scanner.nextLine();
        System.out.println("Enter Tom speed:");
        String input2 = scanner.nextLine();
        System.out.println("Enter Tux speed:");
        String input3 = scanner.nextLine();
        
        System.out.println(dis(Double.parseDouble(input1),Integer.parseInt(input2),Double.parseDouble(input3)));
    }
    
    public static void trian(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter Input Line by Line (Enter q to finish):");
        System.out.println("Enter l1:");
        String input1 = scanner.nextLine();
        System.out.println("Enter r1:");
        String input2 = scanner.nextLine();
        System.out.println("Enter l2:");
        String input3 = scanner.nextLine();
        System.out.println(tri(Double.parseDouble(input1),Double.parseDouble(input2),Double.parseDouble(input3)));
    }
    
    public static double tri(double x, double rad,double y){
        return Math.sqrt(x*x + y*y - 2*x*y*Math.cos(rad));
    }
    
    public static void perf(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter Input Line by Line (Enter q to finish):");
        System.out.println("Enter X:");
        String input1 = scanner.nextLine();
        System.out.println("Enter Y:");
        String input2 = scanner.nextLine();
        System.out.println(perfect(Integer.parseInt(input1),Integer.parseInt(input2)));
    }
    
    public static String perfect(int x, int y){
        int i = 1;
        double p = Math.pow(i++, x);
        String out = "";
        while(p <= y){
            out += " " + p;
            p = Math.pow(i++, x);
        }
        return out;
    }
    
    public static double dis(double d, double x, double y){
        double total = 0;
        while(d > 0.000001){
            double time = distance(d,x,y);
            
            d -= 2*x*time;
            total += time*y*2;
        }
        return Math.round(total*10000)/10000.0;
    }
    
    public static double distance(double d, double x, double y){
       double time = d/(x+y);
       return time;
    }
    
    
    public static String factor(int n){
        if(n <= 1){
            return "";
        }
        LinkedList<Integer> ll = new LinkedList<Integer>();
        a:
        for(int i = 2; i <= n;i++){
            for(int j = 2; j <= i;j++){
                if(i == j){
                    ll.add(i);
                } else if(i%j == 0){
                    continue a;
                }
            }
        }
        String out = "";
        for(int i: ll){
            out += i + " ";
        }
        return out;
    }
    
    public static void prime(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter Input Line by Line (Enter q to finish):");
        System.out.println("Enter num1:");
        String input1 = scanner.nextLine();
        System.out.println("Enter num2:");
        String input2 = scanner.nextLine();
        System.out.println("Enter num3:");
        String input3 = scanner.nextLine();
        
        System.out.println(factor(Integer.parseInt(input1)) + "\n" + 
                            factor(Integer.parseInt(input2)) + "\n" + 
                            factor(Integer.parseInt(input3)));
    }
    
    public static void fact(){
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter Input Line by Line (Enter q to finish):");
        System.out.println("Enter num1:");
        String input1 = scanner.nextLine();
        System.out.println("Enter num2:");
        String input2 = scanner.nextLine();
        System.out.println("Enter num3:");
        String input3 = scanner.nextLine();
        
        System.out.println(factorial(Integer.parseInt(input1)) + "," + 
                            factorial(Integer.parseInt(input2)) + "," + 
                            factorial(Integer.parseInt(input3)));
    }
    public static int factorial(int i){
        if(i  < 2) return 1;
        int out = i;
        while(i > 1){
            i--;
            out *= i;
        }
        return out;
    }
    
}
