/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_FT_1_2_3 extends Area{
    
    public A_FT_1_2_3(Game game){
        super(game,500,500,0x01123);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        (new Wall(game,0,0,width,20)).add();//top
        //(new Wall(game,0,height-20,width,20)).add();//bottom
        (new Wall(game,0,height-20,width/2-15,20)).add();//bottom left
        (new Wall(game,width/2+15,height-20,width/2-15,20)).add();//bottom right
        (new Wall(game,0,0,20,height)).add();//left
        (new Wall(game,width-20,0,20,height)).add();//right
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01121)
            game.player.moveTo(width/2 - 10, height - 30);
        else
            game.player.moveTo(width/2 - 10, height - 30);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new EventLocate(game,width/2-15,height-5,30,5,Event.tarPlayer,0x01122)).add();
        (new EventElemBool(game,140,10,110,110,Element.fire)).add();
        (new Wall(game,400,50,50,height-50)).add();
        (new Wall(game,250,50,200,50)).add();
        (new Wall(game,100,200,350,50)).add();
        (new Wall(game,100,200,50,200)).add();
        (new Wall(game,100,350,350,50)).add();
        (new Torch(game,140,20,110,110,false,game.ev.torched)).add();
        (new Torch(game,300,height - 75,30,30,false,true)).add();
        (new Torch(game,45,height - 75,30,30,false,game.ev.torched)).add();
        (new Torch(game,45,250,30,30,false,game.ev.torched)).add();
        (new Torch(game,45,100,30,30,false,game.ev.torched)).add();
        //(new Floor(game,300,400,50,100)).add();

        (new Jar(game,280,150,15,15,false,true)).add();
        (new Jar(game,320,150,15,15,false,true)).add();
        (new Jar(game,360,150,15,15,false,true)).add();
        
        
    }
}
