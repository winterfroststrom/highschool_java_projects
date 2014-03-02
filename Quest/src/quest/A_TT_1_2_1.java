/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_TT_1_2_1 extends Area{
    public A_TT_1_2_1(Game game){
        super(game,600,500,0x00121);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        this.load1EntryNWall(20, width-20, 0);
        this.load1EntryWWall(20, height-20, 20);
        this.load1EntrySWall(0, 0, 20);
        this.load1EntryEWall(20, height-20, 0);
    }

    @Override
    public void positionPlayer() {}

    @Override
    public void startArea() {
        super.startArea();
        (new Block(game,250,40,30,height-60,true,false)).add();
        //(new EventBind(game,100,100,50,50,Event.tarPlayer,game.blocks.get(0))).add();
    }
}
