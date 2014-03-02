/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.alter;

/**
 *
 * @author Scalene
 */

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
/*
 * Org =
 *  Mode = Start
 *          Battle
 *          Overworld
 *          Status
 *          Dialogue
 *          Cutscene
 *  Part = Painter
 *          Logic
 *          Controls
 */

public class Main extends JFrame implements Runnable{
    private static Main INSTANCE;
    private boolean running;
    private Model model;
    private View view;
    private Controller controller;
    
    SwingWorker run = new SwingWorker() {
       @Override
       public Void doInBackground() {
           while(running){
                step();
                try{
                    Thread.sleep(50);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
           return null;
        }
    };

    private Main(){
        model = new Model();
        view = new View(model);
        controller = new Controller(model);
    }

    public static Main getInstance(){
        if(Main.INSTANCE == null){
            Main.INSTANCE = new Main();
        }
        return INSTANCE;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            SwingUtilities.invokeAndWait(Main.getInstance());
        } catch(Exception e){
            e.printStackTrace();
        }
        Main.getInstance().run.execute();
    }

    @Override
    public void dispose() {
        super.dispose();
        running = false;
        run.cancel(true);
    }

    public void run(){
        running = true;
        add(view);
        addKeyListener(controller);
        setSize(Vars.DefaultScreenWidth+Vars.SystemXOffset,Vars.DefaultScreenHeight+Vars.SystemYOffset);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Alter");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void step(){
        this.repaint();
    }
}
