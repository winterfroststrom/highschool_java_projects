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

public abstract class Area {
    Game game;
    ArrayList<Boolean> vars;
    private ArrayList<Event> el;
    int width;
    int height;
    int id;
    
    public Area(Game game, int width, int height,int id) {
        vars = new ArrayList<Boolean>();
        el = new ArrayList<Event>();
        this.game = game;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public void loadArea(){
        loadStatus();

        loadWalls();

        loadPosition();
    }

    public void loadStatus(){
        if(game.ga.current != null && game.ga.isIsLoaded())
            game.ga.preArea = game.ga.current.id;
        game.clear();
        game.ga.current = this;

        game.dChange = true;
        game.width = width;
        game.height = height;
        game.player.add();
    }

    public void loadWalls(){
        (new Wall(game,0,-15,width,15)).add();
        (new Wall(game,0,height,width,15)).add();
        (new Wall(game,-15,0,15,height)).add();
        (new Wall(game,width,0,15,height)).add();
    }

    public void loadPosition(){
        if(game.ga.preArea == id - 0x00010)
            game.player.moveTo(10, game.player.self.y);
        else if(game.ga.preArea == id + 0x00010)
            game.player.moveTo(width-30, game.player.self.y);
        else if(game.ga.preArea == id - 0x00001)
            game.player.moveTo(game.player.self.x, height-30);
        else if(game.ga.preArea == id + 0x00001)
            game.player.moveTo(game.player.self.x, 10);
        else if(game.ga.preArea == id + 0x00100 || game.ga.preArea == id - 0x00100 )
            game.player.moveTo(game.player.self.x, game.player.self.y);
        else
            positionPlayer();
    }

    public abstract void positionPlayer();

    public void startArea(){
        game.clearEvents();
        for(Event e:el)
            e.add();
        el.clear();
    }

    public void load1EntryEWall(int ey1,int ey2,int w){
        if(ey1 == ey2){
            (new Wall(game,width-w,0,w,height)).add();
            return;
        }
        (new Wall(game,width-w,0,w,ey1)).add();
        (new Wall(game,width-w,ey2,w,height-ey2)).add();
        el.add(new EventLocate(game,width-5,0,5,height,Event.tarPlayer,id + 0x00010));
    }

    public void load1EntryWWall(int ey1,int ey2,int w){
        if(ey1 == ey2){
            (new Wall(game,0,0,w,height)).add();
            return;
        }
        (new Wall(game,0,0,w,ey1)).add();
        (new Wall(game,0,ey2,w,height-ey2)).add();
        el.add(new EventLocate(game,0,0,5,height,Event.tarPlayer,id - 0x00010));
    }

    public void load1EntryNWall(int ex1,int ex2,int h){
        if(ex1 == ex2){
            (new Wall(game,0,0,width,h)).add();
            return;
        }
        (new Wall(game,0,0,ex1,h)).add();
        (new Wall(game,ex2,0,width - ex2,h)).add();
        el.add(new EventLocate(game,0,0,width,5,Event.tarPlayer,id + 0x00001));
    }

    public void load1EntrySWall(int ex1,int ex2, int h){
        if(ex1 == ex2){
            (new Wall(game,0,height-h,width,h)).add();
            return;
        }
        (new Wall(game,0,height-h,ex1,h)).add();
        (new Wall(game,ex2,height-h,width - ex2,h)).add();
        el.add(new EventLocate(game,0,height-5,width,5,Event.tarPlayer,id - 0x00001));
    }

    public void load1GAEntrySWall(int ex1,int ex2, int h, GrandArea ga){
        if(ex1 == ex2){
            (new Wall(game,0,height-h,width,h)).add();
            return;
        }
        (new Wall(game,0,height-h,ex1,h)).add();
        (new Wall(game,ex2,height-h,width - ex2,h)).add();
        el.add(new EventGALocate(game,0,height-5,width,5,Event.tarPlayer,ga));
    }
}
