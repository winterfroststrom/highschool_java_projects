/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.Attacks;

import java.util.ArrayList;
import refactored_game.Objs.SCraft;

/**
 *
 * @author Sparky
 */
public class Bullet {
    public RecShot[] shots;
    public int shootPauseMax;
    public int shootPause;
    public int shootContinueMax;
    public int shootContinue;

    public Bullet(RecShot s){
        this(s,6,0);
    }

    public Bullet(RecShot s,int shootPause,int shootContinue){
        shots = new RecShot[1];
        shots[0] = s;
        this.shootPause = shootPause;
        this.shootPauseMax = shootPause;
        this.shootContinueMax = shootContinue;
    }

    public Bullet(RecShot[] shots,int shootPause, int shootContinue){
        this.shots = new RecShot[shots.length];
        for(int i = 0;i<shots.length;i++)
            this.shots[i] = shots[i];
        this.shootPause = shootPause;
        this.shootPauseMax = shootPause;
        this.shootContinueMax = shootContinue;
    }

    public Bullet getCopy(SCraft s){
        RecShot[] newShots = new RecShot[shots.length];
        for(int i = 0;i<shots.length;i++){
            newShots[i] = shots[i].getCopy(s);
        }
        return new Bullet(newShots,shootPause,shootPauseMax);
    }

    public void setAngle(double lr, double ud){
        for(RecShot s:shots){
            s.scaleud  = ud;
            s.scalelr = lr;
        }
    }
    public void setFrequency(double sPause, double sContinue){
        this.shootPauseMax = (int)sPause;
        this.shootContinueMax = (int)sContinue;
    }

    public RecShot[] multiShot(int shotNum,SCraft s,int speed,int w, int h, int damage){
        RecShot[] out = new RecShot[shotNum];
        for(double i = 1;i<=shotNum/2;i++){
            out[(int)i-1] = new MultiShot(s,speed,w,h,damage,i/shotNum,i/shotNum);
            out[shotNum-(int)i] = new MultiShot(s,speed,w,h,damage,-i/shotNum,i/shotNum);
        }
        if(shotNum % 2 != 0){
            out[shotNum/2] = new MultiShot(s,speed,w,h,damage);
        }
        return out;
    }
    public RecShot[] multiShot(int shotNum,SCraft s,RecShot shot){
        return multiShot(shotNum,s,shot.speed,shot.w,shot.h,shot.damage);
    }

    public void add(ArrayList<RecShot> a,SCraft s){
        for(RecShot shot:this.shots){
            a.add(shot.getCopy(s));
        }
    }

    public boolean isReady(){
        if(shootContinue > 0){
            shootContinue--;
            return true;
        }

        if(shootPause >= shootPauseMax){
            shootPause = 0;
            shootContinue = shootContinueMax;
            return true;
        }
        shootPause++;
        return false;
    }
    public Bullet upgradeMulti(int shotNum,SCraft s,int level){
        return new Bullet(multiShot(shotNum,s,shots[0]),
                            shots[0].upgradeSPause(level),
                            shots[0].upgradeSContinue(level));
    }
    public Bullet upgrade(SCraft s,int level){
        RecShot[] newShots = new RecShot[shots.length];
        for(int i = 0;i<shots.length;i++)
            newShots[i] = shots[i].upgrade(s, level);
        return new Bullet(newShots,
                            newShots[0].upgradeSPause(level),
                            newShots[0].upgradeSContinue(level));
    }
}
