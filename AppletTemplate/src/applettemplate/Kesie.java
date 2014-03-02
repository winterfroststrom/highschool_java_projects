/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applettemplate;

/**
 *
 * @author Scalene
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Kesie extends KeyAdapter{
    Game parent;

    public Kesie(Game parent) {
        this.parent = parent;
    }
    
}
