package platformgame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *<applet code="platformgame/Main.class" width="500" height="500" archive="PlatformGame.jar"></applet>
 * @author Scalene
 */
import java.awt.Color;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.AlphaComposite;
import java.net.URL;


public class Main extends Applet implements Runnable{
    private UCraftPED player;
    private BorderWall[] borders;
    private ArrayList<ECraft> enemies;
    private ArrayList<Craft> objects;
    private ArrayList<RecShot> eShots;
    private ArrayList<RecShot> pShots;
    private ArrayList<Token> tokens;
    private ArrayList<Explosion> explosions;
    private Level level;
    public boolean notOver;
    public boolean notPaused;
    private int score;
    public int pauseRate;
    public final int NORMAL_PAUSE_RATE = 50;
    private final int in = -1;
    private final int stay = 0;
    private final int out = 1;
    private BufferedImage[] buff = new BufferedImage[30];
    private Random gen = new Random();
    private Image buffer;

    @Override
    public void init(){
        initialize();
        loadImages();
        startLevel();
        firstInit();
    }
    @Override
    public void start(){
        Thread th = new Thread (this);
	th.start ();
    }

    @Override
    public void run() {
        while(true){
            if(notPaused){
                if(notOver){
                    next();
                }
            }
            try{
                Thread.sleep(pauseRate);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void paint(Graphics gr){
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        clearImage(g);
        drawImages(g);
        //drawConstructs(g);
        drawIcons(g);
        drawHealth(g);
        drawScore(g);
        if(!this.notOver){
            end(g);
        }
        gr.drawImage(buffer, 0, 0, this);
    }
    public void clearImage(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, getSize().width, getSize().height);
    }
    public void drawImages(Graphics2D g){
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for(BorderWall w:borders){
            w.draw(g);
        }
        for(Craft o:objects){//some error here
            o.draw(g);
        }
        for(Explosion x:explosions){
            x.draw(g);
        }
        for(Token t:tokens){
            t.draw(g);
        }
        for(RecShot s:pShots){
            s.draw(g);
        }
        for(RecShot s:eShots){//some error here
            s.draw(g);
        }
        for(SCraft e:enemies){
            e.draw(g);
        }
        player.draw(g);
    }
    public void drawConstructs(Graphics2D g){
        g.setColor(Color.white);
        for(BorderWall w:borders){
            g.draw(w.construct());
        }
        for(Craft o:objects){//some error here
            g.draw(o.construct());
        }
        for(RecShot s:pShots){
            g.draw(s.construct());
        }
        for(RecShot s:eShots){//some error here
            g.draw(s.construct());
        }
        for(SCraft e:enemies){
            g.draw(e.construct());
        }
        g.draw(player.construct());
    }
    public void drawIcons(Graphics2D g){
        BufferedImage dest = null;
        for(int j =0;j<5;j++){
            if(player.bullets.size() > j)
                g.drawImage(buff[13+j], 150 + j*40, 10,null);
            else{
                for(int i = 0;i<5;i++){
                    dest = new BufferedImage(buff[17-i].getWidth(), buff[17-i].getHeight(),
                                                BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = dest.createGraphics();
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
                    g2.drawImage(buff[17-i], null, 0, 0);
                    g2.dispose();
                    g.drawImage(dest, 150 + 40*(4-i), 10, this);
                }
                break;
            }
        }
    }
    public void drawHealth(Graphics2D g){
        g.setColor(Color.darkGray);
        g.fillRect(25, 15, 100, 20);
        g.setColor(Color.lightGray);
        g.fillRect(25, 15,100*player.health/player.healthMax, 20);
        g.setColor(Color.white);
        int hp = (int)((double)player.health/player.healthMax * 100);
        if(hp<0)
            hp = 0;
        g.drawString(hp + "%", 60, 30);
    }
    public void drawScore(Graphics2D g){
        g.setColor(Color.white);
        g.drawString("Score: " + score, 370, 30);
    }
    public void end(Graphics2D g){
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
               RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Dialog",Font.BOLD,50));
        if(player.isDead()){
            g.drawString("Game Over", 120, 220);
        } else if(level.isEnd() && enemies.isEmpty()){
            g.drawString("Level Clear", 120, 220);
        }
    }

    @Override
    public void update (Graphics g){
        paint(g);
    }

    public void startLevel(){
        player = new UCraftPED(new Point(250,250),this);

        Point p0 = new Point(400,-50);
        Point p1 = new Point(100,-50);
        Point p2 = new Point(250,-50);
        Point p3 = new Point(100,-50);
        Point p4 = new Point(-50,150);
        Point p5 = new Point(550,150);
        for(int k = 1;k<=3;k++){
        level.enviroment.add(new Enviroment(s2c(60),new Rock(new Point(250,-50),6,50,50,this,40,0,1)));
        for(int i = 0;i<10;i++){
            level.enviroment.add(new Enviroment(s2c(1.5),new Rock(p0,3,30,30,this,20,-.2,.3)));
            level.enviroment.add(new Enviroment(0,new Rock(p1,3,30,30,this,20,.2,.3)));
        }
        for(int i = 0;i<10;i++){
            level.enviroment.add(new Enviroment(s2c(3),new Wall(p3,4,300,5,this,30,0,.5)));
        }}

       ActionList a0 = new ActionList();
            a0.add(new tAction(0,Action.turn,1,.5));
            a0.add(new tAction(s2c(1),Action.turn,-1,.5));
            a0.add(new tAction(s2c(1),Action.loop));
        ActionList a1 = new ActionList();
            a1.add(new tAction(0,Action.turn,-1,.5));
            a1.add(new tAction(s2c(1),Action.turn,1,.5));
            a1.add(new tAction(s2c(1),Action.loop));

        ActionList a2 = new ActionList();
            a2.add(new tAction(0,Action.turn,0,1));
            a2.add(new tAction(0,Action.shoot,1));
            a2.add(new tAction(0,Action.frequency,17,0));

        ActionList a3 = new ActionList();
            a3.add(new tAction(0,Action.turn,1,.2));
            a3.add(new tAction(0,Action.shoot,1));
            a3.add(new tAction(0,Action.frequency,17,0));
        ActionList a4 = new ActionList();
            a4.add(new tAction(0,Action.turn,-1,.2));
            a3.add(new tAction(0,Action.shoot,1));
            a3.add(new tAction(0,Action.frequency,17,0));
        for(int k = 1;k<=3;k++){
        for(int j = 0;j<3;j++){
            for(int i = 0;i<4;i++){
                level.spawn.add(new Spawn(s2c(3.5),eLevel(k,new Point(100+i*20,-100),a0,this)));
                level.spawn.add(new Spawn(0,eLevel(k,new Point(400-i*20,-100),a1,this)));
            }
        }
        for(int j = 0;j<3;j++){
            for(int i = 0;i<4;i++){
                level.spawn.add(new Spawn(s2c(1),eLevel(k,new Point(100+i*20,-100),a0,this)));
                level.spawn.add(new Spawn(0,eLevel(k,new Point(400-i*20,-100),a1,this)));
                level.spawn.add(new Spawn(s2c(0.5),eLevel(k,p2,a2,this)));
            }
        }
        level.spawn.add(new Spawn(s2c(8),eLevel(k,p2,a2,this)));
        for(int i = 0;i<10;i++){
            level.spawn.add(new Spawn(s2c(1.5),eLevel(k,p4,a3,this)));
            level.spawn.add(new Spawn(0,eLevel(k,p5,a4,this)));
        }
        ActionList a5 = new ActionList();
            a5.add(new tAction(0,Action.shoot,1));
            a5.add(new tAction(0,Action.target,1));
            a5.add(new tAction(0,Action.speed,12));
            a5.add(new tAction(0,Action.moveTo,400,100));
            a5.add(new tAction(0,Action.atPlace,400,100));
            a5.add(new tAction(0,Action.moveTo,0,100));
            a5.add(new tAction(0,Action.atPlace,0,100));
            a5.add(new tAction(0,Action.loop,2));
        level.spawn.add(new Spawn(s2c(17),new Boss(p2,a5,this,k,1)));
        }
    }

    private ECraft eLevel(int level,Point p,ActionList a,Main base){
        switch(level){
            case 1: return new BAC(p,a.getCopy(),base);
            case 2: return new IAC(p,a.getCopy(),base);
            case 3: return new AAC(p,a.getCopy(),base);
            default: return new AAC(p,a.getCopy(),base);
        }
    }

    private enum Action{
        loop,stop,turn,shoot,bullet,angle,frequency,speed,moveTo,atPlace,target
    }

    private abstract class ECraft extends SCraft{
        public ActionList actions;
        public boolean targeting = false;
        public int index = 0;
        public ECraft(Point p,ActionList actions,int speed, int w, int h, Main base,int health){
            super(p,speed,w,h,base,health);
            this.actions = actions;
            bullets.add(new Bullet(getBullet().multiShot(2, this, 10, 7, 7, 2),4,0));
            bullets.add(new Bullet(new WaveShot(this,12,4,8,5,3),9,0));
            bullets.add(new Bullet(new PierceShot(this,20,5,15,7),20,0));
            bullets.add(new Bullet(new SplashShot(this,4,13,13,15,25),30,0));
        }
        public ECraft(Point p,ActionList actions,int speed, int w, int h, Main base){
            this(p,actions,speed,w,h,base,5);
        }
        public ECraft(ActionList actions,int speed, int w, int h, Main base){
            this(actions.point,actions,speed,w,h,base,5);
        }
        public ECraft(ActionList actions,int speed, int w, int h, Main base,int health){
            this(actions.point,actions,speed,w,h,base,health);
        }
        @Override
        public void shoot(){
            if(targeting)
                getBullet().setAngle(moveToX(base.player.p.x,base.player.p.y),moveToY(base.player.p.x,base.player.p.y));
            getBullet().add(addShots, this);
        }

        @Override
        public void move(){
            super.move();
            if(index > -1 && index < actions.size()){
                tAction ta = actions.get(index);
                ta.counter--;
                while(index > -1 && index < actions.size() && actions.get(index).counter <= 0){
                    ta = actions.get(index);
                    ta.counter = ta.maxCounter;
                    index++;
                    switch(ta.event){
                        case turn: lr = ta.extra;ud = ta.extra2;
                            break;
                        case loop: index = (int) ta.extra;
                            break;
                        case stop: index = -1;
                            break;
                        case bullet: if(isBulletIndex((int)ta.extra))    bulletIndex= (int)ta.extra;
                            break;
                        case shoot: shooting = ta.extra == 1;
                            break;
                        case angle: getBullet().setAngle(ta.extra, ta.extra2);
                            break;
                        case frequency: getBullet().setFrequency(ta.extra, ta.extra2);
                            break;
                        case speed: this.speed = (int)ta.extra;
                            break;
                        case moveTo:this.moveTo(ta.extra,ta.extra2);
                            break;
                        case atPlace: ta.counter = 1; if(p.distance(ta.extra, ta.extra2) > Math.pow(w, 2))  index--;
                            break;
                        case target: targeting = ta.extra == 1;
                            break;
                    }
                }
            }
        }
        public boolean isBoss(){
            return false;
        }
    }

    public void loadImages(){
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
    public void initialize(){
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
    public void firstInit(){
        addKeyListener(new Mover(this));
        buffer = createImage(getSize().width, getSize().height);
        borders = new BorderWall[4];
        borders[0] = new LWall(this);
        borders[1] = new RWall(this);
        borders[2] = new UWall(this);
        borders[3] = new DWall(this);
//        setSize(500,500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("Shooter Game");
//        setResizable(false);
        //setLocationRelativeTo(null);
//        setVisible(true);
    }

    public void next(){
        setPositions();
        checkCollideP();
        checkCollideS();
        doShootings();
        checkDie();
        level.next();
        if(level.isEnd() && enemies.isEmpty())
            this.notOver = false;
        repaint();
    }
    public void doShootings(){
        if(player.shooting){
            if(player.canShoot())
                player.shoot();
        }
        for(SCraft e: enemies){
            if(e.canShoot())
                e.shoot();
        }
    }
    public void setPositions(){
        player.move();
        if(player.notInBounds())
            player.moveUndo();
        for(Shot s:pShots){
            s.move();
        }
        for(Shot s:eShots){
            s.move();
        }
        for(SCraft e:enemies){
            e.move();
        }
        for(Craft o:objects){
            o.move();
        }
    }
    public void checkCollideP(){
        for(SCraft e: enemies){
            if(player.collide(e)){
                e.hit(player.hit(e));
                player.moveUndo();
            }
        }
        for(Craft o: objects){
            if(player.collide(o)){
                o.hit(player.hit(o));
                player.moveUndo();
            }
        }
        for(BorderWall w:borders){
            if(player.collide(w)){
                if(w.collideDamage > 0)
                    player.hit(w);
                player.moveUndo();
            }
        }
        for(Token t:tokens){
            if(player.collide(t)){
                t.hit(player);
                player.upgrade(t.num);
            }
        }
    }
    public void checkCollideS(){
        for(Shot s: eShots){
            if(player.collide(s)){
                player.hit(s.hit(player));
                player.moveUndo();
           }
        }
        for(Shot s: pShots){
            for(SCraft e: enemies){
                if(e.collide(s)){
                    e.hit(s.hit(e));
                }
            }
            for(Craft o: objects){
                if(o.collide(s)){
                    o.hit(s.hit(o));
                }
            }
        }
    }
    public void checkDie(){
        if(player.health <= 0){
            notOver = false;
        }
        doCheckDie(pShots);
        doCheckDie(eShots);
        doCheckDie(tokens);
        doCheckDie(explosions);
        doCheckDieAndScore(objects);
        doCheckDieAndTokens(enemies);
    }
    private void doCheckDie(ArrayList c){
        Iterator<Tangible> it = c.iterator();
        while(it.hasNext()){
            Tangible temp = it.next();
            if(temp.isDead() || temp.notInBounds())
                it.remove();
        }
    }
    private void doCheckDieAndScore(ArrayList<Craft> c){
        Iterator<Craft> it = c.iterator();
        while(it.hasNext()){
            Craft temp = it.next();
            if(temp.isDead() || temp.notInBounds()){
                score += temp.healthMax/10;
                it.remove();
            }
        }
    }
    private void doCheckDieAndTokens(ArrayList<ECraft> c){
        Iterator<ECraft> it = c.iterator();
        while(it.hasNext()){
            ECraft temp = it.next();
            if(temp.isDead() || temp.notInBounds()){
                explosions.add(new Explosion(temp.p,temp.w,temp.h,this));
                score += temp.healthMax;
                it.remove();
                if(gen.nextInt(20)>18){
                    tokens.add(new Token(temp,randToken()));
                }
            }
        }
    }
    private int randToken(){
        int sum = 1;
        for(int i = 0;i<player.upgrades.length-1;i++)
            if(player.upgrades[i] > 0)
                sum++;
        return gen.nextInt(sum);
    }

    public void clearAll(){
        initialize();
        startLevel();
    }

    private int s2c(double seconds){
        return (int) (seconds*1000/(NORMAL_PAUSE_RATE));
    }
    private class Level{
        public ArrayList<Spawn> spawn;
        public ArrayList<Enviroment> enviroment;
        public int sCounter = 0;
        public int eCounter = 0;
        public Main base;
        public boolean bossOn = false;

        public Level(Main base){
            this.spawn = new ArrayList<Spawn>();
            this.enviroment = new ArrayList<Enviroment>();
            this.base = base;
        }

        public void next(){
            if(bossOn)
                return;
            nextE();
            nextS();
        }

        public void nextE(){
            if(eCounter < enviroment.size()){
                Enviroment e = enviroment.get(eCounter);
                e.counter--;
                while(eCounter < enviroment.size() && enviroment.get(eCounter).counter <= 0){
                    base.objects.add(enviroment.get(eCounter).event);
                    eCounter++;
                }
            }
        }

        public void nextS(){
            if(sCounter < spawn.size()){
                Spawn s = spawn.get(sCounter);
                s.counter--;
                while(sCounter < spawn.size() && spawn.get(sCounter).counter <= 0){
                    if(spawn.get(sCounter).event.isBoss()){
                        bossOn = true;

                    }
                    base.enemies.add(spawn.get(sCounter).event);
                    sCounter++;
                }
            }
        }

        public boolean isEnd(){
            return sCounter >= spawn.size() && eCounter >= enviroment.size();
        }
    }
    private class Spawn extends CounterSet<ECraft>{
        public Spawn(int counter, ECraft e){
            super(counter,e);
        }
    }
    private class Enviroment extends CounterSet<Craft>{
        public Enviroment(int counter, Craft c){
            super(counter,c);
        }
    }
    private class ActionList extends ArrayList<tAction>{
        public Point point;
        public ActionList(Point p){
            super();
            point = p;
        }
        public ActionList(){
            super();
            point = new Point(0,0);
        }
        public ActionList getCopy(){
            ActionList copy = new ActionList(point);
            for(tAction a:this){
                copy.add(a.getCopy());
            }
            return copy;
        }
    }
    private class tAction extends CounterSet<Action>{
        public int maxCounter;
        public double extra;
        public double extra2;

        public tAction(int counter, Action a){
            super(counter,a);
            this.maxCounter = counter;
            this.extra = 0;
        }

        public tAction(int counter, Action a, double extra){
            super(counter,a);
            this.maxCounter = counter;
            this.extra = extra;
        }

        public tAction(int counter, Action a, double extra, double extra2){
            super(counter,a);
            this.maxCounter = counter;
            this.extra = extra;
            this.extra2 = extra2;
        }

        @Override
        public tAction getCopy(){
            return new tAction(maxCounter,event,extra,extra2);
        }
    }
    private class CounterSet<T>{
        public int counter;
        public T event;
        public CounterSet(int counter, T event){
            this.counter = counter;
            this.event = event;
        }
        public CounterSet<T> getCopy(){
            return new CounterSet(counter,event);
        }
    }

    private class BAC extends ECraft{
        public BAC(Point p,ActionList actions,Main base){
            super(p,actions,3,17,17,base,5);
        }
        public BAC(ActionList actions,Main base){
            this(actions.point,actions,base);
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[4], (int)p.x-8, (int)p.y -8,
                        (int)p.x + w+8, (int)p.y + h+8,
                        0, 0,buff[4].getWidth(null),buff[4].getHeight(null),null);
        }
    }
    private class IAC extends ECraft{
        public IAC(Point p,ActionList actions,Main base){
            super(p,actions,3,15,15,base,15);
            for(int i = 0;i<5;i++){
                if(i==1)
                    bullets.set(i,bullets.get(i).upgradeMulti(4,this, 3));
                else
                    bullets.set(i,bullets.get(i).upgrade(this, 3));
           }
        }
        public IAC(ActionList actions,Main base){
            this(actions.point,actions,base);
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[5], (int)p.x-8, (int)p.y -8,
                        (int)p.x + w+8, (int)p.y + h+8,
                        0, 0,buff[5].getWidth(null),buff[5].getHeight(null),null);
        }
    }
    private class AAC extends ECraft{
        public AAC(Point p,ActionList actions,Main base){
            super(p,actions,3,13,13,base,30);
             for(int i = 0;i<5;i++){
                if(i==1)
                    bullets.set(i,bullets.get(i).upgradeMulti(6,this, 5));
                else
                    bullets.set(i,bullets.get(i).upgrade(this, 5));
           }
        }
        public AAC(ActionList actions,Main base){
            this(actions.point,actions,base);
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[6], (int)p.x-8, (int)p.y -8,
                        (int)p.x + w+8, (int)p.y + h+8,
                        0, 0,buff[6].getWidth(null),buff[6].getHeight(null),null);
        }
    }

    private class Boss extends ECraft{
        public int level;
        public int extra;
        public Boss(Point p,ActionList actions,Main base,int level,int extra){
            super(p,actions,2,100,100,base,1000);
            this.level = level;
            this.extra = extra;
            if(level <1 || level>3)
                this.level = 1;
            if(extra <1)
                this.extra = 1;
            if(this.level + this.extra >5){
                for(int i = 0;i<5;i++){
                    if(i==1)
                        bullets.set(i,bullets.get(i).upgradeMulti(6,this, 5));
                    else
                        bullets.set(i,bullets.get(i).upgrade(this, 5));
                }
            }else if(this.level + this.extra >3){
                for(int i = 0;i<5;i++){
                    if(i==1)
                        bullets.set(i,bullets.get(i).upgradeMulti(5,this, 4));
                    else
                        bullets.set(i,bullets.get(i).upgrade(this, 4));
                }
            } else {
                for(int i = 0;i<5;i++){
                    if(i==1)
                        bullets.set(i,bullets.get(i).upgradeMulti(3,this, 2));
                    else
                        bullets.set(i,bullets.get(i).upgrade(this, 2));
                }
            }
            this.health *= level*extra;
            this.healthMax *= level*extra;
            this.speed *= level*extra;
        }
        @Override
        public boolean isDead(){
            if(health>0)
                return false;
            base.level.bossOn = false;
            base.player.health = base.player.healthMax;
            return true;
        }

        @Override
        public boolean isBoss(){
            return true;
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[27+level-1], (int)p.x-15, (int)p.y -15,
                        (int)p.x + w+15, (int)p.y + h+15,
                        0, 0,buff[27+level-1].getWidth(null),buff[27+level-1].getHeight(null),null);
            g.setColor(Color.darkGray);
            g.fillRect(50, 40,400, 10);
            g.setColor(Color.lightGray);
            g.fillRect(50, 40, (int)(((double)health)/((double)healthMax)*400), 10);
        }
    }

    private class UCraftPED extends SCraft{
        public int[] upgrades = new int[5];
        public int invincible = 0;

        public UCraftPED(Point p,Main base){
            super(p,4,13,13,base,50);
            upgrades[0]++;
            dir = -1;
            addShots = base.pShots;
            shooting = true;
        }

        public void lShift(){
            bulletIndex--;
            if(isNotBulletIndex())
                bulletIndex = bullets.size() - 1;
        }
        public void rShift(){
            bulletIndex++;
            if(isNotBulletIndex())
                bulletIndex = 0;
        }
        @Override
        public void shoot(){
            super.shoot();
        }
        @Override
        public void move(){
            super.move();
            if(invincible>0)
                invincible--;
        }
        @Override
        public void hit(int d){
            if(invincible <= 0)
                super.hit(d);
        }
        @Override
        public int hit(Craft c){
            if(invincible > 0)
                return 0;
            this.health -= c.collideDamage;
            invincible = 30;
            return collideDamage;
        }
        @Override
        public void draw(Graphics2D g){
            if(invincible > 0 && invincible  % 3 == 0){
                return;
            }
            if(lr == 0){
                g.drawImage(buff[0], (int)p.x-6, (int)p.y -7,
                        (int)p.x + w+7, (int)p.y + h+7,
                        0, 0,buff[0].getWidth(null),buff[0].getHeight(null),null);
            } else if(lr < 0) {
                g.drawImage(buff[1], (int)p.x-7, (int)p.y -7,
                        (int)p.x + w+7, (int)p.y + h+7,
                        0, 0,buff[1].getWidth(null),buff[1].getHeight(null),null);
            } else {
                g.drawImage(buff[2], (int)p.x-7, (int)p.y -7,
                        (int)p.x + w+7, (int)p.y + h+7,
                        0, 0,buff[2].getWidth(null),buff[2].getHeight(null),null);
            }
        }
        public void upgrade(int num){
            if(upgrades[num] >= 5)
                return;
            upgrades[num]++;
            switch(num){
                case 0:
                    bullets.set(num,bullets.get(num).upgrade(this,upgrades[num]));
                    break;
                case 1:
                    if(upgrades[num] < 2){
                        bullets.add(new Bullet(getBullet().multiShot(2, this, 10, 7, 7, 2),4,0));
                    } else {
                        bullets.set(num,bullets.get(num).upgradeMulti(upgrades[num]+1,this,upgrades[num]));
                    }
                    break;
                case 2:
                    if(upgrades[num] < 2){
                        bullets.add(new Bullet(new WaveShot(this,12,4,8,5,3),9,0));
                    } else {
                        bullets.set(num,bullets.get(num).upgrade(this,upgrades[num]));
                    }
                    break;
                case 3:
                    if(upgrades[num] < 2){
                        bullets.add(new Bullet(new PierceShot(this,20,5,15,7),20,0));
                    } else {
                        bullets.set(num,bullets.get(num).upgrade(this,upgrades[num]));
                    }
                    break;
                case 4:
                    if(upgrades[num] < 2){
                        bullets.add(new Bullet(new SplashShot(this,4,13,13,15,25),30,0));
                    } else {
                        bullets.set(num,bullets.get(num).upgrade(this,upgrades[num]));
                    }
                    break;
            }
        }
    }
    private abstract class SCraft extends Craft implements Shooter{
        public int bulletIndex = 0;
        public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        public ArrayList<RecShot> addShots = base.eShots;
        public boolean shooting = false;

        public SCraft(Point p,int speed,int w, int h, Main base, int health){
            super(p,speed,w,h,base);
            this.health = health;
            healthMax = health;
            bullets.add(new Bullet(new BasicShot(this,10,7,7,5)));
        }

        public SCraft(Point p,int speed,int w, int h, Main base){
            this(p,speed,w,h,base,4);
        }

        @Override
        public boolean canShoot(){
            return shooting && getBullet().isReady();
        }

        public Bullet getBullet(){
            return bullets.get(bulletIndex);
        }

        public boolean isBulletIndex(int index){
            return bullets.size() > index;
        }

        public boolean isNotBulletIndex(){
            return bulletIndex >= bullets.size() || bulletIndex < 0;
        }

        @Override
        public void shoot(){
            getBullet().add(addShots, this);
        }
    }

    private class Rock extends Craft{
        public Rock(Point p,int w, int h,Main base, int health){
            this(p,0,w,h,base,health,0,0);
        }
        public Rock(Point p,int speed,int w, int h,Main base, int health,double lr,double ud){
            super(p,speed,w,h,base,health);
            collideDamage = 6;
            this.lr = lr;
            this.ud = ud;
        }
        @Override
        public Line getPath(){
            Point p1 = p.getPoint();
            Point p2 = p.getPoint();
            p1.x += this.w/2;
            p2.x -= this.w/2;
            p1.y += this.h/2;
            p2.y -= this.h/2;
            return new Line(p1,p2);
        }
        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[12], (int)p.x-3, (int)p.y -3,
                        (int)p.x + w+3, (int)p.y + h+3,
                        0, 0,buff[12].getWidth(null),buff[12].getHeight(null),null);
        }
    }
    private class Wall extends Craft{
        public Wall(Point p,int w, int h,Main base, int health){
            this(p,0,w,h,base,health,0,0);
        }
        public Wall(Point p,int speed,int w, int h,Main base, int health,double lr,double ud){
            super(p,speed,w,h,base,health);
            collideDamage = 3;
            this.lr = lr;
            this.ud = ud;
        }
        @Override
        public Line getPath(){
            Point p1 = p.getPoint();
            Point p2 = p.getPoint();
            p1.x += this.w/2;
            p2.x -= this.w/2;
            p1.y += this.h/2;
            p2.y -= this.h/2;
            return new Line(p1,p2);
        }
        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[3], (int)p.x-3, (int)p.y -3,
                        (int)p.x + w+3, (int)p.y + h+3,
                        0, 0,buff[3].getWidth(null),buff[3].getHeight(null),null);
        }
    }
    private abstract class Craft extends RecObj implements Tangible,Drawable{
        public Main base;
        public int health = 15;
        public int healthMax = 15;
        public int collideDamage = 10;
        public int dir = 1;

        public Craft(Point p,int speed,int w, int h, Main base){
            super(p,speed,w,h);
            this.base = base;
        }

        public Craft(Point p,int speed,int w, int h, Main base, int health){
            super(p,speed,w,h);
            this.base = base;
            this.health = health;
            this.healthMax = health;
        }

        public int hit(Craft c){
            this.health -= c.collideDamage;
            return collideDamage;
        }

        public void hit(int d){
            this.health -= d;
        }

        public boolean notInBounds(){
            if(this.construct().intersects(-100, -100, 700, 650))
                return false;
            return true;
        }

        public boolean isDead(){
            return health <= 0;
        }
    }

    private class Bullet{
        public RecShot[] shots;
        public int shootPauseMax;
        public int shootPause;
        public int shootContinueMax;
        public int shootContinue;

        public Bullet(RecShot s){
            this(s,6,0);
        }

        public Bullet(RecShot s,int shootPause,int shootContinue){
            shots = new RecShot[1];
            shots[0] = s;
            this.shootPause = shootPause;
            this.shootPauseMax = shootPause;
            this.shootContinueMax = shootContinue;
        }

        public Bullet(RecShot[] shots,int shootPause, int shootContinue){
            this.shots = new RecShot[shots.length];
            for(int i = 0;i<shots.length;i++)
                this.shots[i] = shots[i];
            this.shootPause = shootPause;
            this.shootPauseMax = shootPause;
            this.shootContinueMax = shootContinue;
        }

        public Bullet getCopy(SCraft s){
            RecShot[] newShots = new RecShot[shots.length];
            for(int i = 0;i<shots.length;i++){
                newShots[i] = shots[i].getCopy(s);
            }
            return new Bullet(newShots,shootPause,shootPauseMax);
        }

        public void setAngle(double lr, double ud){
            for(RecShot s:shots){
                s.scaleud  = ud;
                s.scalelr = lr;
            }
        }
        public void setFrequency(double sPause, double sContinue){
            this.shootPauseMax = (int)sPause;
            this.shootContinueMax = (int)sContinue;
        }

        public RecShot[] multiShot(int shotNum,SCraft s,int speed,int w, int h, int damage){
            RecShot[] out = new RecShot[shotNum];
            for(double i = 1;i<=shotNum/2;i++){
                out[(int)i-1] = new MultiShot(s,speed,w,h,damage,i/shotNum,i/shotNum);
                out[shotNum-(int)i] = new MultiShot(s,speed,w,h,damage,-i/shotNum,i/shotNum);
            }
            if(shotNum % 2 != 0){
                out[shotNum/2] = new MultiShot(s,speed,w,h,damage);
            }
            return out;
        }
        public RecShot[] multiShot(int shotNum,SCraft s,RecShot shot){
            return multiShot(shotNum,s,shot.speed,shot.w,shot.h,shot.damage);
        }

        public void add(ArrayList<RecShot> a,SCraft s){
            for(RecShot shot:this.shots){
                a.add(shot.getCopy(s));
            }
        }

        public boolean isReady(){
            if(shootContinue > 0){
                shootContinue--;
                return true;
            }

            if(shootPause >= shootPauseMax){
                shootPause = 0;
                shootContinue = shootContinueMax;
                return true;
            }
            shootPause++;
            return false;
        }
        public Bullet upgradeMulti(int shotNum,SCraft s,int level){
            return new Bullet(multiShot(shotNum,s,shots[0]),
                                shots[0].upgradeSPause(level),
                                shots[0].upgradeSContinue(level));
        }
        public Bullet upgrade(SCraft s,int level){
            RecShot[] newShots = new RecShot[shots.length];
            for(int i = 0;i<shots.length;i++)
                newShots[i] = shots[i].upgrade(s, level);
            return new Bullet(newShots,
                                newShots[0].upgradeSPause(level),
                                newShots[0].upgradeSContinue(level));
        }
    }
    private class SplashShot extends RecShot{
        public int radius;
        public boolean exploded = false;

        public SplashShot(SCraft s,int speed, int w, int h, int damage,int radius){
            this(s,speed,w,h,damage,radius,0,1);
        }
        public SplashShot(SCraft s,int speed, int w, int h, int damage,int radius,double scalelr,double scaleud){
            super(s,speed,w,h,damage,scalelr,scaleud);
            this.radius = radius;
        }
        @Override
        public int hit(Craft c){
            hit = true;
            this.w += radius;
            this.h += radius;
            this.p.x -= radius/2;
            this.p.y -= radius/2;
            this.speed = 0;
            return damage;
        }
        @Override
        public void move(){
            super.move();
            if(hit){
                exploded = true;
                base.explosions.add(new Explosion(p,w,h,base));
            }
        }
        @Override
        public boolean isDead(){
            return exploded;
        }

        @Override
        public SplashShot getCopy(SCraft s){
            return new SplashShot(s,speed,w,h,damage,radius,scalelr,scaleud);
        }

        @Override
        public SplashShot upgrade(SCraft s,int level){
            switch(level){
                case 1: return new SplashShot(s,4,13,13,15,25);
                case 2: return new SplashShot(s,4,14,14,15,27);
                case 3: return new SplashShot(s,4,14,14,15,32);
                case 4: return new SplashShot(s,5,15,15,16,34);
                case 5: return new SplashShot(s,5,16,16,18,37);
                default: return new SplashShot(s,7,18,18,20,40);
            }
        }
        @Override
        public int upgradeSPause(int level){
            switch(level){
                case 1: return 30;
                case 2: return 28;
                case 3: return 26;
                case 4: return 23;
                case 5: return 21;
                default: return 15;
            }
        }
        @Override
        public int upgradeSContinue(int level){
             switch(level){
                case 1: return 0;
                case 2: return 0;
                case 3: return 0;
                case 4: return 0;
                case 5: return 0;
                default: return 0;
            }
        }
        @Override
        public void draw(Graphics2D g){
            if(!hit){
                g.drawImage(buff[10], (int)p.x-5, (int)p.y -5,
                        (int)p.x + w+5, (int)p.y + h+5,
                        0, 0,buff[10].getWidth(null),buff[10].getHeight(null),null);
            }
        }

    }
    private class WaveShot extends RecShot{
        public int increment;
        public WaveShot(SCraft s,int speed, int w, int h, int damage,int increment){
            this(s,speed,w,h,damage,increment,0,1);
        }
        public WaveShot(SCraft s,int speed, int w, int h, int damage,int increment,double scalelr,double scaleud){
            super(s,speed,w,h,damage,scalelr,scaleud);
            this.increment = increment;
        }
        @Override
        public void move(){
            super.move();
            this.w += increment;
            this.p.x -= increment/2;
        }
        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[8], (int)p.x-8, (int)p.y -8,
                        (int)p.x + w+8, (int)p.y + h+8,
                        0, 0,buff[8].getWidth(null),buff[8].getHeight(null),null);
        }
        @Override
        public WaveShot upgrade(SCraft s,int level){
             switch(level){
                case 1: return new WaveShot(s,12,4,8,5,3);
                case 2: return new WaveShot(s,12,6,8,6,3);
                case 3: return new WaveShot(s,12,10,8,12,4);
                case 4: return new WaveShot(s,12,10,8,13,4);
                case 5: return new WaveShot(s,12,12,8,13,5);
                default: return new WaveShot(s,12,12,8,13,5);
            }
        }
        @Override
        public int upgradeSPause(int level){
            switch(level){
                case 1: return 9;
                case 2: return 9;
                case 3: return 15;
                case 4: return 15;
                case 5: return 11;
                default: return 11;
            }
        }
        @Override
        public int upgradeSContinue(int level){
             switch(level){
                case 1: return 0;
                case 2: return 1;
                case 3: return 0;
                case 4: return 1;
                case 5: return 2;
                default: return 2;
            }
        }

        @Override
        public WaveShot getCopy(SCraft s){
            return new WaveShot(s,speed,w,h,damage,increment,scalelr,scaleud);
        }
    }
    private class PierceShot extends RecShot{
        public PierceShot(SCraft s,int speed, int w, int h, int damage,double scalelr,double scaleud ){
            super(s,speed,w,h,damage,scalelr,scaleud);
        }
        public PierceShot(SCraft s,int speed, int w, int h, int damage){
            this(s,speed,w,h,damage,0,1);
        }
        @Override
        public PierceShot getCopy(SCraft s){
            return new PierceShot(s,speed,w,h,damage,scalelr,scaleud);
        }
        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[9], (int)p.x-10, (int)p.y -8,
                        (int)p.x + w+10, (int)p.y + h+8,
                        0, 0,buff[9].getWidth(null),buff[9].getHeight(null),null);
        }


        @Override
        public boolean isDead(){
            return false;
        }
        @Override
        public PierceShot upgrade(SCraft s,int level){
             switch(level){
                case 1: return new PierceShot(s,20,5,15,7);
                case 2: return new PierceShot(s,20,5,15,15);
                case 3: return new PierceShot(s,20,5,15,20);
                case 4: return new PierceShot(s,20,5,15,25);
                case 5: return new PierceShot(s,20,5,15,30);
                default: return new PierceShot(s,20,5,15,30);
            }
        }
        @Override
        public int upgradeSPause(int level){
             switch(level){
                case 1: return 20;
                case 2: return 19;
                case 3: return 17;
                case 4: return 15;
                case 5: return 12;
                default: return 12;
            }
        }
        @Override
        public int upgradeSContinue(int level){
            switch(level){
                case 1: return 0;
                case 2: return 0;
                case 3: return 0;
                case 4: return 0;
                case 5: return 1;
                default: return 1;
            }
        }
    }
    private class BasicShot extends RecShot{
        public BasicShot(SCraft s,int speed, int w, int h, int damage,double scalelr,double scaleud){
            super(s,speed,w,h,damage,scalelr,scaleud);
        }
        public BasicShot(SCraft s,int speed, int w, int h, int damage){
            super(s,speed,w,h,damage,0,1);
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[7], (int)p.x-2, (int)p.y -2,
                        (int)p.x + w+2, (int)p.y + h+2,
                        0, 0,buff[7].getWidth(null),buff[7].getHeight(null),null);
        }

        @Override
        public BasicShot getCopy(SCraft s){
            return new BasicShot(s,speed,w,h,damage,scalelr,scaleud);
        }
        @Override
        public BasicShot upgrade(SCraft s,int level){
             switch(level){
                case 1: return new BasicShot(s,10,7,7,5);
                case 2: return new BasicShot(s,11,7,7,7);
                case 3: return new BasicShot(s,12,7,7,7);
                case 4: return new BasicShot(s,13,7,7,7);
                case 5: return new BasicShot(s,14,7,7,7);
                default: return new BasicShot(s,16,7,7,9);
            }
        }
        @Override
        public int upgradeSPause(int level){
             switch(level){
                case 1: return 6;
                case 2: return 7;
                case 3: return 7;
                case 4: return 7;
                case 5: return 8;
                default: return 10;
            }
        }
        @Override
        public int upgradeSContinue(int level){
             switch(level){
                case 1: return 0;
                case 2: return 0;
                case 3: return 1;
                case 4: return 1;
                case 5: return 2;
                default: return 2;
            }
        }
    }
    private class MultiShot extends RecShot{
        public MultiShot(SCraft s,int speed, int w, int h, int damage,double scalelr,double scaleud){
            super(s,speed,w,h,damage,scalelr,scaleud);
        }
        public MultiShot(SCraft s,int speed, int w, int h, int damage){
            this(s,speed,w,h,damage,0,1);
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[18], (int)p.x-6, (int)p.y -6,
                        (int)p.x + w+6, (int)p.y + h+6,
                        0, 0,buff[18].getWidth(null),buff[18].getHeight(null),null);
        }

        @Override
        public MultiShot getCopy(SCraft s){
            return new MultiShot(s,speed,w,h,damage,scalelr,scaleud);
        }
        @Override
        public MultiShot upgrade(SCraft s,int level){
            switch(level){
                case 1: return new MultiShot(s, 10, 7, 7, 2);
                case 2: return new MultiShot(s, 12, 7, 7, 2);
                case 3: return new MultiShot(s, 14, 8, 8, 3);
                case 4: return new MultiShot(s, 16, 8, 8, 3);
                case 5: return new MultiShot(s, 18, 9, 9, 4);
                default: return new MultiShot(s, 18, 9, 9, 2);
            }
        }
        @Override
        public int upgradeSPause(int level){
            switch(level){
                case 1: return 4;
                case 2: return 4;
                case 3: return 4;
                case 4: return 4;
                case 5: return 4;
                default: return 4;
            }
        }
        @Override
        public int upgradeSContinue(int level){
            switch(level){
                case 1: return 0;
                case 2: return 0;
                case 3: return 0;
                case 4: return 0;
                case 5: return 0;
                default: return 0;
            }
        }
    }
    private abstract class RecShot extends Shot implements Drawable{
        public int w;
        public int h;

        public RecShot(SCraft s,int speed, int w, int h, int damage,double scalelr,double scaleud){
            super(s,speed,damage,scalelr,scaleud);
            this.w = w;
            this.h = h;
            modifyPoint(s);
        }

        @Override
        public Shape construct(){
            return new Rectangle2D.Double(p.x, p.y, w, h);
        }
        public abstract RecShot getCopy(SCraft s);
        public abstract RecShot upgrade(SCraft s,int level);
        public abstract int upgradeSPause(int level);
        public abstract int upgradeSContinue(int level);
        @Override
        public void modifyPoint(SCraft s){
            p.x += (s.w/2 - this.w/2);
            p.y += (s.h/2 - this.h/2);
        }
    }
    private abstract class Shot implements Collidable,Tangible{
        public boolean hit = false;
        public Point p;
        public int speed;
        public Main base;
        public double scaleud;
        public double scalelr;
        public int dir;
        public int damage;

        public Shot(SCraft s,int speed, int damage,double scalelr,double scaleud){
            this.p = s.getPoint();
            this.speed = speed;
            this.base = s.base;
            this.dir = s.dir;
            this.damage = damage;
            this.scalelr = scalelr;
            this.scaleud = scaleud;
        }

        public int hit(Craft c){
            hit = true;
            return damage;
        }

        @Override
        public boolean isDead(){
            return hit;
        }
        @Override
        public boolean notInBounds(){
            if(this.construct().intersects(0, -10, 500, 510))
                return false;
            return true;
        }
        @Override
        public void move(){
            p.x += (scalelr * speed * dir);
            p.y += (scaleud * speed * dir);
        }
        public Point falseMove(){
            return new Point(p.x + (scalelr * speed),p.y + (scaleud * speed * dir));
        }
        @Override
        public Line getPath(){
            return new Line(p.getPoint(),falseMove());
        }
        @Override
        public abstract Shape construct();
        public abstract void modifyPoint(SCraft s);
    }

    private interface Tangible{
        public boolean notInBounds();
        public boolean isDead();
    }
    private interface Collidable{
        public Shape construct();
        public void move();
        public Line getPath();
    }
    private interface Shooter{
        public void shoot();
        public boolean canShoot();
    }
    private interface Drawable{
        public void draw(Graphics2D g);
    }

    private class Token implements Tangible,Collidable,Drawable{
        public Point p;
        public int counter = 200;
        public int w = 13;
        public int h = 13;
        public Main base;
        public int num;

        public Token(SCraft s,int num){
            this.p = s.getPoint();
            this.base = s.base;
            if(num >-1 && num < 5)
                this.num = num;
        }
        public int hit(Craft c){
            counter = 0;
            return 0;
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[19 + num], (int)p.x-2, (int)p.y -2,
                        (int)p.x + w+2, (int)p.y + h+2,
                        0, 0,buff[19 + num].getWidth(null),buff[19 + num].getHeight(null),null);
        }

        @Override
        public boolean isDead(){
            return counter <= 0;
        }
        @Override
        public boolean notInBounds(){
            if(this.construct().intersects(0, 0, 500, 500))
                return false;
            return true;
        }
        @Override
        public void move(){
            counter--;
        }
        @Override
        public Line getPath(){
            Point p1 = p.getPoint();
            Point p2 = p.getPoint();
            p1.x += this.w/2;
            p2.x -= this.w/2;
            p1.y += this.h/2;
            p2.y -= this.h/2;
            return new Line(p1,p2);
        }
        @Override
        public Shape construct(){
            return new Rectangle2D.Double(p.x, p.y, w, h);
        }
    }
    private class Explosion implements Drawable,Tangible{
        public Point p;
        public int counter = 10;
        public int w;
        public int h;
        public Main base;

        public Explosion(Point p,int w,int h,Main base){
            this.p = new Point(p.x - 5,p.y - 5);
            this.base = base;
            this.w = w + 10;
            this.h = h + 10;
        }
        public int hit(Craft c){
            counter = 0;
            return 0;
        }

        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[24 + counter/4], (int)p.x-6, (int)p.y -6,
                        (int)p.x + w+6, (int)p.y + h+6,
                        0, 0,buff[24 + counter/4].getWidth(null),buff[24 + counter/4].getHeight(null),null);
        }

        @Override
        public boolean isDead(){
            counter--;
            return counter <= 0;
        }
        @Override
        public boolean notInBounds(){
            return false;
        }
    }

    private class Mover extends KeyAdapter{
        public Main base;

        public Mover(Main add){
            base = add;
        }

        @Override
        public void keyReleased(KeyEvent e){
            int action = e.getKeyCode();
            switch(action){
                case KeyEvent.VK_UP: base.player.ud = base.stay;
                    break;
                case KeyEvent.VK_DOWN: base.player.ud = base.stay;
                    break;
                case KeyEvent.VK_LEFT: base.player.lr = base.stay;
                    break;
                case KeyEvent.VK_RIGHT: base.player.lr = base.stay;
                    break;
                case KeyEvent.VK_Q: base.player.lShift();
                    break;
                case KeyEvent.VK_E: base.player.rShift();
                    break;
                case KeyEvent.VK_0: base.clearAll();
                    break;
                case KeyEvent.VK_P: base.notPaused = !base.notPaused;
                    break;
            }
            /*if(action  == KeyEvent.VK_UP)
                base.player.ud = base.stay;
            if(action  == KeyEvent.VK_DOWN)
                base.player.ud = base.stay;
            if(action  == KeyEvent.VK_LEFT)
                base.player.lr = base.stay;
            if(action  == KeyEvent.VK_RIGHT)
                base.player.lr = base.stay;
            if(action == KeyEvent.VK_Q)
                base.player.lShift();
            if(action == KeyEvent.VK_E)
                base.player.rShift();*/
            //if(action  == KeyEvent.VK_SPACE)
                //base.player.shooting = false;
        }

        @Override
        public void keyPressed(KeyEvent e){
            int action = e.getKeyCode();
            //System.out.println(action);
            switch(action){
                case KeyEvent.VK_UP: base.player.ud = base.in;
                    break;
                case KeyEvent.VK_DOWN: base.player.ud = base.out;
                    break;
                case KeyEvent.VK_LEFT: base.player.lr = base.in;
                    break;
                case KeyEvent.VK_RIGHT: base.player.lr = base.out;
                    break;
            }
            /*if(action  == KeyEvent.VK_UP)
                base.player.ud = base.in;
            if(action  == KeyEvent.VK_DOWN)
                base.player.ud = base.out;
            if(action  == KeyEvent.VK_LEFT)
                base.player.lr = base.in;
            if(action  == KeyEvent.VK_RIGHT)
                base.player.lr = base.out;*/
            //if(action  == KeyEvent.VK_SPACE)
                //base.player.shooting = true;
        }
    }

    private class BorderWall extends Wall{
        public BorderWall(Point p, int w, int h, Main base){
            super(p,w,h,base,0);
            collideDamage = 0;
        }
        @Override
        public int hit(Craft c){
            return collideDamage;
        }
        @Override
        public void hit(int d){
        }
        @Override
        public boolean isDead(){
            return false;
        }
        @Override
        public void draw(Graphics2D g){
            g.drawImage(buff[11], (int)p.x-3, (int)p.y -3,
                        (int)p.x + w+3, (int)p.y + h+3,
                        0, 0,buff[11].getWidth(null),buff[11].getHeight(null),null);
        }
    }
    private class LWall extends BorderWall{
        public LWall(Main base){
            super(new Point(0,0),8,500,base);
            collideDamage = 3;
        }
    }
    private class RWall extends BorderWall{
        public RWall(Main base){
            super(new Point(485,0),8,500,base);
            collideDamage = 3;
        }
    }
    private class UWall extends BorderWall{
        public UWall(Main base){
            super(new Point(-5,-5),500,3,base);
        }
    }
    private class DWall extends BorderWall{
        public DWall(Main base){
            super(new Point(0,500),500,3,base);
        }
    }

    private class RecObj implements Collidable{
        public double lr;
        public double ud;
        public int speed;
        public Point p;
        public int w;
        public int h;

        public RecObj(Point p, int w, int h){
            this.p = p.getPoint();
            this.w = w;
            this.h = h;
        }

        public RecObj(Point p, int speed, int w, int h){
            this.p = p.getPoint();
            this.speed = speed;
            this.w = w;
            this.h = h;
        }
        @Override
        public void move(){
            p.x += (lr * speed);
            p.y += (ud * speed);
        }

        @Override
        public Line getPath(){
            return new Line(p.getPoint(),falseMove());
        }

        public Point falseMove(){
            return new Point(p.x + (lr * speed),p.y + (ud * speed));
        }

        public void moveUndo(){
            p.x -= (lr * speed);
            p.y -= (ud * speed);
        }

        public void moveTo(double x,double y){
            this.lr = (x - p.x)/Math.abs(y + x - p.x - p.y);
            this.ud = (y - p.y)/Math.abs(y + x - p.x - p.y);
        }

        public double moveToX(double x,double y){
            return (x - p.x)/Math.abs(y + x - p.x - p.y);
        }

        public double moveToY(double x,double y){
            return (y - p.y)/Math.abs(y + x - p.x - p.y);
        }
        public Point getPoint(){
            return new Point(p.x,p.y);
        }
        @Override
        public Rectangle2D.Double construct(){
            return new Rectangle2D.Double(p.x, p.y, w, h);
        }

        public boolean collide(Collidable c){
            return c.construct().intersects(p.x, p.y, w, h) || getPath().intersect(c.getPath());
        }
    }
    private class Line{
        public Point p1;
        public Point p2;
        public Line(Point p1, Point p2){
            this.p1 = p1;
            this.p2 = p1;
        }
        public boolean intersect(Line l){
            if(between(p1.x,p2.x,l.p1.x)||between(p1.x,p2.x,l.p2.x)){
                if(between(p1.y,p2.y,l.p1.y)||between(p1.y,p2.y,l.p2.y)){
                    return true;
                }
            }
            return false;
        }
        public boolean between(double a, double b, double c){
            return c > Math.min(a, b) && c < Math.max(a, b);
        }
    }
    private class Point{
        public double x;
        public double y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        public Point(Point p){
            this.x = p.x;
            this.y = p.y;
        }
        public Point getPoint(){
            return new Point(this);
        }
        @Override
        public String toString(){
            return "Point[" + x + "," + y + "]";
        }

        private double distance(double x,double y){
            return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2);
        }
    }
}
