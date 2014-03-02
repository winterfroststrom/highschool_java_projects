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

public enum Land {
    //                        H F W     MC
    Grassland   (new Resource(1,4,1),   3,  new Color(65,150,25),  new Color(30,80,10)),
    Plains      (new Resource(3,3,1),   4,  new Color(170,150,70), new Color(160,165,95)),
    Desert      (new Resource(3,1,1),   5,  new Color(220,210,110),new Color(250,250,150)),
    Lavaland    (new Resource(4,1,3),   8,  new Color(220,0,0),    new Color(100,0,0)),
    Artic       (new Resource(3,1,1),   6,  new Color(255,255,255),new Color(210,210,210)),
    Shallows    (new Resource(1,3,3),   3,  new Color(40,155,175), new Color(25,70,80)),
    Ocean       (new Resource(1,1,1),   2,  new Color(20,70,130),  new Color(10,40,75));
    
    Resource resource;
    int MoveCost;
    Color fill;
    Color line;

    private Land(Resource resource, int MoveCost, Color fill, Color line) {
        this.resource = resource;
        this.MoveCost = MoveCost;
        this.fill = fill;
        this.line = line;
    }



    boolean isLand(){
        return !(equals(Land.Shallows) || equals(Land.Ocean) || equals(Land.Lavaland));
    }

    Land cycle(){
        switch(this){
            case Grassland:
                return Plains;
            case Plains:
                return Desert;
            case Desert:
                return Lavaland;
            case Lavaland:
                return Artic;
            case Artic:
                return Shallows;
            case Shallows:
                return Ocean;
            case Ocean:
                return Grassland;
            default: return null;
        }
    }

    static Land getLandByOrdinal(int ordinal){
        switch(ordinal){
            case 0:
                return Grassland;
            case 1:
                return Plains;
            case 2:
                return Desert;
            case 3:
                return Lavaland;
            case 4:
                return Artic;
            case 5:
                return Shallows;
            case 6:
                return Ocean;
            default: return Grassland;
        }
    }
}
