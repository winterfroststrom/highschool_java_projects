/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.Color;
import java.awt.Graphics2D;

public class Torch extends Block{
    
    public Torch(Game game,int x,int y,int width,int height,boolean mobile,boolean carry){
        super(game,x,y,width,height,mobile,false);
        canCarry = true;
        isCarry = carry;
        this.carry = Element.fire;
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(200,200,200,100));
        g.fill(self);
        if(isCarry)
            g.setColor(Color.RED);
        else
            g.setColor(new Color(255,255,0,150));
        g.fill(push);
    }
}
