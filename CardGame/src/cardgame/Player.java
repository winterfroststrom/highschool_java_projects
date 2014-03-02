/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Attackable{
    public final int MAXHANDSIZE = 10;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> discard;
    private int[] resources;
    private CitySet cities;
    private FieldSet fields;

    public Player(String playerFile){
        hand = new ArrayList<Card>(MAXHANDSIZE);
        resources = new int[4];
        ArrayList<Integer>[] cards = PlayerParser.parsePlayer(playerFile);
        cities = CityParser.parseCity(cards[0]);
        fields = new FieldSet(cities);
        if(cities == null)
            TheRulez.error();
        deck = CardParser.createDeck(cards[1],cards[2]);
        shuffleDeck();
        while(canDraw()){
            draw();
        }
        discard = new ArrayList<Card>();
    }

    public void gainResources(){
        resources = fields.resourceSum().addResources(resources);
    }

    public void resetAttackers(){
        fields.resetAttackers(0);
        fields.resetAttackers(1);
        fields.resetAttackers(2);
    }

    public void attack(int field, int unitIndex, Attackable target){
        if(fields.isValidField(field))
            fields.attack(field, unitIndex, target);
    }

    public void attacked(UnitCard attacker){
        changeHealth(-attacker.getAttack(),getTopCity());
    }

    public boolean canDiscard(){
        return hand.size() > 0;
    }

    public void discard(int index){
        discard.add(hand.remove(index));
    }

    public boolean hasLost(){
        if(deck.size() < 1)
            return true;
        if(cities.getTotalHealth() < 1)
            return true;
        return false;
    }

    public int getHealth(int city){
        return cities.getHealth(city);
    }

    public void changeHealth(int change,int city){
        cities.changeHealth(change, city);
    }

    public int getTopCity(){
        return cities.getTopCity();
    }

    public boolean canDraw(){
        if(!deck.isEmpty())
            return hand.size() <= MAXHANDSIZE;
        return false;
    }

    public void draw(){
        hand.add(deck.remove(deck.size()-1));
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public boolean hasResources(Resource res){
        return res.hasResources(resources);
    }
}
