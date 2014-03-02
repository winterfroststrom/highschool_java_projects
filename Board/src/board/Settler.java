/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
public class Settler extends Unit{

    public Settler(Nation nation, Tile tile){
        super(nation,tile,"Settler",
                UnitEnum.BSA + UnitEnum.getByNation(nation,UnitType.Settler).attack,
                UnitEnum.BSM + UnitEnum.getByNation(nation,UnitType.Settler).movement,
                UnitEnum.BSH + UnitEnum.getByNation(nation,UnitType.Settler).hammers,
                UnitEnum.BSF + UnitEnum.getByNation(nation,UnitType.Settler).food,
                false,
                UnitEnum.BSL + UnitEnum.getByNation(nation,UnitType.Settler).leadership,
                UnitType.Settler);
    }

}
