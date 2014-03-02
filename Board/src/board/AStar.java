/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.util.TreeSet;
import java.util.LinkedList;

public class AStar {

    public static LinkedList<Tile> path(Game parent, Tile start,Tile end){
        TreeSet<TileNode> closed = new TreeSet<TileNode>();
        TreeSet<TileNode> open = new TreeSet<TileNode>();
        open.add(new TileNode(start));
        while(!open.isEmpty()){
            TileNode x = open.first();
            if(x.node.equals(end))
                return createPath(x);
            open.remove(x);
            closed.add(x);
            for(Tile t: parent.board.adjacents(x.node)){
                TileNode y = new TileNode(t);
                for(TileNode r:closed){
                    if(r.equals(y))
                        continue;
                }
                int g = x.g + y.node.getMoveCost();
                boolean isBetter = true;
                if(open.contains(y) && g > y.g)
                    isBetter = false;
                if(isBetter){
                    y.origin = x;
                    y.g = g;
                    y.h = hCost(y.node,end);
                    y.f = y.g + y.h;
                    open.add(y);
                }
            }
        }
        return null;
    }

    static private class TileNode implements Comparable{
        Tile node;
        TileNode origin;
        int g;
        int h;
        int f;

        public TileNode(Tile node) {
            this.node = node;
        }

        public boolean hasOrigin(){
            return origin != null;
        }

        @Override
        public boolean equals(Object obj) {
            if(!obj.getClass().equals(this.getClass()))
                return false;
            return node.equals(((TileNode)obj).node);
        }

        @Override
        public int hashCode() {
            return node.hashCode();
        }

        public int compareTo(Object o) {
            if(((TileNode) o).equals(this))
                return 0;
            return f - ((TileNode) o).f;
        }

        @Override
        public String toString() {
            return "["+g+" "+h+" "+f+"]";
        }
    }

    private static LinkedList<Tile> createPath(TileNode begin){
        LinkedList<Tile> out = new LinkedList<Tile>();
        while(begin.hasOrigin()){
            out.add(begin.node);
            begin = begin.origin;
        }
        return out;
    }

    private static int hCost(Tile start,Tile end){
        int x = Math.abs(start.x - end.x);
        int y = Math.abs(start.y - end.y);
        if(x > y)
            return 5 * y + 4 * (x - y);
        else
            return 5 * x + 4 * (y - x);
    }
}
