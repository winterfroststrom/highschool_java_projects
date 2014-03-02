/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class Tile {
    private static final int MINPOPULATION = 3;

    Point point;
    int radius;
    int x;
    int y;
    City city;
    private int population;
    private static Random gen = new Random();
    private Map<Unit,Nation> units;
    private Land land;
    private Construct construct;
    private Shape shape;
    private Shape inner;

    public Tile(Point point, int radius, int x, int y, Land land, Construct construct) {
        this.point = point;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.land = land;
        this.construct = construct;
        units = new HashMap<Unit,Nation>();
        setShape();
    }

    public boolean hasCity(){
        return city != null;
    }

    public boolean isCity(){
        return construct.equals(Construct.City);
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void makeCity(City city){
        this.city = city;
        setConstruct(Construct.City);
    }

    public void unmakeCity(){
        this.city = null;
        setConstruct(Construct.Void);
    }

    public int getHammers(){
        return (land.resource.hammer + construct.resource.hammer) * population;
    }

    public int getFood(){
        return (land.resource.food + construct.resource.food) * population;
    }

    public int getWealth(){
        return (land.resource.wealth + construct.resource.wealth) * population;
    }

    public int getMoveCost(){
        return land.MoveCost + construct.MoveCost;
    }

    public boolean hasUnits(){
        return units.size() > 0;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public void setConstruct(Construct construct) {
        this.construct = construct;
        setShape();
    }

    public void change(){
        if(isCity())
            return;
        if(hasCity() && gen.nextInt(population) >= Math.max(MINPOPULATION,population - 1)){
            int sum = 0;
            if(hasCity()){
                if(land.isLand()){
                    for(int i = 0;i<Constants.TILETYPES - 2;i++){
                        sum += (1 - city.taxes[i] + construct.affin[i]);
                    }
                    int val = gen.nextInt(sum);
                    sum = 0;
                    for(int i =0;i<Constants.TILETYPES - 2;i++){
                        sum += (1 - city.taxes[i] + construct.affin[i]);
                        if(sum >= val){
                            setConstruct(Construct.getConstructByOrdinal(i));
                            return;
                        }
                    }
                } else {
                    setConstruct(Construct.Fishing);
                }
            }// else if(population > Constants.CITYPOPULATION){
                //nation.addCity(this);
            //}
        }
    }

    public Construct getConstruct() {
        return construct;
    }

    public Land getLand() {
        return land;
    }

    private void setShape(){
        GeneralPath temp = Geometry.makeHex(this,radius);
        inner = Geometry.makeHex(this,radius-2);
        switch(construct){
            case Farm: temp.append(Geometry.makeFarm(this), false);
                break;
            case Cottage: temp.append(Geometry.makeCottage(this), false);
                break;
            case Workshop: temp.append(Geometry.makeWorkshop(this), false);
                break;
            case Mine: temp.append(Geometry.makeMine(this), false);
                break;
            case Fishing: temp.append(Geometry.makeFishing(this), false);
                break;
            case City: temp.append(Geometry.makeCity(this), false);
                break;
        }
        shape = temp;
    }

    public Shape getHex(){
        return shape;
    }

    public Shape getInner(){
        return inner;
    }

    public boolean hasEnemy(Nation n){
        for(Unit u:units.keySet()){
            if(!u.nation.equals(n) && !u.isDead()){
                return true;
            }
        }
        return false;
    }

    private boolean hasNation(Nation n){
        return units.containsValue(n);
    }

    public void addUnit(Unit u){
        units.put(u, u.nation);
    }

    public void removeUnit(Unit u){
        units.remove(u);
    }

    public Set<Unit> getUnits(){
        Set<Unit> set = new HashSet<Unit>();
        for(Unit u : units.keySet())
            if(!u.hasCommander())
                set.add(u);
        return set;
    }

    public ArrayList<Unit> getEnemies(Nation n){
        ArrayList<Unit> enemies = new ArrayList<Unit>();
        for(Unit u:units.keySet()){
            if(!u.nation.equals(n)){
                enemies.add(u);
            }
        }
        return enemies;
    }

    public Nation getANation(){
        for(Nation n:units.values())
            return n;
        return null;
    }

    public Nation getOwner(){
        return city.nation;
    }

    public int getPopulation() {
        return population;
    }


    public void changePopulation(int change) {
        this.population += change;
    }


    @Override
    public String toString() {
        return "Tile[" + x + "," + y + "," + land.name() + "," +  construct.name() + "]";
    }
}
