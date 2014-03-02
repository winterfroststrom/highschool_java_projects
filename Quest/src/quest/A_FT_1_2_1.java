/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.util.ArrayList;

public class A_FT_1_2_1 extends Area{
    
    public A_FT_1_2_1(Game game){
        super(game,500,500,0x01121);
        
        vars.add(Boolean.FALSE);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        (new Wall(game,0,0,width/2-15,20)).add();//top left
        (new Wall(game,width/2+15,0,width/2-15,20)).add();//top right
        (new Wall(game,0,height-20,width,20)).add();//bottom
        (new Wall(game,0,0,20,height)).add();//left
        //(new Wall(game,width-20,0,20,height)).add();//right
        (new Wall(game,width-20,0,20,height/2 - 15)).add();//right top
        (new Wall(game,width-20,height/2 + 15,20,height/2 - 15)).add();//right bottom
    }

    @Override
    public void positionPlayer() {
        if(game.ga.preArea == 0x01131)
            game.player.moveTo(width - 30, height/2-10);
        else if(game.ga.preArea == 0x01122)
            game.player.moveTo(width/2 - 10, 10);
        else
            game.player.moveTo(width - 30, height/2-10);
    }


    @Override
    public void startArea() {
        super.startArea();
        (new EventLocate(game,width-5,height/2-15,5,30,Event.tarPlayer,0x01131)).add();

        (new Wall(game,300,25,width-300,50)).add();
        (new Wall(game,300,0,50,75)).add();
        (new Block(game,100,100,50,50,false,true)).add();
        (new Block(game,150,100,50,50,false,true)).add();
        (new Block(game,200,100,50,50,false,true)).add();
        (new Block(game,100,150,50,50,false,true)).add();
        (new Block(game,100,200,50,50,false,true)).add();
        (new Block(game,150,200,50,50,false,true)).add();
        (new Block(game,200,150,50,50,false,true)).add();
        (new Block(game,200,200,50,50,false,true)).add();

        (new Block(game,300,250,50,50,false,true)).add();
        (new Block(game,350,250,50,50,false,true)).add();
        (new Block(game,250,300,50,50,false,true)).add();
        (new Block(game,250,350,50,50,false,true)).add();
        (new Block(game,300,350,50,50,false,true)).add();
        (new Block(game,350,300,50,50,false,true)).add();
        (new Block(game,350,350,50,50,false,true)).add();

        if(vars.get(0)){
            (new EventLocate(game,width/2-15,0,30,5,Event.tarPlayer,0x01122)).add();
            (new Block(game,250,250,50,50,true,true)).add();
        } else {
            ArrayList<Event> ev = new ArrayList<Event>();
            ev.add((new EventLocate(game,width/2-15,0,30,5,Event.tarPlayer,0x01122)));

            (new EventChain(game,298,298,1,1,Event.tarBlock,ev,this.id,0)).add();

            (new Block(game,250,200,50,50,true,true)).add();
        }
    }
}
