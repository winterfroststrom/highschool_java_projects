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

public class TileInfo {
    Game parent;

    public TileInfo(Game parent) {
        this.parent = parent;
    }


    public void paintPart(Graphics2D g){
        if(parent.board.hover !=null){
            statInfo(g,parent.board.hover);
        } else if(parent.board.selected !=null){
            statInfo(g,parent.board.selected);
        }
        if(parent.units.selectedUnit != null){
            unitsInfo(g);
        }
    }

    private void statInfo(Graphics2D g,Tile tile){
        g.setColor(new Color(255, 53, 94, 100));
        g.fill(parent.top.T);
        //g.drawRect(0, parent.top.U.y - parent.top.T.height, parent.top.U.x, parent.top.T.height);

        //g.setColor(new Color(120, 134, 107, 170));
        g.setColor(Color.BLACK);
        Font f = new Font(Font.DIALOG,Font.BOLD,13);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        String string = "";
        if(tile.isCity()){
            string = tile.city.name;
        } else if(tile.hasCity()){
            string = tile.city.name + " Area";
        } else {
            string = "Unclaimed";
        }

        int x = fm.stringWidth(string)/2;
        g.drawString(string, parent.top.T.x + parent.top.T.width/2 - x,
                        parent.top.T.y + 15);
        f = new Font(Font.DIALOG,Font.BOLD,11);
        g.setFont(f);

        if(tile.hasCity()){
            string = tile.city.nation.name;
        } else {
            string = "Unclaimed";
        }
        g.drawString(string, parent.top.T.x + 5, parent.top.T.y + 25);

        string = tile.getLand().toString();
        g.drawString(string, parent.top.T.x + 5, parent.top.T.y + 35);
        string = tile.getConstruct().toString();
        g.drawString(string,parent.top.U.x/2, parent.top.T.y + 35);

        f = new Font(Font.DIALOG,Font.ROMAN_BASELINE,10);
        g.setFont(f);
        string = "H/F/W: " + (tile.getHammers()) + "/"  + (tile.getFood()) + "/" + (tile.getWealth());
        g.drawString(string, parent.top.T.x + 5, parent.top.T.y + 50);
        if(tile.hasCity())
            string = "Population: " + tile.getPopulation();
        else
            string = "";
        g.drawString(string, parent.top.T.x + 5, parent.top.T.y + 65);
        string = "";
        g.drawString(string, parent.top.T.x + 5, parent.top.T.y + 80);
        string = "Movement: " + (tile.getMoveCost());
        g.drawString(string, parent.top.T.x + 5, parent.top.T.y + 95);
    }
    private void unitsInfo(Graphics2D g){
        g.setColor(new Color(242, 133, 0, 100));
        g.setStroke(new BasicStroke(6));
        g.fill(parent.top.U);

        g.setColor(Color.BLACK);
        Font f = new Font(Font.DIALOG,Font.BOLD,15);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        String string = parent.units.selectedUnit.name;

        int x = fm.stringWidth(string)/2;
        g.drawString(string, parent.top.U.x + parent.top.U.width/2 - x, parent.top.U.y + 15);
        f = new Font(Font.DIALOG,Font.BOLD,11);
        g.setFont(f);

        string = parent.units.selectedUnit.nation.name;
        g.drawString(string,parent.top.U.x + 5, parent.top.U.y + 25);

        f = new Font(Font.DIALOG,Font.ROMAN_BASELINE,10);
        g.setFont(f);

        string = "Health: " + (parent.units.selectedUnit.health);
        g.drawString(string,parent.top.U.x + 5, parent.top.U.y + 38);
        string = "Attack: " + (parent.units.selectedUnit.attack);
        g.drawString(string,parent.top.U.x + 5, parent.top.U.y + 52);
        string = "Movement: " + parent.units.selectedUnit.movement();
        g.drawString(string,parent.top.U.x + 5, parent.top.U.y + 66);
        string = "Leadership: " + (parent.units.selectedUnit.leadership);
        g.drawString(string,parent.top.U.x + 5, parent.top.U.y + 80);
        string = "Water: " + (parent.units.selectedUnit.water);
        g.drawString(string,parent.top.U.x + 5, parent.top.U.y + 94);
    }
}
