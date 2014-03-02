/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_TT_1_1_1 extends Area{
    public A_TT_1_1_1(Game game){
        super(game,500,500,0x00111);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        this.load1EntryNWall(0, 0, 20);
        this.load1EntryWWall(0, 0, 20);
        this.load1EntrySWall(0, 0, 20);
        this.load1EntryEWall(20, height-20, 20);
    }

    @Override
    public void positionPlayer() {
        if(game.ga.preArea == 0x00000)
            game.player.moveTo(width/2-10, height/2-10);
        else if(game.ga.preArea == 0x02111)
            game.player.moveTo(140, 310);
    }

    @Override
    public void startArea() {
        super.startArea();
//        (new EventLocate(game,game.width-5,20,5,height-40,Event.tarPlayer,0x0021)).add();
        (new Wall(game,100,100,100,200)).add();
        (new Wall(game,100,300,35,10)).add();
        (new Wall(game,165,300,35,10)).add();
        (new EventGALocate(game,135,300,30,5,Event.tarPlayer,new GA_Ritual_Tower(game))).add();
    }
}
