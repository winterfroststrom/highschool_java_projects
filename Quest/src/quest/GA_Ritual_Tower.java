/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class GA_Ritual_Tower extends GrandArea{
    A_RT_1_1_1 a111;

    public GA_Ritual_Tower(Game game){
        super(game);
        preArea = 0x00000;
    }

    @Override
    public void load() {
        a111 = new A_RT_1_1_1(game);
    }

    @Override
    public Area getAreaById(int id) {
        switch(id){
            case 0x02111: return a111;
            default: return null;
        }
    }

    @Override
    public void die() {
        preArea = 0x00000;
        current = a111;
        current.loadArea();
    }

    @Override
    public void setCurrentById(int id) {
        switch(id){
            case 0x00000: current = a111;
                break;
            case 0x00111: current = a111;
                break;
//            case 0x0113: current = a22;
//                break;
            default: current = null;
                break;
        }
    }
}
