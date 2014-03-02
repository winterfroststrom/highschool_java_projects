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

public class InterpretedString {
    Node start;
    Node end;
    int size;

    public void addFirst(Symbol s){
        size++;
        if(start == null)
            start = new Node(null,s,null);
        else {
            Node newstart = new Node(null,s,start);
            start.previous = newstart;
            start = newstart;
        }
    }

    public void addLast(Symbol s){
        size++;
        if(start == null)
            start = new Node(null,s,null);
        else if(end == null){
            end = new Node(start,s,null);
            start.next = end;
        } else {
            Node newend = new Node(end,s,null);
            end.next = newend;
            end = newend;
        }
    }

    @Override
    public String toString() {
        String out = "";
        Node p = start;
        while(p != null){
            out += p.toString();
            p = p.next;
        }
        return out;
    }

    public Symbol getByIndex(int index){
        if(index < 0)
            return null;
        It it = new It(this);
        while(it.index < index){
            if(!it.hasNext())
                return null;
            it.next();
        }
        return it.current.s;
    }

    public Symbol[] toArray(){
        Symbol[] out = new Symbol[size];
        int i = 0;
        for(ListIterator<Symbol> it = listIterator();it.hasNext();){
            out[i++] = it.next();
        }
        return out;
    }

    private static class Node{
        Node next;
        Node previous;
        Symbol s;

        public Node(Node previous, Symbol s, Node next) {
            this.next = next;
            this.previous = previous;
            this.s = s;
        }

        @Override
        public String toString() {
            return s.toString();
        }
    }

    public ListIterator<Symbol> listIterator(){
        return new It(this);
    }

    

    private static class It implements ListIterator<Symbol>{
        Node current;
        int index;
        boolean loaded;
        InterpretedString is;

        public It(InterpretedString is) {
            this.current = is.start;
            this.is = is;
            loaded = false;
            index = 0;
        }

        public void add(Symbol s) {
            is.size++;
            index++;
            current = new Node(current,s,current.next);
            if(hasPrevious())
                current.previous.next = current;
            if(hasNext())
                current.next.previous = current;
        }

        public boolean hasNext() {
            //System.out.println(current.s + "Next: " + current.next);
            if(!loaded){
                return current != null;
            }
            return current != null && current.next != null;
        }

        public boolean hasPrevious() {
            return current != null && current.previous != null;
        }

        public Symbol next() {
            if(!loaded){
                loaded = true;
                return current.s;
            }
            index++;
            current = current.next;
            return current.s;
        }

        public int nextIndex() {
            return index + 1;
        }

        public Symbol previous() {
            index--;
            current = current.previous;
            return current.s;
        }

        public int previousIndex() {
            return index - 1;
        }

        public void remove() {
            //java standard implementation details
            if(current == null)
                return;
            is.size--;
            index--;
            if(hasNext())
                current.next.previous = current.previous;
            if(hasPrevious()){
                current.previous.next = current.next;
            } else {
                is.start = hasNext()?current.next:null;
            }
        }

        public void set(Symbol s) {
            current.s = s;
        }
    }
}
