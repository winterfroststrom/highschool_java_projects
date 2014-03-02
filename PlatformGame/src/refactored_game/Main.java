/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game;

/**
 *
 * @author Sparky
 */
import refactored_game.Objs.Explosion;
import refactored_game.Objs.Token;
import refactored_game.graphics.PaintManager;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import refactored_game.Levels.Level;
import refactored_game.Levels.LevelManager;
import refactored_game.Objs.Attacks.RecShot;
import refactored_game.Objs.Attacks.Shot;
import refactored_game.Objs.Craft;
import refactored_game.Objs.ECrafts.ECraft;
import refactored_game.Objs.SCraft;
import refactored_game.Objs.UCraftPED;
import refactored_game.Objs.Walls.*;
import refactored_game.interfaces.Tangible;

public class Main extends Applet implements Runnable {

    public UCraftPED player;
    public BorderWall[] borders;
    public ArrayList<ECraft> enemies;
    public ArrayList<Craft> objects;
    public ArrayList<RecShot> eShots;
    public ArrayList<RecShot> pShots;
    public ArrayList<Token> tokens;
    public ArrayList<Explosion> explosions;
    public Level level;
    public boolean notOver;
    public boolean notPaused;
    public int score;
    public int pauseRate;
    public final int NORMAL_PAUSE_RATE = 50;
    public final int in = -1;
    public final int stay = 0;
    public final int out = 1;
    public Random gen = new Random();
    public Image buffer;

    @Override
    public void init() {
        firstInit();
    }

    @Override
    public void start() {
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        while (true) {
            if (notPaused) {
                if (notOver) {
                    next();
                }
            }
            try {
                Thread.sleep(pauseRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics gr) {
        gr.drawImage((new PaintManager()).paint(this, buffer), 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void initialize() {
        notPaused = true;
        enemies = new ArrayList<ECraft>();
        objects = new ArrayList<Craft>();
        eShots = new ArrayList<RecShot>();
        pShots = new ArrayList<RecShot>();
        tokens = new ArrayList<Token>();
        explosions = new ArrayList<Explosion>();
        level = new Level(this);
        notOver = true;
        score = 0;
        pauseRate = 50;
    }

    public void firstInit() {
        initialize();
        Buffer.init();
        (new LevelManager(this)).startLevel();
        addKeyListener(new Mover(this));
        buffer = createImage(getSize().width, getSize().height);
        
//        setSize(500,500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("Shooter Game");
//        setResizable(false);
        //setLocationRelativeTo(null);
//        setVisible(true);
    }

    public void next() {
        doMovement();
        doShootings();
        checkDie();
        level.next();
        if (level.isEnd() && enemies.isEmpty()) {
            this.notOver = false;
        }
        repaint();
    }

    public void doShootings() {
        if (player.shooting) {
            if (player.canShoot()) {
                player.shoot();
            }
        }
        for (SCraft e : enemies) {
            if (e.canShoot()) {
                e.shoot();
            }
        }
    }

    public void checkDie() {
        (new DeathManager()).checkDie(this);
    }

    public void clearAll() {
        initialize();
        (new LevelManager(this)).startLevel();
    }

    private void doMovement() {
        (new MovementManager()).doMovement(this);
    }
}
