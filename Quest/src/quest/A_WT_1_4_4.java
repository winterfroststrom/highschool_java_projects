/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_WT_1_4_4 extends Area{
    public A_WT_1_4_4(Game game){
        super(game,300,300,0x03144);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();

        this.load1EntrySWall(width/2-20, width/2+20, 20);
        this.load1EntryEWall(0,0, 20);
        this.load1EntryWWall(height/2-20, height/2+20, 20);
        this.load1EntryNWall(0,0, 20);
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
