/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Objs.ECrafts;

import java.awt.Color;
import java.awt.Graphics2D;
import refactored_game.Buffer;
import refactored_game.Main;
import refactored_game.Objs.ECrafts.Actions.ActionList;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class Boss extends ECraft {

    public int level;
    public int extra;

    public Boss(Point p, ActionList actions, Main base, int level, int extra) {
        super(p, actions, 2, 100, 100, base, 1000);
        this.level = level;
        this.extra = extra;
        if (level < 1 || level > 3) {
            this.level = 1;
        }
        if (extra < 1) {
            this.extra = 1;
        }
        if (this.level + this.extra > 5) {
            for (int i = 0; i < 5; i++) {
                if (i == 1) {
                    bullets.set(i, bullets.get(i).upgradeMulti(6, this, 5));
                } else {
                    bullets.set(i, bullets.get(i).upgrade(this, 5));
                }
            }
        } else if (this.level + this.extra > 3) {
            for (int i = 0; i < 5; i++) {
                if (i == 1) {
                    bullets.set(i, bullets.get(i).upgradeMulti(5, this, 4));
                } else {
                    bullets.set(i, bullets.get(i).upgrade(this, 4));
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (i == 1) {
                    bullets.set(i, bullets.get(i).upgradeMulti(3, this, 2));
                } else {
                    bullets.set(i, bullets.get(i).upgrade(this, 2));
                }
            }
        }
        this.health *= level * extra;
        this.healthMax *= level * extra;
        this.speed *= level * extra;
    }

    @Override
    public boolean isDead() {
        if (health > 0) {
            return false;
        }
        base.level.bossOn = false;
        base.player.health = base.player.healthMax;
        return true;
    }

    @Override
    public boolean isBoss() {
        return true;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(Buffer.buff[27 + level - 1], (int) p.x - 15, (int) p.y - 15,
                (int) p.x + w + 15, (int) p.y + h + 15,
                0, 0, Buffer.buff[27 + level - 1].getWidth(null), Buffer.buff[27 + level - 1].getHeight(null), null);
        g.setColor(Color.darkGray);
        g.fillRect(50, 40, 400, 10);
        g.setColor(Color.lightGray);
        g.fillRect(50, 40, (int) (((double) health) / ((double) healthMax) * 400), 10);
    }
}
