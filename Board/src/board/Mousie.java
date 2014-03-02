/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mousie extends MouseAdapter{
    Game parent;
    int section;

    public Mousie(Game par){
        parent = par;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if(section == 1){
            parent.board.selected = parent.board.hover;
            if(parent.board.selected != null)
                parent.units.setTile(parent.board.selected);
        } else if(section == 0){
            if(parent.top.M.contains(e.getPoint())){
                parent.control.pressEvent(e.getPoint());
            } else if(parent.top.L.contains(e.getPoint())){
                parent.units.pressEvent(e.getPoint());
            }
        }
        parent.action = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        parent.units.releaseEvent();
        parent.action = true;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        
        parent.units.dragEvent(e.getPoint());
        parent.action = true;
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        
        if(parent.top.G.contains(e.getPoint())){
            section = Constants.BOARDAREA;
            parent.board.hover = parent.board.getPressedTile(e.getX(),e.getY()+parent.board.yrel);
        } else if(parent.top.D.contains(e.getPoint())){
            section = Constants.OTHERAREA;
            parent.board.hover = null;
        }
        parent.action = true;
    }
}
