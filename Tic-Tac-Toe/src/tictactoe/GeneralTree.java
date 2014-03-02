/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Sparky
 */
import java.util.LinkedList;

public class GeneralTree<T> {
    public GTNode<T> root;
    
    public static class GTNode<T>{
        public LinkedList<GTNode> children;
        public T data;
    }
}
