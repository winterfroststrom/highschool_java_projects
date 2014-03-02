/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model.Alter;

/**
 *
 * @author Scalene
 */
public class AlterReality extends Alter{
    protected int health;
    protected Equipment[] equipment = new Equipment[NUMBER_OF_EQUIPMENT];

    public String getName(){
        return "Reality";
    }

    public int getStatNumber(){
        return NUMBER_OF_REALITY_STATS;
    }
    public String[] getStatNames(){
        return reality_stat_names;
    }
    public int[] getStatValues(AlterReality system){
        return system.reality_stats;
    }
    public int[] getStatFormat(){
        return reality_stat_formats;
    }
    public boolean[] getStatVisible(){
        return reality_stats_visible;
    }
    public int getOffenseResistence(AlterReality system, int offense){
        return (int)(Math.max(  100*system.getRealityStat(INDEX_ARMOR)/
                                system.getRealityStat(INDEX_DEXTERITY),
                                100*system.getRealityStat(INDEX_ARMOR)/
                                system.getRealityStat(INDEX_DEXTERITY))*
                                offense/system.getRealityStat(INDEX_FOCUS)/100);
    }
    public int getOffense(AlterReality system){
        return (int)(Math.min(  100*system.getRealityStat(INDEX_STRENGTH)/
                                system.getRealityStat(INDEX_DEXTERITY),
                                100*system.getRealityStat(INDEX_STRENGTH)/
                                system.getRealityStat(INDEX_DEXTERITY))*
                                (system.getRealityStat(INDEX_STRENGTH)+
                                system.getRealityStat(INDEX_DEXTERITY))*
                                system.getRealityStat(INDEX_FOCUS)/100);
    }
    public int getRestore(AlterReality system){
        return 0;
    }
    public int getRestoreResistence(AlterReality system, int restore){
        return restore;
    }
    
    protected int getSystemPriority(AlterReality system){
        return system.getRealityStat(INDEX_SPEED);
    }
    protected int getSystemTurns(AlterReality system){
        return 1;
    }

    public final int getCondition(){
        return health;
    }

    public final void setCondition(int change){
        health += change;
    }

    public final boolean isDead(AlterReality system){
        return health > 0;
    }

    public final int getPriority(AlterReality field){
        return (field.getSystemPriority(this)*field.getRealityStat(INDEX_ESSENCE) +
                getSystemPriority(this)*getRealityStat(INDEX_ESSENCE))/
                (field.getRealityStat(INDEX_ESSENCE)+getRealityStat(INDEX_ESSENCE));
    }
    public final int getTurns(AlterReality field){
        return (field.getSystemTurns(this)*field.getRealityStat(INDEX_ESSENCE) +
                getSystemTurns(this)*getRealityStat(INDEX_ESSENCE))/
                (field.getRealityStat(INDEX_ESSENCE)+getRealityStat(INDEX_ESSENCE));
    }
    public final void attack(AlterReality defender,AlterReality field){
        int offense =       (getOffense(this)*
                                getRealityStat(INDEX_ESSENCE)*100 +
                            field.getOffense(this)*
                                field.getRealityStat(INDEX_ESSENCE)*50)
                            /
                            (getRealityStat(INDEX_ESSENCE)*100 + field.getRealityStat(INDEX_ESSENCE)*50);
        
        int resistence =    (defender.getOffenseResistence(this,offense)*
                                defender.getRealityStat(INDEX_ESSENCE)*100 +
                            field.getOffenseResistence(this,offense)*
                                field.getRealityStat(INDEX_ESSENCE)*50)
                            /
                            (defender.getRealityStat(INDEX_ESSENCE)*100 + field.getRealityStat(INDEX_ESSENCE)*50);
        defender.setCondition(Math.min(resistence - offense,0));
    }
    
    public final void restore(AlterReality healed,AlterReality field){
        int restore =       (getRestore(this)*
                                getRealityStat(INDEX_ESSENCE)*100 +
                            field.getRestore(this)*
                                field.getRealityStat(INDEX_ESSENCE)*50)
                            /
                            (getRealityStat(INDEX_ESSENCE)*100 + field.getRealityStat(INDEX_ESSENCE)*50);

        int resistence =    (healed.getRestoreResistence(this,restore)*
                                healed.getRealityStat(INDEX_ESSENCE)*100 +
                            field.getRestoreResistence(this,restore)*
                                field.getRealityStat(INDEX_ESSENCE)*50)
                            /
                            (healed.getRealityStat(INDEX_ESSENCE)*100 + field.getRealityStat(INDEX_ESSENCE)*50);
        healed.setCondition(Math.max(restore - resistence,0));
    }

    protected final int getRealityStat(int stat){
        return (int)(reality_stats[stat]*reality_stat_mboosts[stat])+reality_stat_aboosts[stat];
    }
}
