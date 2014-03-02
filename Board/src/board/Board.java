/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.BasicStroke;

public class Board {
    Game parent;
    final static Color SLINE = new Color(200,245,200);
    final static Color HLINE = new Color(150,215,215);
    final int WIDTH;
    final int HEIGHT;
    final int OFFSETX;
    final int OFFSETY;
    
    Tile[][] board;

    Tile selected;
    Tile hover;
    BufferedImage part;
    Graphics2D gprt;
    int yrel;
    int ydir;

    public Board(Game par,Land[][] lMap,Construct[][] cMap){
        parent = par;
        int width = (int)(parent.top.HEXLEN*(parent.top.BW*3/2.+.5));
        OFFSETX = (parent.top.G.width - width)/2;
        OFFSETY = parent.top.HEXLEN;
        WIDTH = width + OFFSETX*2;
        HEIGHT = (int)(parent.top.HEXLEN*Math.sin(Math.PI/3)*parent.top.BH*2) + OFFSETY*2 + 100;
        
        yrel = 0;
        ydir = 0;

        part = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        gprt = (Graphics2D) part.getGraphics();
        gprt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        makeBoard(lMap,cMap);
    }
    
    private void makeBoard(Land[][] lMap,Construct[][] cMap){
        board = new Tile[parent.top.BW][];
        for(int i = 0;i<board.length;i++){
            int k = (i % 2);
            board[i] = new Tile[parent.top.BH-k];
            for(int j = 0;j<board[i].length;j++){
                Point temp = new Point(OFFSETX+parent.top.HEXLEN+i*(3*parent.top.HEXLEN)/2,
                                        OFFSETY+parent.top.HEXLEN+(int)((j+k/2.)*2*(parent.top.HEXLEN*Math.sin(Math.PI/3))));
                if(lMap != null && cMap != null){
                    board[i][j] = new Tile(temp,parent.top.HEXLEN,i,j,lMap[i][j],cMap[i][j]);
                } else {
                    board[i][j] = new Tile(temp,parent.top.HEXLEN,i,j,Land.Plains,Construct.Void);
                }
            }
        }
    }

    public Tile getPressedTile(int x,int y){
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board[i].length;j++){
                if(board[i][j].getHex().contains(x, y)){
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public ArrayList<Tile> adjacents(Tile t){
        ArrayList<Tile> adjs = new ArrayList<Tile>();
        if(topRight(t) != null)
            adjs.add(topRight(t));
        if(topLeft(t) != null)
            adjs.add(topLeft(t));
        if(bottom(t) != null)
            adjs.add(bottom(t));
        if(bottomRight(t) != null)
            adjs.add(bottomRight(t));
        if(bottomLeft(t) != null)
            adjs.add(bottomLeft(t));
        if(top(t) != null)
            adjs.add(top(t));
        return adjs;
    }

    public Tile getAdjacent(Tile t, int location){
        switch(location){
            case Constants.bottom:
                return bottom(t);
            case Constants.bottomLeft:
                return bottomLeft(t);
            case Constants.bottomRight:
                return bottomRight(t);
            case Constants.top:
                return top(t);
            case Constants.topLeft:
                return topLeft(t);
            case Constants.topRight:
                return topRight(t);
        }
        return null;
    }

    public Tile top(Tile t){
        if(inBounds(t.x,t.y-1)){
            return board[t.x][t.y-1];
        }
        return null;
    }
    public Tile bottom(Tile t){
        if(inBounds(t.x,t.y+1)){
            return board[t.x][t.y+1];
        }
        return null;
    }
    public Tile topLeft(Tile t){
        int w = (t.x%2==1)?1:0;
        if(inBounds(t.x-1,t.y+w-1)){
            return board[t.x-1][t.y+w-1];
        }
        return null;
    }
    public Tile bottomLeft(Tile t){
        int w = (t.x%2==1)?1:0;
        if(inBounds(t.x-1,t.y+w)){
            return board[t.x-1][t.y+w];
        }
        return null;
    }
    public Tile topRight(Tile t){
        int w = (t.x%2==1)?1:0;
        if(inBounds(t.x+1,t.y+w-1)){
            return board[t.x+1][t.y+w-1];
        }
        return null;
    }
    public Tile bottomRight(Tile t){
        int w = (t.x%2==1)?1:0;
        if(inBounds(t.x+1,t.y+w)){
            return board[t.x+1][t.y+w];
        }
        return null;
    }

    private boolean inBounds(int x,int y){
        if(x < 0 || x > board.length-1)
            return false;
        if(y < 0 || y > board[x].length-1)
            return false;
        return true;
    }

    private int inYBounds(int y){
        if(y < 0){
            ydir = 0;
            return 0;
        }
        if(y > HEIGHT - parent.top.G.height){
            ydir = 0;
            return HEIGHT - parent.top.G.height;
        }
        return y;
    }

    public void paintPart(Graphics2D g){
        if(ydir != 0)
            yrel = inYBounds(yrel + ydir);
        gprt.clearRect(0, 0, WIDTH, HEIGHT);
        if(parent.change){
            for(int i = 0;i < board.length;i++){
                for(int j = 0;j < board[i].length;j++){
                    board[i][j].change();
                    cycleAction(i,j);
                }
            }
            parent.change = false;
        } else {
            for(int i = 0;i < board.length;i++){
                for(int j = 0;j < board[i].length;j++){
                    cycleAction(i,j);
                }
            }
        }
        for(Nation n:parent.nations){
            for(Tile t:n.getInfluence()){
                gprt.setColor(n.color);
                gprt.draw(Geometry.makeNation(t));
            }
        }
        hoverSelect();
        g.drawImage(part, 0, 0, parent.top.G.width, parent.top.G.height,
                        0, yrel, WIDTH, parent.top.G.height + yrel, null);
    }

    private void cycleAction(int i, int j){
        gprt.setStroke(new BasicStroke(2));

        gprt.setColor(board[i][j].getLand().fill);
        gprt.fill(board[i][j].getInner());
        gprt.setColor(board[i][j].getLand().line);
        gprt.draw(board[i][j].getHex());

        if(board[i][j].hasUnits()){
            gprt.setColor(board[i][j].getANation().color);
            gprt.fill(Geometry.makeStack(board[i][j]));
        }
    }

    private void hoverSelect(){
        if(selected != null){
            gprt.setStroke(new BasicStroke(4));
            gprt.setColor(SLINE);
            gprt.draw(selected.getHex());
        }
        if(hover != null){
            gprt.setStroke(new BasicStroke(3));
            gprt.setColor(HLINE);
            gprt.draw(hover.getHex());
        }
    }

    public void addUnit(Unit u){
        board[u.tile.x][u.tile.y].addUnit(u);
    }

    public void removeUnit(Unit u){
        board[u.tile.x][u.tile.y].removeUnit(u);
    }

    @Override
    public String toString() {
        String all = "Land[][] lMap = \n{";
        for(int i = 0;i<board.length;i++){
            all += "{";
            for(int j = 0;j<board[i].length;j++){
                if(j!=0)
                    all += ",";
                all += ("Land." + board[i][j].getLand());
            }
            all += "},\n";
        }
        all += "};\nConstruct[][] cMap = \n{";
        for(int i = 0;i<board.length;i++){
            all += "{";
            for(int j = 0;j<board[i].length;j++){
                if(j!=0)
                    all += ",";
                all += ("Construct." + board[i][j].getConstruct());
            }
            all += "},\n";
        }
        all += "};\n";
        return all;
    }
}
