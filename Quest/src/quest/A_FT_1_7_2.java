/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_FT_1_7_2 extends Area{

    public A_FT_1_7_2(Game game){
        super(game,500,500,0x01172);
        
        vars.add(Boolean.TRUE);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        (new Wall(game,0,0,width,20)).add();//top
        (new Wall(game,0,height-20,width,20)).add();//bottom
        (new Wall(game,0,0,20,height)).add();//left
        (new Wall(game,width-20,0,20,height)).add();//right
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01162)
            game.player.moveTo(25, height/2-10);
        else
            game.player.moveTo(25, height/2-10);
    }

    @Override
    public void startArea() {
        super.startArea();
        if(!game.ev.FTboss)
            (new Prometheus_The_Burning(game)).add();
    }
}
