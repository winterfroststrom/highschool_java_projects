/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Sparky
 */
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.util.Random;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.applet.Applet;
import java.awt.Point;
import java.awt.Image;

public class Controller  extends Applet implements Runnable{
    Mousie mousie = new Mousie(this);
    Kesie kesie = new Kesie(this);
    Random ran = new Random();
    Model model;
    int player;
    Image buffer;
    
    @Override
    public void init() {
        this.addMouseListener(mousie);
        this.addKeyListener(kesie);
        reset();
    }

    private void reset(){
        model = new Model();
        if(ran.nextBoolean()){
            player = Model.p1;
        } else {
            player = Model.p2;
        }
        buffer = createImage(500,500);
    }
    
    private void playAI(){
        if(model.hasWin() != Model.none) return;
        if(model.hasMove()){ 
            Point move = depthTwoAI(model.getPossibleMoves());
            if(move == null){
                move = randomAI(model.getPossibleMoves());
            }
            makeMove(move);
        }
    }
    
    private Point randomAI(LinkedList<Point> moves){
        if(moves.size() < 1) return null;
        int moveIndex = ran.nextInt(moves.size());
        Point move = moves.get(moveIndex);
        return move;
    }
    
    private Point depthOneAI(LinkedList<Point> moves){
        if(moves.size() < 1) return null;
        Map<Point,Model> states = model.getPossibleStates();
        for(Point move:states.keySet()){
            Model state = states.get(move);
            int winner = state.hasWin();
            if(winner != Model.none){
                if(winner == player){
                    moves.remove(move);
                } else {
                    return move;
                }
            }
        }
        return randomAI(moves);
    }
    
    private Point depthTwoAI(LinkedList<Point> moves){
        if(moves.size() < 1) return null;
        Map<Point,Model> states = model.getPossibleStates();
        for(Point move:states.keySet()){
            Model state = states.get(move);
            int winner = state.hasWin();
            if(winner != Model.none){
                if(winner == player){
                    moves.remove(move);
                } else {
                    return move;
                }
            }
        }
        return depthTwoAIHelper(moves,states);
    }
    
    private Point depthTwoAIHelper(LinkedList<Point> moves, Map<Point,Model> states){
        for(Model state:states.values()){
            Map<Point,Model> states2 = state.getPossibleStates();
            for(Point move:states2.keySet()){
                int winner = states2.get(move).hasWin();
                if(winner != Model.none){
                    return move;
                }
            }
        }
        return randomAI(moves);
    }
    
    private void makeMove(Point move){
        model.setPlayer(model.getNext(), move.x, move.y);
    }
    
    @Override
    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        while(true){
            if(player!=model.getNext()){
                playAI();
            }
            repaint();
            try{
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(View.paint(buffer, model),0,0,this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
    private static class Kesie extends KeyAdapter{
        public Controller controller;

        public Kesie(Controller controller) {
            this.controller = controller;
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                controller.reset();
            }
        }

    }
    private static class Mousie extends MouseAdapter{
        public Controller controller;

        public Mousie(Controller controller) {
            this.controller = controller;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            
            Point p = e.getPoint();
            /*System.out.print(p.x/Constants.rSize+"-"+ p.y/Constants.rSize);
            System.out.print(controller.model.isValidPlace(p.x/Constants.rSize, p.y/Constants.rSize));
            System.out.println(controller.model.isValidPlace(p.x/Constants.rSize, p.y/Constants.rSize) && 
                    !controller.model.hasPlayer(p.x/Constants.rSize, p.y/Constants.rSize));*/
            if(controller.model.getPossibleMoves().size() > 0){
                if(controller.model.isValidPlace(p.x/Constants.rSize, p.y/Constants.rSize) && 
                        !controller.model.hasPlayer(p.x/Constants.rSize, p.y/Constants.rSize) && 
                        controller.model.hasWin() == Model.none){
                    controller.model.setPlayer(controller.model.getNext(), p.x/Constants.rSize, p.y/Constants.rSize);
                }
            }
            //System.out.println(controller.model);
        }
    }
}
