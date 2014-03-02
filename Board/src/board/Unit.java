/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */

import java.util.LinkedList;

public abstract class Unit {
    Nation nation;
    Tile tile;
    String name;
    int health;
    int attack;
    private int move;
    private LinkedList<Tile> path;
    int MAXMOVE;
    int food;
    int leadership;
    int hammers;
    boolean water;
    boolean dead = false;
    Commander com;
    UnitType type;

    public Unit(Nation nation, Tile tile, 
                    String name, int attack, int move,
                    int hammers, int food,
                    boolean water,int leadership,UnitType type) {
        this.nation = nation;
        this.tile = tile;
        this.name = name;
        this.health = 100;
        this.attack = attack;
        this.leadership = leadership;
        this.move = move;
        MAXMOVE = move;
        this.food = food;
        this.hammers = hammers;
        this.water = water;
        this.type = type;
    }

    public Unit init(){
        nation.addUnit(this);
        return this;
    }

    public void attack(Unit target){
        Battle.battle(this, target);
    }

    public void reset(){
        move = MAXMOVE;
        health += ((health < 90)?10:100-health);
    }

    public boolean canMoveTo(Tile t){
        return move >= t.getMoveCost() && (t.getLand().isLand()||water);
    }

    public void moveTo(Tile t){
        path = null;
        Movement.move(nation.parent, this, t);
    }

    public boolean isDead(){
        return health < 1 || dead;
    }

    public boolean hasCommander(){
        return com != null;
    }

    public void die(){
        dead = true;
        tile.removeUnit(this);
    }

    @Override
    public String toString() {
        return name + "[" + health + "," + attack + "," + MAXMOVE + "," + leadership + "]";
    }

    public String displayStat(){
        return name + "-> A: " + attack + " M: " + MAXMOVE + " L: " + leadership;
    }
    public String displayCost(){
        return "Cost: H: " + hammers + " F: " + food;
    }


    public boolean hasPath(){
        return path != null && path.size() > 0;
    }

    public void autoMove(){
        while(hasPath() && canMoveTo(path.getLast())){
            Movement.move(nation.parent, this, path.removeLast());
        }
    }

    public void setPath(Tile end){
        path = AStar.path(nation.parent, tile, end);
    }

    public boolean isCommander(){
        return false;
    }

    public void useMove(int change){
        move -= change;
    }

    public String movement(){
        return move + "/" + MAXMOVE;
    }
}
