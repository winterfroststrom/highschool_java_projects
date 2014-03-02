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
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

public class Prometheus_The_Burning extends BossType{
    boolean armor;
    ArrayList<IronArcher> ia;
    int timer;
    int atktimer;
    int attackType;
    static final int bc = 1;
    static final int bs = 2;

    public Prometheus_The_Burning(Game game) {
        super(game,game.ga.current.width-50,game.ga.current.height/2-10,20,20,20,20);
        ia = new ArrayList<IronArcher>();
        armor = true;
        ArrayList<String> dia = new ArrayList<String>();
        dia.add("You may have bested me once...");
        dia.add("But not twice!");
        dia.add("Upon these walls I shall smear your blood");
        dia.add("in vengence of those you've killed.");
        game.loadDia(dia);
    }

    @Override
    public void add() {
        game.boss = this;
    }

    @Override
    public void attack() {
        if(attackType == bs)
            atk = new BloodSalvo(this,game.player.self.x+game.player.self.width/2,
                                game.player.self.y+game.player.self.height/2,true);
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
        for(Iterator<IronArcher> it=ia.iterator();it.hasNext();)
            if(it.next().isDead())
                it.remove();
        if(health()<10){
            special();
        }
        if(timer-- > 0){
            return;
        }
        if((atk == null || atk.isDead()) && atktimer < 1){
            if(35500 > Math.pow(self.x - game.player.self.x, 2) + Math.pow(self.y - game.player.self.y, 2)){
                attack = true;
                attackType = bc;
                atktimer = 15;
                speed = 20;
            } else {
                attack = true;
                attackType = bs;
                atktimer = 15;
                speed = 5;
            }
            double angle = Math.atan2((game.player.self.y+game.player.self.height/2)
                    - (self.y+self.height/2),(game.player.self.x+game.player.self.width/2)
                    - (self.x+self.width/2));
                xdir = Math.cos(angle);
                ydir = Math.sin(angle);
        }
        if(--atktimer < 1){
            xdir = 0;
            ydir = 0;
            timer = 10;
        }
    }

    @Override
    public void special(){
        if(ia.size()<1){
            ia.add(new IronArcher(game,50,50,0x0127,0));
            ia.add(new IronArcher(game,400,50,0x0127,0));
            ia.add(new IronArcher(game,50,400,0x0127,0));
            ia.add(new IronArcher(game,400,400,0x0127,0));
        }
        for(IronArcher a:ia)
            if(!game.npcs.contains(a))
                game.npcs.add(a);
    }

    @Override
    public void die() {
        for(IronArcher a:ia)
            if(game.npcs.contains(a))
                game.npcs.remove(a);
        (new EventLocate(game,game.ga.current.width/2-15,game.ga.current.height/2-15,
                30,30,Event.tarPlayer,0x01131)).add();
        game.ev.FTboss = true;
    }

    @Override
    public boolean intersects(Shape s) {
        return self.intersects(s.getBounds());
    }

    @Override
    public void movedy(int deltay) {
        if(atk!= null && attackType == bc)
            atk.area.transform(AffineTransform.getTranslateInstance(0, deltay));
        super.movedy(deltay);
    }

    @Override
    public void movedx(int deltax) {
        if(atk!= null && attackType == bc)
            atk.area.transform(AffineTransform.getTranslateInstance(deltax,0));
        super.movedx(deltax);
    }


    @Override
    public void damage(int damage,Element e) {
        if(e!=null && e.equals(Element.fire)){
            if(armor)
                armor = false;
            else
                damage *= 2;
        }
        if(armor)
            super.damage(damage/2, e);
        else
            super.damage(damage, e);
    }
}
