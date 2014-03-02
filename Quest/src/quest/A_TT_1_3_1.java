/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_TT_1_3_1 extends Area{
    
    public A_TT_1_3_1(Game game){
        super(game,300,500,0x00131);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        this.load1EntryNWall(0, 0, 20);
        this.load1EntryWWall(20, height-20, 0);
        this.load1EntrySWall(0, 0, 20);
        this.load1EntryEWall(0, 0, 20);
    }

    @Override
    public void positionPlayer() {}

    @Override
    public void startArea() {
        super.startArea();
    }
}
