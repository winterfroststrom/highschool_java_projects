/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Main;

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
import alter.View.View;
import alter.Model.Model;
import alter.Control.Control;

public class Main extends JFrame implements Runnable{
    private static final Main INSTANCE = new Main();
    View view;
    Model model;
    Control control;
    private boolean running;
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
        control = new Control();
        view = new View();
        model = new Model();
    }

    public static Main getInstance(){
        return INSTANCE;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            SwingUtilities.invokeAndWait(INSTANCE);
        } catch(Exception e){
            e.printStackTrace();
        }
        INSTANCE.run.execute();
    }

    @Override
    public void dispose() {
        super.dispose();
        running = false;
        run.cancel(true);
    }

    public void run(){
        running = true;
        add(getView());
        addKeyListener(getControl());
        setSize(500+8,400+34);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Alter");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void step(){
        control.process(view.getEvents());
        model.process(control.getEvents());
        view.process(model.getEvents());
    }

    public View getView(){
        return view;
    }

    public Control getControl(){
        return control;
    }
}
