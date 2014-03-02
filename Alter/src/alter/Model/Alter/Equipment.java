/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model.Alter;

/**
 *
 * @author Scalene
 */
public abstract class Equipment extends Alter{
    public final int equipment_id;

    public Equipment(int equipment_id) {
        this.equipment_id = equipment_id;
        for(int i = 0;i < NUMBER_OF_REALITY_STATS;i++){
            reality_stats[i] = 1;
        }
    }
    
}
