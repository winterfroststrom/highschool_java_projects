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

public class Kesie extends KeyAdapter{
    Game parent;

    public Kesie(Game par){
        parent = par;
        parent.action = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int action = e.getKeyCode();
        switch(action){
            case KeyEvent.VK_UP: parent.board.ydir = -15;
                return;
            case KeyEvent.VK_DOWN: parent.board.ydir = 15;
                return;
            case KeyEvent.VK_LEFT: parent.board.ydir = -parent.board.HEIGHT;
                return;
            case KeyEvent.VK_RIGHT: parent.board.ydir = parent.board.HEIGHT;
                return;
            case KeyEvent.VK_ENTER: parent.endTurn();
                return;
        }
        parent.action = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int action = e.getKeyCode();
        switch(action){
            case KeyEvent.VK_UP: parent.board.ydir = 0;
                break;
            case KeyEvent.VK_DOWN: parent.board.ydir = 0;
                break;
            case KeyEvent.VK_LEFT: parent.board.ydir = 0;
                break;
            case KeyEvent.VK_RIGHT: parent.board.ydir = 0;
                break;
        }
        parent.action = true;
    }
}
