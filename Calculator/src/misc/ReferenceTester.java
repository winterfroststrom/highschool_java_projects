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
import java.util.ArrayList;
import java.util.Collection;
public class ReferenceTester {
    
    public static void main(String[] args){
        System.out.println((7 & 0x4));
         
    }
    
    private static class Node<T>{
        T data;

        public Node(T data) {
            this.data = data;
            T[] a = (T[])new Object[5];
            try{
                System.out.println("asdf");
                return;
            } catch(NullPointerException x){
                throw x;
            } finally{
                System.out.println("asdf");
            }
        }

        @Override
        public String toString() {
            //if((? extends String) instanceof String[])
            Object o = new Object();
            return data.toString();
        }
    }
}
