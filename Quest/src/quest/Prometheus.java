/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author Scalene
 */
public class Prometheus extends BossType{
    boolean armor;
    boolean stage1;
    int timer;

    public Prometheus(Game game) {
        super(game,game.ga.current.width-50,game.ga.current.height/2-10,20,20,20,20);
        stage1 = true;
        armor = true;
        ArrayList<String> dia = new ArrayList<String>();
        dia.add("Who goes there?");
        dia.add("You...Foul mage do not desecrate ");
        dia.add("these holy walls!");
        dia.add("Have at thee!");
        game.loadDia(dia);
    }

    @Override
    public void add() {
        game.boss = this;
    }

    @Override
    public void attack() {
        if(stage1)
            atk = new BloodCross(this,game.player.self.x+game.player.self.width/2,
                                game.player.self.y+game.player.self.height/2,false);
        else
            atk = new BloodCross(this,game.player.self.x+game.player.self.width/2,
                                game.player.self.y+game.player.self.height/2,true);
        game.atks.add(atk);
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(255,0,0,170));
        g.fillRect(self.x-2, self.y-2, self.width+4, self.height+4);
        g.setColor(Color.WHITE);
        g.fill(self);
    }

    @Override
    public void think() {
        if(health()<10&&stage1){
            game.ev.fireball = true;
            if(game.ev.playermaxatk < 2)
                game.ev.playermaxatk++;
            stage1 = false;
            timer = 30;
            speed = 15;
        }
        if(stage1){
            timer--;
            if(!armor){
                armor = true;
                timer = 50;
            }
            if(timer > 40)
                return;
            if(timer < 1){
                timer = 30;
                xdir = 0;
                ydir = 0;
            }
            if((atk == null || atk.isDead())&&timer<10){
                attack = true;
                double angle = Math.atan2((game.player.self.y+game.player.self.height/2)
                        - (self.y+self.height/2),(game.player.self.x+game.player.self.width/2)
                        - (self.x+self.width/2));
                xdir = Math.cos(angle);
                ydir = Math.sin(angle);
                timer = 15;
            }
        } else {
            if((atk == null || atk.isDead())&& timer % 15 == 0){
                attack = true;
                double angle = Math.atan2((game.player.self.y+game.player.self.height/2)
                        - (self.y+self.height/2),(game.player.self.x+game.player.self.width/2)
                        - (self.x+self.width/2));
                xdir = Math.cos(angle);
                ydir = Math.sin(angle);
            }
            timer--;
            if(timer == 0){
                timer = 59;
                xdir = 0;
                ydir = 0;
            }
        }
    }

    public void special(){
        
    }

    @Override
    public void die() {
        (new EventLocate(game,game.ga.current.width/2-15,0,30,5,Event.tarPlayer,0x01133)).add();
        game.ev.MidBoss = true;
    }

    @Override
    public boolean intersects(Shape s) {
        return self.intersects(s.getBounds());
    }

    @Override
    public void movedy(int deltay) {
        if(atk!= null)
            atk.area.transform(AffineTransform.getTranslateInstance(0, deltay));
        super.movedy(deltay);
    }

    @Override
    public void movedx(int deltax) {
        if(atk!= null)
            atk.area.transform(AffineTransform.getTranslateInstance(deltax,0));
        super.movedx(deltax);
    }


    @Override
    public void damage(int damage,Element e) {
        if(stage1){
            if(e != null)
                switch(e){
                    case fire: if(armor){armor = false;damage *= 2;}
                        break;
                }
            if(!armor)
                super.damage(damage,e);
        } else {
            if(e!=null&&e.equals(Element.fire))
                damage*=2;
            super.damage(damage,e);
        }
    }
}
