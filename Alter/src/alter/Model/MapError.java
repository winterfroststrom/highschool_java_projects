/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model;

/**
 *
 * @author Scalene
 */

public class MapError extends Throwable{
    public static final int TAG_ERROR = 0;
    public static final int NAME_ERROR = 1;
    public static final int X_ERROR = 2;
    public static final int Y_ERROR = 3;
    public static final int SIZE_ERROR = 4;
    public static final int TILE_ERROR = 5;
    public static final int ARRAY_ERROR = 6;
    public static final int ITEM_ERROR = 7;
    public static final int NPC_ERROR = 8;
    public static final int NODE_ERROR = 9;
    int error;

    public MapError(int error) {
        super();
        this.error = error;
    }

    @Override
    public String toString() {
        switch(error){
            case TAG_ERROR: return "There is a problem with one of the map's tags";
            case NAME_ERROR: return "Invalid map name.";
            case X_ERROR: return "Invalid map X coordinate name.";
            case Y_ERROR: return "Invalid map Y coordinate name.";
            case SIZE_ERROR: return "Invalid map loop size data.";
            case TILE_ERROR: return "Invalid map tile data.";
            case ARRAY_ERROR: return "Invalid map array data.";
            case ITEM_ERROR: return "Invalid map item data.";
            case NPC_ERROR: return "Invalid map npc data.";
            case NODE_ERROR: return "Invalid map node data.";
            default: return super.toString();
        }
    }
}
