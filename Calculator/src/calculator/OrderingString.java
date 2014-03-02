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
import java.util.LinkedList;
import java.util.ArrayList;


public class OrderingString {
    //order by creating a tree
    public OrderingString(InterpretedString input){
        order(input);
    }

    public static void main(String[]args){
        InterpretedString is = new InterpretedString();
        is.addLast(new Symbol("101"));//
        is.addLast(new Symbol("+"));//
        is.addLast(new Symbol("asd"));//
        order(is);
    }

    public static void order(InterpretedString input){
        ArrayList<Tree> t = new ArrayList<Tree>();
        int paraIndex = 0;
        t.add(paraIndex,new Tree());
        for(ListIterator<Symbol> li = input.listIterator();li.hasNext();){
            Symbol s = li.next();
            switch(s.type){
                case Symbol.n:
                case Symbol.v: t.get(paraIndex).addNew(s);
                    break;
                case Symbol.pm: t.get(paraIndex).addTop(s);
                    break;
                case Symbol.op: t.get(paraIndex).addNewInPlace(s);
                    break;
                case Symbol.sp: paraIndex++; t.add(paraIndex,new Tree());
                    break;
                case Symbol.ep: paraIndex--; t.get(paraIndex).addNewTree(t.get(paraIndex + 1));
                    break;
                case Symbol.o:
            }
        }
        System.out.println(t.get(paraIndex).toString());
        eval(t.get(paraIndex));
    }

    private static void eval(Tree t){
        Tree.Node n = t.root;
        System.out.println(eval2(n).toString());
    }

    private static Symbol eval2(Tree.Node n){
        if(n == null)
            return null;
        Symbol a1 = eval2(n.left);
        Symbol a2 = eval2(n.right);
        Symbol s = Symbol.operate(n.content, a1, a2);
        if(s != null){
            return s;
        }
        return n.content;
    }

    private static class Tree{
        Node root;
        Node rightmost;//rightmost never == null when root != null
        int size = 0;

        public void addNewInPlace(Symbol s){
            if(root!=null){
                size++;
                Node temp = new Node(rightmost.top,s);
                rightmost.top.right = temp;
                rightmost.top = temp;
                temp.left = rightmost;
                rightmost = temp;
            } else {
                addTop(s);
            }
        }

        public void addNew(Symbol s){
            if(root != null){
                size++;
                Node temp = new Node(rightmost,s);
                rightmost.right = temp;
                rightmost = temp;
            } else {
                addTop(s);
            }
        }

        public void addNewTree(Tree t){
            size += t.size;
            if(root != null){
                rightmost.right = t.root;
                t.root.top = rightmost;
                //System.out.println(t.rightmost);
                rightmost = t.root;

            } else {
                root = t.root;
                rightmost = t.rightmost;
            }
        }

        public void addTop(Symbol s){
            size++;
            Node temp = new Node(null,s);
            temp.left = root;
            if(root != null)
                root.top = temp;
            root = temp;
            rightmost = root;
        }

        @Override
        public String toString(){
            String string = "";
            LinkedList<Node> l = new LinkedList<Node>();
            if(root != null)
                l.addLast(root);
            while(!l.isEmpty()) {
                LinkedList<Node> w = new LinkedList<Node>();
                for(Node n:l) {
                    string += (n.toString() + "   ");
                    if(n.left != null)
                        w.addLast(n.left);
                    if(n.right != null)
                        w.addLast(n.right);
                }
                string += "\n";
                l = w;
            }
            return string;
        }

        public int getSize() {
            return size;
        }


        public static class Node{
            Node left;
            Node right;
            Node top;
            Symbol content;

            public Node(Node top, Symbol s){
                this.top = top;
                content = s;
            }
            @Override
            public String toString(){
                return content.toString();
            }
        }
    }
/*
    private static class DTree{
        Branch root;
        Branch current;

        public DTree() {
            root = new Branch(null);
            current = root;
        }

        public void add(ArrayList<Symbol> s){
            current.add(s);
        }

        public void newDeeper(){
            Branch newLevel = new Branch(current);
            current.add(newLevel);
            current = newLevel;
        }

        public void goUp(){
            current = current.up;
        }

        public static class Branch{
            Branch up;
            ArrayList<Node> nodes;

            public Branch(Branch up) {
                this.up = up;
                nodes = new ArrayList<Node>();
            }
            
            public void sort(){
                
            }

            public void add(ArrayList<Symbol> s){
                nodes.add(new Node(s));
            }

            public void add(Branch b){
                nodes.add(new Node(b));
            }

            public static class Node{
                ArrayList<Symbol> s;
                Branch b;
                public Node(ArrayList<Symbol> s) {
                    this.s = s;
                }
                public Node(Branch b) {
                    this.b = b;
                }
            }
        }
    }
    public OrderingString(InterpretedString input){
        Terming(Preterming(input));
    }

    private void Terming(ArrayList<Preterm> pt){
        int[] pl = new int[pt.size()];
        for(int i = 0;i<pt.size();i++){
            Preterm p = pt.get(i);
            if(p.isStartParenthesis()){
                pl[i] = 1;
            } else if(p.isEndParenthesis()){
                pl[i] = 2;
            } else if(p.isSeparater()){
                pl[i] = 3;
            }
        }
        for(int i = 0;i<pl.length;i++){
            // + t1 + t3 ( + t4 ( + t6 + t7 ) + t5 )
            // 3 00 3 00 1 3 00 1 3 00 3 00 2 3 00 2
            //
            if(pl[i] == 1){
                
            }
        }
    }
    
    private static class Term{
        public ArrayList<Preterm> arr;
        Preterm s;
        public Term(){
            arr = new ArrayList<Preterm>();
        }
        public Term(Preterm s){
            this.s = s;
        }
        public void add(Preterm s){
            if(arr == null)
                arr = new ArrayList<Preterm>();
            arr.add(s);
        }
    }

    private ArrayList<Preterm> Preterming(InterpretedString input){
        ArrayList<Preterm> arr = new ArrayList<Preterm>();
        Preterm t = new Preterm();
        for(ListIterator<Symbol> li = input.listIterator();li.hasNext();){
            Symbol s = li.next();
            if(s.string.matches("[+-]")){
                arr.add(t);
                t = new Preterm();
                t.add(s);
            } else if(s.isStartParenthesis() || s.isEndParenthesis()){
                arr.add(t);
                t = new Preterm(s);
                arr.add(t);
                t = new Preterm();
            } else {
                t.add(s);
            }
        }
        return arr;
    }

    private static class Preterm{
        public ArrayList<Symbol> arr;
        
        public Preterm(){
            arr = new ArrayList<Symbol>();
        }
        public Preterm(Symbol s){
            arr = new ArrayList<Symbol>();
            arr.add(s);
        }
        public void add(Symbol s){
            arr.add(s);
        }
        public boolean isStartParenthesis(){
            return arr.get(0).isStartParenthesis();
        }
        public boolean isEndParenthesis(){
            return arr.get(0).isEndParenthesis();
        }
        public boolean isSeparater(){
            return arr.get(0).string.matches("[+-]");
        }
    }*/
}
