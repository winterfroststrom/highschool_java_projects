/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neohellianalmagemachina;

/**
 *
 * @author Scalene
 */

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Painter {
    Main main;
    Image buffer;
    Graphics2D bufferg;
    final Rectangle single;
    Rectangle two;
    Rectangle key;
    Rectangle status;
    Rectangle controls;
    Rectangle title;
    Rectangle description;
    public static final Color[] units = {Color.BLUE,Color.LIGHT_GRAY,Color.YELLOW};
    Logic logic;

    public Painter(Main main,Image buffer,Logic logic) {
        this.main = main;
        this.buffer = buffer;
        this.logic = logic;
        bufferg = (Graphics2D) buffer.getGraphics();
        bufferg.setBackground(Color.DARK_GRAY);
        bufferg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        single = new Rectangle(150, 180, 200, 50);
        two = new Rectangle(150, 250, 200, 50);
        key = new Rectangle(0, 500, 200, 100);
        status = new Rectangle(370, 500, 130, 100);
        controls = new Rectangle(200, 500, 170, 100);
        title = new Rectangle(0,40,500,100);
        description = new Rectangle(100,350,300,200);
    }

    private void paintGame(){
        if(logic.winstate != Logic.ongoing){
            paintWin();
            return;
        }
        if(logic.getHotSeat()){
            paintHotSeat();
            return;
        }
        paintBoard();
        if(logic.getPaused()){
            paintPaused();
            return;
        }
        bufferg.setColor(Color.WHITE);
        bufferg.drawLine(0, 500, 500, 500);
        paintControls();
        paintStatus();
        paintKey();
        paintCursor();
        paintLocations();
    }

    

    public Image paint(){
        bufferg.clearRect(0, 0, 500, 600);
        if(main.quality)
            bufferg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        else
            bufferg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        switch(main.gametype){
            case Main.single:
            case Main.two: paintGame();
                break;
            default: paintStart();
                break;
        }

        return buffer;
    }

    private void paintStart(){
        bufferg.setColor(Color.RED);
        bufferg.fillRect(-1, -1, 501, 601);
        bufferg.setColor(Color.BLACK);

        bufferg.fillRect(title.x, title.y, title.width, title.height);
        bufferg.fillRect(single.x, single.y, single.width, single.height);
        bufferg.fillRect(two.x, two.y, two.width, two.height);
        bufferg.fillRect(description.x, description.y, description.width, description.height);
        bufferg.setColor(Color.WHITE);
        bufferg.setFont(new Font(Font.DIALOG,Font.PLAIN,30));
        bufferg.drawString("Neo-Hellian Almagemachina", 75, 100);
        bufferg.setFont(new Font(Font.DIALOG,Font.PLAIN,12));
        bufferg.drawString("Single Player (A)", single.x + 60, single.y + 30);
        bufferg.drawString("Two Player <hotseat> (S)", two.x + 35, two.y + 30);
        bufferg.drawString("This game was programmed by a sparky person.", description.x + 10, description.y + 10 + 4);
        bufferg.drawString("The concept comes from a manga called" , description.x + 10, description.y + 30 + 4);
        bufferg.drawString("\"The World God Only Knows,\"", description.x + 10, description.y + 50 + 4);
        bufferg.drawString("or (\"Kami nomi zo Shiru Sekai,\")", description.x + 10, description.y + 70 + 4);
        bufferg.drawString("by 	Wakaki Tamiki." , description.x + 10, description.y + 90 + 4);
        bufferg.drawString("The specific chapter is FLAG 47 (\"The Girl's Her\")." , description.x + 10, description.y + 110 + 4);
        bufferg.drawString("In the manga, the game has the same title." , description.x + 10, description.y + 130 + 4);
        bufferg.drawString("However, not all of the game was clear." , description.x + 10, description.y + 150 + 4);
        bufferg.drawString("Gamemechanic-wise, everything is guessed at" , description.x + 10, description.y + 170 + 4);
        bufferg.drawString("with some consideration of game efficacy." , description.x + 10, description.y + 190 + 4);
        


    }

    private void paintBoard(){
        for(int i = 5;i>0;i--){
            GeneralPath path = new GeneralPath(new Ellipse2D.Double(250-50*i, 250-50*i,100*i,100*i));
            path.moveTo(250,250);
            path.lineTo(250,250-i*50);
            path.lineTo(250-i*50,250-i*50);
            path.lineTo(250,250);
            path.lineTo(250,250+i*50);
            path.lineTo(250+i*50,250+i*50);
            path.lineTo(250,250);
            path.lineTo(250-i*50,250);
            path.lineTo(250-i*50,250+i*50);
            path.lineTo(250,250);
            path.lineTo(250+i*50,250);
            path.lineTo(250+i*50,250-i*50);
            path.lineTo(250,250);
            path.closePath();
            GeneralPath path2 = new GeneralPath(new Ellipse2D.Double(250-50*i, 250-50*i,100*i,100*i));
            path2.moveTo(250,250);
            path2.lineTo(250-i*50,250-i*50);
            path2.lineTo(250-i*50,250);
            path2.lineTo(250,250);
            path2.lineTo(250+i*50,250+i*50);
            path2.lineTo(250+i*50,250);
            path2.lineTo(250,250);
            path2.lineTo(250+i*50,250-i*50);
            path2.lineTo(250,250-i*50);
            path2.lineTo(250,250);
            path2.lineTo(250-i*50,250+i*50);
            path2.lineTo(250,250+i*50);
            path2.lineTo(250,250);
            path2.closePath();
            if(i%2 == 0){
                bufferg.setColor(Color.RED);
                bufferg.draw(path);
                bufferg.fill(path);
                bufferg.setColor(Color.BLACK);
                bufferg.draw(path2);
                bufferg.fill(path2);
            } else {
                bufferg.setColor(Color.BLACK);
                bufferg.draw(path);
                bufferg.fill(path);
                bufferg.setColor(Color.RED);
                bufferg.draw(path2);
                bufferg.fill(path2);
            }
        }
        bufferg.setColor(Color.DARK_GRAY);
        GeneralPath path = new GeneralPath(new Ellipse2D.Double(0,0,500,500));
        path.moveTo(0, 0);
        path.lineTo(0, 500);
        path.lineTo(500, 500);
        path.lineTo(500, 0);
        path.closePath();
        bufferg.draw(path);
        bufferg.fill(path);
        bufferg.setColor(Color.WHITE);
        bufferg.drawOval(0, 0, 500, 500);
        bufferg.drawOval(50, 50, 400, 400);
        bufferg.drawOval(100, 100, 300, 300);
        bufferg.drawOval(150, 150, 200, 200);
        bufferg.drawOval(200, 200, 100, 100);

        bufferg.drawLine(250-177, 250-177, 250+177, 250+177);
        bufferg.drawLine(250-177, 250+177, 250+177, 250-177);
        bufferg.drawLine(250, 0, 250, 500);
        bufferg.drawLine(0, 250, 500, 250);
    }

    private void paintKey(){
        bufferg.setColor(Color.WHITE);
        bufferg.drawString("HP ATK RNG MOV", key.x + 80, key.y + 25);
        bufferg.drawRect(key.x, key.y, 199, 99);
        bufferg.setColor(units[Logic.wizard]);
        bufferg.drawString(Logic.names[Logic.wizard] + " (Q)", key.x + 20, key.y + 40);
        bufferg.drawString("   " + Logic.stats[Logic.wizard][Logic.HP] 
                + "       " + Logic.stats[Logic.wizard][Logic.ATK]
                + "       " + Logic.stats[Logic.wizard][Logic.RNG]
                + "       " + Logic.stats[Logic.wizard][Logic.MOV], key.x + 80, key.y + 40);
        bufferg.setColor(units[Logic.warrior]);
        bufferg.drawString(Logic.names[Logic.warrior] + " (W)", key.x + 20, key.y + 55);
        bufferg.drawString("   " + Logic.stats[Logic.warrior][Logic.HP]
                + "       " + Logic.stats[Logic.warrior][Logic.ATK]
                + "       " + Logic.stats[Logic.warrior][Logic.RNG]
                + "       " + Logic.stats[Logic.warrior][Logic.MOV], key.x + 80, key.y + 55);
        bufferg.setColor(units[Logic.priest]);
        bufferg.drawString(Logic.names[Logic.priest] + " (E)", key.x + 20, key.y + 70);
        bufferg.drawString("   " + Logic.stats[Logic.wizard][Logic.HP]
                + "       " + Logic.stats[Logic.priest][Logic.ATK]
                + "       " + Logic.stats[Logic.priest][Logic.RNG]
                + "       " + Logic.stats[Logic.priest][Logic.MOV], key.x + 80, key.y + 70);
    }

    private void paintStatus(){
        bufferg.setColor(Color.WHITE);
        bufferg.drawRect(status.x, status.y, status.width, status.height);

        for(int i = 0;i<3;i++){
            //System.out.println(logic.board[logic.player][coordinates[i][0]][coordinates[i][1]]);
            if(logic.board[logic.player][logic.playerhc[logic.player][i][0]][logic.playerhc[logic.player][i][1]] != i)
                continue;
            bufferg.setColor(logic.playerhp[logic.player][i] > 0?units[i]:Color.WHITE);
            int distance = logic.playerhc[logic.player][i][0]*50 + 40;
            double angle = Math.toRadians(Logic.angles[logic.playerhc[logic.player][i][1]]);
            bufferg.fillOval(250 + (int)(distance*Math.cos(angle))-15, 250 - (int)(distance*Math.sin(angle))-15, 30, 30);
        }

        paintPieceStatus();
    }

    private void paintControls(){
        bufferg.setColor(Color.WHITE);
        bufferg.drawRect(controls.x, controls.y, controls.width, controls.height);
        bufferg.setStroke(new BasicStroke(logic.getState() == Logic.attack?4:1));
        bufferg.drawRect(controls.x + 10, controls.y + 10, 130, 20);
        bufferg.drawString("Attack (A)", controls.x + 30, controls.y + 25);
        bufferg.setStroke(new BasicStroke(logic.getState() == Logic.move?4:1));
        bufferg.drawRect(controls.x + 10, controls.y + 40, 130, 20);
        bufferg.drawString("Move (S)", controls.x + 30, controls.y + 55);
        bufferg.setStroke(new BasicStroke(1));
        bufferg.drawRect(controls.x + 10, controls.y + 70, 130, 20);
        bufferg.drawString("Navigate (Arrows)", controls.x + 30, controls.y + 85);
        bufferg.drawRect(controls.x + 145, controls.y + 10, 20, 80);
        bufferg.drawString("D", controls.x + 151, controls.y + 22);
        bufferg.drawString("e", controls.x + 152, controls.y + 32);
        bufferg.drawString("c", controls.x + 153, controls.y + 42);
        bufferg.drawString("i", controls.x + 154, controls.y + 52);
        bufferg.drawString("d", controls.x + 152, controls.y + 62);
        bufferg.drawString("e", controls.x + 152, controls.y + 72);
        bufferg.drawString("(D)", controls.x + 147, controls.y + 84);
    }

    private void paintCursor(){
        bufferg.setColor(Color.GREEN);
        int distance = logic.getHco()*50 + 40;
        double angle = Math.toRadians(Logic.angles[logic.getCco()]);
        bufferg.fillOval(250 + (int)(distance*Math.cos(angle))-10, 250 - (int)(distance*Math.sin(angle))-10, 20, 20);
    }

    private void paintLocations(){
        boolean[][] locations;
        switch(logic.getState()){
            case Logic.attack:
            case Logic.move:
                if(logic.placed[Logic.p2]){
                    locations = logic.getRes();
                } else{
                    locations = logic.getPlayerValidLocations(logic.getHco(),logic.getCco());
                }
                bufferg.setColor(logic.getState() == Logic.attack?Color.WHITE:Color.ORANGE);
                for(int c = 0;c < Logic.csize;c++){
                    for(int h = 0;h < Logic.hsize;h++){
                        if(locations[h][c]){
                            int distance = h*50 + 40;
                            double angle = Math.toRadians(Logic.angles[c]);
                            bufferg.fillOval(250 + (int)(distance*Math.cos(angle))-3,
                                    250 - (int)(distance*Math.sin(angle))-3, 6, 6);
                        }
                    }
                }
                break;
        }
    }

    private void paintPieceStatus(){
        bufferg.setColor(Color.WHITE);
        bufferg.drawRect(status.x, status.y, status.width, status.height);
        bufferg.drawString(logic.playernames[logic.player], status.x + 10, status.y + 15);
        bufferg.drawString("Opposition", status.x + 60, status.y + 15);

        for(int i = 0;i<Logic.playernum;i++){
            for(int j = 0;j<Logic.unitnum;j++){
                bufferg.setColor(logic.playerhp[i^logic.player][j] > 0?units[j]:Color.WHITE);
                int size = (((logic.getUnit() == j) && (0 == i))?25:20);
                bufferg.drawOval(status.x + 20 + 60*i, status.y + 20 + 25*j, size, size);
                bufferg.fillOval(status.x + 20 + 60*i, status.y + 20 + 25*j, size, size);
                bufferg.setColor(Color.BLACK);
                if(logic.playerhp[i^logic.player][j] > 0)
                    bufferg.drawString("" + logic.playerhp[i^logic.player][j],
                            status.x + 20 + 7 + 60*i, status.y + 20 + 15 + 25*j);
            }
        }
    }

    private void paintHotSeat(){
        bufferg.setColor(Color.WHITE);
        switch(main.gametype){
            case Main.single:
                Logic.Turn t1 = logic.history.getTurn((main.computer+1)%2);
                if(t1 != null)
                bufferg.drawString(logic.playernames[(main.computer+1)%2]+ ": "+ t1.toString(), 20, 220);
                bufferg.drawString(logic.getAIStatus(), 20, 240);
                bufferg.drawString(" Press (R) to Begin Your Turn",160,255);
                break;
            case Main.two: bufferg.drawString("<hotseat> Switch to Player " + logic.playernames[logic.player]
                    + " -> Press (R)", 140, 240);
                break;
        }
    }

    private void paintPaused(){
        bufferg.setColor(Color.WHITE);
        bufferg.drawString("Exit out of Pause -> Press (R)", 110, 590);
        bufferg.drawString("New Game -> (P)", 15, 20);
        bufferg.drawString("Art Quality -> (O).", 15, 40);
        bufferg.drawString("Navigate History -> (Arrows)", 115, 545);
        bufferg.drawString("Add to Memory -> (A) (D)", 125, 560);
        bufferg.drawString("Clear Memory -> (SPACE)", 120, 575);

        paintHistory();
        paintPieceStatus();
    }

    private void paintHistory(){
        Logic.Turn t1 = logic.history.getTurn(logic.player);
        Logic.Turn t2 = logic.history.getTurn((logic.player + 1) % 2);

        int[][] atks = new int[Logic.playernum][2];
        for(int i = 0;i<2;i++){
            for(int j = 0;j<2;j++){
                atks[i][j] = -1;
            }
        }

        if(t1 != null){
            bufferg.setColor(Color.CYAN);
            if(t1.state == Logic.attack){
                atks[0][0] = t1.h;
                atks[0][1] = t1.c;
            }
            bufferg.drawString(logic.playernames[logic.player] + ": " + t1.toString(), 10, 510);
        }
        if(t2 != null){
            if(t2.state == Logic.attack){
                atks[1][0] = t2.h;
                atks[1][1] = t2.c;
            }
            bufferg.setColor(Color.GREEN);
            bufferg.drawString(logic.playernames[((logic.player + 1) % 2)] + ": " + t2.toString(), 10, 530);
        }


        bufferg.setColor(Color.GREEN);
        for(Logic.Turn t:logic.history.memory[logic.player][1]){
            if(t == null)
                continue;
            int distance = t.h*50 + 40;
            double angle = Math.toRadians(Logic.angles[t.c]);
            bufferg.fillOval(250 + (int)(distance*Math.cos(angle))-6,
                    250 - (int)(distance*Math.sin(angle))+3,6,6);
        }
        bufferg.setColor(Color.CYAN);
        for(Logic.Turn t:logic.history.memory[logic.player][0]){
            if(t == null)
                continue;
            int distance = t.h*50 + 40;
            double angle = Math.toRadians(Logic.angles[t.c]);
            bufferg.fillOval(250 + (int)(distance*Math.cos(angle))-6,
                    250 - (int)(distance*Math.sin(angle))-9,6,6);
        }

        for(int c = 0;c < Logic.csize;c++){
            for(int h = 0;h < Logic.hsize;h++){
                int distance = h*50 + 40;
                double angle = Math.toRadians(Logic.angles[c]);
                bufferg.setColor(Color.WHITE);
                bufferg.drawString("<" + h + "," + c + ">",250 + (int)(distance*Math.cos(angle))-15,
                        250 - (int)(distance*Math.sin(angle))+5);
                if(atks[0][0] == h && atks[0][1] == c){
                    bufferg.setColor(Color.CYAN);
                    bufferg.fillOval(250 + (int)(distance*Math.cos(angle)),
                        250 - (int)(distance*Math.sin(angle))-9,6,6);
                }
                if(atks[1][0] == h && atks[1][1] == c){
                    bufferg.setColor(Color.GREEN);
                    bufferg.fillOval(250 + (int)(distance*Math.cos(angle)),
                        250 - (int)(distance*Math.sin(angle))+3,6,6);
                }
            }
        }
    }

    private void paintWin(){
        bufferg.setColor(Color.WHITE);
        bufferg.setFont(new Font(Font.DIALOG,Font.BOLD,29));
        switch(logic.winstate){
            case Logic.p1wins:
                bufferg.drawString(logic.playernames[Logic.p1] + " <Old Devils> is victorious!", 10, 270);
                break;
            case Logic.p2wins:
                bufferg.drawString(logic.playernames[Logic.p2] + " <New Devils> is victorious!", 10, 270);
                break;
            case Logic.stalemate: 
                bufferg.setFont(new Font(Font.DIALOG,Font.BOLD,50));
                bufferg.drawString("Stalemate.", 130, 270);
                break;
        }
        bufferg.setFont(new Font(Font.DIALOG,Font.PLAIN,12));
        bufferg.drawString("New Game -> (P).", 200, 290);
    }

    public void clear(){
        bufferg = (Graphics2D) buffer.getGraphics();
        bufferg.setBackground(Color.DARK_GRAY);
    }
}
