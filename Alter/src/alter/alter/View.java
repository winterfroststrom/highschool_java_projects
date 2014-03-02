/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alter.alter;

/**
 *
 * @author Sparky
 */
import java.awt.Color;
import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import javax.swing.JPanel;

public class View extends JPanel{
    Model model;

    public View(Model model) {
        this.model = model;
        this.setBackground(Vars.DefaultColor);
        
    }
    
    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        switch(model.getState()){
            case Vars.StateStart:  paintStart(g, true);
                break;
            case Vars.StateFile: paintFile(g, true);
                break;
        }
    }
    
    public void paintStart(Graphics2D g, boolean main){
        g.setColor(Vars.DefaultFontColor);

        drawStringCentered(g,Vars.DefaultFont.deriveFont(Font.PLAIN, 50),"Alter",
                Vars.DefaultScreenWidth/2,Vars.DefaultScreenHeight/2);
        
        drawStringCentered(g,Vars.DefaultFont,"In the Beginning",
                Vars.DefaultScreenWidth/2,Vars.DefaultScreenHeight/2 + Vars.DefaultFontSize);
        
        drawStringCentered(g,Vars.DefaultFont.deriveFont(Font.PLAIN, 30),"Enter",
                Vars.DefaultScreenWidth/2,Vars.DefaultScreenHeight*7/8);
        
    }
    
    public void paintFile(Graphics2D g, boolean main){
        
        if(main && model.isTransitioning()){
            switch(model.getPrevState()){
                
                case Vars.StateStart: paintStart(g, false);
                    break;
            }
        }
        
    }
    
    public void drawStringCentered(Graphics g, Font f, String string, int xCenter, int yBaseline){
        g.setFont(f);
        FontMetrics fm = getFontMetrics(f);
        g.drawString(string, xCenter - fm.stringWidth(string) /2, yBaseline);
    }
    
}
