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

public class FieldSet {
    private CitySet fieldBase;
    private Field[] fields;
    public FieldSet(CitySet base){
        fieldBase = base;
        fields = new Field[3];
        for(int i =0;i<3;i++){
            fields[i] = new Field(fieldBase.fieldSize(i));
        }
    }

    public void resetAttackers(int field){
        fields[field].resetAttacks();
    }

    public void attack(int field, int unitIndex, Attackable target){
        fields[field].attack(unitIndex, target);
    }

    public boolean isValidField(int field){
        return field < 3 && field > -1;
    }

    public boolean canPlace(int field){
        return fields[field].canPlace();
    }

    public void place(Card add, int field){
        fields[field].place(add);
    }

    public boolean canRemove(int field){
        return fields[field].canRemove();
    }

    public Card remove(int index,int field){
        return fields[field].remove(index);
    }

    public Resource resourceSum(){
        ArrayList<Resource> temp = new ArrayList<Resource>(3);
        temp.add(fields[0].resourceSum());
        temp.add(fields[1].resourceSum());
        temp.add(fields[2].resourceSum());
        return Resource.sum(temp);
    }

    @Override
    public String toString(){
        return "FieldSet[inner[" + fields[0].toString() + "]&" +
                "outer[" + fields[1].toString() + "]&" +
                "frontier[" + fields[2].toString() + "]]";
    }
}
