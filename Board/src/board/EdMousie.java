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

public class EdMousie extends MouseAdapter{
    Game parent;

    public EdMousie(Game par){
        parent = par;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        editorPressed();
    }

    private void editorPressed(){
        
        if(parent.board.selected != null && parent.mousie.section == Constants.BOARDAREA){
            switch(parent.ekesie.editorKey){
                case 'L':
                    if(parent.ekesie.largeSweep){
                        parent.board.selected.setLand(parent.board.selected.getLand().cycle());
                        for(Tile t: parent.board.adjacents(parent.board.selected)){
                            t.setLand(t.getLand().cycle());
                        }
                    } else {
                        parent.board.selected.setLand(parent.board.selected.getLand().cycle());
                    }
                    break;
                case 'T':
                    if(parent.ekesie.largeSweep){

                    } else {

                    }
                    break;
                case 'C':
                    if(parent.board.selected.isCity()){
                        parent.board.selected.city.nation.removeCity(parent.board.selected.city);
                        break;
                    }
                    if(parent.ekesie.largeSweep){
                        parent.board.selected.setConstruct(parent.board.selected.getConstruct().cycle());
                        for(Tile t: parent.board.adjacents(parent.board.selected)){
                            t.setConstruct(t.getConstruct().cycle());
                        }
                    } else {
                        parent.board.selected.setConstruct(parent.board.selected.getConstruct().cycle());
                    }
                    break;
                case 'U':
                    if(parent.ekesie.largeSweep){

                    } else {

                    }
                    break;
            }
        }
    }
}

