/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_FT_1_2_2 extends Area{
    public A_FT_1_2_2(Game game){
        super(game,500,500,0x01122);

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
        (new Wall(game,width-20,0,20,height)).add();//right
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01121)
            game.player.moveTo(width/2 - 10, height - 30);
        else if(game.ga.preArea == 0x01123)
            game.player.moveTo(width/2 - 10, 10);
        else
            game.player.moveTo(width/2 - 10, height - 30);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new EventLocate(game,width/2-15,height-5,30,5,Event.tarPlayer,0x01121)).add();
        (new EventLocate(game,width/2-15,0,30,5,Event.tarPlayer,0x01123)).add();
        (new Wall(game,300,height-100,50,100)).add();
        (new Wall(game,300,height-100,width-300,50)).add();
        (new Wall(game,400,0,50,100)).add();
        (new Wall(game,400,50,width - 400,50)).add();
        (new Floor(game,50,400,250,50)).add();
        (new Floor(game,50,300,50,150)).add();
        (new Floor(game,50,300,150,50)).add();
        (new Floor(game,0,200,300,50)).add();
        (new Floor(game,250,200,50,150)).add();
        (new Floor(game,250,300,200,50)).add();
        (new Floor(game,400,150,50,200)).add();
        (new Floor(game,275,0,50,150)).add();
        (new Floor(game,50,100,275,50)).add();
        (new IronArcher(game,100,350,this.id,0)).add();
        (new IronArcher(game,325,225,this.id,1)).add();
        (new IronArcher(game,337,35,this.id,2)).add();
        //(new Floor(game,300,400,50,100)).add();
    }
}
