/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_WT_1_2_1 extends Area{
    public A_WT_1_2_1(Game game){
        super(game,300,300,0x03121);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();

        this.load1EntryNWall(width/2-20, width/2+20, 20);
        this.load1EntryEWall(0, 0, 20);
        this.load1EntryWWall(0, 0, 20);
        this.load1GAEntrySWall(width/2-30, width/2+30, 20,null);
    }

    @Override
    public void positionPlayer() {
        if(game.ga.preArea == 0x00000)
            game.player.moveTo(width/2-10, height-30);
        else if(game.ga.preArea == 0x03000)
            game.player.moveTo(width/2-10, height-30);
    }

    @Override
    public void startArea() {
        super.startArea();

        (new Jar(game,100,100,15,15,false,true)).add();
    }
}
