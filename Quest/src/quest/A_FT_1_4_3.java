/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class A_FT_1_4_3 extends Area{

    public A_FT_1_4_3(Game game) {
        super(game,500,500,0x01143);
        
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
        vars.add(Boolean.TRUE);
    }

    @Override
    public void loadWalls() {
        super.loadWalls();
        
        (new Wall(game,0,0,width,20)).add();//top
        (new Wall(game,0,height-20,width/2-15,20)).add();//bottom left
        (new Wall(game,width/2+15,height-20,width/2-15,20)).add();//bottom right
        (new Wall(game,0,0,20,height/2 - 15)).add();//left top
        (new Wall(game,0,height/2+15,20,height/2 - 15)).add();//left bottom
        (new Wall(game,width-20,0,20,height)).add();//right
    }

    public void positionPlayer(){
        if(game.ga.preArea == 0x01142)
            game.player.moveTo(width/2 - 10, height - 30);
        else if(game.ga.preArea == 0x01133)
            game.player.moveTo(10, height/2-10);
        else
            game.player.moveTo(width/2 - 10, height - 30);
    }

    @Override
    public void startArea() {
        super.startArea();
        (new EventLocate(game,width/2-15,height-5,30,5,Event.tarPlayer,0x01142)).add();
        (new EventLocate(game,0,height/2-15,5,30,Event.tarPlayer,0x01133)).add();
        
        (new Torch(game,width/2-25,height/2-25,50,50,false,game.ev.torched)).add();
        (new Spearman(game,100,100,this.id,0)).add();
        (new Spearman(game,370,100,this.id,1)).add();
        (new Spearman(game,100,370,this.id,2)).add();
        (new Spearman(game,370,370,this.id,3)).add();
    }
}
