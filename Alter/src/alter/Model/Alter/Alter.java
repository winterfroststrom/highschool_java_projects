/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model.Alter;

/**
 *
 * @author Scalene
 */
public class Alter {
    protected static final int INDEX_VITALITY           = 0;
    protected static final int INDEX_ARMOR              = 1;
    protected static final int INDEX_STRENGTH           = 2;
    protected static final int INDEX_SPEED              = 3;
    protected static final int INDEX_DEXTERITY          = 4;
    protected static final int INDEX_ESSENCE            = 5;
    protected static final int INDEX_FOCUS              = 6;
    protected static final int INDEX_COMPLETENESS       = 7;
    protected static final int INDEX_COMPLEXITY         = 8;
    protected static final int INDEX_SELF               = 9;
    protected static final int INDEX_JOY                = 10;
    protected static final int INDEX_TENSION            = 11;

    protected static final int NUMBER_OF_REALITY_STATS  = 12;

    protected static final int FORMAT_INTEGER           = 0;
    protected static final int FORMAT_STRING            = 1;
    protected static final int FORMAT_PERCENT           = 2;

    protected static final int EQUIPMENT_HEAD           = 0;
    protected static final int EQUIPMENT_BODY           = 1;
    protected static final int EQUIPMENT_LEGS           = 2;
    protected static final int EQUIPMENT_LEFT           = 3;
    protected static final int EQUIPMENT_RIGHT          = 4;
    protected static final int EQUIPMENT_BACK           = 5;
    protected static final int EQUIPMENT_EXTRA          = 6;
    protected static final int NUMBER_OF_EQUIPMENT      = 7;

    protected static final String[] equipment_names = {"Head", "Body", "Legs", "Left", "Right", "Back", "Extra"};

    protected static final String[] reality_stat_names = {      "Vitality",     "Armor",        "Strength",     "Speed",        "Dexterity",    "Essence",      "Focus",        "Completeness", "Complexity",   "Self",         "Joy",          "Tension"};
    protected static final int[] reality_stat_formats = {       FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_INTEGER, FORMAT_PERCENT, FORMAT_PERCENT, FORMAT_PERCENT};
    protected static final boolean[] reality_stats_visible = {  true,           true,           true,           true,           true,           true,           false,          false,          false,          false,          false,          false};

    protected int[] reality_stats = new int[NUMBER_OF_REALITY_STATS];
    protected double[] reality_stat_mboosts = new double[NUMBER_OF_REALITY_STATS];
    protected int[] reality_stat_aboosts = new int[NUMBER_OF_REALITY_STATS];
}
