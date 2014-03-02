/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applettemplate;

/**
 *
 * @author Scalene
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.AffineTransform;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Display {
    Main top;

    public Display(Main top) {
        this.top = top;
    }


    public void paint(Graphics2D g){
        g.clearRect(0, 0, top.A.width, top.A.height);

    }
}
