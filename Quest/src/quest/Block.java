/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Block extends IObject{
    Rectangle push;
    boolean canCarry;
    boolean isCarry;
    Element carry;
    boolean mobile;
    boolean solid;

    public Block(Game game,int x,int y,int width,int height,boolean mobile,boolean solid){
        super(game,x,y,width,height,0,0);
        push = new Rectangle(x+6,y+6,width-12,height-12);
        this.mobile = mobile;
        this.solid = solid;
        canCarry = false;
        isCarry = false;
    }

    @Override
    public void add() {
        game.blocks.add(this);
    }

    @Override
    public void die() {}


    @Override
    public void think() {}

    @Override
    public boolean canAffect() {
        return false;
    }

    @Override
    public void reverseMove() {}

    @Override
    public void attack() {

    }

    @Override
    public void move(){
        previous.x = self.x;
        previous.y = self.y;
        double theta = Math.atan2((self.y+self.height/2)-(game.player.self.y+game.player.self.height/2),
                                    (game.player.self.x+game.player.self.width/2)-(self.x+self.width/2));
        if(Math.abs(theta) > 3*Math.PI/4-Math.PI/4 &&Math.abs(theta) < 3*Math.PI/4+Math.PI/4)
            return;
        if(Math.abs(theta) > Math.PI/4-Math.PI/4 && Math.abs(theta) < Math.PI/4+Math.PI/4)
            return;
        if(theta > 3*Math.PI/4 || theta < -3*Math.PI/4){
                movedx(5);
        } else if(theta > Math.PI/4) {
                movedy(5);
        } else if(theta > -Math.PI/4) {
                movedx(-5);
        } else {
                movedy(-5);
        }
    }

    @Override
    public void movedx(int deltax) {
        if(mobile){
            super.movedx(deltax);
            push.x += deltax;
        }
    }

    @Override
    public void movedy(int deltay) {
        if(mobile){
            super.movedy(deltay);
            push.y += deltay;
        }
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(200,200,200,100));
        g.fill(self);
        g.fill(push);
    }

    @Override
    public void pIterate() {
        if(mobile){
            if(self.intersects(game.player.self)){
                pCollide(game.player);
            }
            for(NPC np:game.npcs){
                if(np.self.intersects(self)){
                    pCollide(np);
                }
            }
            for(Wall w:game.walls){
                if(w.self.intersects(self)){
                    pCollide(w);
                }
            }
            for(Block b:game.blocks){
                if(b.self.intersects(self)&& !b.equals(this)){
                    pCollide(b);
                }
            }
        }
    }

    @Override
    public void damage(int damage, Element e) {
    
    }
}
