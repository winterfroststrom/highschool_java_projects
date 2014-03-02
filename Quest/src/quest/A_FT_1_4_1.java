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

public class A_FT_1_4_1 extends Area{

    public A_FT_1_4_1(Game game){
        super(game,500,500,0x01141);

        vars.add(Boolean.FALSE);

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
        (new Wall(game,0,0,width/2-15,20)).add();//top left
        (new Wall(game,width/2+15,0,width/2-15,20)).add();//top right
        (new Wall(game,0,height-20,width,20)).add();//bottom
        //(new Wall(game,0,0,20,height)).add();//left
        (new Wall(game,0,0,20,height/2 - 15)).add();//left top
        (new Wall(game,0,height/2+15,20,height/2 - 15)).add();//left bottom
        (new Wall(game,width-20,0,20,height)).add();//right
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01131)
            game.player.moveTo(10, height/2-10);
        else if(game.ga.preArea == 0x01142)
            game.player.moveTo(width/2-10, 10);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new Wall(game,0,25,100,50)).add();
        (new Wall(game,50,0,50,75)).add();

        (new Floor(game,50,50,150,150)).add();
        (new Floor(game,50,height-200,150,150)).add();
        (new Floor(game,width-200,50,150,150)).add();
        (new Floor(game,width-200,height-200,150,150)).add();
        (new EventLocate(game,0,height/2-15,5,30,Event.tarPlayer,0x01131)).add();
        
        if(vars.get(0)) {
            (new EventLocate(game,width/2-15,0,30,5,Event.tarPlayer,0x01142)).add();
        } else {
            ArrayList<Event> e = new ArrayList<Event>();
            e.add(new EventLocate(game,width/2-15,0,30,5,Event.tarPlayer,0x01142));
            (new EventChain(game,0,0,width,height,Event.tarNPCAb,e,this.id,0)).add();

            (new Spearman(game,width/2-15,height/2-15,this.id,1)).add();

            (new Spearman(game,width/2-15,50,this.id,1)).add();
            (new Spearman(game,width-65,height/2-15,this.id,1)).add();
            (new Spearman(game,50,height/2-15,this.id,1)).add();
            (new Spearman(game,width/2-15,height-65,this.id,1)).add();
        }
    }
}
