/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
public enum Construct {
    //                    H F W               G P D L A S O
    Void    (new Resource(0,0,0),   new int[]{1,1,1,1,1,1,1}    ,0),
    Farm    (new Resource(0,3,0),   new int[]{2,1,0,0,0,0,0}    ,-1),
    Cottage (new Resource(0,0,3),   new int[]{2,2,1,0,0,0,0}    ,-2),
    Workshop(new Resource(2,0,2),   new int[]{1,2,1,0,0,0,0}    ,1),
    Mine    (new Resource(3,0,0),   new int[]{0,1,1,2,1,0,0}    ,-3),
    Fishing (new Resource(0,2,2),   new int[]{0,0,0,0,0,2,1}    ,-1),
    City    (new Resource(4,4,4),   new int[]{0,0,0,0,0,0,0}    ,0);

    Resource resource;
    int[] affin;
    int MoveCost;

    private Construct(Resource resource, int[] affin, int MoveCost) {
        this.resource = resource;
        this.affin = affin;
        this.MoveCost = MoveCost;
    }

    private Construct(Resource resource, int[] affin) {
        this.resource = resource;
        this.affin = affin;
    }


    Construct cycle(){
        switch(this){
            case Void:
                return Farm;
            case Farm:
                return Cottage;
            case Cottage:
                return Workshop;
            case Workshop:
                return Mine;
            case Mine:
                return Fishing;
            case Fishing:
                return Farm;
            default:
                return null;
        }
    }

    static Construct getConstructByOrdinal(int ordinal){
        switch(ordinal){
            case 0:
                return Void;
            case 1:
                return Farm;
            case 2:
                return Cottage;
            case 3:
                return Workshop;
            case 4:
                return Mine;
            case 5:
                return Fishing;
            case 6:
                return City;
            default :
                return Void;
        }
    }
}
