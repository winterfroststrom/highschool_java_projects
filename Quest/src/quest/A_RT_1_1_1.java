/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_RT_1_1_1 extends Area{
    public A_RT_1_1_1(Game game){
        super(game,300,300,0x02111);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        this.load1EntryNWall(0, 0, 20);
        this.load1EntryEWall(0, 0, 20);
        this.load1EntryWWall(0, 0, 20);
        this.load1GAEntrySWall(width/2-50, width/2+50, 20,new GA_Tower_Town(game));
    }

    @Override
    public void positionPlayer() {
        if(game.ga.preArea == 0x00000)
            game.player.moveTo(width/2-10, height/2-10);
        else if(game.ga.preArea == 0x00111)
            game.player.moveTo(width/2-10, height-30);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new Block(game,50,50,width-100+5,50,false,false)).add();
    }
}
