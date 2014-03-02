/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 *
 * @author Scalene
 */
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;

public class Main extends JFrame{
    JLabel l1;
    JComboBox c1;
    JButton b1;
    /**
     * @param args the command line arguments
     */

    public Main() {
        this.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(10, 10, 150, 20);
        l1.setText("Number of Players: ");
        this.add(l1);
        
        String[] players = {"2","3","4"};
        c1 = new JComboBox(players);
        c1.setBounds(160,10,50,20);
        this.add(c1);

        b1 = new JButton();
        b1.setText("Play Monopoly");
        b1.setBounds(50,130,150,30);
        b1.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    startGame();
                }
            });
        this.add(b1);

        setSize(250,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Monopoly Set-Up");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        Main m = new Main();
    }

    public void startGame(){
        int num = Integer.parseInt(c1.getSelectedItem().toString());
        this.setVisible(false);
        Monopoly m = new Monopoly(this,num);
        m.run();
    }
}
