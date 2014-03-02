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
public class Player extends IObject{
    private boolean mChanged;
    final double angle = Math.cos(Math.PI/4);
    double sangle;
    int attackSet;
    static final int staff = 0;
    static final int windb = 1;
    static final int fireb = 2;
    int sx;
    int sy;
    int tdir;

    public Player(Game game) {
        super(game, 0, 0, 20, 20,10,10);
    }

    @Override
    public void add() {
//        self.x =  game.WIDTH/2 - 10;
//        self.y =  game.HEIGHT/2 - 10;
    }

    public void moveTo(int x,int y) {
        movedx(x - self.x);
        movedy(y - self.y);
    }

    @Override
    public void think() {
        if(mChanged){
            if(Math.abs(xdir) + Math.abs(ydir) == 2){
                xdir *= angle;
                ydir *= angle;
            }
            mChanged = false;
        }
        sangle += tdir*Math.PI/24;
        sx = self.x + self.width/2 + (int)((35)*Math.cos(sangle));
        sy = self.y + self.height/2 + (int)((35)*-Math.sin(sangle));
    }

    @Override
    public void attack() {
        switch(attackSet){
            case staff: atk = new StaffAtk(this,sx,sy);
                break;
            case windb: atk = new WindBlow(this,sx,sy);
                break;
            case fireb: atk = new FireBall(this,sx,sy);
        }
        game.atks.add(atk);
    }


    public void setXMove(int dir){
        this.xdir = dir;
        mChanged = true;
    }

    public void setYMove(int dir){
        this.ydir = dir;
        mChanged = true;
    }

    @Override
    public void movedy(int deltay) {
        if(game.HEIGHT >= game.height){
            game.centery = (game.HEIGHT - game.height)/2;
        }else if(deltay <= game.centery){
            game.centery = 0;
        } else if(game.centery <= deltay + game.HEIGHT - game.height) {
            game.centery = game.HEIGHT - game.height;
        } else if(self.y + deltay < game.HEIGHT/2) {
            game.centery = 0;
        } else if(self.y + deltay > game.height - game.HEIGHT/2) {
            game.centery = game.HEIGHT - game.height;
        } else {
            game.centery -= deltay;
        }
        super.movedy(deltay);
        /*
        super.movedy(deltay);
        if(game.centery - deltay <= 0){
            if(game.height + game.centery - deltay >= game.HEIGHT){
                if(self.y - deltay > game.HEIGHT/2){
                    if(self.y - deltay <= game.height - game.HEIGHT/2){
                        game.centery -= deltay;
                    }
                }
            }
        }
         */
    }


    @Override
    public void movedx(int deltax) {
        if(game.WIDTH >= game.width){
            game.centerx = (game.WIDTH - game.width)/2;
        } else if(deltax <= game.centerx){
            game.centerx = 0;
        } else if(game.centerx <= deltax + game.WIDTH - game.width) {
            game.centerx = game.WIDTH - game.width;
        } else if(self.x + deltax < game.WIDTH/2) {
            game.centerx = 0;
        } else if(self.x + deltax > game.width - game.WIDTH/2) {
            game.centerx = game.WIDTH - game.width;
        } else {
            game.centerx -= deltax;
        }
        super.movedx(deltax);
//        super.movedx(deltax);
//        if(game.centerx - deltax <= 0 && game.width + game.centerx - deltax >= game.WIDTH)
//            if(self.x - deltax > game.WIDTH/2 && self.x - deltax <= game.width - game.WIDTH/2){
//                game.centerx -= deltax;
//            }
    }

    @Override
    public void paintSelf(Graphics2D g) {
        g.setColor(new Color(0,255,0,100));
        g.fill(self);
        g.fillOval(sx - 7, sy - 7, 14, 14);
        g.drawLine(self.x + self.width/2, self.y + self.height/2,
            self.x + self.width/2 + (int)(xdir*speed), self.y + self.height/2 + (int)(ydir*speed));
    }

    public void pIterate(){
        if(game.boss!=null)
            if(game.boss.intersects(self))
                pCollide(game.boss);
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
        for(NPC n:game.npcs){
            if(self.intersects(n.self)){
                pCollide(n);
            }
        }
    }

    public void die(){
        if(atk!=null)
            atk.dead = true;
        atk = null;
        if(game.ev.fireball && !game.ev.MidBoss){
            game.ev.playermaxatk--;
            game.ev.fireball = false;
            game.player.attackSet = staff;
        }
        dead = false;
        setHp(5);
    }
}