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

public class Commander extends Unit{
    private ArrayList<Unit> units;
    private final static int MAXSIZE = 8;
    int bonus;

    public Commander(Nation nation, Tile tile){
        super(nation, tile,
                UnitEnum.getByNation(nation,UnitType.Commander).name(),
                UnitEnum.getByNation(nation,UnitType.Commander).attack + 10,
                UnitEnum.getByNation(nation,UnitType.Commander).movement + 15,
                UnitEnum.getByNation(nation,UnitType.Commander).hammers + 500,
                UnitEnum.getByNation(nation,UnitType.Commander).food + 100,
                false, 
                UnitEnum.getByNation(nation,UnitType.Commander).leadership + 3,
                UnitType.Commander);
        units = new ArrayList<Unit>(MAXSIZE);
    }

    public boolean canAdd(Unit u){
        return units.size() < MAXSIZE && !u.isCommander();
    }

    public void add(Unit u){
        u.com = this;
        units.add(u);
        bonus += u.leadership;
    }

    public void remove(Unit u){
        u.com = null;
        units.remove(u);
        bonus -= u.leadership;
    }

    @Override
    public boolean isCommander() {
        return true;
    }
}