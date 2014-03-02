/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */

public class A_FT_1_3_2 extends Area{
    public A_FT_1_3_2(Game game) {
        super(game,500,500,0x01132);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        (new Wall(game,0,0,width/2-15,15)).add();//top left
        (new Wall(game,width/2+15,0,width/2-15,15)).add();//top right
        (new Wall(game,0,height-20,width,20)).add();//bottom
        (new Wall(game,0,0,20,height)).add();//left
        (new Wall(game,width-20,0,20,height)).add();//right
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01133)
            game.player.moveTo(width/2 - 10, 10);
        else
            game.player.moveTo(width/2 - 10, 10);

    }

    @Override
    public void startArea() {
        super.startArea();
        (new Wall(game,0,height-100,100,50)).add();
        (new Wall(game,0,50,100,50)).add();
        (new Wall(game,50,50,50,height-100)).add();
        (new Torch(game,235,235,30,30,false,game.ev.torched)).add();
        if(!game.ev.MidBoss)
            (new Prometheus(game)).add();
        else
            (new EventLocate(game,game.ga.current.width/2-15,0,30,5,Event.tarPlayer,0x01133)).add();

        (new Block(game,125,150,15,15,false,false)).add();
        (new Block(game,225,150,15,15,false,false)).add();
        (new Block(game,325,150,15,15,false,false)).add();
        (new Block(game,425,150,15,15,false,false)).add();

        (new Block(game,125,335,15,15,false,false)).add();
        (new Block(game,225,335,15,15,false,false)).add();
        (new Block(game,325,335,15,15,false,false)).add();
        (new Block(game,425,335,15,15,false,false)).add();
    }
}
