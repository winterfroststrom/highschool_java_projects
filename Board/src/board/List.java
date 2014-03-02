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
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.util.Set;
import java.awt.Rectangle;

public class List {
    Game parent;
    private final int width;
    private final int height;

    private final Color drawColor = new Color(255,255,255,170);
    private final Color fontColor = new Color(0,0,0);
    private final int sliderWidth = 11;
    private final int sliderHeight = 10;
    private final int topOffset = 20;
    private final int sideOffset = 5;
    private final int min;
    private final int max;
    private final int listTextSize = 10;

    private boolean onSlider;
    private Rectangle slider;
    private Rectangle listBox;
    private Rectangle selected;
    private Point p;
    private BufferedImage buffer;
    private Graphics2D burger;

    Point corner;
    Unit[] list;
    Tile tile;

    Unit selectedUnit;
    Unit selectedOwnedUnit;

    public List(Game par,Point corner) {
        parent = par;
        this.corner = corner;

        width = parent.top.L.width;
        height = parent.top.L.height;
        min = topOffset;
        max = height - topOffset;

        p = new Point(width - sliderWidth/2 - 2, sliderHeight/2 + topOffset);
        buffer = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        burger = (Graphics2D) buffer.getGraphics();
        
        slider = new Rectangle(width - sliderWidth - sideOffset/2, topOffset,
                                    sliderWidth, sliderHeight);
        listBox = new Rectangle(sideOffset, topOffset,
                                    width - sideOffset*2 - sliderWidth,
                                    height - sliderHeight - topOffset);
        selected = null;
        list = null;
        onSlider = false;
    }

    public void setTile(Tile t){
        tile = t;
        add(t.getUnits());
    }

    public void refresh(){
        add(tile.getUnits());
    }

    private void add(Set<Unit> list){
        selected = null;
        selectedUnit = null;
        selectedOwnedUnit = null;
        this.list = list.toArray(new Unit[list.size()]);
        if(!list.isEmpty()){
            selectedUnit = this.list[0];
            if(selectedUnit.nation.equals(parent.nations.get(0)))
                selectedOwnedUnit = this.list[0];
            selected = new Rectangle(listBox.x, topOffset + 2, listBox.width, listTextSize - 1);
        }
    }

    public void paintPart(Graphics2D g){
        burger.clearRect(0, 0, width, height);
        drawBackground();
        drawTitle();
        drawSlider();
        if(list != null){
            drawList();
        }
        g.drawImage(buffer, corner.x, corner.y, null);
    }

    private void drawBackground(){
        burger.setColor(drawColor);
        burger.setStroke(new BasicStroke(6));
        burger.drawRect(0, 0, width, height);
        burger.fillRect(0, 0, width, height);
        burger.setStroke(new BasicStroke(2));
        burger.draw(listBox);
        if(selected != null){
            burger.setStroke(new BasicStroke(0));
            burger.fill(selected);
        }
    }

    private void drawTitle(){
        int textSize = 15;
        burger.setColor(fontColor);
        burger.setFont(new Font(Font.DIALOG,Font.BOLD,textSize));
        FontMetrics fm = burger.getFontMetrics();
        String s = "Units in Tile";
        int textOffset = fm.stringWidth(s)/2;
        burger.drawString(s,width/2-textOffset,topOffset/2 + textSize/2);
    }

    private void drawSlider(){
        burger.setColor(drawColor);
        burger.setStroke(new BasicStroke(2));
        burger.drawRect(width - sliderWidth, topOffset, sliderWidth/2,
                            height - topOffset - sliderHeight);
        burger.fill(slider);
    }

    private void drawList(){
        burger.setColor(fontColor);
        burger.setFont(new Font(Font.DIALOG,Font.ROMAN_BASELINE,listTextSize));
        int start = zeroPlace();
        for(int j = 0;j <= (max-min)/listTextSize && j+start < list.length; j++){
            burger.drawString(list[start + j].name + " --- H: " + list[start + j].health,
                                    sideOffset + 5, topOffset + 10 + j*burger.getFont().getSize());
        }
    }

    public void pressEvent(Point e){
        adjustEventPoint(e);
        selected = null;
        if(slider.contains(e)){
            onSlider = true;
            p = e;
        } else if(list != null){
            if(listBox.contains(e)){
                int start = zeroPlace();
                int y = (e.y - topOffset)/listTextSize;
                if(list.length > y + start){
                    selected = new Rectangle(listBox.x, y * listTextSize + topOffset + 2,
                                                listBox.width,listTextSize - 1);
                    selectedUnit = list[y + start];
                    if(selectedUnit.nation.equals(parent.nations.get(0)))
                        selectedOwnedUnit = list[0];
                }
            }
        }
    }

    public void releaseEvent(){
        onSlider = false;
    }

    public void dragEvent(Point e){
        if(onSlider){
            adjustEventPoint(e);
            if(isVaildY(e.y - p.y + slider.y)){
                slider.translate(0,e.y - p.y);
            }
            p = e;
        }
    }

    private Point adjustEventPoint(Point e){
        e.translate(-corner.x,-corner.y);
        return e;
    }

    private int zeroPlace(){
        double percent = (1d*(slider.y-min)/(max-min));
        int difference = (max-min)/listTextSize;
         return (list.length - difference > 0)?
                    (int)((list.length - difference) * percent)
                    :
                    0;

    }

    public boolean isVaildY(double y){
        return y > min && y < max;
    }
}
