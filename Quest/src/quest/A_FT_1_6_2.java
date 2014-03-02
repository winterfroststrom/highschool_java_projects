/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_FT_1_6_2 extends Area{
    public A_FT_1_6_2(Game game){
        super(game,500,200,0x01162);
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
        if(game.ga.preArea == 0x01172)
            game.player.moveTo(width-30, height/2-10);
        else if(game.ga.preArea == 0x01152)
            game.player.moveTo(10, height/2-10);
        else
            game.player.moveTo(10, height/2-10);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new EventLocate(game,0,height/2-15,5,30,Event.tarPlayer,0x01152)).add();
        (new EventLocate(game,width-5,height/2-15,5,30,Event.tarPlayer,0x01172)).add();

        (new Jar(game,82,50,15,15,false,true)).add();
        (new Jar(game,182,50,15,15,false,true)).add();
        (new Jar(game,282,50,15,15,false,true)).add();
        (new Jar(game,382,50,15,15,false,true)).add();

        (new Jar(game,82,height-65,15,15,false,true)).add();
        (new Jar(game,182,height-65,15,15,false,true)).add();
        (new Jar(game,282,height-65,15,15,false,true)).add();
        (new Jar(game,382,height-65,15,15,false,true)).add();
    }
}
