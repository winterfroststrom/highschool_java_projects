/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
    Game game;

    public Mouse(Game game) {
        this.game = game;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println(e.getPoint().x + "," + e.getPoint().y);
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
