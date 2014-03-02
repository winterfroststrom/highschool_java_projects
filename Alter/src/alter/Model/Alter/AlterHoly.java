/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model.Alter;

/**
 *
 * @author Scalene
 */
public class AlterHoly extends AlterReality{
    private static final int INDEX_HOPE            = 0;
    private static final int INDEX_COMPASSION      = 1;
    private static final int INDEX_DIVINITY        = 2;
    private static final int INDEX_RIGHT           = 3;

    private static final int NUMBER_OF_HOLY_STATS  = 4;

    private String[] holy_stat_names =      {"Hope",            "Compassion",   "Divinity",     "Right"};
    private int[] holy_stat_formats =       {FORMAT_PERCENT,    FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER};
    private boolean[] holy_stats_visible =  {true,              true,           true,           true};

    @Override
    public String getName(){
        return "Holy";
    }

    @Override
    public int getStatNumber(){
        return NUMBER_OF_HOLY_STATS;
    }

    @Override
    public String[] getStatNames(){
        return holy_stat_names;
    }

    @Override
    public int[] getStatValues(AlterReality system){
        int[] holy_stats = new int[NUMBER_OF_HOLY_STATS];
        holy_stats[INDEX_HOPE] = getHolyStat(system,INDEX_HOPE);
        holy_stats[INDEX_COMPASSION] = getHolyStat(system,INDEX_COMPASSION);
        holy_stats[INDEX_DIVINITY] = getHolyStat(system,INDEX_DIVINITY);
        holy_stats[INDEX_RIGHT] = getHolyStat(system,INDEX_RIGHT);
        return holy_stats;
    }

    @Override
    public int[] getStatFormat(){
        return holy_stat_formats;
    }

    @Override
    public boolean[] getStatVisible(){
        return holy_stats_visible;
    }

    @Override
    public int getOffense(AlterReality system){
        return getHolyStat(system,INDEX_RIGHT);
    }

    @Override
    public int getOffenseResistence(AlterReality system, int offense){
        return getHolyStat(system,INDEX_HOPE)/(getHolyStat(system,INDEX_DIVINITY)/100+1);
    }

    @Override
    public int getRestore(AlterReality system){
        return getHolyStat(system,INDEX_COMPASSION);
    }

    @Override
    public int getRestoreResistence(AlterReality system, int restore){
        return -100*health/getHolyStat(system,INDEX_HOPE)*getHolyStat(system,INDEX_COMPASSION)/100;
    }

    private static int getHolyStat(AlterReality system, int stat){
        switch(stat){
            case INDEX_HOPE:
                return (system.getRealityStat(INDEX_JOY)*6+system.getRealityStat(INDEX_TENSION)*4)*
                        system.getRealityStat(INDEX_FOCUS)/1000;
            case INDEX_COMPASSION:
                return system.getRealityStat(INDEX_COMPLETENESS)*
                        (100-system.getRealityStat(INDEX_SELF))/100;
            case INDEX_DIVINITY:
                return (system.getRealityStat(INDEX_ESSENCE)*70+system.getRealityStat(INDEX_COMPLEXITY)*30)/100;
            case INDEX_RIGHT:
                return system.getRealityStat(INDEX_COMPLETENESS)*(system.getRealityStat(INDEX_SELF))/100;
            default: return 0;
        }
    }
}
