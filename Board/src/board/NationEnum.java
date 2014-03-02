/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.awt.Color;

public enum NationEnum {
    Darklands(new Color(0,0,0,170)),
    Templars_of_Work(new Color(255, 159, 0));

    final Color color;

    static final String[][] CITY_NAMES = {
            {"Dark Haven", "Shadow's Edge","Ravina","Pitt's Berg","Old Tower",
                     "Heaven's Bane","Hell's Gate","Dusty's Tome","2 + 2 = 5"}
                                        ,
            {"You a' Smite Steve","Terra Poundsly", "Miss-Pacific", "Haymarket"}
                                            };

    private NationEnum(Color color) {
        this.color = color;
    }

    static String getNextName(Nation n,int index){
        String[] nationNames = CITY_NAMES[NationEnum.valueOf(n.name).ordinal()];
        if(nationNames.length > index){
            return nationNames[index];
        }
        else return n.name + " City " + (index - nationNames.length);
    }
}
