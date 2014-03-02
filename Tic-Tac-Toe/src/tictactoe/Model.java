/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Sparky
 */
public class Model {
    private int[] board;
    private int last;
    private int winner;
    private LinkedList<Point> moves;
    public static final int size = 3;
    public static final int p1 = -1;
    public static final String p1s = "O";
    public static final int p2 = 1;
    public static final String p2s = "X";
    public static final int none = 0;
    public static final String nones = "_";
    
    public Model(){
        board = new int[size * size];
        last = none;
        winner = none;
        moves = new LinkedList<Point>();
        for(int y = 0; y < Model.size;y++){
            for(int x = 0; x < Model.size;x++){
                moves.add(new Point(x,y));
            }
        }
    }
    
    public boolean isValidPlace(int x, int y){
        return inRangeX(x) && inRangeY(y);
    }
    
    public int getIndex(int x, int y){
        return x + y*size;
    }
    
    public boolean hasPlayer(int x, int y){
        return board[getIndex(x, y)] != none;
    }
    
    public int getLast(){
        return last;
    }
    
    public int getNext(){
        if(last == p1)
            return p2;
        return p1;
    }
    
    public void setPlayer(int player, int x, int y){
        board[getIndex(x,y)] = player;
        last = player;
        moves.remove(new Point(x,y));
    }
    
    private boolean inRangeX(int x){
        return board.length/size > x && x >= 0;
    }
    
    private boolean inRangeY(int y){
        return board.length/size > y && y >= 0;
    }
    
    public int getSize(){
        return size;
    }
    
    public int getPlayer(int x, int y){
        return board[getIndex(x,y)];
    }
    
    public String getPlayerString(int x, int y){
        switch(board[getIndex(x,y)]){
            case p1: return p1s;
            case p2: return p2s;
            default: return nones;
        }
    }
    
    public int hasHorizontalWin(){
        for(int y = 0; y < size;y++){
            boolean win = getPlayer(0,y) != none;
            for(int x = 1; x < size; x++){
                win = getPlayer(x-1,y)==getPlayer(x,y) && win;
                if(!win) break;
            }
            if(win) {
                winner = getPlayer(0,y);
                return winner;
            }
        }
        return none;
    }
    
    public int hasVerticalWin(){
        for(int x = 0; x < size;x++){
            boolean win = getPlayer(x,0) != none;
            for(int y = 1; y < size; y++){
                win = getPlayer(x,y)==getPlayer(x,y - 1) && win;
                if(!win) break;
            }
            if(win) {
                winner = getPlayer(x,0);
                return winner;
            }
        }
        return none;
    }
    
    public int hasDiagnal1Win(){
        int x = 0;
        int y = 0;
        boolean win = getPlayer(x,y) != none;
        for(int i = 1; i < size; i++){
            win = getPlayer(x,y)==getPlayer(i, i) && win;
            if(!win) break;
        }
        if(win) {
            winner = getPlayer(x,y);
            return winner;
        }
        return none;
    }
    
    public int hasDiagnal2Win(){
        int x = 0;
        int y = size - 1;
        boolean win = getPlayer(x,y) != none;
        for(int i = 1; i < size; i++){
            win = getPlayer(x,y)==getPlayer(i, size - i - 1) && win;
            if(!win) break;
        }
        if(win) {
            winner = getPlayer(x,y);
            return winner;
        }
        return none;
    }
    
    public int hasWin(){
        if(winner != none) return winner;
        winner = hasHorizontalWin();
        if(winner != none) return winner;
        winner = hasVerticalWin();
        if(winner != none) return winner;
        winner = hasDiagnal1Win();
        if(winner != none) return winner;
        winner = hasDiagnal2Win();
        if(winner != none) return winner;
        return none;
    }

    @Override
    public Model clone() throws CloneNotSupportedException {
        Model t = new Model();
        for(int y = 0;y < size;y++){
            for(int x = 0;x < size;x++){
                if(hasPlayer(x,y)){
                    t.board[getIndex(x,y)] = getPlayer(x,y);
                }
            }
        }
        t.last = last;
        return t;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Model)) return false;
        Model t = (Model) obj;
        if(t.last == last)
        for(int y = 0;y < size;y++){
            for(int x = 0;x < size;x++){
                if(getPlayer(x,y) != t.getPlayer(x, y))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Arrays.hashCode(this.board);
        hash = 37 * hash + this.last;
        return hash;
    }
    
    @Override
    public String toString(){
        String out = "\n";
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                out += getPlayerString(x,y);
            }
            out += "\n";
        }
        return out;
    }
    
    public LinkedList<Point> getPossibleMoves(){
        LinkedList<Point> out = new LinkedList<Point>();
        for(Point move : moves){
            out.add(new Point(move.x, move.y));
        }
        return out;
    }
    
    public boolean hasMove(){
        return moves.size() > 0;
    }
    
    public Model getNextState(Point move){
        try {
            Model state = clone();
            if(!state.hasPlayer(move.x, move.y))
                state.setPlayer(state.getNext(), move.x, move.y);
            return state;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Map<Point,Model> getPossibleStates(){
        Map<Point,Model> states = new HashMap<Point,Model>();
        for(Point move: moves){
            states.put(move,getNextState(move));
        }
        return states;
    }
}
