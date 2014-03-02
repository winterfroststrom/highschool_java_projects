/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Game {
    final int WIDTH = 600;
    final int HEIGHT = 500;
    int height;
    int width;
//    Area area;
//    int preArea;
    boolean dChange;
    boolean paused;
    boolean dialogue;
    ArrayList<String> dia;
    Player player;
    Screens screens;
    BossType boss;
    EnvironmentVars ev;
    int centerx;
    int centery;
    GrandArea ga;
    ArrayList<NPC> npcs;
    ArrayList<Wall> walls;
    ArrayList<Event> events;
    ArrayList<Attack> atks;
    ArrayList<Floor> floor;
    ArrayList<Block> blocks;
    ArrayList<Item> items;

    public Game() {
        walls = new ArrayList<Wall>();
        npcs = new ArrayList<NPC>();
        events = new ArrayList<Event>();
        atks = new ArrayList<Attack>();
        items = new ArrayList<Item>();
        floor = new ArrayList<Floor>();
        blocks = new ArrayList<Block>();
        player = new Player(this);
        ga = new GA_Hammer_Forge(this);
//        ga = new GA_Tower_Town(this);
//        ga = new GA_Ritual_Tower(this);
//        ga = new GA_Spiral_Shell(this);
        ga.loadGA();
        ev = new EnvironmentVars();
        centerx = 0;
        centery = 0;
        screens = new Screens(this);

        paused = false;
        dialogue = false;
    }

    public void loop(){
        sensors();
        events();
        think();
        attack();
        moveANDpcollide();
        collide();
        remove();
    }

    private void sensors(){
        for(Event e:events){
            e.sense();
        }
        for(NPC n:npcs){
            n.sense();
        }
    }

    private void events(){
        for(ListIterator<Event> it = events.listIterator();it.hasNext();){
            Event e = it.next();
            if(e.isUsed())
                it.remove();
            if(e.isActivated())
                e.event(it);
        }
    }

    private void think(){
        player.think();
        if(boss!=null)
        boss.think();
        for(NPC n:npcs){
            n.think();
        }
        for(Floor f:floor)
            f.think();
    }

    public void attack(){
        if(player.canAttack()){
            player.attack();
        }
        if(boss!=null&&boss.canAttack())
            boss.attack();
        for(NPC n:npcs){
            if(n.canAttack()){
                n.attack();
            }
        }
    }

    private void moveANDpcollide(){
        player.move();
        for(Block b:blocks){
            if(player.self.intersects(b.push)){
                b.move();
            }
            b.pIterate();
        }
        player.pIterate();
        if(boss!=null){
            boss.move();
            boss.pIterate();
        }
        for(NPC n:npcs){
            n.move();
            n.pIterate();
        }
        for(Attack a:atks){
            a.iterate();
        }
        for(Item i:items){
            i.iterate();
        }
        for(Floor f:floor)
            f.move();
    }

    private void collide(){
        for(Item i:items){
            if(i.self.intersects(player.self)){
                i.affect(player);
            }
        }
        for(Attack a:atks){
            if(a.intersect(player) && a.owner != player && player.canAffect()){
                a.affect(player);
            }
            if(boss!=null && boss.intersects(a.area) && a.owner != boss && boss.canAffect()){
                a.affect(boss);
            }
            if(a.affectNPC)
                for(NPC n:npcs){
                    if(a.intersect(n) && a.owner != n && n.canAffect()){
                        a.affect(n);
                    }
                }
            for(Wall w:walls){
                if(a.intersect(w)){
                    a.affect(w);
                }
            }
            for(Block b:blocks){
                if(a.intersect(b)){
                    a.affect(b);
                }
            }
        }
        for(Floor f:floor){
            if(f.canAffect()&&f.self.intersects(player.self)){
                f.affect(player);
            }
            if(f.canAffect()&&boss!=null&&boss.intersects(f.self)){
                f.affect(boss);
            }
        }
        for(NPC n:npcs){
            for(Floor f:floor){
                if(f.canAffect()&&f.self.intersects(n.self))
                    f.affect(n);
            }
            if(n.self.intersects(player.self)){
                
            }
            for(NPC np:npcs){
                if(np.self.intersects(n.self)&& !np.equals(n)){
                    
                }
            }
        }
    }

    private void remove(){
        for(Iterator<Attack> it = atks.iterator();it.hasNext();){
            Attack a = it.next();
            if(!a.intersect(0, 0, width, height) || a.isDead()){
                if(a.owner == player)
                    player.atk = null;
                else if(a.owner == boss && boss != null)
                    boss.atk = null;
                it.remove();
            }
        }
        for(Iterator<Item> it = items.iterator();it.hasNext();){
            Item i = it.next();
            if(!i.self.intersects(0, 0, width, height) || i.isDead()){
                it.remove();
            }
        }
        for(Iterator<Wall> it = walls.iterator();it.hasNext();){
            Wall w = it.next();
            if(w.isDead()){
                it.remove();
            }
        }
        for(Iterator<Block> it = blocks.iterator();it.hasNext();){
            Block b = it.next();
            if(b.isDead()){
                it.remove();
                b.die();
            }
        }
        for(Iterator<Floor> it = floor.iterator();it.hasNext();){
            Floor f = it.next();
            if(f.isDead()){
                it.remove();
            }
        }
        for(Iterator<NPC> it = npcs.iterator();it.hasNext();){
            NPC n = it.next();
            if(!n.self.intersects(0, 0, width, height)||n.isDead()){
                n.die();
                it.remove();
            }
        }
        if(boss!=null&&boss.isDead()){
            boss.die();
            boss = null;
        }
        if(player != null && player.isDead()){
            ga.die();
            player.die();
        }
    }

    public void paint(Graphics2D g){
        for(Attack a:atks){
            a.paintSelf(g);
        }
        for(Item i:items){
            i.paintSelf(g);
        }
        for(Event e: events){
            e.paintSelf(g);
        }
        for(Floor f:floor){
            f.paintSelf(g);
        }
        for(Wall w:walls){
            w.paintSelf(g);
        }
        for(Block b:blocks){
            b.paintSelf(g);
        }
        for(NPC n:npcs){
            n.paintSelf(g);
        }
        player.paintSelf(g);
        if(boss != null){
            boss.paintSelf(g);
        }
    }

    public void paint2(Graphics2D g){
        screens.paint(g);
    }

    public boolean dChange(){
        if(dChange){
            ga.current.startArea();
            dChange = false;
            return true;
        }
        return false;
    }

    public void clear(){
        for(Iterator<Attack> it = atks.iterator();it.hasNext();){
            it.next();
            it.remove();
        }
        for(Iterator<Item> it = items.iterator();it.hasNext();){
            it.next();
            it.remove();
        }
        for(Iterator<Wall> it = walls.iterator();it.hasNext();){
            it.next();
            it.remove();
        }
        for(Iterator<Block> it = blocks.iterator();it.hasNext();){
            it.next();
            it.remove();
        }
        for(Iterator<Floor> it = floor.iterator();it.hasNext();){
            it.next();
            it.remove();
        }
        for(Iterator<NPC> it = npcs.iterator();it.hasNext();){
            it.next();
            it.remove();
        }
        boss = null;
    }

    public void clearEvents(){
        for(Iterator<Event> it = events.iterator();it.hasNext();){
            it.next();
            it.remove();
        }
    }

    public void loadDia(ArrayList<String> dia){
        this.dia = dia;
        dialogue = true;
    }
}
