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

public class A_FT_1_3_3 extends Area{

    public A_FT_1_3_3(Game game) {
        super(game,500,500,0x01133);
        
        vars.add(Boolean.FALSE);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        (new Wall(game,0,0,width,20)).add();//top
        (new Wall(game,0,height-20,width/2-15,20)).add();//bottom left
        (new Wall(game,width/2+15,height-20,width/2-15,20)).add();//bottom right
        (new Wall(game,0,0,20,height)).add();//left
        (new Wall(game,width-20,0,20,height/2 - 15)).add();//right top
        (new Wall(game,width-20,height/2 + 15,20,height/2 - 15)).add();//right bottom
    }

        public void positionPlayer(){
        if(game.ga.preArea == 0x01143)
            game.player.moveTo(width-30, height/2-10);
        else if(game.ga.preArea == 0x01152)
            game.player.moveTo(width/2 - 10, height - 30);
        else
            game.player.moveTo(width/2 - 10, height - 30);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new EventLocate(game,width-5,height/2-15,5,30,Event.tarPlayer,0x01143)).add();

        (new Block(game,100,100,100,100,false,true)).add();
        (new Block(game,width-200,100,100,100,false,true)).add();
        (new Block(game,100,height-200,100,100,false,true)).add();
        (new Block(game,width-200,height-200,100,100,false,true)).add();

        (new Jar(game,100,450,15,15,false,true)).add();
        (new Jar(game,150,450,15,15,false,true)).add();
        (new Jar(game,385,450,15,15,false,true)).add();
        (new Jar(game,335,450,15,15,false,true)).add();

        (new Torch(game,width/2-20,height/2-20,40,40,false,game.ev.torched)).add();

        if(vars.get(0)){
            (new Torch(game,150,height/2-20,40,40,false,true)).add();
            (new Torch(game,width-190,height/2-20,40,40,false,true)).add();
            (new Torch(game,width/2-20,150,40,40,false,true)).add();
            (new Torch(game,width/2-20,height-190,40,40,false,true)).add();
        } else {
            ArrayList<Event> e = new ArrayList<Event>();
            e.add(new EventElement(game,150,height/2-20,40,40,Element.fire));
            e.add(new EventElement(game,width-190,height/2-20,40,40,Element.fire));
            e.add(new EventElement(game,width/2-20,150,40,40,Element.fire));
            e.add(new EventElement(game,width/2-20,height-190,40,40,Element.fire));

            (new EventMulti(game,e,new EventLocate(
                        game,width/2-15,height-5,30,5,Event.tarPlayer,0x01132),0x01133,0)).add();
            (new Torch(game,150,height/2-20,40,40,false,false)).add();
            (new Torch(game,width-190,height/2-20,40,40,false,false)).add();
            (new Torch(game,width/2-20,150,40,40,false,false)).add();
            (new Torch(game,width/2-20,height-190,40,40,false,false)).add();
        }
    }
}
