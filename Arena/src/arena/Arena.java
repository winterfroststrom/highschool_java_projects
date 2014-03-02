/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arena;

/**
 *
 * @author Scalene
 */

import java.applet.Applet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.geom.Line2D;
//import java.awt.Shape;
//import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Random;

public class Arena extends Applet implements Runnable{
    private final int PAUSE = 50;
    private Rectangle2D bounds;
    private Image buffer;
    private Graphics2D g2;
    private BufferedImage[] buff;
    private int[][] levels;
    private Random gen;
    private HashMap<Rectangle2D,Integer> levelSelect;
    private Rectangle2D pauseButton;
    private Rectangle2D returnReset;

    private Player player;
    private boolean dead;
    private boolean paused;
    private boolean started;
    private int level;
    private ArrayList<Attack> attacks;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Enemy> enemies;
    private ArrayList<Object> walls;
    private ArrayList<Object> effects;

    @Override
    public void init(){
        buffer = createImage(500,500);
        g2 = (Graphics2D) buffer.getGraphics();
        g2.setBackground(Color.pink);
        buff = new BufferedImage[12];
        loadImages();

        bounds = new Rectangle2D.Double(-50,-50,600,600);
        pauseButton = new Rectangle2D.Double(186, 115, 128, 35);
        returnReset = new Rectangle2D.Double(186, 165, 128, 35);

        gen = new Random();
        levelSelect = new HashMap<Rectangle2D,Integer>();
        int k = 1;
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                levelSelect.put(new Rectangle2D.Double(34 + j*166, 34 + i*166, 100, 100), k);
                k++;
            }
        }

        levels = new int[9][5];
        
        for(int i = 0;i<4;i++){
            levels[i][0] = 1;
            levels[i][1] = 3;
            levels[i][2] = 1;
            levels[i][3] = i;
            levels[i][4] = 3;
        }
        levels[4][0] = 4;
        levels[4][1] = 0;
        levels[4][2] = 4;
        levels[4][3] = 0;
        levels[4][4] = 2;
        for(int i = 5;i<9;i++){
            levels[i][0] = 1;
            levels[i][2] = 1;
            levels[i][3] = i-5;
            levels[i][4] = 1;
        }

        this.addKeyListener(new Keys());
        Mouse m = new Mouse();
        this.addMouseListener(m);
        this.addMouseMotionListener(m);

        reset();
    }
    public void loadImages(){
        try{
            buff[0] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/you.png"));
            buff[1] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/yourange.png"));
            buff[2] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/archer.png"));
            buff[3] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/knight.png"));
            buff[4] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/mage.png"));
            buff[5] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/rogue.png"));
            buff[6] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/wall.png"));
            buff[7] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/arrow.png"));
            buff[8] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/fire.png"));
            buff[9] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/slah.png"));
            buff[10] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/rogueslash.png"));
            buff[11] = ImageIO.read(new URL("http://desertshadow.net/sparky/arenagame/bin/ground.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void reset(){
        paused = false;
        dead = false;
        started = false;
        attacks = new ArrayList<Attack>();
        obstacles = new ArrayList<Obstacle>();
        walls = new ArrayList<Object>();
        enemies = new ArrayList<Enemy>();
        effects = new ArrayList<Object>();
        level = -1;

        addObjects();
    }
    public void addObjects(){
        player = new Player(new Point(250,250),new Dimension(20,20));

        walls.add(new XWall(new Point(2,250),new Dimension(3,500)));
        walls.add(new XWall(new Point(498,250),new Dimension(3,500)));
        walls.add(new YWall(new Point(250,2),new Dimension(500,3)));
        walls.add(new YWall(new Point(250,498),new Dimension(500,3)));

        obstacles.add(new XWall(new Point(495,100),new Dimension(10,200)));
        obstacles.add(new XWall(new Point(495,400),new Dimension(10,200)));
        obstacles.add(new XWall(new Point(5,100),new Dimension(10,200)));
        obstacles.add(new XWall(new Point(5,400),new Dimension(10,200)));

        obstacles.add(new YWall(new Point(100,5),new Dimension(200,10)));
        obstacles.add(new YWall(new Point(400,5),new Dimension(200,10)));
        obstacles.add(new YWall(new Point(100,495),new Dimension(200,10)));
        obstacles.add(new YWall(new Point(400,495),new Dimension(200,10)));

        obstacles.add(new XWall(new Point(195,495),new Dimension(10,15)));
        obstacles.add(new XWall(new Point(305,495),new Dimension(10,15)));
        obstacles.add(new XWall(new Point(195,5),new Dimension(10,15)));
        obstacles.add(new XWall(new Point(305,5),new Dimension(10,15)));

        obstacles.add(new YWall(new Point(5,305),new Dimension(15,10)));
        obstacles.add(new YWall(new Point(5,195),new Dimension(15,10)));
        obstacles.add(new YWall(new Point(495,305),new Dimension(15,10)));
        obstacles.add(new YWall(new Point(495,195),new Dimension(15,10)));
    }

    @Override
    public void start(){
        Thread th = new Thread(this);
        th.start();
    }
    @Override
    public void run(){
        while(true){
            if(started){
                if(!dead){
                    if(!paused){
                        next();
                    }
                }
            }
            repaint();
            try{
                Thread.sleep(PAUSE);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void next(){
        loadCycle();
        think();
        moveObjects();
        attack();
        collide();
        removeObjects();
    }
    public void loadCycle(){
        if(enemies.size() < 4){
            Point temp;
            Point temp2;
            Point temp3;
            Point temp4;
            switch(gen.nextInt(4)){
                case 0: temp = new Point(-10,250); temp2 = new Point(250,510); temp3 = new Point(510,250); temp4 = new Point(250,-10);
                    break;
                case 1: temp = new Point(250,510); temp2 = new Point(510,250); temp3 = new Point(250,-10); temp4 = new Point(-10,250);
                    break;
                case 2: temp = new Point(510,250); temp2 = new Point(250,-10); temp3 = new Point(-10,250); temp4 = new Point(250,510);
                    break;
               default: temp = new Point(250,-10); temp2 = new Point(-10,250); temp3 = new Point(250,510); temp4 = new Point(510,250);
                    break;
            }
            switch(gen.nextInt(levels[level-1][0]) + levels[level-1][1]){
                case 0: addEnemy(temp);
                    break;
                case 1: addEnemy(temp); addEnemy(temp2);
                    break;
                case 2: addEnemy(temp); addEnemy(temp2); addEnemy(temp3);
                    break;
                default: addEnemy(temp); addEnemy(temp2); addEnemy(temp3); addEnemy(temp4);
                    break;
            }
        }
    }
    public void addEnemy(Point p){
        int i = gen.nextInt(levels[level-1][4]);
        switch(gen.nextInt(levels[level-1][2]) + levels[level-1][3]){
            case 0: 
                if(i>0) enemies.add(new Archer(p,new Dimension(30,30),5,new HP(4),new Counter(15)));
                else enemies.add(new SkilledArcher(p,new Dimension(30,30),5,new HP(7),new Counter(15)));
                break;
            case 1: 
                if(i>0) enemies.add(new Knight(p,new Dimension(35,35),4,new HP(20),new Counter(12)));
                else enemies.add(new SkilledKnight(p,new Dimension(35,35),4,new HP(25),new Counter(12)));
                break;
            case 2:
                if(i>0) enemies.add(new Mage(p,new Dimension(25,25),5,new HP(3),new Counter(15)));
                else enemies.add(new SkilledMage(p,new Dimension(25,25),5,new HP(8),new Counter(15)));
                break;
            case 3: 
                if(i>0) enemies.add(new Rogue(p,new Dimension(23,23),8,new HP(10),new Counter(8)));
                else enemies.add(new SkilledRogue(p,new Dimension(23,23),9,new HP(8),new Counter(8)));
                break;
        }
    }

    public void think(){
        for(Enemy e:enemies){
            e.think();
        }
    }
    public void moveObjects(){
        if(player.isMoving())
            player.move();
        for(Obstacle o:obstacles){
            if(o.isMoving())
                o.move();
        }
        for(Enemy e:enemies){
            e.setTarget();
            if(e.isMoving())
                e.move();
        }
        for(Attack s:attacks){
            if(s.isMoving())
                s.move();
        }
    }
    public void attack(){
        if(player.isAttacking())
            player.attack();
        for(Enemy e:enemies){
            if(e.isAttacking())
                e.attack();
        }
    }
    public void collide(){
        for(Obstacle o:obstacles){
            if(o.affectAttacker()){
                if(player.collided(o))
                    o.moveObject(player);
                for(Enemy e:enemies){
                    if(e.collided(o))
                        o.moveObject(e);
                }
            }
            if(o.affectAttack()){
                for(Attack a:attacks){
                    if(a.collided(o)){
                        o.moveObject(a);
                        a.applyTargetEffect(o);
                    }
                }
            }
        }
        for(Enemy e:enemies){
            if(player.collided(e)){
                e.moveObject(player);
            }
        }
        for(Attack a:attacks){
            if(player.collided(a)){
                player.moveObject(a);
                a.moveObject(player);
                a.applyTargetEffect(player);
            }
            if(a.affectEnemy()){
                for(Enemy e:enemies){
                    if(e.collided(a)){
                        e.moveObject(a);
                        a.moveObject(e);
                        a.applyTargetEffect(e);
                    }
                }
            }
        }
        for(Object w:walls){
            if(player.collided(w)){
                w.moveObject(player);
            }
        }
    }
    public void removeObjects(){
        for(Iterator<Obstacle> it = obstacles.iterator();it.hasNext();){
            Obstacle o = it.next();
            if(!o.inBounds(bounds)||o.isDead()){
                it.remove();
            }
        }
        for(Iterator<Attack> it = attacks.iterator();it.hasNext();){
            Attack a = it.next();
            if(!a.inBounds(bounds)||a.isDead()){
                it.remove();
            }
        }
        for(Iterator<Enemy> it = enemies.iterator();it.hasNext();){
            Enemy e = it.next();
            if(!e.inBounds(bounds)||e.isDead()){
                it.remove();
                player.kills++;
            }
        }
        for(Iterator<Object> it = effects.iterator();it.hasNext();){
            Object o= it.next();
            o.health.change(1);
            if(!o.inBounds(bounds)||o.isDead()){
                it.remove();
            }
        }
        if(player.isDead()){
            dead = true;
        }
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }
    @Override
    public void paint(Graphics g){
        g2.clearRect(0, 0, buffer.getWidth(null), buffer.getWidth(null));
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(buff[11], 0, 0, 500, 500, 0, 0, buff[11].getWidth(), buff[11].getHeight(), null);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(started){
            g2.setStroke(new BasicStroke(1));
            drawImages(g2);
            g2.setTransform(new AffineTransform());
            //drawObjects(g2);
            drawScreen(g2);
            if(paused||dead){
                drawMenu(g2);
            }
        } else {
            drawStartScreen(g2);
        }
        g.drawImage(buffer, 0, 0, null);
    }
    public void drawStartScreen(Graphics2D g){
        g.setStroke(new BasicStroke(10));
        g.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,50));
        for(Rectangle2D r:levelSelect.keySet()){
            g.setColor(new Color(150,0,0));
            g.draw(r);
            g.setColor(new Color(200,100,100));
            g.fill(r);
        }
        int k = 1;
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                g.setColor(Color.red);
                g.drawString(k + "", 72 + j*166, 105 + i*166);
                k++;
            }
        }
        g.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,10));
        g.drawString("Game by: Sparky", 400, 482);
        g.drawString("Art by: Shadowfiles", 410, 491);
    }
    public void drawImages(Graphics2D g){
        for(Obstacle o:obstacles){
            o.draw(g);
        }
        for(Attack a:attacks){
            a.draw(g);
        }
        for(Enemy e:enemies){
            e.draw(g);
            e.drawHealth(g);
        }
        for(Object w:walls){
            w.draw(g);
        }
        for(Object o:effects){
            if(o.health.current %2 ==0)
                o.draw(g);
        }
        player.draw(g);
    }
    public void drawObjects(Graphics2D g){
        g.setColor(new Color(150,0,0));
        for(Obstacle o:obstacles){
            g.draw(o.construct());
        }
        for(Attack a:attacks){
            g.draw(a.construct());
        }
        for(Enemy e:enemies){
            g.draw(e.construct());
            e.drawHealth(g);
        }
        for(Object w:walls){
            g.draw(w.construct());
        }
        for(Object o:effects){
            g.draw(o.construct());
        }
        g.draw(player.construct());
    }
    public void drawScreen(Graphics2D g){
        g.setColor(new Color(150,0,0));
        g.fillRect(30, 467, 40, 6);
        g.setColor(Color.red);
        g.fillRect(30, 467, (40*player.health.current)/player.health.max, 6);
        g.setColor(new Color(150,0,0));
        g.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        g.drawString("Kills: " + player.kills, 80, 477);
    }
    public void drawMenu(Graphics2D g){
        if(!dead){
            g.setColor(new Color(150,0,0));
            g.setFont(new Font("Dialog",Font.BOLD,30));
            g.drawString("Paused", (int)pauseButton.getMinX() + 12, (int)pauseButton.getMinY() - 67);
        } else {
            g.setColor(new Color(150,0,0));
            g.setFont(new Font("Dialog",Font.BOLD,30));
            g.drawString("You died!", (int)pauseButton.getMinX() - 1, (int)pauseButton.getMinY() - 67);
        }
        g.setFont(new Font("Dialog",Font.BOLD,20));
        g.drawString(player.kills + " kills",(int)pauseButton.getMinX() + 39, (int)pauseButton.getMinY() - 25);
        g.setStroke(new BasicStroke(5));
        g.draw(pauseButton);
        g.draw(returnReset);
        g.setColor(new Color(200,0,0));
        g.fill(pauseButton);
        g.fill(returnReset);
        g.setColor(Color.red);
        g.drawString("Select Level", (int)pauseButton.getMinX() + 8, (int)pauseButton.getMinY() + 28);
        if(dead){
            g.drawString("Reset", (int)returnReset.getMinX() + 40, (int)returnReset.getMinY() + 28);
        } else {
            g.drawString("Return", (int)returnReset.getMinX() + 29, (int)returnReset.getMinY() + 28);
        }
    }

    private class Mouse extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            if(started&&!dead){
                player.target = e.getPoint();
                player.attacking = true;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(started&&!dead)
                player.target = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(started)
                if(paused||dead){
                    if(pauseButton.contains(e.getPoint())){
                        reset();
                    }
                    if(returnReset.contains(e.getPoint())){
                        if(dead){
                            int temp = level;
                            reset();
                            level = temp;
                            started = true;
                        } else {
                            player.attacking = false;
                            paused = false;
                        }
                    }
                } else {
                    player.attacking = false;
                }
            else{
                for(Rectangle2D r:levelSelect.keySet()){
                    if(r.contains(e.getPoint())){
                        level = levelSelect.get(r);
                        started = true;
                    }
                }
            }
        }
    }

    private class Keys extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int action = e.getKeyCode();
            switch(action){
                case KeyEvent.VK_A:
                    if(player.movingD[2])player.movingD[2]=false;
                    player.movingD[0] = true;
                    player.setTheta();
                    break;
                case KeyEvent.VK_W:
                    if(player.movingD[1])player.movingD[1]=false;
                    player.movingD[3] = true;
                    player.setTheta();
                    break;
                case KeyEvent.VK_S:
                    if(player.movingD[3])player.movingD[3]=false;
                    player.movingD[1] = true;
                    player.setTheta();
                    break;
                case KeyEvent.VK_D:
                    if(player.movingD[0])player.movingD[0]=false;
                    player.movingD[2] = true;
                    player.setTheta();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int action = e.getKeyCode();
            switch(action){
                case KeyEvent.VK_A: player.movingD[0] = false;player.setTheta();
                    break;
                case KeyEvent.VK_W: player.movingD[3] = false;player.setTheta();
                    break;
                case KeyEvent.VK_S: player.movingD[1] = false;player.setTheta();
                    break;
                case KeyEvent.VK_D: player.movingD[2] = false;player.setTheta();
                    break;
                case KeyEvent.VK_R: paused = !paused;
                    break;
                case KeyEvent.VK_SPACE: player.attackStyle = (player.attackStyle == 0)?1:0;
                    break;
            }
        }
    }

    private class Player extends Attacker{
        public boolean[] movingD;
        public int attackStyle;
        Projectile proj;
        Melee melee;
        public int kills;

        public Player(Point p, Dimension d){
            super(p,d,10,-1,10,new HP(20),new Counter(0));
            int[] counter = new int[3];
            counter[0] = 9;//melee
            counter[1] = 12;//range
            counter[2] = 13;
            this.counters = new Counter(counter);
            movingD = new boolean[4];
            target = new Point(0,0);
            proj = new Arrow(new Dimension(10,10),13,4,this);
            melee = new Melee(new Dimension(50,50),12,this);
            for(boolean b:movingD)
                b = false;
        }

        @Override
        public boolean isMoving(){
            if(counters.next(2)){
                health.change(-1);
                counters.reset(2);
            }
            if(movingD[0]&&movingD[1]&&movingD[2]&&movingD[3])
                return false;
            for(boolean b:movingD)
                if(b)
                    return true;
            return false;
        }

        public void setTheta(){
            if(movingD[0]){
                if(movingD[1]){
                    this.setTheta(225);
                } else if(movingD[3]){
                    this.setTheta(135);
                } else{
                    this.setTheta(Object.LEFT);
                }
            } else if(movingD[1]){
                if(movingD[2]){
                    this.setTheta(315);
                } else{
                    this.setTheta(Object.DOWN);
                }
            } else if(movingD[2]){
                if(movingD[3]){
                    this.setTheta(45);
                } else {
                    this.setTheta(Object.RIGHT);
                }
            }  else if(movingD[3]){
                this.setTheta(Object.UP);
            }
        }
        @Override
        public boolean isAttacking(){
            return counters.next(attackStyle) && attacking;
        }

        @Override
        public void attack(){
            counters.reset(attackStyle);
            switch(attackStyle){
                case 0: attacks.add(melee.copy(this));
                        effects.add(melee.effectsCopy(this));
                    break;
                case 1: attacks.add(proj.copy(this));
                    break;
            }
        }

        @Override
        public void draw(Graphics2D g){
            int temp = (attackStyle == 0)?0:1;
            AffineTransform op = new AffineTransform();
            BufferedImage tempim = new BufferedImage(buff[temp].getWidth(),buff[temp].getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D gr = (Graphics2D) tempim.getGraphics();
            op.rotate(-getTheta(),buff[temp].getWidth()/2, buff[temp].getHeight()/2);
            gr.drawImage(buff[temp], op, null);
            gr.dispose();
            g.drawImage(tempim,(int)(getPoint().getX()-OFFSET),(int)(getPoint().getY()-OFFSET),
                        (int)(getPoint().getX()+d.width+OFFSET),(int)(getPoint().getY()+d.height+OFFSET),
                        0, 0, tempim.getWidth(), tempim.getHeight(), null);
        }
    }

    private class SkilledRogue extends Rogue{
        Projectile proj;
        public int attackStyle;

        public SkilledRogue(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,hp,counter);
            int[] count = new int[2];
            count[0] = counter.max[0];//melee
            count[1] = 15;//range
            this.counters = new Counter(count);
            proj = new Dart(new Dimension(5,5),35,1,this);
            proj.ae = false;
            melee.ae = false;
        }

        @Override
        public void think(){
            this.setTheta(target);
            parrying = false;
            double distance = center().distanceSq(target);
            if(distance < 1500){
                attackStyle = 0;
                moving = false;
            } else if(distance < 8000){
                attackStyle = 0;
                moving = true;
            } else if(distance < 20000){
                attackStyle = 0;
                parrying = true;
                obstacles.add(new Parry(new Dimension(50,50),-1,0,this));
                moving = true;
            } else if(distance < 40000){
                attackStyle = 1;
                parrying = true;
                obstacles.add(new Parry(new Dimension(50,50),-1,0,this));
                moving = false;
            } else {
                attackStyle = 1;
                moving = true;
            }
            if(moving)
                attacking = false;
            else
                attacking = true;
        }
        
        @Override
        public boolean isAttacking(){
            return counters.next(attackStyle) && attacking;
        }

        @Override
        public void attack(){
            counters.reset(attackStyle);
            switch(attackStyle){
                case 0: attacks.add(melee.copy(this));
                        effects.add(melee.effectsCopy(this));
                    break;
                case 1: attacks.add(proj.copy(this));
                    break;
            }
        }
    }
    private class Rogue extends Enemy{
        Melee melee;
        public boolean parrying;
        public Rogue(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,5,6,hp,counter);
            melee = new Melee(new Dimension(30,30),7,this);
        }

        public void think(){
            this.setTheta(target);
            double distance = center().distanceSq(target);
            parrying = false;
            if(distance < 1500){
                moving = false;
            } else if(distance > 8000){
                obstacles.add(new Parry(new Dimension(50,50),-1,0,this));
                parrying = true;
                moving = true;
            } else {
                moving = true;
            }
            if(moving)
                attacking = false;
            else
                attacking = true;
        }

        @Override
        public void attack(){
            super.attack();
            attacks.add(melee.copy(this));
            effects.add(melee.effectsCopy(this));
        }

        @Override
        public void draw(Graphics2D g){
            int temp = (parrying)?10:5;
            AffineTransform op = new AffineTransform();
            BufferedImage tempim = new BufferedImage(buff[temp].getWidth(),buff[temp].getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D gr = (Graphics2D) tempim.getGraphics();
            op.rotate(-getTheta(),buff[temp].getWidth()/2, buff[temp].getHeight()/2);
            gr.drawImage(buff[temp], op, null);
            gr.dispose();
            g.drawImage(tempim,(int)(getPoint().getX()-OFFSET),(int)(getPoint().getY()-OFFSET),
                        (int)(getPoint().getX()+d.width+OFFSET),(int)(getPoint().getY()+d.height+OFFSET),
                        0, 0, tempim.getWidth(), tempim.getHeight(), null);
        }
    }

    private class SkilledKnight extends Knight{
        public SkilledKnight(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,hp,counter);
            int[] count = new int[2];
            count[0] = counter.max[0];
            count[1] = 10;
            melee.ae = false;
            this.counters = new Counter(count);
        }

        @Override
        public boolean isMoving(){
            if(counters.next(1)){
                health.change(-1);
                counters.reset(1);
            }
            return moving;
        }
    }
    private class Knight extends Enemy{
        Melee melee;
        public Knight(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,3,6,hp,counter);
            melee = new Melee(new Dimension(35,35),15,this);
        }

        public void think(){
            this.setTheta(target);
            double distance = center().distanceSq(target);
            if(distance > 1500){
                moving = true;
            } else {
                moving  = false;
            }
            if(moving)
                attacking = false;
            else
                attacking = true;
        }

        @Override
        public void attack(){
            super.attack();
            attacks.add(melee.copy(this));
            effects.add(melee.effectsCopy(this));
        }
    }

    private class SkilledArcher extends Archer{
        public SkilledArcher(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,hp,counter);
            proj.ae = false;
        }

        @Override
        public void think(){
            this.setTheta(target);
            double distance = center().distanceSq(target);
            if(distance < 30000){
                this.addToTheta(180);
                moving = true;
            } else if(distance > 50000){
                moving = true;
            } else {
                moving  = false;
            }
            attacking = true;
        }

        @Override
        public void attack(){
            super.attack();
            Projectile l = proj.copy(this);
            Projectile r = proj.copy(this);
            l.addToTheta(30);
            r.addToTheta(-30);
            attacks.add(l);
            attacks.add(r);
        }
    }
    private class Archer extends Enemy{
        Projectile proj;

        public Archer(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,2,6,hp,counter);
            proj = new Arrow(new Dimension(10,10),13,4,this);
        }

        @Override
        public void think(){
            this.setTheta(target);
            double distance = center().distanceSq(target);
            if(distance < 14000){
                this.addToTheta(180);
                moving = true;
            } else if(distance > 40000){
                moving = true;
            } else {
                moving  = false;
            }
            if(moving)
                attacking = false;
            else
                attacking = true;
        }

        @Override
        public void attack(){
            super.attack();
            attacks.add(proj.copy(this));
        }
    }

    private class SkilledMage extends Mage{
        public int potions;
        public SkilledMage(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,hp,counter);

            potions = 5;
        }

        @Override
        public void think(){
            if(health.current <= health.max/2 && potions > 0){
                health.change(-10);
                potions--;
            }

            super.think();
        }

        @Override
        public void attack(){
            counters.reset();
            int size = (int)(Math.sqrt(incre.value())*6);
            Projectile temp = new Spell(new Dimension(size,size),
                                        20-incre.value(),(int)incre.value(),this);
            temp.ae = false;
            attacks.add(temp);
            incre.reset();
        }
    }
    private class Mage extends Enemy{
        Increment incre;

        public Mage(Point p, Dimension d,double speed,HP hp,Counter counter){
            super(p,d,speed,4,6,hp,counter);
            incre  = new Increment(.2,100);
        }

        @Override
        public void think(){
            this.setTheta(target);
            double distance = center().distanceSq(target);
            if(distance < 5000){
                if(incre.value() > 4){
                    attacking = true;
                    moving = false;
                    return;
                }
            }
            if(distance < 22500){
                this.addToTheta(180);
                moving = true;
            } else if(distance > 62500){
                moving = true;
            } else {
                moving  = false;
            }
            if(incre.value() > 10)
                attacking = true;
            else
                attacking = false;
            if(enemies.size() < 2){
                if(incre.value() > 2){
                    attacking = true;
                    moving = false;
                }
            }
        }

        @Override
        public boolean isMoving(){
            if(moving)
                return true;
            incre.next();
            return false;
        }

        @Override
        public void attack(){
            super.attack();
            int size = (int)(Math.sqrt(incre.value())*6);
            attacks.add(new Spell(new Dimension(size,size),
                                        20-incre.value(),(int)incre.value(),this));
            incre.reset();
        }
    }
    
    private abstract class Enemy extends Attacker{
        public Enemy(Point p, Dimension d,double speed,int iid,int offset,HP hp,Counter counter){
            super(p,d,speed,iid,offset,hp,counter);
            setTarget();
            
        }

        public abstract void think();

        public void setTarget(){
            target = player.center();
        }
    }

    private class Attacker extends Object{
        public boolean attacking;
        public Point target;
        public Counter counters;

        public Attacker(Point p, Dimension d,double speed,int iid,int offset,HP hp,Counter counter){
            super(p,d,speed,0,iid,offset,hp);
            this.counters = counter;
            attacking = false;
        }

        public boolean isAttacking(){
            return counters.next() && attacking;
        }

        public void attack(){
            counters.reset();
        }
    }

    private class Dart extends Projectile{
        public Dart(Dimension d,double speed,int damage,Attacker a){
            super(d,speed,7,5,damage,a);
        }
    }
    private class Arrow extends Projectile{
        public Arrow(Dimension d,double speed,int damage,Attacker a){
            super(d,speed,7,12,damage,a);
        }
    }
    private class Spell extends Projectile{
        public Spell(Dimension d,double speed,int damage,Attacker a){
            super(d,speed,8,10,damage,a);
        }
    }
    private class Projectile extends Attack{
        public Projectile(Dimension d,double speed,int iid,int offset,int damage,Attacker a){
            super(d,speed,iid,offset,new HP(1),Effect.none,damage,a);
            move();
        }

        public Projectile copy(Attacker a){
            Projectile temp = new Projectile(new Dimension(d.width,d.height),speed,IID,OFFSET,damage,a);
            temp.ae = this.ae;
            return temp;
        }
    }

    private class Melee extends Attack{
        public Melee(Dimension d,int damage,Attacker a){
            super(d,(d.height+d.width)/4,9,0,new HP(0),Effect.none,damage,a);
            move();
            moving = false;
        }
        public Melee copy(Attacker a){
            Melee temp = new Melee(new Dimension(d.width,d.height),damage,a);
            temp.ae = this.ae;
            return temp;
        }

        public Melee effectsCopy(Attacker a){
            Melee temp = new Melee(new Dimension(d.width,d.height),damage,a);
            temp.health = new HP(4);
            return temp;
        }
    }

    private enum Effect{
        none,gravity,rfield
    }

    private class Attack extends Object{
        public Effect effect;
        public int damage;
        public Attacker owner;
        public boolean ae;

        public Attack(Dimension d,double speed,int iid,int offset,HP hp,Effect effect,int damage,Attacker a){
            super(a.center(),d,speed,0,iid,offset,hp);
            owner = a;
            this.setTheta(owner.target);
            ae = true;
            this.effect = effect;
            this.damage = damage;
        }

        public void applyTargetEffect(Object o){
            if(o.equals(owner))
                return;
            health.change(health.max);
            o.health.change(damage);
        }

        public void applyEffect(){
            health.change(health.max);
        }

        public boolean affectEnemy(){
            return ae;
        }
    }

    private class Parry extends Obstacle{
        public Parry(Dimension d,int iid,int offset,Attacker a){
            super(a.center(),d,0,iid,offset,new HP(0));
            this.setTheta(a.target);
            move();
        }
        @Override
        public boolean affectAttacker(){
            return false;
        }
        @Override
        public boolean affectAttack(){
            return true;
        }
    }

    private class RField extends Obstacle{
        public double rFactor;
        public int dir;
        public RField(Point p,Dimension d,int iid,int offset,HP hp,double rFactor,int dir){
            super(p,d,0,offset,iid,hp);
            this.rFactor = rFactor;
            this.dir = dir;
        }
        
        @Override
        public void moveObject(Object o){
            double thetab = Math.atan2(center().y-o.center().y,o.center().x - center().x);
            o.p.translate(dir*(int)(deltaX(o.speed,thetab)*rFactor),
                    dir*(int)(deltaY(o.speed,thetab)*rFactor));
        }
        /*@Override
        public Ellipse2D.Double construct(){
            return new Ellipse2D.Double(p.getX(), p.getY(), d.getWidth(),d.getHeight());
        }*/
    }

    private class XWall extends Obstacle{
        public XWall(Point p,Dimension d){
            super(p,d,0,6,0,new HP(0));
        }

        @Override
        public boolean isDead(){
            return false;
        }

        @Override
        public void moveObject(Object o){
            o.p.move(o.back.x, o.p.y);
        }
        @Override
        public boolean affectAttack(){
            return false;
        }
    }

    private class YWall extends Obstacle{
        public YWall(Point p,Dimension d){
            super(p,d,0,6,0,new HP(0));
            this.setTheta(90);
        }

        @Override
        public boolean isDead(){
            return false;
        }

        @Override
        public void moveObject(Object o){
            o.p.move(o.p.x, o.back.y);
        }

        @Override
        public boolean affectAttack(){
            return false;
        }
    }

    private class Obstacle extends Object{
        public int effect;
        public int counter;

        public Obstacle(Point p,Dimension d,double speed,int iid,int offset,HP hp){
            super(p,d,speed,0,iid,offset,hp);
        }

        public boolean affectAttacker(){
            return true;
        }

        public boolean affectAttack(){
            return false;
        }
    }

    private class HP{
        public int max;
        public int current;

        public HP(int hp){
            current = hp;
            max = hp;
        }

        public void change(int damage){
            current -= damage;
            if(current < 0)
                current = 0;
            else if(current > max)
                current = max;
        }

        public boolean isDead(){
            return current < 1;
        }
    }

    private class Counter{
        public int[] current;
        public int[] max;

        public Counter(int[] maxes){
            current = new int[maxes.length];
            max = new int[maxes.length];
            for(int i = 0;i<maxes.length;i++){
                current[i] = maxes[i];
                max[i] = maxes[i];
            }
        }

        public Counter(int cycles){
            current = new int[1];
            max = new int[1];
            current[0] = cycles;
            max[0] = cycles;
        }

        public boolean next(){
            return next(0);
        }

        public void reset(){
            reset(0);
        }

        public boolean next(int counter){
            current[counter]--;
            if(current[counter] < 0)
                current[counter] = 0;
            return current[counter] < 1;
        }

        public void reset(int counter){
            current[counter] = max[counter];
        }
    }

    private class Increment{
        public double delta;
        public int max;
        public int current;

        public Increment(double delta,int max){
            this.delta = delta;
            this.max = max;
        }

        public double next(){
            if(current<max)
                current++;
            return delta*current;
        }

        public double value(){
            return delta*current;
        }

        public void reset(){
            current = 0;
        }
    }

    private class Object{
        public final int IID;
        public final int OFFSET;
        public final static double UP = 90;
        public final static double RIGHT = 0;
        public final static double LEFT = 180;
        public final static double DOWN = 270;
        private Point p;
        public Point back;
        public Dimension d;
        public HP health;
        public double speed;
        private double theta;
        public boolean moving;
        public boolean visible;

        public Object(Point p,Dimension d,double speed,double theta,int iid,int offset,HP hp){
            this.d = d;
            this.p = p;
            this.back = (new Point(p.x,p.y));
            this.p.translate(-d.width/2, -d.height/2);
            this.theta = Math.toRadians(theta);
            this.speed = speed;
            this.IID = iid;
            this.health = hp;
            visible = true;
            moving = true;
            this.OFFSET = offset;
        }

        public void setTheta(double theta){
            this.theta = Math.toRadians(theta);
        }

        public void setTheta(Point p){
            this.theta = Math.atan2(center().y-p.y,p.x - center().x);
        }

        public void addToTheta(double delta){
            this.theta += Math.toRadians(delta);
        }

        public boolean isMoving(){
            return moving;
        }

        public void draw(Graphics2D g){
            AffineTransform op = new AffineTransform();
            BufferedImage temp = new BufferedImage(buff[IID].getWidth(),buff[IID].getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D gr = (Graphics2D) temp.getGraphics();
            op.rotate(-theta,buff[IID].getWidth()/2, buff[IID].getHeight()/2);
            gr.drawImage(buff[IID], op, null);
            gr.dispose();
            g.drawImage(temp,(int)(p.getX()-OFFSET),(int)(p.getY()-OFFSET),(int)(p.getX()+d.width+OFFSET),(int)(p.getY()+d.height+OFFSET), 0, 0, temp.getWidth(), temp.getHeight(), null);
        }

        public void move(){
            back.x = p.x;
            back.y = p.y;
            p.translate((int)deltaX(), (int)deltaY());
        }

        public void moveObject(Object o){
            double thetab = Math.atan2(center().y-o.center().y,o.center().x - center().x);
            o.p.translate((int)deltaX(o.speed,thetab), (int)deltaY(o.speed,thetab));
        }

        public double deltaX(){
            return speed*Math.cos(theta);
        }

        public double deltaY(){
            return -speed*Math.sin(theta);
        }

        public double deltaX(double speed,double thetab){
            return speed*Math.cos(thetab);
        }

        public double deltaY(double speed,double thetab){
            return -speed*Math.sin(thetab);
        }

        public Point center(){
            return new Point(p.x+d.width/2,p.y+d.height/2);
        }

        public boolean inBounds(Rectangle2D r){
            return r.contains(p);
        }

        public boolean isDead(){
            return health.isDead();
        }

        public boolean collided(Object o){
            if(construct().intersects(o.construct()))
                return true;
            if(Line2D.linesIntersect(
                    p.x,p.y,p.x - deltaX(), p.y - deltaY(),
                    o.p.x,o.p.y,o.p.x - o.deltaX(), o.p.y - deltaY()))
                return true;
            return false;
        }

        public Rectangle2D.Double construct(){
            return new Rectangle2D.Double(p.getX(), p.getY(), d.getWidth(),d.getHeight());
        }

        public Point getPoint(){
            return new Point(p.x,p.y);
        }

        public double getTheta(){
            return theta;
        }

        public void drawHealth(Graphics2D g){
            g.setColor(new Color(150,0,0));
            g.fillRect(p.x, p.y + d.height + 6, 20, 6);
            g.setColor(Color.red);
            g.fillRect(p.x, p.y + d.height + 6, (20*health.current)/health.max, 6);
        }
    }
}