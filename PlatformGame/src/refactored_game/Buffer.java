/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Sparky
 */
public class Buffer {
    public static BufferedImage[] buff = new BufferedImage[30];
    
    public static void init(){
        try{
            buff[0] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/you.png"));
            buff[1] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/youleft.png"));
            buff[2] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/youright.png"));
            buff[3] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/wall.png"));
            buff[4] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/enemy.png"));
            buff[5] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/enemy2.png"));
            buff[6] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/enemy3.png"));
            buff[7] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/fire1.png"));
            buff[8] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/fire3.png"));
            buff[9] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/fire4.png"));
            buff[10] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/fire5.png"));
            buff[11] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/border.png"));
            buff[12] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/rock.png"));
            buff[13] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/icon1.png"));
            buff[14] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/icon2.png"));
            buff[15] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/icon3.png"));
            buff[16] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/icon4.png"));
            buff[17] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/icon5.png"));
            buff[18] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/fire2.png"));
            buff[19] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/powerup1.png"));
            buff[20] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/powerup2.png"));
            buff[21] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/powerup3.png"));
            buff[22] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/powerup4.png"));
            buff[23] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/powerup5.png"));
            buff[24] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/explosion.png"));
            buff[25] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/explosion2.png"));
            buff[26] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/explosion3.png"));
            buff[27] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/boss.png"));
            buff[28] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/boss2.png"));
            buff[29] = ImageIO.read(new URL("http://desertshadow.net/sparky/bin/boss3.png"));
        }catch(java.io.IOException e){
            e.printStackTrace();
        }
    }
}
