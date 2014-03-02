/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
public class Fodder extends Unit{
    public Fodder(Nation nation, Tile tile){
        super(nation,tile,
                UnitEnum.getByNation(nation,UnitType.Fodder).name(),
                UnitEnum.BFA + UnitEnum.getByNation(nation,UnitType.Fodder).attack,
                UnitEnum.BFM + UnitEnum.getByNation(nation,UnitType.Fodder).movement,
                UnitEnum.BFH + UnitEnum.getByNation(nation,UnitType.Fodder).hammers,
                UnitEnum.BFF + UnitEnum.getByNation(nation,UnitType.Fodder).food,
                false, 
                UnitEnum.BFL + UnitEnum.getByNation(nation,UnitType.Fodder).leadership,
                UnitType.Fodder);
    }
}
