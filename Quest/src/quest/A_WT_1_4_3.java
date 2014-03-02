/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_WT_1_4_3 extends Area{
    public A_WT_1_4_3(Game game){
        super(game,300,300,0x03143);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();

        this.load1EntryNWall(width/2-20, width/2+20, 20);
        this.load1EntryWWall(0,0, 20);
        this.load1EntryEWall(height/2-20, height/2+20, 20);
        this.load1EntrySWall(0,0, 20);
    }

    @Override
    public void positionPlayer() {
    }

    @Override
    public void startArea() {
        super.startArea();
    }
}
