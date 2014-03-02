/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Scalene
 */
public abstract class NPC extends IObject{
    int areaid;
    int id;

    public NPC(Game game,int x,int y, int w, int h, int spd,int hp,int areaid, int id) {
        super(game,x,y,w,h,spd,hp);
        this.areaid = areaid;
        this.id = id;
    }

    public abstract void sense();
    
    @Override
    public void add() {
        game.npcs.add(this);
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(255,0,0,100));
        g.fill(self);
        g.drawLine(self.x + self.width/2, self.y + self.height/2,
                    self.x + self.width/2 + (int)(xdir*speed), self.y + self.height/2 + (int)(ydir*speed));
    }

    public void pIterate(){
        if(self.intersects(game.player.self)){
            pCollide(game.player);
        }
        if(game.boss!=null)
            if(game.boss.intersects(self))
                pCollide(game.boss);
        for(NPC np:game.npcs){
            if(np.self.intersects(self)&& !np.equals(this)){
                pCollide(np);
            }
        }
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
    }

    @Override
    public void die() {
        game.ga.getAreaById(areaid).vars.set(id, false);
    }
}
