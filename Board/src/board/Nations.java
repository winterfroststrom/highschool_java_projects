/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.util.ArrayList;

public class Nations extends ArrayList<Nation>{
    Game parent;
    

    public Nations(Game parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(Nation e) {
        if(isEmpty())
            parent.player = e;
        return super.add(e);
    }

    public boolean winner(){
        return size() < 2 || !contains(parent.player);
    }

    public boolean win(){
        return contains(parent.player);
    }
}
