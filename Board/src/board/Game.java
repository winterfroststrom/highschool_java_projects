/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;

public class Game implements Runnable{
    Main top;
    Board board;
    Nations nations;
    Nation player;
    Calculator calc;
    int turnIndex;

    Kesie kesie;
    Mousie mousie;
    EdKesie ekesie;
    EdMousie emousie;

    List units;
    TileInfo tinfo;
    CityInfo cinfo;
    Control control;

    boolean winner = false;
    
    boolean change = false;

    boolean action = false;

    public Game(Main par){
        top = par;
        mousie = new Mousie(this);
        kesie = new Kesie(this);
        if(top.editor){
            emousie = new EdMousie(this);
            ekesie = new EdKesie(this);
        }
        tinfo = new TileInfo(this);
        cinfo = new CityInfo(this);
        units = new List(this,new Point(top.L.x, top.L.y));
        control = new Control(this);

        init();
    }

    private void init(){
        Land[][] lMap = null;
        Construct[][] cMap = null;

        board = new Board(this,lMap,cMap);
        nations = new Nations(this);

        nations.add(new NationImp(this,board.board[8][3],NationEnum.Darklands));

        nations.add(new NationImp(this,board.board[3][3],NationEnum.Templars_of_Work));
        nations.get(1).createAI();

        board.addUnit(new Fodder(nations.get(turnIndex),board.board[8][3]));
        Unit u = new Fodder(nations.get(turnIndex),board.board[6][1]);
        u.setPath(board.board[3][3]);
        board.addUnit(u.init());
        board.addUnit(new Fodder(nations.get(1),board.board[3][3]).init());
        
        calc = new Calculator(this);
        turnIndex = 0;
    }

    @Override
    public void run(){
        while(!winner){
            //long elapse = System.nanoTime();
            if(action){
                top.repaint();
                action = false;
            }
            if(turnIndex != 0){
                nations.get(turnIndex).play();
                endTurn();
            }
            //elapse = System.nanoTime() - elapse;
            //elapse /= (1000*100);
            try{
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        top.repaint();
    }

    public void paint(Graphics2D gr){
        board.paintPart(gr);
        gr.setColor(Color.WHITE);
        gr.setStroke(new BasicStroke(5));
        gr.drawLine(0, top.G.height, top.A.width, top.G.height);
        units.paintPart(gr);
        tinfo.paintPart(gr);
        cinfo.paintPart(gr);
        control.paintPart(gr);
    }

    public void endTurn(){
        nations.get(turnIndex).endTurn();
        if(nations.get(turnIndex).isDead()){
            nations.get(turnIndex).die();
            nations.remove(nations.get(turnIndex));
            if(nations.winner()){
                winner = true;
                nations.win();
            }
        }
        if(turnIndex == 0){
            change = true;
        }
        turnIndex = (turnIndex + 1) % nations.size();
        calc.refresh();
    }
}
