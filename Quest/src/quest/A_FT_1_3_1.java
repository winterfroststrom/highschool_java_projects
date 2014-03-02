/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_FT_1_3_1 extends Area{
    
    public A_FT_1_3_1(Game game){
        super(game,500,500,0x01131);
        
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
    }

    @Override
    public void loadWalls(){
        super.loadWalls();
        
        (new Wall(game,0,0,width,20)).add();//top
        //(new Wall(game,0,height-20,width,20)).add();//bottom
        (new Wall(game,0,height-20,width/2-20,20)).add();//bottom left
        (new Wall(game,width/2+20,height-20,width/2-20,20)).add();//bottom right
        //(new Wall(game,0,0,20,height)).add();//left
        (new Wall(game,0,0,20,height/2 - 15)).add();//left top
        (new Wall(game,0,height/2+15,20,height/2 - 15)).add();//left bottom
        //(new Wall(game,width-20,0,20,height)).add();//right
        (new Wall(game,width-20,0,20,height/2 - 15)).add();//right top
        (new Wall(game,width-20,height/2 + 15,20,height/2 - 15)).add();//right bottom
    }

    @Override
    public void positionPlayer() {
        if(game.ga.preArea == 0x01121)
            game.player.moveTo(10, height/2-10);
        else if(game.ga.preArea == 0x01141)
            game.player.moveTo(width - 30, height/2-10);
        else if(game.ga.preArea == 0x01000)
            game.player.moveTo(width/2 - 10, height - 30);
        else
            game.player.moveTo(width/2 - 10, height - 30);
    }


    @Override
    public void startArea() {
        super.startArea();
        (new Wall(game,0,25,width,50)).add();
        (new EventLocate(game,0,height/2-15,5,30,Event.tarPlayer,0x01121)).add();
        (new EventLocate(game,width-5,height/2-15,5,30,Event.tarPlayer,0x01141)).add();
        (new EventGALocate(game,0,height-5,width,5,Event.tarPlayer,new GA_Tower_Town(game))).add();
        
        (new Floor(game,width/2-50,0,100,400)).add();
        (new Spearman(game,115,75,this.id,0)).add();
        (new Spearman(game,width-115,75,this.id,1)).add();

        (new Jar(game,100,450,15,15,false,true)).add();
        (new Jar(game,150,450,15,15,false,true)).add();
        (new Jar(game,385,450,15,15,false,true)).add();
        (new Jar(game,335,450,15,15,false,true)).add();
    }
}
