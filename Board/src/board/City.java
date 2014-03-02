/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.util.ArrayList;

public class City {
    int hammers;
    int food;
    int foodReserve;
    int wealth;
    private Unit production;
    private Resource resources;
    int[] taxes;
    String name;
    Tile tile;
    ArrayList<Tile> influence;
    Nation nation;

    public City(Nation nation,Tile tile,String name) {
        taxes = new int[7];
        hammers = 0;
        food = 0;
        wealth = 0;
        this.name = name;
        this.influence = new ArrayList<Tile>();
        this.tile = tile;
        this.resources = new Resource();
        tile.changePopulation(1);
        this.nation = nation;

        tile.makeCity(this);
//        for(Tile t: nation.parent.board.adjacents(tile)){
//            influence.add(t);
//            t.setCity(this);
//            t.changePopulation(1);
//        }
        setProduction(Constants.FodderID);
        updateResources();
    }

    public void captured(Nation n){
        nation.caputured(this);
        n.conquered(this);
        taxes = new int[7];
        hammers = 0;
        wealth = wealth/2;
        nation = n;
        setProduction(Constants.FodderID);
    }

    public boolean hasProduction(){
        return production != null;
    }

    public void setProduction(int id){
        production = createByID(id);
    }

    public boolean hasResources(){
        if(production == null)
            return false;
        return food >= production.food && hammers >= production.hammers;
    }

    public void createUnit(){
        tile.addUnit(production);
        food -= production.food;
        hammers -= production.hammers;
        production.init();
        production = null;
        setProduction(Constants.FodderID);
    }

    private Unit createByID(int id){
        switch(id){
            case Constants.FodderID: return new Fodder(this.nation,this.tile);
            default: return new Fodder(this.nation,this.tile);
        }
    }

    private void updateResources(){
        resources.clear();
        for(Tile t:influence){
            resources.hammer += t.getHammers();
            resources.food += t.getFood();
            resources.wealth += (t.getWealth() + taxes[t.getConstruct().ordinal()]);
        }
        resources.food += (tile.getFood() - getTotalPopulation());
        resources.hammer += tile.getHammers();
        resources.wealth += tile.getWealth();
    }

    public int hammerTurnEstimate(){
        if(resources.hammer == 0){
            if((production.hammers - hammers) != 0)
                return Integer.MAX_VALUE;
            else
                return 0;
        } else {
            return (production.hammers - hammers)/resources.hammer;
        }
    }

    public int foodTurnEstimate(){
        if(resources.food == 0){
            if((production.food - food) != 0)
                return Integer.MAX_VALUE;
            else
                return 0;
        } else {
            return 2 * (production.food - food)/resources.food;/////////////////////
        }
    }

    public int productionTurnEstimate(){
        return Math.max(Math.max(hammerTurnEstimate(),foodTurnEstimate()), 1);
    }

    public void endTurn(){
        updateResources();
        gather();
        if(hasProduction() && hasResources()){
            createUnit();
        }
    }

    private void gather(){
        hammers += resources.hammer;
        food += resources.food/2;
        foodReserve += resources.food/2;///////////////////////////////
        wealth += resources.wealth;
        changePopulation();
    }

    public void setTax(Construct construct){
        taxes[construct.ordinal()]= (taxes[construct.ordinal()] == 0)? 1 : 0;
    }

    public String getProductionDisplayStat(){
        return production.displayStat();
    }

    public String getProductionDisplayCost(){
        return production.displayCost();
    }

    public int getProductionFood(){
        return production.food;
    }

    public int getProductionHammers(){
        return production.hammers;
    }

    public int getProductionTurnHammers(){
        return resources.hammer;
    }

    public int getProductionTurnFood(){
        return resources.food/2;
    }
    
    public boolean getProductionWater(){
        return production.water;
    }

    private int getTotalPopulation(){
        int sum = 0;
        for(Tile t:influence)
            sum += t.getPopulation();
        return (sum + tile.getPopulation());
    }

    public void changePopulation(){
        if(foodReserve < 0){
            Tile t = tile;
            for(Tile ti:influence)
                if(ti.getPopulation() > t.getPopulation())
                    t = ti;
            foodReserve += t.getPopulation() * 7 / 2;
            t.changePopulation(-1);
        } else {
            Tile t = tile;
            for(Tile ti:influence)
                if(ti.getPopulation() < t.getPopulation())
                    t = ti;
            int delfood = foodReserve;
            if(hasProduction())
                delfood =- production.food;
            if(delfood > t.getPopulation() * 7){
                foodReserve -= (t.getPopulation() * 7);
                t.changePopulation(1);
            }
        }
    }
}
