/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
public class Movement {

    public static void move(Game parent,Unit mover,Tile target){
        if(target.hasEnemy(mover.nation)){
            Unit enemy = Battle.selectMatch(mover,target.getEnemies(mover.nation));
            if(Battle.battle(mover, enemy)){
                if(!target.hasEnemy(mover.nation)){
                    mover.moveTo(target);
                    if(target.isCity()){
                        target.city.captured(mover.nation);
                    }
                }
            }
        } else if(target.isCity()){
            mover.tile.removeUnit(mover);
            target.addUnit(mover);
            mover.tile = target;
            mover.useMove(target.getMoveCost());
            target.city.captured(mover.nation);
        } else {
            mover.tile.removeUnit(mover);
            target.addUnit(mover);
            mover.tile = target;
            mover.useMove(target.getMoveCost());
        }
    }
}
