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

public class A_FT_1_4_2 extends Area{
    
    public A_FT_1_4_2(Game game){
        super(game,500,500,0x01142);

        vars.add(Boolean.FALSE);

        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);

        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        //(new Wall(game,0,0,width,20)).add();//top
        (new Wall(game,0,0,width/2-15,15)).add();//top left
        (new Wall(game,width/2+15,0,width/2-15,15)).add();//top right
        //(new Wall(game,0,height-20,width,20)).add();//bottom
        (new Wall(game,0,height-20,width/2-15,20)).add();//bottom left
        (new Wall(game,width/2+15,height-20,width/2-15,20)).add();//bottom right
        (new Wall(game,0,0,20,height)).add();//left
        //(new Wall(game,0,0,20,height/2 - 15)).add();//left top
        //(new Wall(game,0,height/2+15,20,height/2 - 15)).add();//left bottom
        //(new Wall(game,width-20,0,20,height)).add();//right
        (new Wall(game,width-20,0,20,height/2 - 15)).add();//right top
        (new Wall(game,width-20,height/2 + 15,20,height/2 - 15)).add();//right bottom
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01141)
            game.player.moveTo(width/2 - 10, height - 30);
        else if(game.ga.preArea == 0x01143)
            game.player.moveTo(width/2 - 10, 10);
        else if(game.ga.preArea == 0x01152)
            game.player.moveTo(width-30, height/2-10);
        else
            game.player.moveTo(width/2 - 10, height - 30);
    }

    @Override
    public void startArea() {
        super.startArea();
        if(vars.get(0)){
            (new EventLocate(game,width/2-15,height-5,30,5,Event.tarPlayer,0x01141)).add();
            (new EventLocate(game,width-5,height/2-15,5,30,Event.tarPlayer,0x01152)).add();
            (new EventLocate(game,width/2-15,0,30,5,Event.tarPlayer,0x01143)).add();
        } else {

            (new EventLocate(game,width/2-15,height-5,30,5,Event.tarPlayer,0x01141)).add();
            ArrayList<Event> e = new ArrayList<Event>();
            if(game.ev.MidBoss)
                e.add(new EventLocate(game,width-5,height/2-15,5,30,Event.tarPlayer,0x01152));
            (new EventLocate(game,width/2-15,0,30,5,Event.tarPlayer,0x01143)).add();
            (new EventChain(game,0,0,width,height,Event.tarNPCAb,e,this.id,0)).add();
        
            (new IronArcher(game,50,150,this.id,0)).add();
            (new IronArcher(game,100,150,this.id,1)).add();
            (new IronArcher(game,150,150,this.id,2)).add();
            (new IronArcher(game,200,150,this.id,3)).add();
            (new IronArcher(game,250,150,this.id,4)).add();
            (new IronArcher(game,300,150,this.id,5)).add();
            (new IronArcher(game,350,150,this.id,6)).add();
            (new IronArcher(game,400,150,this.id,7)).add();

            (new IronArcher(game,50,height-200,this.id,8)).add();
            (new IronArcher(game,100,height-200,this.id,9)).add();
            (new IronArcher(game,150,height-200,this.id,10)).add();
            (new IronArcher(game,200,height-200,this.id,11)).add();
            (new IronArcher(game,250,height-200,this.id,12)).add();
            (new IronArcher(game,300,height-200,this.id,13)).add();
            (new IronArcher(game,350,height-200,this.id,14)).add();
            (new IronArcher(game,400,height-200,this.id,15)).add();
        }
    }
}
