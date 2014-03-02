/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game.Levels;

import refactored_game.Main;
import refactored_game.Objs.ECrafts.Actions.Action;
import refactored_game.Objs.ECrafts.Actions.ActionList;
import refactored_game.Objs.ECrafts.Actions.tAction;
import refactored_game.Objs.ECrafts.*;
import refactored_game.Objs.Rock;
import refactored_game.Objs.UCraftPED;
import refactored_game.Objs.Walls.*;
import refactored_game.support.Point;

/**
 *
 * @author Sparky
 */
public class LevelManager {

    Main base;

    public LevelManager(Main base) {
        this.base = base;
    }

    public void startLevel() {
        base.player = new UCraftPED(new Point(250, 250), base);

        Point p0 = new Point(400, -50);
        Point p1 = new Point(100, -50);
        Point p2 = new Point(250, -50);
        Point p3 = new Point(100, -50);
        Point p4 = new Point(-50, 150);
        Point p5 = new Point(550, 150);
        for (int k = 1; k <= 3; k++) {
            base.level.enviroment.add(new Enviroment(s2c(60), new Rock(new Point(250, -50), 6, 50, 50, base, 40, 0, 1)));
            for (int i = 0; i < 10; i++) {
                base.level.enviroment.add(new Enviroment(s2c(1.5), new Rock(p0, 3, 30, 30, base, 20, -.2, .3)));
                base.level.enviroment.add(new Enviroment(0, new Rock(p1, 3, 30, 30, base, 20, .2, .3)));
            }
            for (int i = 0; i < 10; i++) {
                base.level.enviroment.add(new Enviroment(s2c(3), new Wall(p3, 4, 300, 5, base, 30, 0, .5)));
            }
        }

        ActionList a0 = new ActionList();
        a0.add(new tAction(0, Action.turn, 1, .5));
        a0.add(new tAction(s2c(1), Action.turn, -1, .5));
        a0.add(new tAction(s2c(1), Action.loop));
        ActionList a1 = new ActionList();
        a1.add(new tAction(0, Action.turn, -1, .5));
        a1.add(new tAction(s2c(1), Action.turn, 1, .5));
        a1.add(new tAction(s2c(1), Action.loop));

        ActionList a2 = new ActionList();
        a2.add(new tAction(0, Action.turn, 0, 1));
        a2.add(new tAction(0, Action.shoot, 1));
        a2.add(new tAction(0, Action.frequency, 17, 0));

        ActionList a3 = new ActionList();
        a3.add(new tAction(0, Action.turn, 1, .2));
        a3.add(new tAction(0, Action.shoot, 1));
        a3.add(new tAction(0, Action.frequency, 17, 0));
        ActionList a4 = new ActionList();
        a4.add(new tAction(0, Action.turn, -1, .2));
        a3.add(new tAction(0, Action.shoot, 1));
        a3.add(new tAction(0, Action.frequency, 17, 0));
        for (int k = 1; k <= 3; k++) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 4; i++) {
                    base.level.spawn.add(new Spawn(s2c(3.5), eLevel(k, new Point(100 + i * 20, -100), a0, base)));
                    base.level.spawn.add(new Spawn(0, eLevel(k, new Point(400 - i * 20, -100), a1, base)));
                }
            }
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 4; i++) {
                    base.level.spawn.add(new Spawn(s2c(1), eLevel(k, new Point(100 + i * 20, -100), a0, base)));
                    base.level.spawn.add(new Spawn(0, eLevel(k, new Point(400 - i * 20, -100), a1, base)));
                    base.level.spawn.add(new Spawn(s2c(0.5), eLevel(k, p2, a2, base)));
                }
            }
            base.level.spawn.add(new Spawn(s2c(8), eLevel(k, p2, a2, base)));
            for (int i = 0; i < 10; i++) {
                base.level.spawn.add(new Spawn(s2c(1.5), eLevel(k, p4, a3, base)));
                base.level.spawn.add(new Spawn(0, eLevel(k, p5, a4, base)));
            }
            ActionList a5 = new ActionList();
            a5.add(new tAction(0, Action.shoot, 1));
            a5.add(new tAction(0, Action.target, 1));
            a5.add(new tAction(0, Action.speed, 12));
            a5.add(new tAction(0, Action.moveTo, 400, 100));
            a5.add(new tAction(0, Action.atPlace, 400, 100));
            a5.add(new tAction(0, Action.moveTo, 0, 100));
            a5.add(new tAction(0, Action.atPlace, 0, 100));
            a5.add(new tAction(0, Action.loop, 2));
            base.level.spawn.add(new Spawn(s2c(17), new Boss(p2, a5, base, k, 1)));
        }
        base.borders = new BorderWall[4];
        base.borders[0] = new LWall(base);
        base.borders[1] = new RWall(base);
        base.borders[2] = new UWall(base);
        base.borders[3] = new DWall(base);
    }

    private ECraft eLevel(int level, Point p, ActionList a, Main base) {
        switch (level) {
            case 1:
                return new BAC(p, a.getCopy(), base);
            case 2:
                return new IAC(p, a.getCopy(), base);
            case 3:
                return new AAC(p, a.getCopy(), base);
            default:
                return new AAC(p, a.getCopy(), base);
        }
    }
    
    public int s2c(double seconds){
        return (int) (seconds*1000/(base.NORMAL_PAUSE_RATE));
    }
}
