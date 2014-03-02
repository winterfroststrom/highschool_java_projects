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

public class CityInfo {
    Game parent;

    public CityInfo(Game parent) {
        this.parent = parent;
    }

    public void paintPart(Graphics2D g){
        g.setColor(new Color(255,255,255,125));
        g.setStroke(new BasicStroke(6));
        g.draw(parent.top.C);
        g.fill(parent.top.C);
        if(parent.board.selected != null && parent.board.selected.isCity() &&
                parent.board.selected.getOwner().equals(parent.nations.get(0)) &&
                parent.board.selected.city.hasProduction()){
            paintCityProduction(g);
            paintProductionUnit(g);
        }
    }

    private void paintCityProduction(Graphics2D g){
        g.setColor(Color.black);
        Font f = new Font(Font.DIALOG,Font.BOLD,13);
        g.setFont(f);

        String string = "";
        string = parent.board.selected.city.name;
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 15);
        f = new Font(Font.DIALOG, Font.PLAIN, 10);
        g.setFont(f);

        string = "Res: W: " + parent.board.selected.city.wealth +
                " H: " + parent.board.selected.city.hammers +
                " F: " + parent.board.selected.city.food;
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 30);

        string = UnitEnum.display(parent.board.selected.city.nation, UnitType.Fodder);
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 40);

        string = UnitEnum.display(parent.board.selected.city.nation, UnitType.Commander);
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 50);

        string = UnitEnum.display(parent.board.selected.city.nation, UnitType.Settler);
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 60);

        string = "";
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 70);

        string = "";
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 80);

        string = "";
        g.drawString(string, parent.top.C.x + 5, parent.top.C.y + 90);
    }

    private void paintProductionUnit(Graphics2D g){
        g.setColor(new Color(253, 238, 0, 100));
        g.setStroke(new BasicStroke(6));
        g.fill(parent.top.P);

        g.setColor(Color.BLACK);
        Font f = new Font(Font.DIALOG,Font.BOLD,15);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        String string = "Production";

        int x = fm.stringWidth(string)/2;
        g.drawString(string, parent.top.P.x + parent.top.P.width/2 - x, parent.top.P.y + 15);
        f = new Font(Font.DIALOG,Font.BOLD,11);
        g.setFont(f);

        string = parent.board.selected.city.nation.name;
        g.drawString(string,parent.top.P.x + 5, parent.top.P.y + 25);

        f = new Font(Font.DIALOG,Font.ROMAN_BASELINE,10);
        g.setFont(f);

        string = parent.board.selected.city.getProductionDisplayStat();
        g.drawString(string,parent.top.P.x + 5, parent.top.P.y + 38);
        
        string = "Turns: ";
        string += parent.board.selected.city.productionTurnEstimate();
        string += " H: " + parent.board.selected.city.hammerTurnEstimate();
        string += " (+" + parent.board.selected.city.getProductionTurnHammers() + ")";
        string += " F: " + parent.board.selected.city.foodTurnEstimate();
        string += " (+" + parent.board.selected.city.getProductionTurnFood() + ")";
        g.drawString(string,parent.top.P.x + 5, parent.top.P.y + 52);

        string = parent.board.selected.city.getProductionDisplayCost();
        g.drawString(string,parent.top.P.x + 5, parent.top.P.y + 66);
        
        string = "Water: " + (parent.board.selected.city.getProductionWater());
        g.drawString(string,parent.top.P.x + 5, parent.top.P.y + 80);

        //g.drawString(string,parent.top.P.x + 5, parent.top.P.y + 94);
    }
}
