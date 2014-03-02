/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import refactored_game.Buffer;
import refactored_game.Main;
import refactored_game.Objs.Attacks.RecShot;
import refactored_game.Objs.*;
import refactored_game.Objs.Walls.BorderWall;

/**
 *
 * @author Sparky
 */
public class PaintManager {

    public Image paint(Main base, Image buffer) {
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        clearImage(base, g);
        drawImages(base, g);//drawConstructs(base, g);
        drawIcons(base, g);
        drawHealth(base, g);
        drawScore(base, g);
        if (!base.notOver) {
            end(base, g);
        }
        g.drawImage(base.buffer, 0, 0, base);
        return buffer;
    }

    private void clearImage(Main base, Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, base.getSize().width, base.getSize().height);
    }

    private void drawImages(Main base, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for (BorderWall w : base.borders) {
            draw(w, g);
        }
        for (Craft o : base.objects) {//some error here
            o.draw(g);
        }
        for (Explosion x : base.explosions) {
            draw(x, g);
        }
        for (Token t : base.tokens) {
            draw(t, g);
        }
        for (RecShot s : base.pShots) {
            s.draw(g);
        }
        for (RecShot s : base.eShots) {//some error here
            s.draw(g);
        }
        for (SCraft e : base.enemies) {
            e.draw(g);
        }
        draw(base.player, g);
    }

    private void draw(UCraftPED player, Graphics2D g){
        if (player.invincible > 0 && player.invincible % 3 == 0) {
            return;
        }
        if (player.lr == 0) {
            g.drawImage(Buffer.buff[0], (int) player.p.x - 6, (int) player.p.y - 7,
                    (int) player.p.x + player.w + 7, (int) player.p.y + player.h + 7,
                    0, 0, Buffer.buff[0].getWidth(null), Buffer.buff[0].getHeight(null), null);
        } else if (player.lr < 0) {
            g.drawImage(Buffer.buff[1], (int) player.p.x - 7, (int) player.p.y - 7,
                    (int) player.p.x + player.w + 7, (int) player.p.y + player.h + 7,
                    0, 0, Buffer.buff[1].getWidth(null), Buffer.buff[1].getHeight(null), null);
        } else {
            g.drawImage(Buffer.buff[2], (int) player.p.x - 7, (int) player.p.y - 7,
                    (int) player.p.x + player.w + 7, (int) player.p.y + player.h + 7,
                    0, 0, Buffer.buff[2].getWidth(null), Buffer.buff[2].getHeight(null), null);
        }
    }
    
    private void draw(Token t, Graphics2D g) {
        g.drawImage(Buffer.buff[19 + t.num], (int) t.p.x - 2, (int) t.p.y - 2,
                (int) t.p.x + t.w + 2, (int) t.p.y + t.h + 2,
                0, 0, Buffer.buff[19 + t.num].getWidth(null), Buffer.buff[19 + t.num].getHeight(null), null);
    }
    
    private void draw(BorderWall bw, Graphics2D g){
        g.drawImage(Buffer.buff[11], (int) bw.p.x - 3, (int) bw.p.y - 3,
                (int) bw.p.x + bw.w + 3, (int) bw.p.y + bw.h + 3,
                0, 0, Buffer.buff[11].getWidth(null), Buffer.buff[11].getHeight(null), null);
    }
    
    private void draw(Explosion ex, Graphics2D g){
        g.drawImage(Buffer.buff[24 + ex.counter / 4], (int) ex.p.x - 6, (int) ex.p.y - 6,
                (int) ex.p.x + ex.w + 6, (int) ex.p.y + ex.h + 6,
                0, 0, 
                Buffer.buff[24 + ex.counter / 4].getWidth(null), 
                Buffer.buff[24 + ex.counter / 4].getHeight(null), null);
    }
    
    private void drawConstructs(Main base, Graphics2D g) {
        g.setColor(Color.white);
        for (BorderWall w : base.borders) {
            g.draw(w.construct());
        }
        for (Craft o : base.objects) {//some error here
            g.draw(o.construct());
        }
        for (RecShot s : base.pShots) {
            g.draw(s.construct());
        }
        for (RecShot s : base.eShots) {//some error here
            g.draw(s.construct());
        }
        for (SCraft e : base.enemies) {
            g.draw(e.construct());
        }
        g.draw(base.player.construct());
    }

    private void drawIcons(Main base, Graphics2D g) {
        for (int j = 0; j < 5; j++) {
            if (base.player.bullets.size() > j) {
                g.drawImage(Buffer.buff[13 + j], 150 + j * 40, 10, null);
            } else {
                for (int i = 0; i < 5; i++) {
                    BufferedImage dest = new BufferedImage(Buffer.buff[17 - i].getWidth(), Buffer.buff[17 - i].getHeight(),
                            BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = dest.createGraphics();
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
                    g2.drawImage(Buffer.buff[17 - i], null, 0, 0);
                    g2.dispose();
                    g.drawImage(dest, 150 + 40 * (4 - i), 10, base);
                }
                break;
            }
        }
    }

    private void drawHealth(Main base, Graphics2D g) {
        g.setColor(Color.darkGray);
        g.fillRect(25, 15, 100, 20);
        g.setColor(Color.lightGray);
        g.fillRect(25, 15, 100 * base.player.health / base.player.healthMax, 20);
        g.setColor(Color.white);
        int hp = (int) ((double) base.player.health / base.player.healthMax * 100);
        if (hp < 0) {
            hp = 0;
        }
        g.drawString(hp + "%", 60, 30);
    }

    private void drawScore(Main base, Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("Score: " + base.score, 370, 30);
    }

    private void end(Main base, Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Dialog", Font.BOLD, 50));
        if (base.player.isDead()) {
            g.drawString("Game Over", 120, 220);
        } else if (base.level.isEnd() && base.enemies.isEmpty()) {
            g.drawString("Level Clear", 120, 220);
        }
    }
}
