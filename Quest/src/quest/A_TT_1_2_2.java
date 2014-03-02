/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_TT_1_2_2 extends Area{
    public A_TT_1_2_2(Game game){
        super(game,600,500,0x00122);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        this.load1EntryNWall(0, 0, 20);
        this.load1EntryWWall(0, 0, 20);
        this.load1EntrySWall(20, 0, 0);
        this.load1EntryEWall(0, 0, 20);
    }

    @Override
    public void positionPlayer() {
        if(game.ga.preArea == 0x01131)
            game.player.moveTo(290, 360);
    }

    @Override
    public void startArea() {
        super.startArea();
        
        (new Wall(game,250,250,100,100)).add();
        (new Wall(game,250,350,35,10)).add();
        (new Wall(game,315,350,35,10)).add();
        (new EventGALocate(game,285,350,30,5,Event.tarPlayer,new GA_Hammer_Forge(game))).add();
    }
}
