/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 *
 * @author Scalene
 */
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;

public class Monopoly extends JFrame{
    Main base;
    Board board;
    Player[] players;
    int numberPlayers;
    int turn;
    int dice;
    boolean doubled;
    Random gen = new Random();
    CC cc;
    C c;
    boolean c3 = false;

    JLabel pturn;
    JLabel[] pmoney;
    private JButton end;
    JButton roll;
    JLabel diceRoll;
    JLabel[] lboard = new JLabel[40];
    JLabel[] ppieces;
    JButton buy;
    JLabel event;
    JButton pay;
    JButton card;
    boolean[] bankrupt;
    
    JButton auction;
    

    JLabel win;
    JButton build;
    JButton[] bidortrade;
    JButton[] pass;
    JButton mortgage;
    JLabel tileinfo;
    JLabel tradeinfo1;
    JLabel tradeinfo2;
    JList properties;
    JList properties2;
    JComboBox ones;
    JComboBox tens;
    JComboBox hundreds;
    JComboBox thousands;
    JComboBox tenthousands;
    

    public Monopoly(Main base, int numberPlayers) {
        cc = new CC(this);
        c = new C(this);
        ppieces = new JLabel[numberPlayers];
        for(int i = 0;i < numberPlayers;i++){
            ppieces[i] = new JLabel();
            this.add(ppieces[i]);
        }
        this.base = base;
        board = new Board(this);
        createLBoard();
        this.numberPlayers = numberPlayers;
        players = new Player[numberPlayers];
        pmoney = new JLabel[numberPlayers];
        bankrupt = new boolean[numberPlayers];
        for(int i = 0;i < numberPlayers;i++){
            bankrupt[i] = false;

            players[i] = new Player(this);
            pmoney[i] = new JLabel("Player " + (i+1) +"'s Money: " + players[i].money);
            pmoney[i].setBounds(550,30 + i*30,150,20);
            pmoney[i].setOpaque(true);
            pmoney[i].setBackground(players[i].getColor());
            this.add(pmoney[i]);

            ppieces[i].setBounds(lboard[players[i].tileBPlace].getBounds().x +players[i].getXOffset(),
                                    lboard[players[i].tileBPlace].getBounds().y + players[i].getYOffset(),
                                    10,10);
            ppieces[i].setOpaque(true);
            ppieces[i].setBackground(players[i].getColor());            
        }

        pturn = new JLabel();
        pturn.setText("Player " + (turn+1) + "'s Turn");
        pturn.setBounds(500, 0, 100, 30);
        this.add(pturn);

        diceRoll = new JLabel();
        diceRoll.setBounds(650, 0, 100, 30);
        this.add(diceRoll);

        end = new JButton();
        end.setText("End Turn");
        end.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(end.isEnabled()){
                        incrementTurn();
                        end.setEnabled(false);
                        roll.setEnabled(true);
                    }
                }
            });
        end.setBounds(650, 400, 100, 30);
        end.setEnabled(false);
        this.add(end);

        buy = new JButton();
        buy.setText("Buy");
        buy.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(buy.isEnabled()){
                        buy();
                        buy.setEnabled(false);
                        auction.setEnabled(false);
                    }
                }
            });
        buy.setBounds(650, 350, 100, 30);
        buy.setEnabled(false);
        this.add(buy);

        auction = new JButton();
        auction.setText("Auction");
        auction.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(auction.isEnabled()){
                        auction();
                        auction.setEnabled(false);
                        buy.setEnabled(false);
                    }
                }
            });
        auction.setBounds(550, 350, 100, 30);
        auction.setEnabled(false);
        this.add(auction);

        roll = new JButton();
        roll.setText("Roll");
        roll.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(roll.isEnabled()){
                        if(c3){
                            int o = dice();
                            int t = dice();
                            diceRoll.setText(o + " " + t);
                            changeMoney(turn,10*(o+t));
                            c3 = false;
                            setEndOn();
                        } else {
                            roll();
                            if(players[turn].isJailed()){
                                if(doubled){
                                    pay.setEnabled(false);
                                    card.setEnabled(false);
                                    players[turn].unJailed();
                                    doubled = false;
                                    move();
                                } else setEndOn();
                            } else move();
                            if(!doubled)roll.setEnabled(false);
                        }
                    }
                }
            });
        roll.setBounds(550, 400, 100, 30);
        this.add(roll);

        pay = new JButton();
        pay.setText("Pay");
        pay.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(pay.isEnabled()){
                        changeMoney(turn,50);
                        players[turn].unJailed();
                        roll.setEnabled(true);
                        pay.setEnabled(false);
                        card.setEnabled(false);
                    }
                }
            });
        pay.setBounds(650, 300, 100, 30);
        pay.setEnabled(false);
        this.add(pay);

        card = new JButton();
        card.setText("Card");
        card.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(card.isEnabled()){
                        if(players[turn].gojfc1){
                            cc.deck.addLast(4);
                            players[turn].gojfc1 = false;
                        } else if(players[turn].gojfc2) {
                            c.deck.addLast(8);
                            players[turn].gojfc2 = false;
                        } else return;
                        players[turn].unJailed();
                        roll.setEnabled(true);
                        pay.setEnabled(false);
                        card.setEnabled(false);
                    }
                }
            });
        card.setBounds(550, 300, 100, 30);
        card.setEnabled(false);
        this.add(card);

        event = new JLabel();
        event.setBounds(100, 100, 250, 60);
        this.add(event);
    }

    public void createLBoard(){
        for(int i = 0;i<10;i++){
            lboard[i] = new JLabel("<html>" + board.getTileName(i) + "</html>");
            lboard[i].setBounds(460-i*43, 400, 40, 40);
            lboard[i].setOpaque(true);
            lboard[i].setBackground(Color.LIGHT_GRAY);
            this.add(lboard[i]);

            lboard[i+10] = new JLabel("<html>" + board.getTileName(i+10) + "</html>");
            lboard[i+10].setBounds(10, 400-38*i, 40, 35);
            lboard[i+10].setOpaque(true);
            lboard[i+10].setBackground(Color.LIGHT_GRAY);
            this.add(lboard[i+10]);

            lboard[i+20] = new JLabel("<html>" + board.getTileName(i+20) + "</html>");
            lboard[i+20].setBounds(10 + 43*i, 0, 43, 40);
            lboard[i+20].setOpaque(true);
            lboard[i+20].setBackground(Color.LIGHT_GRAY);
            this.add(lboard[i+20]);

            lboard[i+30] = new JLabel("<html>" + board.getTileName(i+30) + "</html>");
            lboard[i+30].setBounds(460, 40*i, 40, 40);
            lboard[i+30].setOpaque(true);
            lboard[i+30].setBackground(Color.LIGHT_GRAY);
            this.add(lboard[i+30]);
        }
    }

    public int dice(){
        return gen.nextInt(6)+1;
    }

    public void roll(){
        int o = dice();
        int t = dice();
        diceRoll.setText(o + " " + t);
        dice = o+t;
        doubled = o==t;
        event.setText("");
    }

    public void move(){
        board.moveTile(players[turn]);
        board.tile(players[turn]);
        ppieces[turn].setBounds(lboard[players[turn].tileBPlace].getBounds().x + players[turn].getXOffset(),
                                    lboard[players[turn].tileBPlace].getBounds().y + players[turn].getYOffset(),
                                    10,10);
    }

    

    public void run(){
        this.setLayout(null);
        setSize(800,500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                close();
            }
        });

        setTitle("Monopoly");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void close(){
        base.setVisible(true);
        Player.id = 0;
        this.dispose();
        this.setVisible(false);
    }

    public void incrementTurn(){
        if(players[turn].money<0)
            bankrupt[turn] = true;
        turn++;
        turn = turn % numberPlayers;
        int i = numberPlayers;
        while(bankrupt[turn] && i > 0){
            turn++;
            turn = turn % numberPlayers;
            i--;
        }
        if(i<=0)close();
        pturn.setText("Player " + (turn+1) + "'s Turn");
        doubled = false;
        event.setText("");
        players[turn].jc--;
        if(players[turn].isJailed()){
            if(players[turn].jc == 0)
                roll.setEnabled(false);
            if(players[turn].gojfc1 || players[turn].gojfc2)
                card.setEnabled(true);
            pay.setEnabled(true);
        }
    }

    public void changeMoney(int player,int amount){
        players[player].changeMoney(amount);
        //pmoney[player].setText(String.valueOf(players[player].money));
    }

    public void cc(){
        cc.event();
    }
    public void c(){
        c.event();
    }
    public void buyOption(Player player,Properties p){
        buy.setEnabled(true);
        auction.setEnabled(true);
    }

    public void buy(){
        board.tileOwners[board.b2p(players[turn].tileBPlace)] = players[turn].pid+1;
        //lboard[board.p2b(p.ordinal())].setBackground(player.getColor());
        lboard[players[turn].tileBPlace].setBackground(players[turn].getColor());
        players[turn].changeMoney(board.ptile(players[turn].tileBPlace).cost);
        setEndOn();
    }

    public void auction(){
        setEndOn();
    }

    public void setEndOn(){
        if(!doubled){
            end.setEnabled(true);
        } else {
            roll.setEnabled(true);
        }
    }
}
