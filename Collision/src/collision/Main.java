/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package collision;

/**
 *
 * @author Scalene (Sparky)
 */
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.util.Random;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.math.BigDecimal;
import java.awt.BasicStroke;

public class Main extends JFrame{
    private Random gen;
    private boolean onOff;
    private JTextField jtxtInput;
    private JButton jOnOff;
    private JLabel info;
    private JLabel label;
    private JLabel label2;
    private Point[] points;
    private Paint area;
    private int ref;
    private int num;
    private int[] stat;
    private int statCounter;
    private int statStart;
    private int startEn;
    private int size;
    private int pause;
    private final BasicStroke bold = new BasicStroke(5);
    private final BasicStroke norm = new BasicStroke();

    public Main(){
        getContentPane().setBackground(Color.red);
        onOff = false;
        gen = new Random();
        num = 10;
        points = new Point[num];
        jtxtInput = new JTextField();
        jOnOff = new JButton();
        info = new JLabel();
        label = new JLabel();
        label2 = new JLabel();
        ref = 30;
        stat = new int[30];
        statCounter = 0;
        statStart = 0;
        size = 20;
        pause = 50;

        info.setBounds(180, 500, 100, 50);
        jtxtInput.setBounds(330,525,100, 20);
        jOnOff.setBounds(30,520,100, 30);
        label.setBounds(20, 20, 500, 20);
        label2.setBounds(20, 60, 500, 20);

        jtxtInput.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String in = jtxtInput.getText();
                    jtxtInput.setText("");
                    if(in.equalsIgnoreCase("r")){
                        points = fillArray(points);
                        reset();
                    } else if(in.matches("[e][-][0-9]*")){
                        ref = Integer.parseInt(in.substring(2));
                    } else if(in.matches("[s][-][0-9]*")){
                        points = new Point[Integer.parseInt(in.substring(2))];
                        points = fillArray(points);
                        reset();
                    } else if(in.equalsIgnoreCase("n")){
                        points = fillArrayLE(points);
                        reset();
                    } else if(in.matches("[r][-][0-9]*")){
                        size = Integer.parseInt(in.substring(2));
                        points = fillArray(points);
                        reset();
                    } else if(in.matches("[p][-][0-9]*")){
                        pause = Integer.parseInt(in.substring(2));
                    }
                }
            }
        });
        jOnOff.setText("Start");
        jOnOff.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(onOff){
                    onOff = false;
                    jOnOff.setText("Start");
                }
                else{
                    onOff = true;
                    jOnOff.setText("Stop");
                }
            }
        });
        label.setText("This iz defiantly the area where particles collide and exchange energy.");
        label2.setText("The flowing black line represents entropy.");


        points = fillArray(points);
        startEn = entropy();
        add(jOnOff);
        add(jtxtInput);
        add(info);
        add(label);
        add(label2);
        area = new Paint(this);
        add(area);
        
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Collisions");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        Main prog = new Main();
        while(true){
            //System.out.print(prog.onOff);
            if(prog.onOff){
                prog.checkMove();
                prog.move();
                prog.info.setText("Entropy:" + prog.entropy());
                prog.repaint();
                //System.out.println(prog.points[0].toString());
            }
            try{
                Thread.sleep(prog.pause);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private int entropy(){
        double sum = 0;
        BigDecimal sum2 = new BigDecimal(1);
        for(Point p:points){
            sum+=p.erg;
            sum2 = sum2.multiply(new BigDecimal(distance(0,0,p.xmove,p.ymove)));
        }
        //System.out.println(round(Math.log(sum2.floatValue())));
        return round(sum)+round(Math.log(sum2.floatValue())*100);
    }
    private void checkMove(){
        Rectangle[] rec = new Rectangle[points.length];
        for(int i =0;i<points.length;i++){
            rec[i] = new Rectangle(
                    points[i].x+5, points[i].y+5,
                    round(points[i].xmove)+size-5,
                    round(points[i].ymove)+size-5);
        }
        for(int i =0;i<points.length;i++){
            for(int j =0;j<points.length;j++){
                if(i!=j){
                    if(rec[i].intersects(rec[j]))
                        collide(points[i],points[j]);
                }
            }
        }
    }
    private void move(){
        for(Point p:points){
            makeMove(p);
            if(!inBounds(p)){
                if(p.x>800-size/2||p.x<0)p.xmove=-p.xmove;
                else if(p.y>500-size/2||p.y<0)p.ymove=-p.ymove;
                else if(p.x>430&&p.y>430){
                    p.x-=15;
                }else if(p.x>430&&p.y<35){
                    p.x-=15;
                }else if(p.x>35&&p.y>35){
                    p.y-=15;
                }else if(p.y>430&&p.x<35){
                    p.y-=15;
                }
                makeMove(p);
                if(p.x>800)p.x=800-2*size;
                else if(p.y<-20)p.x=2*size;
                if(p.y<-20)p.y=2*size;
                else if(p.y>500)p.y=500-2*size;
            }
        }
    }
    private void makeMove(Point p){
        p.x+= p.xmove;
        p.y+= p.ymove;
    }
    private void collide(Point p, Point q){
        //System.out.println("asds");
        double res;
        double piv = distance(0,0,p.xmove,p.ymove);
        double qiv = distance(0,0,q.xmove,q.ymove);
        double kIQ = kineticErg(distance(0,0,q.xmove,q.ymove));
        double kIP = kineticErg(distance(0,0,p.xmove,p.ymove));

        if(p.erg < 20||q.erg < 20){
            res = gen.nextDouble()-ref/100.;
            if(res<0)res=0;
        }else if(piv > 60||qiv>60||p.erg<0||q.erg<0){
            res = gen.nextDouble()-ref/50.;
        }else{
            res = (p.erg+q.erg)/(kIQ+kIP);
        }

        double pv = ((1+res)*qiv+piv*(1-res))/2;//v.1=((1+c)v.2+v.1(1-c))/2
        double qv = ((1+res)*piv+qiv*(1-res))/2;
        double temp = p.xmove;
        p.xmove = pv*Math.cos(Math.atan(p.ymove/p.xmove) + Math.atan(pv/piv));
        p.ymove = pv*Math.sin(Math.atan(p.ymove/temp) + Math.atan(pv/piv));
        temp = q.xmove;
        q.xmove = qv*Math.cos(Math.atan(q.ymove/q.xmove) + Math.atan(qv/qiv));
        q.ymove = qv*Math.sin(Math.atan(q.ymove/temp) + Math.atan(qv/qiv));
        double kFP = kineticErg(pv);
        double kFQ = kineticErg(qv);

        double ergChange = kIQ + kIP - kFQ - kFP;
        double ergPercent = kIQ/kIP;
                    
        if(piv>qiv){
            if(ergPercent > 1 - ergPercent){
                p.erg += round((ergPercent)*ergChange);
                q.erg += round((1-ergPercent)*ergChange);
            }else{
                p.erg += round((1-ergPercent)*ergChange);
                q.erg += round((ergPercent)*ergChange);
            }
        }else{
            if(ergPercent > 1 - ergPercent){
                p.erg += round((1-ergPercent)*ergChange);
                q.erg += round((ergPercent)*ergChange);
            }else{
                p.erg += round((ergPercent)*ergChange);
                q.erg += round((1-ergPercent)*ergChange);
            }
        }
        //if(p.erg<0||q.erg<0)System.out.println(p.erg+","+q.erg);
        makeMove(p);
        makeMove(q);
    }
    private class Paint extends JPanel{
        public Main base;
        public Paint(Main parent){
            base = parent;
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D painter = (Graphics2D) g;
            painter.setStroke(norm);
            for(Point point:base.points)
                point.createCircle(painter);
            base.statCounter = base.statCounter % 30;
            base.stat[statCounter] = base.entropy();
            base.statCounter++;
            painter.setColor(Color.black);
            
            for(int i =base.statStart;i<base.stat.length+base.statStart;i++){
                painter.drawLine(80/3*(i-base.statStart),(base.startEn-base.stat[(i)%29])*5+400,
                            80/3*(i+1-base.statStart), (base.startEn-base.stat[(i+1)%29])*5+400);
            }
            painter.setColor(Color.red);
            painter.drawLine(0, base.startEn/18+200, 800, base.startEn/18+200);
            painter.setColor(Color.black);
            painter.setStroke(bold);
            painter.drawLine(0, 500, 800, 500);
        }
    }
    private Point[] fillArray(Point[] p){
        for(int i =0;i<p.length;i++){
            p[i] = new Point(gen.nextInt(760),gen.nextInt(460),normGen(18),normGen(10),normGen(10));
            while(!inBounds(p[i])){
                //System.out.println("asfdasds");
                p[i] = new Point(gen.nextInt(760),gen.nextInt(460),normGen(18),normGen(10),normGen(10));
            }
        }
        return p;
    }
    private int normGen(int high){
        return (gen.nextInt(high)+gen.nextInt(high)+gen.nextInt(high))/3;
    }
    private Point[] fillArrayLE(Point[] p){
        int[] pos = new int[p.length/2];
        for(int i =0;i<p.length/2;i++){
            pos[i] = 460*2/p.length*i;
        }
        for(int i =0;i<p.length/2;i++){
            p[i] = new Point(20,pos[i],30,5,5);
        }
        if(p.length%2==0){
            for(int i =p.length/2;i<p.length;i++){
                p[i] = new Point(740,pos[i-p.length/2],5,10,10);
            }
        }else{
            for(int i =p.length/2;i<p.length-1;i++){
                p[i] = new Point(740,pos[i-p.length/2],5,10,10);
            }
            p[p.length] = new Point(740,pos[p.length/2],5,10,10);
        }
        return p;
    }
    private boolean inBounds(Point p){
        if(p.x < 800-size && p.x > 0)
            if(p.y < 500-size && p.y > 0)
                return true;

        return false;
    }
    private class Point{
        public double erg;
        public double xmove;
        public double ymove;
        public int x;
        public int y;
        public Point(int x, int y, double erg,double xmove, double ymove){
            this.x = x;
            this.y = y;
            this.erg = erg;
            this.xmove = xmove;
            this.ymove = ymove;
        }
        public void createCircle(Graphics2D g){
            double kinetic = distance(0,0,xmove,ymove)*10;
            double ener = erg*10;
            if(kinetic>255)
                kinetic = 255;
            if(ener>255)
                ener = 25;
            else if(ener<0)
                ener=0;
            g.setColor(new Color((int)kinetic,100,(int)ener));
            Shape temp = new Ellipse2D.Double(x, y, size,size);
            g.draw(temp);
            g.fill(temp);
        }
        @Override
        public String toString(){
            return x+","+y+","+erg+","+xmove+","+ymove;
        }
    }
    private void reset(){
        onOff = false;
        jOnOff.setText("Start");
        info.setText("Entropy:" + entropy());
        startEn = entropy();
        repaint();
    }
    private double distance(int x, int y,double x2, double y2){
        return Math.sqrt(Math.pow(x-x2, 2)+Math.pow(y-y2,2));
    }
    private int round(double a){
        return (int) Math.floor(a+.5);
    }
    private double kineticErg(double move){
        return Math.pow(move, 2)/2;
    }
}