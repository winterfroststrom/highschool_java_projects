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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.Point;

public class Control {
    Game parent;
    Rectangle[] vectors;
    private boolean[] validVector;

    public Control(Game parent) {
        this.parent = parent;
        vectors = new Rectangle[6];
        validVector = new boolean[6];

        vectors[Constants.topLeft] = new Rectangle(parent.top.M.x + 15, parent.top.M.y + 30, 15, 15);
        vectors[Constants.bottomLeft] = new Rectangle(parent.top.M.x + 15, parent.top.M.y + 55, 15, 15);

        vectors[Constants.top] = new Rectangle(parent.top.M.x + 42, parent.top.M.y + 15, 15, 15);
        vectors[Constants.bottom] = new Rectangle(parent.top.M.x + 42, parent.top.M.y + 70, 15, 15);

        vectors[Constants.topRight] = new Rectangle(parent.top.M.x + 70, parent.top.M.y + 30, 15, 15);
        vectors[Constants.bottomRight] = new Rectangle(parent.top.M.x + 70, parent.top.M.y + 55, 15, 15);
    }

    public void paintPart(Graphics2D g){
        g.setColor(new Color(0, 128, 0,125));
        g.fill(parent.top.O);
        g.setColor(new Color(255,255,255,125));
        g.setStroke(new BasicStroke(6));
        g.draw(parent.top.M);
        g.fill(parent.top.M);

        g.setStroke(new BasicStroke(3));
        for(int i = 0;i < 6; i++){
            g.draw(vectors[i]);
        }
        if(parent.units.selectedOwnedUnit != null &&
                parent.units.selectedOwnedUnit.nation.equals(parent.nations.get(0)))
            paintVectors(g);
    }

    private void paintVectors(Graphics2D g){
        Tile unitTile = parent.units.selectedOwnedUnit.tile;
        for(int i = 0;i < 6; i++){
            if(parent.board.getAdjacent(unitTile,i) != null &&
                parent.units.selectedOwnedUnit.canMoveTo(parent.board.parent.board.getAdjacent(unitTile,i))){
                g.draw(vectors[i]);
                validVector[i] = true;
            } else {
                validVector[i] = false;
            }
        }
    }

    private void resetVectors(){
        if(parent.units.selectedOwnedUnit != null){
            Tile unitTile = parent.units.selectedOwnedUnit.tile;
            for(int i = 0;i < 6; i++){
                if(parent.board.getAdjacent(unitTile,i) != null &&
                    parent.units.selectedOwnedUnit.canMoveTo(parent.board.parent.board.getAdjacent(unitTile,i))){
                    validVector[i] = true;
                } else {
                    validVector[i] = false;
                }
            }
        }
    }

    public void pressEvent(Point e){
        if(parent.units.selectedOwnedUnit != null){
            for(int i = 0; i < 6;i++){
                if(validVector[i]){
                    if(vectors[i].contains(e)){
                        Tile target = parent.board.getAdjacent(parent.units.selectedOwnedUnit.tile, i);
                        parent.units.selectedOwnedUnit.moveTo(target);
                        parent.units.selectedOwnedUnit = null;
                        parent.units.selectedUnit = null;
                        resetVectors();
                        return;
                    }
                }
            }
        }
    }
}