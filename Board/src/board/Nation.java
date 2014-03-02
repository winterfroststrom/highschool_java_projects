/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Nation {
    Game parent;
    final String name;
    int cityNameIndex;
    int militaryStrength;////////////////////////////////////////////////////////////////////////
    final Color color;
    NationAI ai;
    private ArrayList<City> cities;
    private ArrayList<Unit> units;

    public Nation(Game parent,Tile tile, NationEnum nation) {
        this.parent = parent;
        this.name = nation.name();
        this.color = nation.color;
        this.cityNameIndex = 0;
        this.cities = new ArrayList<City>();
        this.units = new ArrayList<Unit>();
        
        init(tile);
    }

    private void init(Tile tile){
        addCity(tile);
    }

    public void createAI(){
        ai = new NationAI(this);
    }

    public String getNextName(){
        String cityName = NationEnum.getNextName(this,cityNameIndex);
        cityNameIndex++;
        return cityName;
    }

    public boolean validCityTile(Tile tile){
        boolean valid = true;
        for(Tile t:parent.board.adjacents(tile)){
            valid = valid && !t.hasCity();
        }
        return valid;
    }

    public void addCity(Tile tile){
        cities.add(new City(this,tile,getNextName()));
    }

    public void caputured(City city){
        cities.remove(city);
    }

    public void conquered(City city){
        cities.add(city);
    }

    public void removeCity(City city){
        city.tile.unmakeCity();
        for(Tile t: city.influence){
            t.setCity(null);
        }
        cities.remove(city);
    }

    public void addUnit(Unit u){
        militaryStrength += u.type.strength;
        units.add(u);
    }

    public void removeUnit(Unit u){
        militaryStrength -= u.type.strength;
        units.remove(u);
    }

    public void endTurn(){
        if(ai != null)
            ai.think();
        for(City c:cities){
            c.endTurn();
        }
        for(Iterator<Unit> it = units.iterator();it.hasNext();){
            Unit u = it.next();
            if(u.isDead()){
                u.die();
                it.remove();
                continue;
            }
            u.autoMove();
            u.reset();
        }
        if(parent.units.tile != null)
            parent.units.refresh();
    }

    public boolean isDead(){
        return cities.size() < 1;
    }

    public void die(){
        globalDisband();
    }

    public void globalDisband(){
        Iterator<Unit> it = units.iterator();
        while(it.hasNext()){
            it.next().die();
            it.remove();
        }
    }

    public ArrayList<Tile> getInfluence(){
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for(City c :cities){
            tiles.addAll(c.influence);
            tiles.add(c.tile);
        }
        return tiles;
    }

    @Override
    public String toString() {
        return "Nation[" + name + "]";
    }

    public void play(){
        
    }
}
