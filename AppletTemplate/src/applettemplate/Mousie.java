/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applettemplate;

/**
 *
 * @author Scalene
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Mousie extends MouseAdapter{
    Game parent;

    public Mousie(Game parent) {
        this.parent = parent;
    }
}
