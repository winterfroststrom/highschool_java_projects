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

public class Field {
    private ArrayList<Card> field;
    private ArrayList<ResourceCard> resources;
    private ArrayList<UnitCard> units;
    public final int cardMax;

    public Field(int size){
        field = new ArrayList<Card>(size);
        resources = new ArrayList<ResourceCard>(size);
        units = new ArrayList<UnitCard>(size);
        cardMax = size;
    }

    private boolean isUnit(int index){
        return field.get(index).cardType().equalsIgnoreCase("unit");
    }

    public void resetAttacks(){
        for(UnitCard unit:units)
            unit.resetAttack();
    }

    public void attack(int unitIndex, Attackable target){
        if(isUnit(unitIndex)){
            UnitCard unit = (UnitCard) field.get(unitIndex);
            if(unit.canAttack())
                unit.attack(target);
        }
    }

    public boolean canPlace(){
        return cardMax > field.size();
    }

    public void place(Card add){
        if(add.cardType().equalsIgnoreCase("unit")){
            units.add((UnitCard) add);
        }else if(add.cardType().equalsIgnoreCase("resource")){
            resources.add((ResourceCard) add);
        }
        field.add(add);
    }

    public boolean canRemove(){
        return field.size()>0;
    }

    public Card remove(int index){
        Card toRemove = field.remove(index);
        if(toRemove.cardType().equalsIgnoreCase("unit")){
            units.remove((UnitCard)toRemove);
        } else if(toRemove.cardType().equalsIgnoreCase("resource")){
            resources.remove((ResourceCard)toRemove);
        }
        return toRemove;
    }

    public Resource resourceSum(){
        return Resource.sumCards(resources);
    }

    @Override
    public String toString(){
        return "Field["+cardMax+"]";
    }
}
