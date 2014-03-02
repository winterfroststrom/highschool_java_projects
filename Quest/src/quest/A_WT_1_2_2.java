/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_WT_1_2_2 extends Area{
    public A_WT_1_2_2(Game game){
        super(game,300,300,0x03122);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();

        this.load1EntryNWall(width/2-20, width/2+20, 20);
        this.load1EntryEWall(0, 0, 20);
        this.load1EntryWWall(0, 0, 20);
        this.load1EntrySWall(width/2-20, width/2+20, 20);
//        this.load1GAEntrySWall(width/2-20, width/2+20, 20,new GA_Tower_Town(game));
    }

    @Override
    public void positionPlayer() {
    }

    @Override
    public void startArea() {
        super.startArea();
    }
}
