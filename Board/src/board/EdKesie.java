/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EdKesie extends KeyAdapter{
    Game parent;
    char editorKey = 'L';
    boolean largeSweep = false;

    public EdKesie(Game par){
        parent = par;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int action = e.getKeyCode();
        pressedAndEditor(action);
    }

    private void pressedAndEditor(int action){
        
        switch(action){
            case KeyEvent.VK_L: editorKey = 'L';
                return;
            case KeyEvent.VK_T: editorKey = 'T';
                return;
            case KeyEvent.VK_C: editorKey = 'C';
                return;
            case KeyEvent.VK_U: editorKey = 'U';
                return;
            case KeyEvent.VK_S: System.out.println(parent.board);
                return;
            case KeyEvent.VK_M: largeSweep = !largeSweep;
                return;
        }
        if(parent.board.selected != null){
            int ordinal = 0;
            switch(action){
                case KeyEvent.VK_1: ordinal = 0;
                    break;
                case KeyEvent.VK_2: ordinal = 1;
                    break;
                case KeyEvent.VK_3: ordinal = 2;
                    break;
                case KeyEvent.VK_4: ordinal = 3;
                    break;
                case KeyEvent.VK_5: ordinal = 4;
                    break;
                case KeyEvent.VK_6: ordinal = 5;
                    break;
                case KeyEvent.VK_7: ordinal = 6;
                    break;
                default: if(action != KeyEvent.VK_U ||
                            action != KeyEvent.VK_C ||
                            action != KeyEvent.VK_L ||
                            action != KeyEvent.VK_T)
                    return;
            }
            switch(editorKey){
                case 'L':
                    if(largeSweep){
                        parent.board.selected.setLand(Land.getLandByOrdinal(ordinal));
                        for(Tile t: parent.board.adjacents(parent.board.selected)){
                            t.setLand(Land.getLandByOrdinal(ordinal));
                        }
                    } else {
                        parent.board.selected.setLand(Land.getLandByOrdinal(ordinal));
                    }
                    break;
                case 'T':
                    if(largeSweep){

                    } else {

                    }
                    break;
                case 'C':
                    if(parent.board.selected.isCity()){
                        parent.board.selected.city.nation.removeCity(parent.board.selected.city);
                        break;
                    }
                    if(largeSweep){
                        parent.board.selected.setConstruct(Construct.getConstructByOrdinal(ordinal));
                        if(ordinal == 6){
                            if(parent.nations.get(parent.turnIndex).validCityTile(parent.board.selected))
                                parent.nations.get(parent.turnIndex).addCity(parent.board.selected);
                            break;
                        }
                        for(Tile t: parent.board.adjacents(parent.board.selected)){
                            t.setConstruct(Construct.getConstructByOrdinal(ordinal));
                        }
                    } else {
                        parent.board.selected.setConstruct(Construct.getConstructByOrdinal(ordinal));
                        if(ordinal == 6){
                            if(parent.nations.get(parent.turnIndex).validCityTile(parent.board.selected))
                                parent.nations.get(parent.turnIndex).addCity(parent.board.selected);
                        }
                    }
                    break;
                case 'U':
                    if(largeSweep){

                    } else {

                    }
                    break;
            }
        }
    }
}

