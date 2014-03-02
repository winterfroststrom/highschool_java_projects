/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
public class City {
    private int health;
    public final String NAME;
    public final String THEME;
    public final int FIELD;

    public City(String name, int health, String theme, int field){
        NAME = name;
        this.health = health;
        THEME = theme;
        FIELD = field;
    }

    public int getHealth(){
        return health;
    }

    public void changeHealth(int change){
        health += change;
    }

    @Override
    public String toString(){
        return "City["+NAME+","+health+","+THEME+","+FIELD+"]";
    }
}
