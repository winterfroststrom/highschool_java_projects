/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alter.alter;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Sparky
 */
public class Vars {
    public static final int SystemXOffset = 6+2;
    public static final int SystemYOffset = 28+2; 
    
    public static final int DefaultScreenWidth = 500;
    public static final int DefaultScreenHeight = 400;
    public static final int DefaultFontSize = 10;
    public static final Color DefaultFontColor = Color.DARK_GRAY;
    public static final Font DefaultFont = new Font(Font.DIALOG,Font.PLAIN,DefaultFontSize);
    public static final Color DefaultColor = Color.white;
    
    public static final int StateNone = -1;
    public static final int StateStart = 0;
    public static final int StateFile = 1;
}
