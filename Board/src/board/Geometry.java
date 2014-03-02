/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.awt.geom.GeneralPath;

public class Geometry {

    public static GeneralPath makeCity(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x-tile.radius/3, tile.point.y);
        path.lineTo(tile.point.x-tile.radius/3, tile.point.y+tile.radius/4);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y+tile.radius/4);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y);
        path.closePath();
        GeneralPath path2 = new GeneralPath();
        path.moveTo(tile.point.x-tile.radius/3, tile.point.y);
        path.lineTo(tile.point.x-tile.radius/6, tile.point.y-tile.radius/4);
        path.lineTo(tile.point.x, tile.point.y);
        path.lineTo(tile.point.x+tile.radius/6, tile.point.y-tile.radius/4);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y);
        path.append(path2, false);
        return path;
    }
    public static GeneralPath makeFishing(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x+tile.radius/3, tile.point.y);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y+tile.radius/4);
        path.lineTo(tile.point.x-tile.radius/4, tile.point.y+tile.radius/4);
        path.lineTo(tile.point.x-tile.radius/3, tile.point.y);
        path.closePath();
        GeneralPath path2 = new GeneralPath();
        path2.moveTo(tile.point.x+tile.radius/3, tile.point.y-tile.radius/7);
        path2.lineTo(tile.point.x+tile.radius/3, tile.point.y-tile.radius/3);
        path2.lineTo(tile.point.x-tile.radius/3, tile.point.y-tile.radius/7);
        path2.closePath();
        path.append(path2, true);
        return path;
    }

    public static GeneralPath makeMine(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x-tile.radius/4, tile.point.y-tile.radius/4);
        path.lineTo(tile.point.x+tile.radius/4, tile.point.y-tile.radius/4);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y+tile.radius/4);
        path.lineTo(tile.point.x-tile.radius/3, tile.point.y+tile.radius/4);
        path.closePath();
        return path;
    }

    public static GeneralPath makeWorkshop(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x, tile.point.y);
        path.lineTo(tile.point.x+tile.radius/4, tile.point.y);
        path.lineTo(tile.point.x+tile.radius/4, tile.point.y+tile.radius/4);
        path.lineTo(tile.point.x-tile.radius/4, tile.point.y+tile.radius/4);
        path.lineTo(tile.point.x-tile.radius/4, tile.point.y-tile.radius/6);
        path.lineTo(tile.point.x, tile.point.y-tile.radius/5);
        path.closePath();
        GeneralPath path2 = new GeneralPath();
        path2.moveTo(tile.point.x-tile.radius/5, tile.point.y-tile.radius/3);
        path2.lineTo(tile.point.x-tile.radius/3, tile.point.y-tile.radius/6);
        path2.lineTo(tile.point.x+tile.radius/4, tile.point.y-tile.radius/6);
        path2.closePath();
        path.append(path2, false);
        return path;
    }

    public static GeneralPath makeFarm(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x-tile.radius/3, tile.point.y);
        path.lineTo(tile.point.x-tile.radius/3, tile.point.y-tile.radius/3);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y-tile.radius/3);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y+tile.radius/3);
        path.lineTo(tile.point.x-tile.radius/3, tile.point.y+tile.radius/3);
        path.lineTo(tile.point.x-tile.radius/3, tile.point.y);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y);
        path.moveTo(tile.point.x, tile.point.y+tile.radius/3);
        path.lineTo(tile.point.x, tile.point.y-tile.radius/3);
        return path;
    }

    public static GeneralPath makeCottage(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x, tile.point.y-tile.radius/3);
        path.lineTo(tile.point.x-tile.radius/3, tile.point.y);
        path.lineTo(tile.point.x+tile.radius/3, tile.point.y);
        path.closePath();
        GeneralPath path2 = new GeneralPath();
        path2.moveTo(tile.point.x-tile.radius/4, tile.point.y);
        path2.lineTo(tile.point.x-tile.radius/4, tile.point.y+tile.radius/3);
        path2.lineTo(tile.point.x+tile.radius/4, tile.point.y+tile.radius/3);
        path2.lineTo(tile.point.x+tile.radius/4, tile.point.y);
        path.append(path2, false);
        return path;
    }

    public static GeneralPath makeNation(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x-tile.radius/2, tile.point.y-tile.radius/2);
        path.lineTo(tile.point.x+tile.radius/2, tile.point.y-tile.radius/2);
        path.lineTo(tile.point.x+tile.radius/2, tile.point.y+tile.radius/2);
        path.lineTo(tile.point.x-tile.radius/2, tile.point.y+tile.radius/2);
        path.closePath();
        return path;
    }

    public static GeneralPath makeStack(Tile tile){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x-tile.radius/2, tile.point.y-tile.radius/2);
        path.lineTo(tile.point.x+tile.radius/2, tile.point.y+tile.radius/2);
        path.lineTo(tile.point.x+tile.radius/2, tile.point.y-tile.radius/2);
        path.lineTo(tile.point.x-tile.radius/2, tile.point.y+tile.radius/2);
        path.closePath();
        return path;
    }

    public static GeneralPath makeHex(Tile tile, int radius){
        GeneralPath path = new GeneralPath();
        path.moveTo(tile.point.x-radius, tile.point.y);
        path.lineTo(tile.point.x-radius*Math.cos(Math.PI/3),
                        tile.point.y-radius*Math.sin(Math.PI/3));
        path.lineTo(tile.point.x+radius*Math.cos(Math.PI/3),
                        tile.point.y-radius*Math.sin(Math.PI/3));
        path.lineTo(tile.point.x+radius, tile.point.y);
        path.lineTo(tile.point.x+radius*Math.cos(Math.PI/3),
                        tile.point.y+radius*Math.sin(Math.PI/3));
        path.lineTo(tile.point.x-radius*Math.cos(Math.PI/3),
                        tile.point.y+radius*Math.sin(Math.PI/3));
        path.closePath();
        return path;
    }
}
