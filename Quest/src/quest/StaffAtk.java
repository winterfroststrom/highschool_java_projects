/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class StaffAtk extends Attack{
    int time = 8;
    int damage = 1;

    public StaffAtk(IObject o,int x,int y){
        super(o, o.self.x + o.self.width/2, o.self.y+o.self.height/2, x, y, o.self.width*3/2,o.self.height*3/2,true);
        mobile = false;
    }

    @Override
    public void affect(Wall w) {}
    @Override
    public void affect(IObject o) {
        if(time > 2){
            o.damage(damage,carry);
        }
    }
    @Override
    public void affect(Block b) {b.damage(1, carry);}
    
    @Override
    public boolean isDead() {
        return time < 1 || dead;
    }

    @Override
    public void iterate() {
        time--;
    }
}
