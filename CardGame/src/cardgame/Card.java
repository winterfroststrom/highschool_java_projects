/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
public class Card {
    public final Resource COST;
    public final String NAME;
    
    public Card(String name, Resource cost){
        this.COST = cost;
        this.NAME = name;
    }

    @Override
    public String toString(){
        return "Card["+NAME+","+COST+"]";
    }

    public String cardType(){
        return "base";
    }
}
