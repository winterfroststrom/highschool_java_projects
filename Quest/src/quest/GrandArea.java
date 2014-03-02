/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public abstract class GrandArea {
    Game game;
    int preArea;
    Area current;
    private boolean isLoaded;
    
    public GrandArea(Game game){
        this.game = game;
        isLoaded = false;
    }

    public abstract void load();

    public abstract void die();

    public abstract Area getAreaById(int id);

    public abstract void setCurrentById(int id);

    public void loadGA(){
        load();
        if(game.ga.isLoaded)
            setCurrentById(game.ga.current.id);
        else
            setCurrentById(0x00000);
        current.loadStatus();
        current.loadPosition();
        game.ga = this;
        current.loadWalls();
        isLoaded = true;
    }

    public boolean isIsLoaded() {
        return isLoaded;
    }
}
