/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_FT_1_5_2 extends Area{
    
    public A_FT_1_5_2(Game game){
        super(game,500,500,0x01152);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        (new Wall(game,0,0,width,20)).add();//top
        (new Wall(game,0,height-20,width,20)).add();//bottom
        (new Wall(game,0,0,20,height/2 - 15)).add();//left top
        (new Wall(game,0,height/2+15,20,height/2 - 15)).add();//left bottom
        (new Wall(game,width-20,0,20,height/2 - 15)).add();//right top
        (new Wall(game,width-20,height/2 + 15,20,height/2 - 15)).add();//right bottom
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01162)
            game.player.moveTo(width-30, height/2-10);
        else if(game.ga.preArea == 0x01142)
            game.player.moveTo(10, height/2-10);
        else
            game.player.moveTo(10, height/2-10);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new EventLocate(game,0,height/2-15,5,30,Event.tarPlayer,0x01142)).add();
        (new EventLocate(game,width-5,height/2-15,5,30,Event.tarPlayer,0x01162)).add();

        (new Platform(game,20,height/2-25,50,50,width-70,height/2 -25,5)).add();
        (new Floor(game,20,20,width-40,height-40)).add();
    }
}
