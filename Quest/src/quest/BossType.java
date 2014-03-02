/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.Shape;

public abstract class BossType extends IObject{
    public BossType(Game game,int x,int y, int w, int h, int spd,int hp) {
        super(game,x,y,w,h,spd,hp);
    }

    @Override
    public boolean canAttack() {
        if(attack){
            attack = false;
            return true;
        }
        return false;
    }

    public abstract void special();

    @Override
    public void pIterate() {
        for(Wall w:game.walls){
            if(w.self.intersects(self)){
                pCollide(w);
            }
        }
        for(Block b:game.blocks){
            if(b.self.intersects(self)){
                pCollide(b);
            }
        }
        if(intersects(game.player.self))
            pCollide(game.player);
    }

    public abstract void die();

    public abstract boolean intersects(Shape s);
}
