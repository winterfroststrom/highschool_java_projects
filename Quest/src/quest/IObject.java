/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public abstract class IObject extends Object{
    int speed;
    private int hp;
    int hpmax;
    double xdir;
    double ydir;
    boolean dead;
    boolean platform;
    int dtime;
    int dtimetil;
    Attack atk;

    public IObject(Game game,int x,int y, int w, int h, int spd,int hp) {
        super(game,x,y,w,h);
        this.hp = hp;
        this.hpmax = hp;
        this.speed = spd;
        dtime = 3;
        platform = false;
    }

    @Override
    public boolean canAttack() {
        if(attack && (atk == null || atk.isDead())){
            attack = false;
            return true;
        }
        return false;
    }

    /*public void mCollide(IObject o){
        double massrat = ((double)o.self.height*o.self.width)/(self.height*self.width);
        self.x -= (massrat * o.speed * o.xdir);
        self.y -= (massrat * o.speed * o.ydir);
    }*/

    @Override
    public void move() {
        if((atk == null || atk.isDead() || atk.mobile)){
            previous.x = self.x;
            previous.y = self.y;
            movedx((int)(xdir * speed));
            movedy((int)(ydir * speed));
        }
    }

    @Override
    public boolean canAffect() {
        return dtimetil-- < 1;
    }

    public void damage(int damage,Element e){
        hp -= damage;
        if(hp < 1)hp =0;
        else if(hp>hpmax)hp = hpmax;
        dtimetil = dtime;
    }

    @Override
    public boolean isDead(){
        platform = false;
        return hp < 1 || dead;
    }

    public void mCollide(IObject o,double ratio){
        this.movedx((int)(o.speed*o.xdir*ratio));
        this.movedy((int)(o.speed*o.ydir*ratio));
    }

    @Override
    public void reverseMove() {
        previous.x = self.x;
        previous.y = self.y;
        this.movedx(-(int)(xdir * speed));
        this.movedy(-(int)(ydir * speed));
    }

    public int health(){
        return hp;
    }

    public abstract void pIterate();

    @Override
    public void movedx(int deltax) {
        super.movedx(deltax);
        if(atk!=null && !atk.mobile)
            if(atk != null){
                atk.dead = true;
                atk = null;
            }
    }

    @Override
    public void movedy(int deltay) {
        super.movedy(deltay);
        if(atk!=null && !atk.mobile)
            if(atk != null){
                atk.dead = true;
                atk = null;
            }
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public abstract void die();
}
